package net.lipecki.mtgcards.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gregorry
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardClusterDto {

	private String name;

	private Integer count;

}
