package com.fighter.brain;

import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;

public class strategie {
	private FireDto feu;
	private FacilityDto caserne;
	private VehiculeDto vehicule;
	
	public strategie(FireDto feu, FacilityDto caserne, VehiculeDto vehicule) {
		this.caserne = caserne; 
		this.feu = feu;
		this.vehicule = vehicule;
	}
	
	public int calculScore() {
		int res = 20;
		String type_feu = feu.getType();
		
		if(res>10) {
			launchMission();
		}
		return res;
	}
	
	public boolean launchMission() {
		//Envoie vehicule et feu à la mission
		return true;
	}
}
