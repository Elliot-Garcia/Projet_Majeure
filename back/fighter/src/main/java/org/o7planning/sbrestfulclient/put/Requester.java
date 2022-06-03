package org.o7planning.sbrestfulclient.put;

import org.springframework.web.client.RestTemplate;

import com.fighter.model.communicator.ConstantURL;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


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

	public static void putVehicule( VehiculeDto body, int id ) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		RestTemplate restTemplate = new RestTemplate();

		// Data attached to the request.
		HttpEntity<VehiculeDto> requestBody = new HttpEntity<>(body, headers);

		// Send request with PUT method.
		restTemplate.put(ConstantURL.getVehiculeurl(), requestBody, new Object[] {});

		String resourceUrl = ConstantURL.getVehiculeurl() + "/" + ConstantURL.teamCode + "/" + body.getId();

		String e = restTemplate.getForObject(resourceUrl, String.class);

		System.out.println(e);
	}

}
