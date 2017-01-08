package net.lipecki.mtgcards.decks;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.mtgcards.execeptions.AppException;
import net.lipecki.mtgcards.gatherer.CardDefinitionsRepository;
import net.lipecki.mtgcards.model.CardClusterDto;
import net.lipecki.mtgcards.model.DeckDto;
import net.lipecki.mtgcards.model.mtgtxt.MtgTxtFormat;
import net.lipecki.mtgcards.rest.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gregorry
 */
@Slf4j
@RestController
@RequestMapping(Api.V1 + "/decks")
@Transactional
public class DecksController {

	private final CardDefinitionsRepository cardDefinitionsRepository;

	private final DecksRepository decksRepository;

	public DecksController(final CardDefinitionsRepository cardDefinitionsRepository, final DecksRepository decksRepository) {
		this.cardDefinitionsRepository = cardDefinitionsRepository;
		this.decksRepository = decksRepository;
	}

	@PostMapping("/import/txt")
	public DeckDto addDeck(@RequestBody final String body) {
		return addDeck(MtgTxtFormat.parseDeck(body));
	}

	private DeckDto addDeck(final DeckDto deckDto) {
		final Deck deck = Deck.builder()
				.title(deckDto.getTitle())
				.mainCards(new ArrayList<>())
				.sideboardCards(new ArrayList<>())
				.build();

		final List<String> missingCards = new ArrayList<>();

		final DeckCardDefinitionParser mainCards = matchCards(deckDto.getCards());
		deck.getMainCards().addAll(mainCards.getMatchedCards());
		missingCards.addAll(mainCards.getMissingCards());

		final DeckCardDefinitionParser sideboardCards = matchCards(deckDto.getSideboard());
		deck.getSideboardCards().addAll(sideboardCards.getMatchedCards());
		missingCards.addAll(sideboardCards.getMissingCards());

		if (missingCards.isEmpty()) {
			decksRepository.save(deck);
			return deckDto;
		} else {
			throw AppException
					.of("Can't add deck due to missing card definitions")
					.withData("missingCards", missingCards);
		}
	}

	private DeckCardDefinitionParser matchCards(final List<CardClusterDto> cards) {
		return new DeckCardDefinitionParser(cardDefinitionsRepository::findOneByNameAllIgnoreCase).parse(cards);
	}

}
