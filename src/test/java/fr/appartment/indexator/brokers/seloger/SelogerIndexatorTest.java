package fr.appartment.indexator.brokers.seloger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SelogerIndexatorTest {

	private SelogerIndexator selogerIndexator = new SelogerIndexator();

	@Test
	public void should_parse_page_number() {
		int result = selogerIndexator.findPageNumber("<p class=\"pagination_result_number\">Page 1 / 29 </p>");
		assertThat(result).isEqualTo(29);
	}
}
