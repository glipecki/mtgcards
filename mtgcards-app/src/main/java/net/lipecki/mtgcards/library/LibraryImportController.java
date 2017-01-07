package net.lipecki.mtgcards.library;

import net.lipecki.mtgcards.rest.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gregorry
 */
@RestController
@RequestMapping(Api.V1 + "/library")
public class LibraryImportController {

	@PostMapping("/import")
	public void importLibrary(@RequestParam("file") final MultipartFile file) {
	}

}
