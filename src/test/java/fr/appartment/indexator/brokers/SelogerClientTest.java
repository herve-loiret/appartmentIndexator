package fr.appartment.indexator.brokers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class SelogerClientTest {

	private SelogerClient selogerClient = new SelogerClient();

	@Test
	public void should_generate_url_with_page_1() {
		
		String url = selogerClient.generateUrl(Arrays.asList("75010"), 1000, 300000, 1);

		assertThat(url).startsWith("http://www.seloger.com/list.htm?idtt=2&naturebien=1,2,4&idtypebien=1,2,9&tri=initial");
		assertThat(url).contains("&cp=75010");
		assertThat(url).contains("&pxmin=1000");
		assertThat(url).contains("&pxmax=300000");
	}
	
	@Test
	public void should_generate_url_with_page_2() {
		String url = selogerClient.generateUrl(Arrays.asList("44250"), 1000, 300000, 2);

		assertThat(url).startsWith("http://www.seloger.com/list.htm?idtt=2&naturebien=1,2,4&idtypebien=1,2,9&tri=initial");
		assertThat(url).contains("&cp=75010");
		assertThat(url).contains("&pxmin=1000");
		assertThat(url).contains("&pxmax=300000");
	}

}
