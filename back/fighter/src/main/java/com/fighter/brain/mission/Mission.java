package com.fighter.brain.mission;

import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public class Mission {

	private InterfaceVehicule vehicule;
	private FireDto feu;
	
	public Mission(InterfaceVehicule v, FireDto f) {
		this.vehicule = v;
		this.feu = f;
	}
	
	public void debutMission() {
		this.vehicule.getVehiculeDto().setLat(this.feu.getLat());
		this.vehicule.getVehiculeDto().setLon(this.feu.getLon());
		Requester.postVehicule(this.vehicule.getVehiculeDto());
	}
	
	public void finMission() {
		this.vehicule.getVehiculeDto().setLat(this.vehicule.findFacilityById(this.vehicule.getVehiculeDto().getFacilityRefID()).getLat());
		this.vehicule.getVehiculeDto().setLon(this.vehicule.findFacilityById(this.vehicule.getVehiculeDto().getFacilityRefID()).getLon());
		Requester.postVehicule(this.vehicule.getVehiculeDto());
	}
	
}
