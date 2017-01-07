package net.lipecki.mtgcards.matches;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gregorry
 */
public interface DeckMatchRepository extends JpaRepository<DeckMatch, Long> {
}
