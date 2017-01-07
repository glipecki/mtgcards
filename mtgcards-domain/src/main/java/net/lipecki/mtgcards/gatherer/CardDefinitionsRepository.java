package net.lipecki.mtgcards.gatherer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author gregorry
 */
public interface CardDefinitionsRepository extends JpaRepository<CardDefinition, Long> {

	Optional<CardDefinition> findOneByNameAllIgnoreCase(final String name);

}
