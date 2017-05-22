package fr.appartment.indexator.brokers.seloger;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import fr.appartment.indexator.brokers.seloger.SelogerUrlGenerator;

public class SelogerUrlGeneratorTest {

	private SelogerUrlGenerator selogerUrlGenerator = new SelogerUrlGenerator();

	@Test
	public void should_generate_url_with_page_1() {

		String url = selogerUrlGenerator.generateUrl(Arrays.asList("75010"), 1000, 300000, 1);

		assertThat(url)
				.startsWith("http://www.seloger.com/list.htm?idtt=2&naturebien=1,2,4&idtypebien=1,2,9&tri=d_dt_crea");
		assertThat(url).contains("&cp=75010");
		assertThat(url).contains("&pxmin=1000");
		assertThat(url).contains("&pxmax=300000");
	}

	@Test
	public void should_generate_url_with_page_2() {
		String url = selogerUrlGenerator.generateUrl(Arrays.asList("75010"), 1000, 300000, 2);

		assertThat(url)
				.startsWith("http://www.seloger.com/list.htm?idtt=2&naturebien=1,2,4&idtypebien=1,2,9&tri=d_dt_crea");
		assertThat(url).contains("&cp=75010");
		assertThat(url).contains("&pxmin=1000");
		assertThat(url).contains("&pxmax=300000");
		assertThat(url).contains("&LISTING-LISTpg=2");
	}

}
