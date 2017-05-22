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

import fr.appartment.indexator.brokers.DataIndexator;
import fr.appartment.indexator.domain.Appartment;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SelogerIndexator implements DataIndexator {

	private static final int APPARTMENT_DATA_SCRITP_POSITION = 2;

	private SelogerClient client;

	public SelogerIndexator(SelogerClient client) {
		this.client = client;
	}

	@Override
	public List<Appartment> index(List<String> postalCodes, Integer minPrice, Integer maxPrice) {

		List<Appartment> appartements = new ArrayList<>();

		int currentPage = 1;
		int pageTotal = 0;
		do {
			String pageContent = client.getPage(postalCodes, minPrice, maxPrice, 1);
			appartements.addAll(deserializeAppartment(pageContent));
			pageTotal = this.findPageNumber(pageContent);
		} while (currentPage <= pageTotal);

		return appartements;
	}

	@SuppressWarnings("unchecked")
	private List<Appartment> deserializeAppartment(String pageContent) {

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

	public int findPageNumber(String pageContent) {
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

}
