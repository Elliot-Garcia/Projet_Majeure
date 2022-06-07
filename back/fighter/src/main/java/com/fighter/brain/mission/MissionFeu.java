package com.fighter.brain.mission;

import com.fighter.model.calcul.Path;
import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FireDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public class MissionFeu extends Mission {
	
	private InterfaceVehicule vehicule;
	private FireDto feu;
	private Path chemin;
	
	MissionFeu(InterfaceVehicule v, double lonDestination, double latDestination, FireDto f) {
		super(v, lonDestination, latDestination);
	}
	
	public boolean missionContinue() {
		return Requester.requestFireByID( this.feu.getId()).equals(null);
	}

}
