package com.fighter.brain;

import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.DTOclass;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;



public class remoteControl {

	private FireDto[] fire;
	private VehiculeDto[] vehicule;
	private FacilityDto[] facility;


	public remoteControl() {

		this.fire = Requester.requestFire();
		this.vehicule = Requester.requestVehicule();
		this.facility = Requester.requestFacility();
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
	
	private boolean compareData(DTOclass[] pastData, DTOclass[] newData) {

		if (pastData.equals(newData)) {
			return true;
		}
		pastData = newData;
		return false;
	}

	private boolean compareFire() {
		return this.compareData( this.fire, Requester.requestFire() );
	}
	
	private boolean compareVehicule() {
		return this.compareData(this.vehicule, Requester.requestVehicule() );
	}

	private boolean comparefacility() {
		return this.compareData(this.facility, Requester.requestVehicule() );
	}

}
