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
	
	/**
	 * nothing to do in that case
	 */
	protected void missionAction(boolean deplacement) {
		
	}
	
	/**
	 * the mission continue as long as the fire existe.
	 * @return true : it existe, false : it doesn't
	 */
	protected boolean missionContinue() {
		return ( !(Requester.requestFireByID(this.feu.getId()) == null) );
	}

}