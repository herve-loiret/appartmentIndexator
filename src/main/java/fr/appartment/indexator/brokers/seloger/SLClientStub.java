package fr.appartment.indexator.brokers.seloger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import fr.appartment.indexator.domain.Appartment;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@Profile("local")
@Slf4j
public class SLClientStub extends SLClient {

	@Override
	@SneakyThrows
	public String getSearchPage(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page) {

		// just for making the tests execute this method
		String searchUrl = urlGenerator.generateSearchUrl(postalCodes, minPrice, maxPrice, page);
		log.info("search url : {}", searchUrl);

		Path path;

		switch (page) {
		case 1:
			path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page1.htm").toURI());
			return new String(Files.readAllBytes(path));
		case 2:
			path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page2.htm").toURI());
			return new String(Files.readAllBytes(path));
		default:
			path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page3.htm").toURI());
			return new String(Files.readAllBytes(path));
		}

	}

	@Override
	@SneakyThrows
	public String getDetailsPage(Appartment appartment) {
		urlGenerator.generateDetailsUrl(appartment);
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_details.json").toURI());
		return new String(Files.readAllBytes(path));
	}

	@Override
	@SneakyThrows
	public String getAutocompletePage(String search) throws IOException {
		urlGenerator.getAutocompleteUrl(search);

		String filePath = "mocks/seloger/seloger_autocomplete_75011.json";

		if (search.startsWith("44")) {
			filePath = "mocks/seloger/seloger_autocomplete_44250.json";
		}

		if (search.startsWith("ile")) {
			filePath = "mocks/seloger/seloger_autocomplete_ile_de_france.json";
		}

		Path path = Paths.get(ClassLoader.getSystemResource(filePath).toURI());
		return new String(Files.readAllBytes(path));
	}

}
