package com.fighter.brain;

import java.util.Arrays;

import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.DTOclass;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;





public class remoteControl {

	private FireDto[] fire;
	private VehiculeDto[] vehicule;
	private FacilityDto[] facility;
	
	private int[] newFire;
	

	public remoteControl() {

		//this.fire = Requester.requestFire();
		this.vehicule = Requester.requestVehicule();
		this.facility = Requester.requestFacility();
	}
	
	
	/**
	 * launch a new statgie
	 * @param newFire
	 * @return
	 */
	public final boolean launchStrat( FireDto newFire) {
		//lucnhe strat
		//new strategie( newFire  );
		
		System.out.println("New strat");
		new strategie( newFire ,this.facility, this.vehicule).calculScore(newFire ,this.facility, this.vehicule);
		
		return true;
	}
	
	/**
	 * Cycle of checking new fire apparition and edting dataset
	 * @return true if new straegy attempt has been made
	 */
	public final boolean compare() {
		boolean ret = true;
		
		this.editFacility();
		this.editVehicule();
		ret &= this.compareFire();
		
		return(ret);	
	}
	
	/**
	 * Compare to set of data
	 * @param two DTOclass[]
	 * @return true if equals, false else
	 */
	private boolean compareData(DTOclass[] pastData, DTOclass[] newData) {

		if (newData.equals(pastData)) {
			return true;
		}

		return false;
	}
	
	/**
	 * comapre new and past data about fire. 
	 * if some new fire, spot it and ask for a new strat on it
	 * @return true if at least one try has been made, Else false
	 */
	private boolean compareFire() {
		boolean ret = false;
		FireDto[] newFire = Requester.requestFire();
		
		if (this.fire == null) {
			for ( FireDto someFire : newFire) {
					this.launchStrat( someFire );
				}
		}
		
		else if ( !this.compareData( this.fire, newFire )) {
			System.out.println(this.fire);
			for ( FireDto someFire : newFire) {
				
				if(Arrays.asList(this.fire).contains(someFire)) {
					this.launchStrat( someFire );
					ret = true;
				}
	        }	
		}
		this.fire = newFire;
		return ret;
	}

	/**
	 * Save new data of vehicule 
	 * @return true
	 */
	private boolean editVehicule() {
		this.vehicule = Requester.requestVehicule();
		return true;
	}
	
	/**
	 * Save new data of Facility
	 * @return true
	 */
	private boolean editFacility() {
		this.facility = Requester.requestFacility();
		return true;
	}
	
	public String toString() {
		return getClass().getSimpleName() +"\nFire: " + this.fire.length + "\nVehicule: " + this.vehicule.length + "\nFacilty: " + this.facility.length;
	}

}
