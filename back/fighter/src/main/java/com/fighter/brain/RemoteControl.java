package com.fighter.brain;

import java.util.Arrays;
import java.util.List;

import com.fighter.brain.mission.LogMission.LogMission;
import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.DTOclass;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;





public class RemoteControl {

	private List<FireDto> fire;
	private List<VehiculeDto> vehicule;
	private List<FacilityDto> facility;
	
	private int[] newFire;
	

	public RemoteControl() {

		//this.fire = Requester.requestFire();
		this.vehicule = Arrays.asList(Requester.requestVehicule());
		this.facility = Arrays.asList(Requester.requestFacility());
	}
	
	
	/**
	 * launch a new statgie
	 * @param newFire
	 * @return
	 */
	private final boolean launchStrat( FireDto newFire) {
		new Strategy( newFire, this.facility, this.vehicule).assignVehicule();
		
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
		ret &= this.checkMissionedFire();
		
		return(ret);	
	}
	
	private boolean checkMissionedFire() {
		boolean ret = false;
		
		LogMission.debutMission(new FireDto());
		
		for ( FireDto missonedFire : LogMission.getFire()) {
			if (this.fire.contains(missonedFire)){
				ret |= this.fire.remove(missonedFire);
			}
		}
		return ret;
	}
	
	/**
	 * Compare to set of data
	 * @param two DTOclass[]
	 * @return true if equals, false else
	 */
	private boolean compareData( List<FireDto> pastData, List<FireDto> newData) {
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
		List<FireDto> newFire = Arrays.asList(Requester.requestFire());
		
		if (this.fire == null) {
			for ( FireDto someFire : newFire) {
					this.launchStrat( someFire );
				}
		}
		
		else if ( !this.compareData( this.fire, newFire )) {
			System.out.println(this.fire);
			for ( FireDto someFire : newFire) {
				
				if(this.fire.contains(someFire)) {
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
		this.vehicule = Arrays.asList(Requester.requestVehicule());
		return true;
	}
	
	/**
	 * Save new data of Facility
	 * @return true
	 */
	private boolean editFacility() {
		this.facility = Arrays.asList(Requester.requestFacility());
		return true;
	}
	
	public String toString() {
		return getClass().getSimpleName() +"\nFire: " + this.fire.size() + "\nVehicule: " + this.vehicule.size() + "\nFacilty: " + this.facility.size();
	}

}
