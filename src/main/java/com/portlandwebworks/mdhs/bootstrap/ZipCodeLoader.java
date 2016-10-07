package com.portlandwebworks.mdhs.bootstrap;

import com.portlandwebworks.mdhs.utils.ZipCodeRangeFinder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.StrNotNullOrEmpty;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author nick
 */
@Component
public class ZipCodeLoader {

	private final static Logger log = LoggerFactory.getLogger(ZipCodeLoader.class);
	private static final String ZIP_RESOURCE = "classpath:db/zip-codes.csv";
	private final ResourceLoader resourceLoader;
	private final ZipCodeRangeFinder rangeFinder;

	@Autowired
	public ZipCodeLoader(ResourceLoader resourceLoader, ZipCodeRangeFinder rangeFinder) {
		this.resourceLoader = resourceLoader;
		this.rangeFinder = rangeFinder;
	}

	public void loadKnownZipCodes() {
		try (CsvListReader reader = new CsvListReader(new InputStreamReader(getResourceStream()), CsvPreference.EXCEL_PREFERENCE);) {
			reader.getHeader(false); //skip header
			List row;
			while ((row = reader.read(CELL_PROCESSORS)) != null) {
				rangeFinder.addKnownLocation((String) row.get(0), (String) row.get(1), (Double) row.get(3), (Double) row.get(4));
			}
			log.debug("Zip Code CSV Data parsed. All known zip codes.");
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private InputStream getResourceStream() {
		try {
			return resourceLoader.getResource(ZIP_RESOURCE).getInputStream();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private static final CellProcessor[] CELL_PROCESSORS = new CellProcessor[]{
		new StrNotNullOrEmpty(),
		new StrNotNullOrEmpty(),
		null,
		new ParseDouble(),
		new ParseDouble()
	};
}
