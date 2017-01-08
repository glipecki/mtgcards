package net.lipecki.mtgcards.decks;

import net.lipecki.mtgcards.gatherer.CardDefinition;
import net.lipecki.mtgcards.model.CardClusterDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author gregorry
 */
class DeckCardDefinitionParser {

	private final List<DeckCardDefinition> matchedCards = new ArrayList<>();

	private final List<String> missingCards = new ArrayList<>();

	private final Function<String, Optional<CardDefinition>> cardDefinitionSupplier;

	public DeckCardDefinitionParser(final Function<String, Optional<CardDefinition>> cardDefinitionSupplier) {
		this.cardDefinitionSupplier = cardDefinitionSupplier;
	}

	public DeckCardDefinitionParser parse(final List<CardClusterDto> cards) {
		for (final CardClusterDto card : cards) {
			final Optional<CardDefinition> cardDefinition = cardDefinitionSupplier.apply(card.getName());
			if (cardDefinition.isPresent()) {
				matchedCards.add(
						DeckCardDefinition
								.builder()
								.cardDefinition(cardDefinition.get())
								.count(card.getCount())
								.build()
				);
			} else {
				missingCards.add(card.getName());
			}
		}
		return this;
	}

	public List<DeckCardDefinition> getMatchedCards() {
		return matchedCards;
	}

	public List<String> getMissingCards() {
		return missingCards;
	}

}
