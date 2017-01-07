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
@Entity(name = "card_definition_imports")
public class GathererImportReport {

	public static final String SEQ_ID = "sq_card_definition_imports_id";

	@Id
	@SequenceGenerator(name = SEQ_ID, sequenceName = SEQ_ID, allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ID)
	private Long id;

	@Column
	private String checksum;

	@Column
	private String filePath;

	@Column
	private Boolean success;

}
