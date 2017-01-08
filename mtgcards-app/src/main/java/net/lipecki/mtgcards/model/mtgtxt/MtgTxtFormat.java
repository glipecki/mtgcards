package net.lipecki.mtgcards.model.mtgtxt;

import net.lipecki.mtgcards.model.CardClusterDto;
import net.lipecki.mtgcards.model.DeckDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gregorry
 */
public class MtgTxtFormat {

	public static final String COUNT_NAME_SEPARATOR = " ";

	public static final String COUNT_SUFFIX = "x";

	public static CardClusterDto txtLineToCardInDeckDto(final String line) {
		return CardClusterDto.builder()
				.name(StringUtils.substringAfter(line, COUNT_NAME_SEPARATOR))
				.count(parseCount(StringUtils.substringBefore(line, COUNT_NAME_SEPARATOR)))
				.build();
	}

	public static DeckDto parseDeck(final String body) {
		final List<CardClusterDto> mainCards = new ArrayList<>();
		final List<CardClusterDto> sideboardCards = new ArrayList<>();

		String title = null;
		List<CardClusterDto> current = mainCards;
		for (final String line : MtgTxtParser.asLines(body)) {
			if (isCardLine(line)) {
				current.add(txtLineToCardInDeckDto(line));
			} else if (isSideboardSwitch(line)) {
				current = sideboardCards;
			} else if (!mainCards.isEmpty() && StringUtils.isBlank(line)) {
				current = sideboardCards;
			} else if (mainCards.isEmpty() && StringUtils.isNotBlank(line)) {
				title = line;
			}
		}

		return DeckDto.builder()
				.title(title)
				.cards(mainCards)
				.sideboard(sideboardCards)
				.build();
	}

	private static boolean isCardLine(final String line) {
		return line.matches("\\d.*");
	}

	private static boolean isSideboardSwitch(final String line) {
		return line.toLowerCase().startsWith("sideboard");
	}

	private static Integer parseCount(final String countPart) {
		return Integer.parseInt(StringUtils.substringBefore(countPart, COUNT_SUFFIX));
	}

}
