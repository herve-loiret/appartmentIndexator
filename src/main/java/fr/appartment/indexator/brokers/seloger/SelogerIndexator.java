package fr.appartment.indexator.brokers.seloger;

import static fr.appartment.indexator.utils.ParserUtils.parseDouble;
import static fr.appartment.indexator.utils.ParserUtils.parseInt;
import static fr.appartment.indexator.utils.ParserUtils.parseNumberBoolean;
import static fr.appartment.indexator.utils.ParserUtils.parseString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.appartment.indexator.brokers.OnlyNewIndexator;
import fr.appartment.indexator.domain.Appartment;
import fr.appartment.indexator.service.AppartmentService;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SelogerIndexator extends OnlyNewIndexator {

	private static final int APPARTMENT_DATA_SCRITP_POSITION = 2;

	public SelogerIndexator(SelogerClient client, AppartmentService appartmentService) {
		super(client, appartmentService);
	}

	@SuppressWarnings("unchecked")
	protected List<Appartment> parseAppartmentsFromSearchPage(String pageContent) {

		List<Appartment> appartments = new ArrayList<>();

		Document page = Jsoup.parse(pageContent);
		String scriptContent = page.getElementsByTag("script").get(APPARTMENT_DATA_SCRITP_POSITION).data();
		scriptContent = scriptContent.replaceAll("logged: logged,", "");
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		try {
			engine.eval(scriptContent);
			Map<String, Object> products = (Map<String, Object>) ((Map<String, Object>) engine.get("ava_data"))
					.get("products");
			for (Entry<String, Object> productsEntry : products.entrySet()) {
				Map<String, Object> product = (Map<String, Object>) productsEntry.getValue();
				Appartment appartment = new Appartment();
				
				appartment.setExternalId(parseString(product.get("idannonce")));
				appartment.setUrl(findAppartmentUrl(page, product));
				appartment.setPostalCode(parseString(product.get("cp")));
				appartment.setEtage(parseInt(product.get("etage")));
				appartment.setPrice(parseDouble(product.get("prix")));
				appartment.setSurface(parseDouble(product.get("surface")));
				appartment.setNbPhotos(parseInt(product.get("nb_photos")));
				appartment.setType(parseString(product.get("typedebien")));
				appartment.setTypeChauffage(parseString(product.get("idtypechauffage")));
				appartment.setTypeCuisine(parseString(product.get("idtypecuisine")));
				appartment.setHasBalcon(parseNumberBoolean(product.get("si_balcon")));
				appartment.setHasSdbain(parseNumberBoolean(product.get("si_sdbain")));
				appartment.setHasSdEau(parseNumberBoolean(product.get("si_sdEau")));
				appartment.setNbChambres(parseInt(product.get("nb_chambres")));
				appartment.setNbPieces(parseInt(product.get("nb_pieces")));
				appartments.add(appartment);
			}
		} catch (ScriptException e) {
			log.error("error while trying to index appartment", e);
		}

		return appartments;
	}

	@Override
	public int findTotalPageFromSearchPaget(String pageContent) {
		int result = 0;
		Document page = Jsoup.parse(pageContent);
		String pageString = page.getElementsByClass("pagination_result_number").first().html();
		Pattern pattern = Pattern.compile("Page ([0-9]+) / ([0-9]+)");
		Matcher matcher = pattern.matcher(pageString);
		if (matcher.find()) {
			// int currentPage = matcher.group(1));
			result = Integer.valueOf(matcher.group(2));
		}

		return result;

	}

	private String findAppartmentUrl(Document page, Map<String, Object> product) {
		int position = parseInt(product.get("position"));
		Element listInfos = page.getElementsByClass("listing_infos").get(position);
		String url = listInfos.getElementsByTag("a").first().attr("href");
		return url;
	}

	@Override
	@SneakyThrows
	protected Appartment parseAppartmentFromDetailPage(Appartment appartment, String detailPage) {

		ObjectMapper mapper = new ObjectMapper();
		AppartmentDetail appartmentDetail = mapper.readValue(detailPage , AppartmentDetail.class);
		appartment.setDescription(appartmentDetail.getDescriptif());
		
		return appartment;
	}
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class AppartmentDetail{
		private String descriptif;
	}

}
