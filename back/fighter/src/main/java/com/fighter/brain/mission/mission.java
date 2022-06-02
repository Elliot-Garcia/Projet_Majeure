package com.fighter.brain.mission;

import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;

public class mission {

	private VehiculeDto vehicule;
	private FireDto feu;
	
	mission(VehiculeDto v, FireDto f) {
		this.vehicule = v;
		this.feu = f;
	}
	
	public void debutMission() {
		this.vehicule.setLat(this.feu.getLat());
		this.vehicule.setLon(this.feu.getLon());
	}
	/*
	public void finMission() {
		this.vehicule.setLat(this.vehicule.caserne.lon);
		this.vehicule.setLon(this.vehicule.caserne.lon);
	}
	*/
}
