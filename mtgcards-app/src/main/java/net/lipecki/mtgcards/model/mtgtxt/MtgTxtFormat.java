package net.lipecki.mtgcards.model.mtgtxt;

import net.lipecki.mtgcards.model.CardClusterDto;
import net.lipecki.mtgcards.model.DeckDto;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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
		final List<String> lines = MtgTxtParser.asLines(body);

		final String title = lines.get(0);
		final List<String> cardLines = lines.subList(1, lines.size());

		return DeckDto.builder()
				.title(title)
				.cards(parseCards(cardLines))
				.build();
	}

	public static List<CardClusterDto> parseCards(final List<String> cardLines) {
		return cardLines
				.stream()
				.map(MtgTxtFormat::txtLineToCardInDeckDto)
				.collect(Collectors.toList());
	}

	private static Integer parseCount(final String countPart) {
		return Integer.parseInt(StringUtils.substringBefore(countPart, COUNT_SUFFIX));
	}

}
