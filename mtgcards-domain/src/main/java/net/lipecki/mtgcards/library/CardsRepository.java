package net.lipecki.mtgcards.library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author gregorry
 */
public interface CardsRepository extends JpaRepository<Card, Long> {

	List<Card> findByDefinitionId(final Long cardDefinitionId);

}
