package net.lipecki.mtgcards.library;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gregorry
 */
public interface LibraryRepository extends JpaRepository<Card, Long> {
}
