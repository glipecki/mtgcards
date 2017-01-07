package net.lipecki.mtgcards.gatherer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author gregorry
 */
public interface GathererImportReportsRepository extends JpaRepository<GathererImportReport, Long> {

	Optional<GathererImportReport> findOneByChecksum(final String checksum);

}
