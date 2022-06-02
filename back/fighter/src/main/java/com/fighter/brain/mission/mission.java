package com.fighter.brain.mission;

import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public class mission {

	private InterfaceVehicule vehicule;
	private FireDto feu;
	
	mission(InterfaceVehicule v, FireDto f) {
		this.vehicule = v;
		this.feu = f;
	}
	
	public void debutMission() {
		this.vehicule.getVehiculeDto().setLat(this.feu.getLat());
		this.vehicule.getVehiculeDto().setLon(this.feu.getLon());
		Requester.putVehicule(this.vehicule.getVehiculeDto());
	}
	
	public void finMission() {
		this.vehicule.getVehiculeDto().setLat(this.vehicule.findFacilityById(this.vehicule.getVehiculeDto().getFacilityRefID()).getLat());
		this.vehicule.getVehiculeDto().setLon(this.vehicule.findFacilityById(this.vehicule.getVehiculeDto().getFacilityRefID()).getLon());
		Requester.putVehicule(this.vehicule.getVehiculeDto());
	}
	
}
