package com.fighter.model.calcul;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

public class MapBoxPath {

	// Format Coord : lon1,lat1;lon2,lat2
	static final String MapBoxURL1 = "https://api.mapbox.com/directions/v5/mapbox/driving/";
	static final String MapBoxURL2 = "?geometries=geojson&access_token=pk.eyJ1IjoiZWxsaWdhcjM0IiwiYSI6ImNsM3ZjOHhoaDA5MXYzYnBsdHFxamsxcjYifQ.UyvHBJ_M2OpnPGJUV-BBYg";
	
	/**
	 * Requête à l'api de MapBox pour récuperer l'itinéraire à suivre sur la route
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return liste de points pour du véhicule jusqu'a l'objectif
	 */
	public static JsonNode requestMapBoxPath(double lon1, double lat1, double lon2, double lat2) {
		String url = MapBoxURL1 + "" + lon1 + "," + lat1 + ";" + lon2 + "," + lat2 + "" + MapBoxURL2;
		//System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
		JsonNode result = restTemplate.getForObject(url, JsonNode.class);
		return result;
	}
	
	public static void main(String[] args) {
		MapBoxPath map = new MapBoxPath();
		System.out.println(map.requestMapBoxPath(4,45,4.2,45.6));
	}
	
}
