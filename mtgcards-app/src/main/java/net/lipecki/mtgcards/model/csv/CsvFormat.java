package net.lipecki.mtgcards.model.csv;

import net.lipecki.mtgcards.model.CardDto;
import org.apache.commons.csv.CSVRecord;

/**
 * @author gregorry
 */
public class CsvFormat {

	public static final String[] HEADER = new String[]{
			CsvFormat.NAME, CsvFormat.COUNT, CsvFormat.FOIL
	};

	public static final String NAME = "name";

	public static final String COUNT = "count";

	public static final String FOIL = "foil";

	public static CardDto csvRecordToImportRecord(final CSVRecord csvRecord) {
		return CardDto.builder()
				.name(csvRecord.get(CsvFormat.NAME))
				.count(Integer.valueOf(csvRecord.get(CsvFormat.COUNT)))
				.foil("foil".equals(csvRecord.get(CsvFormat.FOIL)))
				.build();
	}

}
