package com.fighter.brain.mission;

import com.fighter.model.calcul.Path;
import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FireDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public class MissionFeu extends Mission {
	
	private InterfaceVehicule vehicule;
	private FireDto feu;
	private Path chemin;
	
	public MissionFeu(InterfaceVehicule v, double lonDestination, double latDestination, FireDto f) {
		super(v, lonDestination, latDestination);
		this.feu = f;
	}
	
	protected boolean missionContinue() {
		System.out.println("Vick :" );
		System.out.println(Requester.requestFireByID(this.feu.getId()) == null);
		return ( !(Requester.requestFireByID(this.feu.getId()) == null) );
	}

}