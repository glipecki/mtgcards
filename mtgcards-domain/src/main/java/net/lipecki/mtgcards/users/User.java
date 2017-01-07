package net.lipecki.mtgcards.users;

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
@Entity(name = "users")
public class User {

	public static final String SEQ_ID = "sq_users_id";

	@Id
	@SequenceGenerator(name = SEQ_ID, sequenceName = SEQ_ID, allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ID)
	private Long id;

	@Column
	private String username;

}
