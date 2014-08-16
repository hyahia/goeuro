package com.goeuro.locator.utils;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {
	private static Logger logger = Logger.getLogger(JSONHelper.class);

	public static ArrayList<ArrayList<Object>> parseJSONObject(String text, String tag) throws JSONException{
		text = "{\"result\":" + text + "}";
		logger.debug("fine result:" + text);
		
		JSONObject obj = new JSONObject(text);
		ArrayList<Object> record = new ArrayList<Object>();
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
		JSONArray arr = obj.getJSONArray(tag);
		
		for (int i = 0; i < arr.length(); i++)
		{
			// "Position" is set as a static value as the parameter named "_type" is not returned in the result.
			record.add("Position");
			record.add(arr.getJSONObject(i).get("_id"));
			record.add(arr.getJSONObject(i).get("name"));
			record.add(arr.getJSONObject(i).get("type"));
			record.add(((JSONObject)arr.getJSONObject(i).get("geo_position")).get("latitude"));
			record.add(((JSONObject)arr.getJSONObject(i).get("geo_position")).get("longitude"));
			
			result.add(record);
			record = new ArrayList<Object>();
		}
		
		logger.debug(result);
		return result;
	}
	
	/**
	 * Unit testing main method
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "[{\"_id\":376217,\"key\":null,\"name\":\"Berlin\",\"fullName\":\"Berlin, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.52437,\"longitude\":13.41053},\"locationId\":8384,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":448103,\"key\":null,\"name\":\"Berlingo\",\"fullName\":\"Berlingo, Italy\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Italy\",\"geo_position\":{\"latitude\":45.50298,\"longitude\":10.04366},\"locationId\":147721,\"inEurope\":true,\"countryCode\":\"IT\",\"coreCountry\":true,\"distance\":null},{\"_id\":425332,\"key\":null,\"name\":\"Berlingerode\",\"fullName\":\"Berlingerode, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":51.45775,\"longitude\":10.2384},\"locationId\":124675,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":425333,\"key\":null,\"name\":\"Berlingen\",\"fullName\":\"Berlingen, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":50.23894,\"longitude\":6.71934},\"locationId\":124676,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":314826,\"key\":null,\"name\":\"Berlin Tegel\",\"fullName\":\"Berlin Tegel (TXL), Germany\",\"iata_airport_code\":\"TXL\",\"type\":\"airport\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.5548,\"longitude\":13.28903},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":314827,\"key\":null,\"name\":\"Berlin Schönefeld\",\"fullName\":\"Berlin Schönefeld (SXF), Germany\",\"iata_airport_code\":\"SXF\",\"type\":\"airport\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.3887261,\"longitude\":13.5180874},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null}]";
		String tag = "result";
		
		try {
			System.out.println(parseJSONObject(text, tag));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
