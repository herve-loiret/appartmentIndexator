package fr.appartment.indexator.brokers.seloger;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "local")
public class SLUrlGeneratorTest {

	@Autowired
	private SLUrlGenerator sLUrlGenerator;

	// @Before
	// public void setup() {
	// SLResolveLocationParameter resolveLocationParameter =
	// Mockito.mock(SLResolveLocationParameter.class);
	// sLUrlGenerator.setSearchToParamService(resolveLocationParameter);
	// }

	@Test
	public void should_generate_url_with_page_1() {

		String url = sLUrlGenerator.generateSearchUrl(Arrays.asList("75011"), 1000, 300000, 1);

		assertThat(url)
				.startsWith("http://www.seloger.com/list.htm?idtt=2&naturebien=1&idtypebien=1,2,9,11&tri=d_dt_crea");
		assertThat(url).contains("&ci=750111");
		assertThat(url).contains("&pxmin=1000");
		assertThat(url).contains("&pxmax=300000");
	}

	@Test
	public void should_generate_url_with_page_2() {
		String url = sLUrlGenerator.generateSearchUrl(Arrays.asList("75010"), 1000, 300000, 2);

		assertThat(url)
				.startsWith("http://www.seloger.com/list.htm?idtt=2&naturebien=1&idtypebien=1,2,9,11&tri=d_dt_crea");
		assertThat(url).contains("&ci=750111");
		assertThat(url).contains("&pxmin=1000");
		assertThat(url).contains("&pxmax=300000");
		assertThat(url).contains("&LISTING-LISTpg=2");
	}

}
