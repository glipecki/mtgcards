package net.lipecki.mtgcards.gatherer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gregorry
 */
public interface GathererRepository extends JpaRepository<CardDefinition, Long> {
}
