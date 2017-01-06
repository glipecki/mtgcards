package net.lipecki.mtgcards.gatherer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author gregorry
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "card_definitions")
public class CardDefinition {

	@Id
	@SequenceGenerator(name = "sq_card_definitions_id", sequenceName = "sq_card_definitions_id", allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_card_definitions_id")
	private Long id;

	private String name;

	private String link;

}
