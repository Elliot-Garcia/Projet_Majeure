package com.fighter.brain.mission;

import com.fighter.model.calcul.Path;
import com.fighter.model.dto.FireDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public class MissionRavitaillement extends Mission {


	
	public MissionRavitaillement(InterfaceVehicule v, double lonDestination, double latDestination) {
		super(v, lonDestination, latDestination);
	}
	
	/**
	 * fill the vehicule if needed in fuel and liquide
	 */
	protected void missionAction(boolean deplacement) {
		int RAV_FUEL = 10;
		if (this.vehicule.getVehiculeDto().getType().getFuelCapacity() - (this.vehicule.getVehiculeDto().getFuel() + RAV_FUEL) > 0) {
			this.vehicule.getVehiculeDto().setFuel(this.vehicule.getVehiculeDto().getFuel()+RAV_FUEL);
		}
		else {
			this.vehicule.getVehiculeDto().setFuel(this.vehicule.getVehiculeDto().getType().getFuelCapacity());
		}
		if (this.vehicule.getVehiculeDto().getType().getLiquidCapacity() - (this.vehicule.getVehiculeDto().getLiquidQuantity() + RAV_FUEL) > 0) {
			this.vehicule.getVehiculeDto().setLiquidQuantity(this.vehicule.getVehiculeDto().getLiquidQuantity()+1);
		}
		else {
			this.vehicule.getVehiculeDto().setLiquidQuantity(this.vehicule.getVehiculeDto().getType().getLiquidCapacity());
		}	
	}
	
	/**
	 * as long as the vehicule is not full
	 */
	@Override
	protected boolean missionContinue() {
		return ( (this.vehicule.getVehiculeDto().getFuel() < this.vehicule.getVehiculeDto().getType().getFuelCapacity())
				|| (this.vehicule.getVehiculeDto().getLiquidQuantity() < this.vehicule.getVehiculeDto().getType().getLiquidCapacity() ) );

	}
}
