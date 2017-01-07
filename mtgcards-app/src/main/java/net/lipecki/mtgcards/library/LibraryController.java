package net.lipecki.mtgcards.library;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.mtgcards.gatherer.CardDefinition;
import net.lipecki.mtgcards.gatherer.CardDefinitionsRepository;
import net.lipecki.mtgcards.rest.Api;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author gregorry
 */
@Slf4j
@RestController
@RequestMapping(Api.V1 + "/library")
public class LibraryController {

	private static <T> Stream<T> convertToStream(final Iterator<T> iterator) {
		final Iterable<T> iterable = () -> iterator;
		return StreamSupport.stream(iterable.spliterator(), false);
	}

	private final CardsRepository cardsRepository;

	private final CardDefinitionsRepository cardDefinitionsRepository;

	public LibraryController(final CardsRepository cardsRepository, final CardDefinitionsRepository cardDefinitionsRepository) {
		this.cardsRepository = cardsRepository;
		this.cardDefinitionsRepository = cardDefinitionsRepository;
	}

	@PostMapping("/import/csv")
	public LibraryImportBatchResult importCsvRecords(@RequestBody final String csvBody) throws IOException {
		final CSVFormat csvFormat = CSVFormat.RFC4180.withHeader(ImportCsvRecord.HEADER);
		final CSVParser records = csvFormat.parse(new StringReader(csvBody));

		final Iterator<CSVRecord> recordsIterator = records.iterator();
		final Stream<CSVRecord> recordsStream = convertToStream(recordsIterator);
		final List<ImportRecord> importRecords = recordsStream
				.map(
						record -> ImportRecord.builder()
								.name(record.get(ImportCsvRecord.NAME))
								.count(Integer.valueOf(record.get(ImportCsvRecord.COUNT)))
								.foil("foil".equals(record.get(ImportCsvRecord.FOIL)))
								.build()
				)
				.collect(Collectors.toList());

		return importRecords(importRecords);
	}

	private LibraryImportBatchResult importRecords(final List<ImportRecord> importRecords) {
		final LibraryImportBatchResult report = new LibraryImportBatchResult();

		for (final ImportRecord record : importRecords) {
			final Optional<CardDefinition> cardDefinition = cardDefinitionsRepository.findOneByNameAllIgnoreCase(record.getName());
			if (cardDefinition.isPresent()) {
				for (int i = 0; i < record.getCount(); ++i) {
					cardsRepository.save(
							Card.builder()
									.definition(cardDefinition.get())
									.foil(record.isFoil())
									.build()
					);
					report.addAddedCard(cardDefinition.get().getName());
				}
			} else {
				report.addNotFoundCard(record.getName());
				log.warn("Card definition not found for name: {}", record.getName());
			}
		}

		return report;
	}

}
