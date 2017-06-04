package fr.appartment.indexator.brokers.seloger;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.appartment.indexator.domain.Appartment;
import lombok.SneakyThrows;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "local")
public class SLClientStubTest {

	@Autowired
	private SLClientStub client;

	private final List<String> keywords = Arrays.asList("");

	@Test
	@SneakyThrows
	public void should_stub_return_page_1() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page1.htm").toURI());
		String content = new String(Files.readAllBytes(path));

		String page = client.getSearchPage(keywords, null, null, 1);

		assertThat(page).isNotBlank();
		assertThat(page).isEqualTo(content);
	}

	@Test
	@SneakyThrows
	public void should_stub_return_page_2() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page2.htm").toURI());
		String content = new String(Files.readAllBytes(path));

		String page = client.getSearchPage(keywords, null, null, 2);

		assertThat(page).isNotBlank();
		assertThat(page).isEqualTo(content);
	}

	@Test
	@SneakyThrows
	public void should_stub_return_page_3() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page3.htm").toURI());
		String content = new String(Files.readAllBytes(path));

		String page = client.getSearchPage(keywords, null, null, 3);

		assertThat(page).isNotBlank();
		assertThat(page).isEqualTo(content);
	}

	@Test
	@SneakyThrows
	public void should_stub_return_details() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_details.json").toURI());
		String content = new String(Files.readAllBytes(path));

		String details = client.getDetailsPage(new PodamFactoryImpl().manufacturePojo(Appartment.class));

		assertThat(details).isNotBlank();
		assertThat(details).isEqualTo(content);
	}

	@Test
	@SneakyThrows
	public void should_stub_return_AutocompletePage() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_autocomplete_75011.json").toURI());
		String content = new String(Files.readAllBytes(path));

		String autompletePage = client.getAutocompletePage("random");

		assertThat(autompletePage).isNotBlank();
		assertThat(autompletePage).isEqualTo(content);
	}

}
