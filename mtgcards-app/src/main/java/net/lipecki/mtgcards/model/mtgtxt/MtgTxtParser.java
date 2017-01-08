package net.lipecki.mtgcards.model.mtgtxt;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gregorry
 */
public class MtgTxtParser {

	private static final String LINE_END_REGEX = "\\r\\n|\\n|\\r";

	public static List<String> asLines(final String body) {
		return Arrays
				.asList(body.split(LINE_END_REGEX))
				.stream()
				.collect(Collectors.toList());
	}

}
