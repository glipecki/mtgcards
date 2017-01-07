package net.lipecki.mtgcards.decks;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gregorry
 */
public interface DecksRepository extends JpaRepository<Deck, Long> {
}
