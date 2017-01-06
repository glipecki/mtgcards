package net.lipecki.mtgcards;

import net.lipecki.mtgcards.rest.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

	@RequestMapping(Api.V1 + "/greeting")
	public String greeting() {
		return "Welcome!";
	}

}
