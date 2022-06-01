package com.fighter.brain.mission;

public class mission {

	private Vehicule vehicule;
	private Feu feu;
	
	mission(Vehicule v, Feu f) {
		this.vehicule = v;
		this.feu = f;
	}
	
	public void debutMission() {
		this.vehicule.lat = this.feu.lat;
		this.vehicule.lon = this.feu.lon;
	}
	
	public void finMission() {
		this.vehicule.lat = this.vehicule.caserne.lon;
		this.vehicule.lon = this.vehicule.caserne.lon;
	}
	
}
