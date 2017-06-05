package fr.appartment.indexator.brokers.seloger;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import fr.appartment.indexator.domain.Appartment;
import lombok.SneakyThrows;

public class SLIndexatorTest {

	private SLIndexator sLIndexator = new SLIndexator(null, null);

	@Test
	public void should_parse_page_number() {
		int result = sLIndexator
				.findTotalPageFromSearchPaget("<p class=\"pagination_result_number\">Page 1 / 29 </p>");
		assertThat(result).isEqualTo(29);
	}

	@Test
	@SneakyThrows
	public void should_parse_details() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_details.json").toURI());
		String detailPage = new String(Files.readAllBytes(path));

		Appartment appartment = sLIndexator.parseAppartmentFromDetailPage(new Appartment(), detailPage);

		assertThat(appartment.getDescription()).startsWith(
				"A proximité de la Nation, dans une belle résidence, nous vous proposons, un viager occupé par un homme de 81 ans.");
	}

	@Test
	@SneakyThrows
	public void should_parse_appartment_list() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page1.htm").toURI());
		String searchPage = new String(Files.readAllBytes(path));

		List<Appartment> appartments = sLIndexator.parseAppartmentsFromSearchPage(searchPage);

		assertThat(appartments).size().isEqualTo(20);
		Appartment appartment = appartments.get(0);
		assertThat(appartment.getExternalId()).isEqualTo("118648837");
		assertThat(appartment.getType()).isEqualTo("Appartement");
		assertThat(appartment.getTypeTransactions()).isEqualTo(Arrays.asList("vente"));
		assertThat(appartment.getPostalCode()).isEqualTo("75011");
		assertThat(appartment.getEtage()).isEqualTo(5);
		assertThat(appartment.getTypeChauffage()).isEqualTo("électrique");
		assertThat(appartment.getPrice()).isEqualTo(155_000);
		assertThat(appartment.getSurface()).isEqualTo(17);
		assertThat(appartment.getNbPhotos()).isEqualTo(10);
	}

}
