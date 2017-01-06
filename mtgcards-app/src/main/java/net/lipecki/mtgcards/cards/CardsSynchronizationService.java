package net.lipecki.mtgcards.cards;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.mtgcards.config.AppConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author gregorry
 */
@Slf4j
@Component
public class CardsSynchronizationService {

	private final AppConfig appConfig;

	public CardsSynchronizationService(final AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	@PostConstruct
	public void initDb() {
		final String filePath = appConfig.getGatherer().getFilePath();
		if (StringUtils.isNotEmpty(filePath)) {

		} else {

		}
		log.info("Initialize cards DB");
	}

}
