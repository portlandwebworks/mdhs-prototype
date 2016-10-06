package com.portlandwebworks.mdhs.bootstrap;

import com.portlandwebworks.mdhs.facilities.model.Facility;
import com.portlandwebworks.mdhs.facilities.model.Facility.LicenseType;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseEnum;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrNotNullOrEmpty;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

/**
 *
 * @author nick
 */
@Component
public class CsvParser {

	private static final Logger log = LoggerFactory.getLogger(CsvParser.class);

	static final String CSV_RESOURCE = "classpath:defaults/data.csv";
	private final ResourceLoader resourceLoader;

	@Autowired
	public CsvParser(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public List<Facility> parse() {
		log.debug("Parsing CSV data for default facility configuration.");
		List<Facility> facilities = new ArrayList<>();
		try (CsvBeanReader reader = new CsvBeanReader(new InputStreamReader(getResourceStream()), CsvPreference.EXCEL_PREFERENCE);) {
			reader.getHeader(false); //skip header
			Facility facility;
			while ((facility = reader.read(Facility.class, CSV_FEILD_MAPPINGS, CSV_CELL_PROCESSORS)) != null) {
				facilities.add(facility);
			}
			log.debug("CSV Data parsed. Found {} facilities.", facilities.size());
			return facilities;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private InputStream getResourceStream() {
		try {
			return resourceLoader.getResource(CSV_RESOURCE).getInputStream();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private static class ParseOpenToaAgeRange implements CellProcessor {

		public ParseOpenToaAgeRange() {
		}

		@Override
		public <T> T execute(Object o, CsvContext cc) {
			if (o != null) {
				String[] ageRanges = o.toString().split(",");
				return (T) Arrays.asList(ageRanges).stream().map(number -> {
					switch (number) {
						case "1":
							return Facility.AgeRange.INFANT;
						case "2":
							return Facility.AgeRange.TODDLER;
						case "3":
							return Facility.AgeRange.CHILD;
						case "4":
							return Facility.AgeRange.PRETEEN;
						case "5":
							return Facility.AgeRange.TEEN;
						default:
							throw new AssertionError("Invalid age range specified" + number);
					}
				}).collect(Collectors.toList());
			} else {
				return null;
			}
		}
	}

	private static class ParseAllowedGender implements CellProcessor {

		public ParseAllowedGender() {
		}

		@Override
		public <T> T execute(Object o, CsvContext cc) {
			if (o != null) {
				String genderNumber = o.toString();
				switch (genderNumber) {
					case "1":
						return (T) Facility.AllowedGender.MALE;
					case "2":
						return (T) Facility.AllowedGender.FEMALE;
					case "3":
						return (T) Facility.AllowedGender.BOTH;
					default:
						throw new AssertionError("Invalid age range specified" + genderNumber);
				}
			} else {
				return null;
			}
		}
	}

	private final StrNotNullOrEmpty STR_PROCESSOR = new StrNotNullOrEmpty();
	private final String[] CSV_FEILD_MAPPINGS = new String[]{
		"name",
		"licenseType",
		null, //provider type...need number translations
		"description",
		"qualityRating",
		null, //quality rating text value
		"capacity",
		"city",
		"zipCode",
		null, //county number
		"county",
		"phoneNumber",
		"openToGender",
		"openToAgeRange",
		"openings"
	};
	private final CellProcessor[] CSV_CELL_PROCESSORS = new CellProcessor[]{
		STR_PROCESSOR,
		new NotNull(new ParseEnum(LicenseType.class, true)),
		null,
		STR_PROCESSOR,
		new ParseInt(),//qualityRating
		null,
		new ParseInt(), //capacity
		STR_PROCESSOR, //city
		STR_PROCESSOR, //zip
		null,
		STR_PROCESSOR, //county
		STR_PROCESSOR, //phone
		new NotNull(new ParseAllowedGender()),
		new ParseOpenToaAgeRange(),
		new ParseInt()
	};

}
