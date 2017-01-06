package net.lipecki.mtgcards.users;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gregorry
 */
public interface UsersRepository extends JpaRepository<User, Long> {
}
