package fr.appartment.indexator.brokers.seloger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("local")
@Slf4j
public class SelogerClientStub extends SelogerClient {

	@Override
	public String getPage(List<String> postalCodes, Integer minPrice, Integer maxPrice, int page) {

		Path path;

		try {
			switch (page) {
			case 1:
				path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page1.htm").toURI());
				return new String(Files.readAllBytes(path));
			case 2:
				path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page2.htm").toURI());
				return new String(Files.readAllBytes(path));
			case 3:
				path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page3.htm").toURI());
				return new String(Files.readAllBytes(path));
			}
		} catch (IOException | URISyntaxException e) {
			log.error("error while trying to read stubs files", e);
		}

		return "";
	}

}
