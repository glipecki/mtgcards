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
		int mainMatchedCards = 0;
		int mainRequiredCards = 0;
		for (final DeckCardDefinition deckCard : deck.getMainCards()) {
			final List<Card> cards = cardsRepository.findByDefinitionId(deckCard.getCardDefinition().getId());
			mainMatchedCards += Math.min(deckCard.getCount(), cards.size());
			mainRequiredCards += deckCard.getCount();
		}
		double mainCompletionPercent = (double) Math.round(100 * mainMatchedCards / mainRequiredCards) / 100;

		int sideboardMatchedCards = 0;
		int sideboardRequiredCards = 0;
		for (final DeckCardDefinition deckCard : deck.getSideboardCards()) {
			final List<Card> cards = cardsRepository.findByDefinitionId(deckCard.getCardDefinition().getId());
			sideboardMatchedCards += Math.min(deckCard.getCount(), cards.size());
			sideboardRequiredCards += deckCard.getCount();
		}
		double sideboardCompletionPercent = sideboardRequiredCards > 0 ? (double) Math.round(100 * sideboardMatchedCards / sideboardRequiredCards) / 100 : 1;

		int overallMatchedCards = mainMatchedCards + sideboardMatchedCards;
		int overallRequiredCards = mainRequiredCards + sideboardRequiredCards;
		double overallCompletionPercent = (double) Math.round(100 * overallMatchedCards / overallRequiredCards) / 100;

		deckMatchRepository.save(
				DeckMatch.builder()
						.deck(deck)
						.mainMatchedCards(mainMatchedCards)
						.mainRequiredCards(mainRequiredCards)
						.mainCompletion(mainCompletionPercent)
						.sideboardMatchedCards(sideboardMatchedCards)
						.sideboardRequiredCards(sideboardRequiredCards)
						.sideboardCompletion(sideboardCompletionPercent)
						.overallMatchedCards(overallMatchedCards)
						.overallRequiredCards(overallRequiredCards)
						.overallCompletion(overallCompletionPercent)
						.build()
		);
	}

}
