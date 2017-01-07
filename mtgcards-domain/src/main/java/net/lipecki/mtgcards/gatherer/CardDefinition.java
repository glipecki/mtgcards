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
@Table(indexes = { @Index(name = "idx_name", columnList = "name") })
public class CardDefinition {

	public static final String SEQ_ID = "sq_card_definitions_id";

	@Id
	@SequenceGenerator(name = SEQ_ID, sequenceName = SEQ_ID, allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ID)
	private Long id;

	private String name;

}
