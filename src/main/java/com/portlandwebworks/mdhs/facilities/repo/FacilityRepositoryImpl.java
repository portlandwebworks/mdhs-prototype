package com.portlandwebworks.mdhs.facilities.repo;

import com.portlandwebworks.mdhs.facilities.FacilityQuery;
import com.portlandwebworks.mdhs.facilities.FacilityResult;
import com.portlandwebworks.mdhs.facilities.model.Facility;
import com.portlandwebworks.mdhs.utils.ZipCodeRangeFinder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.springframework.util.StringUtils.*;

/**
 *
 * @author nick
 */
public class FacilityRepositoryImpl implements FacilityRepositoryCustom {

	private final static Logger log = LoggerFactory.getLogger(FacilityRepositoryImpl.class);

	@PersistenceContext
	private EntityManager em;

	private final ZipCodeRangeFinder rangeFinder;

	@Autowired
	public FacilityRepositoryImpl(ZipCodeRangeFinder rangeFinder) {
		this.rangeFinder = rangeFinder;
	}

	@Override
	public Page<FacilityResult> findFacilities(FacilityQuery query, Pageable pageInfo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Facility> facilityQuery = cb.createQuery(Facility.class);
		Root<Facility> facilityRoot = facilityQuery.from(Facility.class);

		final List<Predicate> preds = new ArrayList<>();

		facilityQuery = facilityQuery.where(preds.toArray(new Predicate[]{}));

		if (!isEmpty(query.getDescription())) {
			preds.add(cb.equal(facilityRoot.get("description"), query.getDescription()));
		}

		if (query.getAgeRanges() != null && !query.getAgeRanges().isEmpty()) {
			query.getAgeRanges().stream().forEach(ar -> {
				preds.add(cb.isMember(ar, facilityRoot.get("openToAgeRange")));
			});
		}

		if (query.getGenders() != null && !query.getGenders().isEmpty()) {
			preds.add(facilityRoot.get("openToGender").in(query.getGenders()));
		}

		if (query.getCapacityMin() != null) {
			preds.add(cb.ge(facilityRoot.get("capacity"), query.getCapacityMin()));
		}

		if (query.getCapacityMax() != null) {
			preds.add(cb.le(facilityRoot.get("capacity"), query.getCapacityMax()));
		}

		if (query.getAvailableOpenings() != null) {
			preds.add(cb.gt(facilityRoot.get("openings"), 0));
		}

		if (query.getNoConvictions() != null) {
			preds.add(cb.equal(facilityRoot.get("acceptsConvictions"), !query.getNoConvictions()));
		}

		if (query.getLicensed() != null && query.getLicensed()) {
			preds.add(cb.equal(facilityRoot.get("licenseType"), Facility.LicenseType.LICENSED));
		}

		Sort.Direction distanceDirection = null;
		if (pageInfo.getSort() != null) {
			final List<Order> orderBys = new ArrayList<>();
			if (pageInfo.getSort().getOrderFor("withinDistance") != null) {
				distanceDirection = pageInfo.getSort().getOrderFor("withinDistance").getDirection();
			}
			pageInfo.getSort().forEach(order -> {
				//distance handled seperately down below outside of query.
				if (!order.getProperty().equals("withinDistance")) {
					if (order.isAscending()) {
						orderBys.add(cb.asc(facilityRoot.get(order.getProperty())));
					} else {
						orderBys.add(cb.desc(facilityRoot.get(order.getProperty())));

					}
				}
			});
			facilityQuery = facilityQuery.orderBy(orderBys);
		}

		/**
		 * Ideally this would do the paging at the database level, but because
		 * we need to be able to calculate distances (and are not using
		 * something like PostGIS), taking care of it manually. Not suitable for
		 * long term usage.
		 */
		List<Facility> facilities = em.createQuery(facilityQuery.where(preds.toArray(new Predicate[]{})))
				.getResultList();

		final Optional<String> zipForCity;
		if (!isEmpty(query.getCity())) {
			zipForCity = rangeFinder.zipForKnownCity(query.getCity());
		} else {
			zipForCity = Optional.empty();
		}

		List<FacilityResult> allResults;
		if (zipForCity.isPresent()) {
			allResults = calcAndSortDistances(zipForCity, facilities, distanceDirection, query);
		} else {
			allResults = facilities.stream().map(f -> new FacilityResult(null, f)).collect(Collectors.toList());
		}

		List<FacilityResult> paged = allResults.subList(pageInfo.getOffset(), getEndingIndex(allResults, pageInfo)).stream().peek(this::addLatLng).collect(Collectors.toList());
		final Page<FacilityResult> page = new PageImpl<>(paged, pageInfo, allResults.size());
		log.debug("Found {} total facilities. Returning page {} of {}.", allResults.size(), pageInfo.getPageNumber(), page.getTotalPages());
		return page;
	}

	@Override
	public String uniqueZipCities() {
		List<Object[]> zipCities = em.createQuery("SELECT DISTINCT f.zipCode, f.city FROM Facility f ORDER BY f.city")
				.getResultList();
		return zipCities
				.stream()
				.map(o -> o[0] + "," + o[1])
				.collect(Collectors.joining("\n"));
	}

	private List<FacilityResult> calcAndSortDistances(final Optional<String> zipForCity, List<Facility> facilities, Sort.Direction distanceDirection, FacilityQuery query) {
		log.debug("Found zip code {}, adding distance to results.", zipForCity.get());
		Stream<FacilityResult> results = facilities.stream().map(f -> {
			FacilityResult result = new FacilityResult(rangeFinder.distance(zipForCity.get(), f.getZipCode()).getDistanceInMiles(), f);
			return result;
		});
		if (query.getWithinDistance() != null) {
			results = results.filter(fr -> fr.getDistance().compareTo(new BigDecimal(query.getWithinDistance())) <= 0);
		}
		if (distanceDirection != null) {
			switch (distanceDirection) {
				case ASC:
					results = results.sorted((fr1, fr2) -> fr1.getDistance().compareTo(fr2.getDistance()));
					break;
				default:
					results = results.sorted((fr1, fr2) -> fr2.getDistance().compareTo(fr1.getDistance()));
			}
		}
		return results.collect(Collectors.toList());
	}

	private int getEndingIndex(List<FacilityResult> allResults, Pageable pageInfo) {
		int finalIndex = 0;
		if (!allResults.isEmpty()) {
			int calcIndex = pageInfo.getOffset() + pageInfo.getPageSize();
			if (calcIndex <= allResults.size()) {
				finalIndex = calcIndex;
			} else {
				finalIndex = allResults.size();
			}
		}
		log.debug("Calculated final index of: {}", finalIndex);
		return finalIndex;
	}

	public void addLatLng(FacilityResult result) {
		String zipCode = result.getFacility().getZipCode();
		rangeFinder.zipInfoForCode(zipCode).ifPresent(info -> {
			result.setLat(info.getLat());
			result.setLng(info.getLng());
		});
	}
}
