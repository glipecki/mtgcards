package net.lipecki.mtgcards.crawler;

import lombok.extern.slf4j.Slf4j;
import net.lipecki.mtgcards.decks.DeckService;
import net.lipecki.mtgcards.execeptions.AppException;
import net.lipecki.mtgcards.model.CardClusterDto;
import net.lipecki.mtgcards.model.DeckDto;
import net.lipecki.mtgcards.rest.Api;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author gregorry
 */
@Slf4j
@RestController
@RequestMapping(Api.V1 + "/crawler")
public class CrawlerController {

	private final DeckService deckService;

	public CrawlerController(final DeckService deckService) {
		this.deckService = deckService;
	}

	@PostMapping("/magic.wizards.com")
	public List<DeckDto> parseMagicWizardsCom(@RequestParam final String url, @RequestParam final Optional<String> titlePrefix) throws IOException {
		final Document doc = Jsoup.connect(url).timeout(60000).maxBodySize(10*1024*1024).get();
		final Elements deckEelements = doc.select(".deck-group");
		final List<DeckDto> decks = new ArrayList<>();
		for (final Element deck : deckEelements) {
			final String deckName = deck.select(".deck-meta h4").text();
			final String title = titlePrefix.isPresent() ? titlePrefix.get() + " - " + deckName : deckName;

			final List<CardClusterDto> mainCards = new ArrayList<>();
			for (final Element cardRow : deck.select(".deck-list-text .sorted-by-overview-container .row")) {
				final Integer cardCount = Integer.parseInt(cardRow.select(".card-count").text());
				final String cardName = cardRow.select(".card-name").text();
				mainCards.add(CardClusterDto.builder().name(cardName).count(cardCount).build());
			}

			final List<CardClusterDto> sideboardCards = new ArrayList<>();
			for (final Element cardRow : deck.select(".deck-list-text .sorted-by-sideboard-container .row")) {
				final Integer cardCount = Integer.parseInt(cardRow.select(".card-count").text());
				final String cardName = cardRow.select(".card-name").text();
				sideboardCards.add(CardClusterDto.builder().name(cardName).count(cardCount).build());
			}

			decks.add(DeckDto.builder().title(title).cards(mainCards).sideboard(sideboardCards).build());
		}
		decks.forEach(deck -> {
			try {
				deckService.addDeck(deck);
			} catch (final AppException appException) {
				log.warn("Deck skipped due to exception [deck={}, error=[{}]]", deck.getTitle(), appException.asExceptionData());
			}
		});
		return decks;
	}

}
