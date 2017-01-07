package net.lipecki.mtgcards.decks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lipecki.mtgcards.gatherer.CardDefinition;

import javax.persistence.*;

/**
 * @author gregorry
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "decks_card_definitions")
public class DeckCardDefinition {

	public static final String SEQ_ID = "sq_decks_card_definitions_id";

	@Id
	@SequenceGenerator(name = SEQ_ID, sequenceName = SEQ_ID, allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ID)
	private Long id;

	@ManyToOne
	private Deck deck;

	@ManyToOne
	private CardDefinition cardDefinition;

	@Column
	private Integer count;

}
