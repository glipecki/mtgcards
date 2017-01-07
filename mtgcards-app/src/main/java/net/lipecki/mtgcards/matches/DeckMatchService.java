package net.lipecki.mtgcards.matches;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.mtgcards.decks.Deck;
import net.lipecki.mtgcards.decks.DeckCardDefinition;
import net.lipecki.mtgcards.decks.DecksRepository;
import net.lipecki.mtgcards.gatherer.CardDefinitionsRepository;
import net.lipecki.mtgcards.library.Card;
import net.lipecki.mtgcards.library.CardsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author gregorry
 */
@Slf4j
@Service
@Transactional
public class DeckMatchService {

	private final DeckMatchRepository deckMatchRepository;

	private final DecksRepository decksRepository;

	private final CardDefinitionsRepository cardDefinitionsRepository;

	private final CardsRepository cardsRepository;

	public DeckMatchService(
			final DeckMatchRepository deckMatchRepository,
			final DecksRepository decksRepository,
			final CardDefinitionsRepository cardDefinitionsRepository,
			final CardsRepository cardsRepository) {
		this.deckMatchRepository = deckMatchRepository;
		this.decksRepository = decksRepository;
		this.cardDefinitionsRepository = cardDefinitionsRepository;
		this.cardsRepository = cardsRepository;
	}

	public void recalculateDeckMatches() {
		// TODO: remove for recalculation only matches older than changes
		deckMatchRepository.deleteAll();
		decksRepository
				.findAll()
				.stream()
				.forEach(this::recalculateDeckMatch);
	}

	private void recalculateDeckMatch(final Deck deck) {
		Integer matchedCards = 0;
		Integer requiredCards = 0;
		for (final DeckCardDefinition deckCard : deck.getDeckCardDefinitions()) {
			final List<Card> cards = cardsRepository.findByDefinitionId(deckCard.getCardDefinition().getId());
			matchedCards += Math.min(deckCard.getCount(), cards.size());
			requiredCards += deckCard.getCount();
		}

		double completionPercent = (double) Math.round(100 * matchedCards / requiredCards) / 100;
		deckMatchRepository.save(
				DeckMatch.builder()
						.deck(deck)
						.matchedCards(matchedCards)
						.requiredCards(requiredCards)
						.completion(completionPercent)
						.build()
		);
	}

}
