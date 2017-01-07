package net.lipecki.mtgcards.decks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gregorry
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "decks")
public class Deck {

	public static final String SEQ_ID = "sq_decks_id";

	@Id
	@SequenceGenerator(name = SEQ_ID, sequenceName = SEQ_ID, allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ID)
	private Long id;

	@Column
	private String title;

	@OneToMany(mappedBy = "deck", cascade = CascadeType.PERSIST)
	private List<DeckCardDefinition> deckCardDefinitions = new ArrayList<>();

}
