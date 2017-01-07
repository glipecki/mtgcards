package net.lipecki.mtgcards.library;

import org.apache.commons.csv.CSVRecord;

/**
 * @author gregorry
 */
public class ImportCsvRecord {

	public static final String[] HEADER = new String[]{
			ImportCsvRecord.NAME, ImportCsvRecord.COUNT, ImportCsvRecord.FOIL
	};
	public static final String NAME = "name";
	public static final String COUNT = "count";
	public static final String FOIL = "foil";

	public static ImportRecord csvToImportRecord(final CSVRecord csvRecord) {
		return ImportRecord.builder()
				.name(csvRecord.get(ImportCsvRecord.NAME))
				.count(Integer.valueOf(csvRecord.get(ImportCsvRecord.COUNT)))
				.foil("foil".equals(csvRecord.get(ImportCsvRecord.FOIL)))
				.build();
	}

}
