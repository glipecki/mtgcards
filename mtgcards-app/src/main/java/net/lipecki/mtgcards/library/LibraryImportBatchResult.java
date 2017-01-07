package net.lipecki.mtgcards.library;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gregorry
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryImportBatchResult {

	private List<String> notFoundCards = new ArrayList<>();

	private Map<String, Integer> addedCards = new HashMap<>();

	public void addAddedCard(final String name) {
		addedCards.putIfAbsent(name, 0);
		addedCards.put(name, addedCards.get(name) + 1);
	}

	public void addNotFoundCard(final String name) {
		notFoundCards.add(name);
	}

}
