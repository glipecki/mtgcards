package net.lipecki.mtgcards.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author gregorry
 */
@Component
@Data
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	@Data
	public static class GathererConfig {

		private String filePath;

	}

	private GathererConfig gatherer = new GathererConfig();

}
