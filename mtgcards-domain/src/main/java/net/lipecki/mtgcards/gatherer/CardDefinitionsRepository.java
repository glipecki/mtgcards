package net.lipecki.mtgcards.gatherer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gregorry
 */
public interface CardDefinitionsRepository extends JpaRepository<CardDefinition, Long> {
}
