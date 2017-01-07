package net.lipecki.mtgcards.library;

import lombok.Builder;
import lombok.Data;

/**
 * @author gregorry
 */
@Data
@Builder
public class ImportRecord {

	private String name;

	private int count;

	private boolean foil;

}
