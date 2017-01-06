package net.lipecki.mtgcards.library;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lipecki.mtgcards.gatherer.CardDefinition;
import net.lipecki.mtgcards.users.User;

import javax.persistence.*;

/**
 * @author gregorry
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cards")
public class Card {

	@Id
	@SequenceGenerator(name = "sq_cards_id", sequenceName = "sq_cards_id", allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cards_id")
	private Long id;

	@ManyToOne
	private CardDefinition definition;

	@ManyToOne
	private User owner;

}
