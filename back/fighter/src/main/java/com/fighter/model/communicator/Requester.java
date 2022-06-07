package com.fighter.model.communicator;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;


public class Requester {
	
	public static FireDto[] requestFire( ) {
		
		RestTemplate restTemplate = new RestTemplate();
		FireDto[] result = restTemplate.getForObject(ConstantURL.getFireurl(), FireDto[].class);
		return result;
	}
	
	public static FireDto requestFireByID( int id ) {

		RestTemplate restTemplate = new RestTemplate();
		String url = ConstantURL.getFireurl() + id;
		
		FireDto result = restTemplate.getForObject(url, FireDto.class);

		return result;
	}
	
	
	public static VehiculeDto[] requestVehicule( ) {
		
		RestTemplate restTemplate = new RestTemplate();
		VehiculeDto[] result = restTemplate.getForObject(ConstantURL.getVehiculeurl(), VehiculeDto[].class);
		return result;
	}
	
	public static FacilityDto[] requestFacility( ) {
		
		RestTemplate restTemplate = new RestTemplate();
		FacilityDto[] result = restTemplate.getForObject(ConstantURL.getFacilityurl(), FacilityDto[].class);
		return result;
	}
	
	public static FacilityDto requestFacilityByID( int id ) {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = ConstantURL.getFacilityurl() + id;
		FacilityDto result = restTemplate.getForObject(url, FacilityDto.class);
		return result;
	}
	
	public static void postVehicle( VehiculeDto info ) {

	      HttpHeaders headers = new HttpHeaders();
	      headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	      headers.setContentType(MediaType.APPLICATION_JSON);

	      RestTemplate restTemplate = new RestTemplate();

	      // Data attached to the request.
	      HttpEntity<VehiculeDto> requestBody = new HttpEntity<>(info, headers);

	      // Send request with POST method.
	      String URL = ConstantURL.getVehiculeurl() + ConstantURL.teamCode;
	      restTemplate.postForObject(URL, requestBody, VehiculeDto.class);
	}
	
	public static void putVehicule( VehiculeDto info) {
		postVehicle( info );
		return ;
		/*
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		RestTemplate restTemplate = new RestTemplate();

		// Data attached to the request.
		HttpEntity<VehiculeDto> requestBody = new HttpEntity<>(info, headers);

		// Send request with PUT method.
		String URL = ConstantURL.getVehiculeurl() + ConstantURL.teamCode + "/" + info.getId();
		System.out.println(URL);
		System.out.println(requestBody.getBody());
		
		restTemplate.put(URL, requestBody);
		*/
	}
}