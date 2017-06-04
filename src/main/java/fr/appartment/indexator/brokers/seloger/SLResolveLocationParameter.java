package fr.appartment.indexator.brokers.seloger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.SneakyThrows;

@Component
public class SLResolveLocationParameter {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private SLClient sLClient;

	@SneakyThrows
	public List<SLParameter> searchKeywordsToParameters(List<String> keywords) {

		List<SLParameter> sLParameters = new ArrayList<>();

		for (String searchKeyword : keywords) {
			String responsePage = sLClient.getAutocompletePage(searchKeyword);
			List<AutoCompleteResult> autoCompleteResults = Arrays.asList(mapper.readValue(responsePage,
					AutoCompleteResult[].class));

			AutoCompleteResult result = findLargestChoice(autoCompleteResults);
			sLParameters.add(this.paramsToParameter(result.getParams()));
		}

		return sLParameters;
	}

	private AutoCompleteResult findLargestChoice(List<AutoCompleteResult> autoCompleteResults) {
		AutoCompleteResult autoCompleteResult = null;

		if (autoCompleteResults.size() == 1) {
			return autoCompleteResults.get(0);
		}

		Optional<AutoCompleteResult> resutlOpt = findParamTypeIfExist(autoCompleteResults, "region");
		if (resutlOpt.isPresent()) {
			return resutlOpt.get();
		}

		resutlOpt = findParamTypeIfExist(autoCompleteResults, "group");
		if (resutlOpt.isPresent()) {
			return resutlOpt.get();
		}

		resutlOpt = findParamTypeIfExist(autoCompleteResults, "ville");
		if (resutlOpt.isPresent()) {
			return resutlOpt.get();
		}

		resutlOpt = findParamTypeIfExist(autoCompleteResults, "quartier");
		if (resutlOpt.isPresent()) {
			return resutlOpt.get();
		}

		return autoCompleteResult;
	}

	private Optional<AutoCompleteResult> findParamTypeIfExist(List<AutoCompleteResult> autoCompleteResults,
			String searchType) {
		return autoCompleteResults.stream()
				.filter(autoCompleteResult -> autoCompleteResult.getType().equalsIgnoreCase(searchType))
				.findFirst();
	}

	private SLParameter paramsToParameter(Params params) {

		// default case
		String name = "ci";
		String value = params.getCi();

		if (!StringUtils.isBlank(params.getCp())) {
			name = "cp";
			value = params.getCp();
		}

		if (!StringUtils.isBlank(params.getDiv())) {
			name = "div";
			value = params.getDiv();
		}

		if (!StringUtils.isBlank(params.getIdq())) {
			name = "idq";
			value = params.getIdq();
		}

		return new SLParameter(name, value);
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class AutoCompleteResult {
		@JsonProperty("Type") // group, quartier, city, region
		private String type;
		@JsonProperty("Params")
		private Params params;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class Params {
		private String cp;
		private String ci;
		private String div;
		private String idq;
	}

}
