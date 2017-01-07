package net.lipecki.mtgcards.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gregorry
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeckDto {

	private List<CardClusterDto> cards;

	private String title;

}
