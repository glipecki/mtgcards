package net.lipecki.mtgcards.matches;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lipecki.mtgcards.decks.Deck;
import net.lipecki.mtgcards.users.User;

import javax.persistence.*;

/**
 * @author gregorry
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "deck_matches")
public class DeckMatch {

	public static final String SEQ_ID = "sq_deck_matches_id";

	@Id
	@SequenceGenerator(name = SEQ_ID, sequenceName = SEQ_ID, allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ID)
	private Long id;

	@ManyToOne
	private Deck deck;

	@ManyToOne
	private User user;

	@Column
	private Integer mainMatchedCards;

	@Column
	private Integer mainRequiredCards;

	@Column
	private Double mainCompletion;

	@Column
	private Integer sideboardMatchedCards;

	@Column
	private Integer sideboardRequiredCards;

	@Column
	private Double sideboardCompletion;

	@Column
	private Integer overallMatchedCards;

	@Column
	private Integer overallRequiredCards;

	@Column
	private Double overallCompletion;

}
