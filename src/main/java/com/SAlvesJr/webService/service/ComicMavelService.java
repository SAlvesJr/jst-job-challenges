package com.SAlvesJr.webService.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SAlvesJr.webService.model.Comics;
import com.SAlvesJr.webService.model.dto.ComicsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ComicMavelService {

	@Value("${access.ApiKeyPulbic.Mavel}")
	private String apiKey;

	@Value("${access.hash.Mavel}")
	private String hash;

	private static final String GET_SEARCH = "https://gateway.marvel.com:443/v1/public/characters";

	String tokenAccess = "&ts=1&apikey=030a7fa46ef934b9255e5ae3c1655b2a&hash=c9a1c16d819b28bc846b4fc367a8b085";

	public ComicsDTO searchComicsName(String name, int quant) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = GET_SEARCH + "?name=" + name + "&limit=100&ts=1&apikey=" + apiKey + "&hash=" + hash;
		String response = restTemplate.getForObject(fooResourceUrl, String.class);

		JSONObject my_obj = new JSONObject(response).getJSONObject("data");
		JSONArray result = my_obj.getJSONArray("results");

//		JSONObject rs = null;
//		List<Comics> jsonList = new ArrayList<>();

		if (result.length() != 0 & result != null) {

			int idCharacte = (int) result.getJSONObject(0).get("id");
			return searchComics(idCharacte, quant);

//			rs = (JSONObject) result.getJSONObject(0).get("comics");
//			String str = rs.get("items").toString();
//			try {
//				jsonList = new ObjectMapper().readValue(str, new TypeReference<List<ComicsDTO>>() {
//				});
//			} catch (JsonMappingException e1) {
//				e1.printStackTrace();
//			} catch (JsonProcessingException e1) {
//				e1.printStackTrace();
//			}
		}

		return null;
	}

	public ComicsDTO searchComics(int idCharacte, int quant) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = GET_SEARCH + "/" + idCharacte + "/comics?noVariants=true&orderBy=title&limit=" + quant
				+ "&ts=1&apikey=" + apiKey + "&hash=" + hash;
		String response = restTemplate.getForObject(fooResourceUrl, String.class);

		JSONObject my_obj = new JSONObject(response).getJSONObject("data");
		JSONArray result = my_obj.getJSONArray("results");
		int totalResposta = (int) my_obj.get("total");

		List<Comics> jsonList = new ArrayList<>();
		if (result.length() != 0 & result != null) {
			try {
				jsonList = new ObjectMapper().readValue(result.toString(), new TypeReference<List<Comics>>() {
				});
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
		}

		ComicsDTO resp = new ComicsDTO(totalResposta, jsonList);

		return resp;

	}
}
