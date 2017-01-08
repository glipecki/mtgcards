package net.lipecki.mtgcards.decks;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.mtgcards.model.DeckDto;
import net.lipecki.mtgcards.model.mtgtxt.MtgTxtFormat;
import net.lipecki.mtgcards.rest.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gregorry
 */
@Slf4j
@RestController
@RequestMapping(Api.V1 + "/decks")
public class DecksController {

	private final DeckService deckService;

	public DecksController(final DeckService deckService) {
		this.deckService = deckService;
	}

	@PostMapping("/import/txt")
	public DeckDto addDeck(@RequestBody final String body) {
		return deckService.addDeck(MtgTxtFormat.parseDeck(body));
	}

}
