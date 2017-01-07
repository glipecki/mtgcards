package net.lipecki.mtgcards.gatherer;

import lombok.Builder;
import lombok.Data;

/**
 * @author gregorry
 */
@Data
@Builder
public class GathererImportResult {

	final Integer importedCards;

	final Integer newCards;

}
