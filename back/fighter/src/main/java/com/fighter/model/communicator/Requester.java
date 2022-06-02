package com.fighter.model.communicator;

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

}
