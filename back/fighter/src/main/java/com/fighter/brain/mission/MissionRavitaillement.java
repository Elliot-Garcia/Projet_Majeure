package com.fighter.brain.mission;

import com.fighter.model.calcul.Path;
import com.fighter.model.dto.FireDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public class MissionRavitaillement extends Mission {

	private InterfaceVehicule vehicule;
	private Path chemin;
	
	public MissionRavitaillement(InterfaceVehicule v, double lonDestination, double latDestination) {
		super(v, lonDestination, latDestination);
	}
	
	@Override
	protected boolean missionContinue() {
		return !(this.vehicule.getVehiculeDto().getFuel() == this.vehicule.getVehiculeDto().getType().getFuelCapacity()
				&& this.vehicule.getVehiculeDto().getLiquidQuantity() == this.vehicule.getVehiculeDto().getType().getLiquidCapacity());
	}

}
