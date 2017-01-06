package net.lipecki.mtgcards.importer;

import net.lipecki.mtgcards.rest.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gregorry
 */
@RestController(Api.V1 + "/import")
public class ImportRestController {

	private final ImportService importService;

	public ImportRestController(final ImportService importService) {
		this.importService = importService;
	}

}
