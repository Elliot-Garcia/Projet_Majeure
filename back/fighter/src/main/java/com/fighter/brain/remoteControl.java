package com.fighter.brain;
import com.fighter.vehiculeModel.testClasse;



import org.springframework.web.client.RestTemplate;



public class remoteControl {
	private final String urlFire = "http://vps.cpe-sn.fr:8081/fire/";
	private final String urlVehicule = "http://vps.cpe-sn.fr:8081/vehicle/";
	private final String urlFacility = "http://vps.cpe-sn.fr:8081/facility/";
	
	private String fire;
	private String vehicule;
	private String facility;
	

	public remoteControl() {
		this.fire = this.request(urlFire);
		this.vehicule = this.request(urlVehicule);
		this.facility = this.request(urlFacility);
	}
	
	public final boolean newMission() {
		
		if( !this.compare() ) {
			
		}
	}
	
	private final boolean compare() {
		boolean ret = true;
		
		ret &= this.compareFire();
		ret &= this.compareVehicule();
		ret &= this.comparefacility();
		
		return(ret);	
	}
	
	
	private String request( String URL) {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(URL, String.class);
		
		return result;
	}
	
	private boolean compareData( String URL, String pastData) {
		String data = this.request(URL);

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
