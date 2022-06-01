package com.fighter.brain;

public class strategie {
	private FireDto feu;
	private FacilityDto caserne;
	private VehiculeDto vehicule;
	
	public stategie(FireDto feu, FacilityDto caserne, Vehicule vehicule) {
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
