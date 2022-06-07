package com.fighter.brain.mission;

import com.fighter.model.calcul.Path;
import com.fighter.model.dto.FireDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public class MissionRavitaillementEssence extends Mission {

	private InterfaceVehicule vehicule;
	private Path chemin;
	
	public MissionRavitaillementEssence(InterfaceVehicule v, double lonDestination, double latDestination) {
		super(v, lonDestination, latDestination);
	}
	
	@Override
	protected boolean missionContinue() {
		// TODO Auto-generated method stub
		return false;
	}

}
