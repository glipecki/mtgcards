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

	public static final String SEQ_ID = "sq_cards_id";

	@Id
	@SequenceGenerator(name = SEQ_ID, sequenceName = SEQ_ID, allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ID)
	private Long id;

	@ManyToOne
	private CardDefinition definition;

	@ManyToOne
	private User owner;

	@Column
	private Boolean foil;

}
