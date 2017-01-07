package net.lipecki.mtgcards.gatherer;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.mtgcards.config.AppConfig;
import net.lipecki.mtgcards.execeptions.AppException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author gregorry
 */
@Slf4j
@Service
public class GathererSynchronizationService {

	private final AppConfig appConfig;

	private final GathererImportReportsRepository gathererImportReportsRepository;

	private final GathererImporterService gathererImporterService;

	public GathererSynchronizationService(
			final AppConfig appConfig,
			final GathererImportReportsRepository gathererImportReportsRepository,
			final GathererImporterService gathererImporterService) {
		this.appConfig = appConfig;
		this.gathererImportReportsRepository = gathererImportReportsRepository;
		this.gathererImporterService = gathererImporterService;
	}

	@PostConstruct
	@Transactional
	public void syncDatabase() throws IOException {
		final String filePath = appConfig.getGatherer().getFilePath();
		if (StringUtils.isBlank(filePath)) {
			log.info("Skipping data base sync due to empty gatherer file path in config");
			return;
		}

		final File gathererFile = new File(filePath);
		if (!gathererFile.exists()) {
			throw new AppException("Gatherer file not exist");
		}

		final String dbFileHash = getFileChecksum(gathererFile);
		if (gathererImportReportsRepository.findOneByChecksum(dbFileHash).isPresent()) {
			log.debug("Skipping already imported gatherer file");
		}

		try (final FileInputStream cardsInputStream = new FileInputStream(new File(filePath))) {
			final GathererImportResult importResult = gathererImporterService.importCards(cardsInputStream);
			gathererImportReportsRepository.save(
					GathererImportReport
							.builder()
							.filePath(filePath)
							.checksum(dbFileHash)
							.success(true)
							.build()
			);
			log.info(
					"Gatherer DB synchronized [file={}, importedCards={}, newCards={}]",
					filePath,
					importResult.getImportedCards(),
					importResult.getNewCards()
			);
		}
	}

	private String getFileChecksum(final File gathererFile) throws IOException {
		return Base64.getEncoder().encodeToString(DigestUtils.md5(new FileInputStream(gathererFile)));
	}

}
