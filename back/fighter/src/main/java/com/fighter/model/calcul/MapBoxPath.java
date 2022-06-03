package com.fighter.model.calcul;

import org.springframework.web.client.RestTemplate;

import com.fighter.model.communicator.ConstantURL;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.LiquidType;

import net.minidev.json.JSONObject;

public class MapBoxPath {

	// Format Coord : lon1,lat1;lon2,lat2
	static final String MapBoxURL1 = "https://api.mapbox.com/directions/v5/mapbox/cycling/";
	static final String MapBoxURL2 = "?geometries=geojson&access_token=pk.eyJ1IjoiZWxsaWdhcjM0IiwiYSI6ImNsM3ZjOHhoaDA5MXYzYnBsdHFxamsxcjYifQ.UyvHBJ_M2OpnPGJUV-BBYg";
	
	
	public static JSONObject requestMapBoxPath(double lon1, double lat1, double lon2, double lat2) {
		String url = MapBoxURL1 + "" + lon1 + "," + lat1 + ";" + lon2 + "," + lat2 + "" + MapBoxURL2;
		System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
		JSONObject result = restTemplate.getForObject(url, JSONObject.class);
		return result;
	}
	
	public static void main(String[] args) {
		MapBoxPath map = new MapBoxPath();
		System.out.println(map.requestMapBoxPath(4,45,4.2,45.6));
	}
	
}
