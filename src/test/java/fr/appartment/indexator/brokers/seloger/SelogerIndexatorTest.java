package fr.appartment.indexator.brokers.seloger;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import fr.appartment.indexator.domain.Appartment;
import lombok.SneakyThrows;

public class SelogerIndexatorTest {

	private SelogerIndexator selogerIndexator = new SelogerIndexator(null, null);

	@Test
	public void should_parse_page_number() {
		int result = selogerIndexator
				.findTotalPageFromSearchPaget("<p class=\"pagination_result_number\">Page 1 / 29 </p>");
		assertThat(result).isEqualTo(29);
	}
	
	
	@Test
	@SneakyThrows
	public void should_parse_details() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_details.json").toURI());
		String detailPage = new String(Files.readAllBytes(path));
		
		Appartment appartment = selogerIndexator.parseAppartmentFromDetailPage(new Appartment(), detailPage);
		
		assertThat(appartment.getDescription()).startsWith("A proximité de la Nation, dans une belle résidence, nous vous proposons, un viager occupé par un homme de 81 ans.");
	}
	
}
