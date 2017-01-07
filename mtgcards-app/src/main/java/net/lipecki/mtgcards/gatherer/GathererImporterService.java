package net.lipecki.mtgcards.gatherer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.lipecki.mtgcards.execeptions.AppException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Set;

/**
 * @author gregorry
 */
@Slf4j
@Transactional
@Service
public class GathererImporterService {

	private final ObjectMapper objectMapper;

	private final CardDefinitionsRepository cardsRepository;

	public GathererImporterService(final ObjectMapper objectMapper,
								   final CardDefinitionsRepository cardsRepository) {
		this.objectMapper = objectMapper;
		this.cardsRepository = cardsRepository;
	}

	public GathererImportResult importCards(final FileInputStream cardsInputStream) {
		final Set<String> validCards = parseCardStream(cardsInputStream).keySet();
		validCards.forEach(card -> cardsRepository.save(CardDefinition.builder().name(card).build()));
		return GathererImportResult
				.builder()
				.importedCards(validCards.size())
				.newCards(validCards.size())
				.build();
	}

	private Map parseCardStream(final FileInputStream cardsInputStream) {
		try {
			return objectMapper.readValue(cardsInputStream, Map.class);
		} catch (final Exception ex) {
			throw new AppException("Can't parse gatherer file stream", ex);
		}
	}

}
