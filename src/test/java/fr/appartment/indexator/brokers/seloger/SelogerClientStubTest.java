package fr.appartment.indexator.brokers.seloger;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import lombok.SneakyThrows;

public class SelogerClientStubTest {

	private SelogerClientStub client = new SelogerClientStub();

	@Test
	@SneakyThrows
	public void should_stub_return_page_1() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page1.htm").toURI());
		String content = new String(Files.readAllBytes(path));

		String page = client.getSearchPage(null, null, null, 1);

		assertThat(page).isNotBlank();
		assertThat(page).isEqualTo(content);
	}

	@Test
	@SneakyThrows
	public void should_stub_return_page_2() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page2.htm").toURI());
		String content = new String(Files.readAllBytes(path));

		String page = client.getSearchPage(null, null, null, 2);

		assertThat(page).isNotBlank();
		assertThat(page).isEqualTo(content);
	}

	@Test
	@SneakyThrows
	public void should_stub_return_page_3() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_11eme_page3.htm").toURI());
		String content = new String(Files.readAllBytes(path));

		String page = client.getSearchPage(null, null, null, 3);

		assertThat(page).isNotBlank();
		assertThat(page).isEqualTo(content);
	}
	
	@Test
	@SneakyThrows
	public void should_stub_return_details() {
		Path path = Paths.get(ClassLoader.getSystemResource("mocks/seloger/seloger_details.json").toURI());
		String content = new String(Files.readAllBytes(path));

		String details = client.getDetailsPage(null);

		assertThat(details).isNotBlank();
		assertThat(details).isEqualTo(content);
	}

}
