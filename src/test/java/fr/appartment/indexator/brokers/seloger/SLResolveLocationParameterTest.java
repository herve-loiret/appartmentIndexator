package fr.appartment.indexator.brokers.seloger;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "local")
public class SLResolveLocationParameterTest {

	@Autowired
	private SLResolveLocationParameter searchToParamService;

	@Test
	public void should_find_region_for_ile_de_france() {
		List<String> keywords = Arrays.asList("ile");
		List<SLParameter> sLParameters = searchToParamService.searchKeywordsToParameters(keywords);
		
		assertThat(sLParameters).hasSize(1);
		assertThat(sLParameters.get(0).getName()).isEqualTo("div");
		assertThat(sLParameters.get(0).getValue()).isEqualTo("2238");
	}
	

	@Test
	public void should_find_groupe_for_44250() {
		List<String> keywords = Arrays.asList("44250");
		List<SLParameter> sLParameters = searchToParamService.searchKeywordsToParameters(keywords);
		
		assertThat(sLParameters).hasSize(1);
		assertThat(sLParameters.get(0).getName()).isEqualTo("cp");
		assertThat(sLParameters.get(0).getValue()).isEqualTo("44250");
	}
	


	@Test
	public void should_find_ville_for_75011() {
		List<String> keywords = Arrays.asList("75011");
		List<SLParameter> sLParameters = searchToParamService.searchKeywordsToParameters(keywords);
		
		assertThat(sLParameters).hasSize(1);
		assertThat(sLParameters.get(0).getName()).isEqualTo("ci");
		assertThat(sLParameters.get(0).getValue()).isEqualTo("750111");
	}
	


	@Test
	public void should_find_multiple_parameters() {
		List<String> keywords = Arrays.asList("75011", "44250");
		List<SLParameter> sLParameters = searchToParamService.searchKeywordsToParameters(keywords);
		
		assertThat(sLParameters).hasSize(2);
		assertThat(sLParameters.get(0).getName()).isEqualTo("ci");
		assertThat(sLParameters.get(0).getValue()).isEqualTo("750111");
		assertThat(sLParameters.get(1).getName()).isEqualTo("cp");
		assertThat(sLParameters.get(1).getValue()).isEqualTo("44250");
	}

}
