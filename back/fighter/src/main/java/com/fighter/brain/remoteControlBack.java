package com.fighter.brain;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.client.RestTemplate;

import com.fighter.model.dto.DTO;
import com.fighter.model.dto.DTOclass;
import com.fighter.model.dto.FireDto;



public class remoteControlBack {
	private final String urlFire = "http://vps.cpe-sn.fr:8081/fire/";
	private final String urlVehicule = "http://vps.cpe-sn.fr:8081/vehicle/";
	private final String urlFacility = "http://vps.cpe-sn.fr:8081/facility/";
	
	private FireDto[] fire;
	private DTOclass[] vehicule;
	private DTOclass[] facility;
	

	public remoteControlBack() {
		
		this.fire = (FireDTO[])(this.request(urlFire, this.fire));
		this.vehicule = this.request(urlVehicule, this.vehicule);
		this.facility = this.request(urlFacility, this.facility);
	}
	
	public final boolean newMission() {
		
		if( !this.compare() ) {
			//launch new mission
			//new strategie(FireDto feu, FacilityDto caserne, VehiculeDto vehicule)
			return(true);
		}
		return false;
	}
	
	private final boolean compare() {
		boolean ret = true;
		
		ret &= this.compareFire();
		ret &= this.compareVehicule();
		ret &= this.comparefacility();
		
		return(ret);	
	}
	
	
	private <T> T[] request( String URL, T[] pastData) {
		
		System.out.println("OUI");
		RestTemplate restTemplate = new RestTemplate();
		T[] result = restTemplate.getForObject(URL, T[].class);
		System.out.println(result[0]);

		return result;
	}
	
	private boolean compareData( String URL, DTOclass[] pastData) {
		
		DTOclass[] data = request(URL, pastData);

		if (data.equals(pastData)) {
			return true;
		}
		pastData = data;
		return false;
	}
	
	private boolean compareFire() {
		return this.compareData(this.urlFire, this.fire );
	}
	
	private boolean compareVehicule() {
		return this.compareData(this.urlVehicule, this.vehicule );
	}
	
	private boolean comparefacility() {
		return this.compareData(this.urlFacility, this.facility );
	}

}
