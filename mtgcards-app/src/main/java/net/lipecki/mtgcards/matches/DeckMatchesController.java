package net.lipecki.mtgcards.matches;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.mtgcards.rest.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * @author gregorry
 */
@Slf4j
@RestController
@RequestMapping(Api.V1 + "/deck-matches")
@Transactional
public class DeckMatchesController {

	private final DeckMatchService deckMatchService;

	public DeckMatchesController(final DeckMatchService deckMatchService) {
		this.deckMatchService = deckMatchService;
	}

	@PostMapping
	public void recalculateDeckMatches() {
		deckMatchService.recalculateDeckMatches();
	}

}
