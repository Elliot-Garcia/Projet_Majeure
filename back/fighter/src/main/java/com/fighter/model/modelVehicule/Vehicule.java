package com.fighter.model.modelVehicule;

import com.fighter.model.calcul.Path;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.FireType;
import com.fighter.model.dto.LiquidType;
import com.fighter.model.dto.VehiculeDto;

public class Vehicule extends AbstractVehicule {

	Vehicule() {
		super();
	}
	public Vehicule(VehiculeDto v) {
		super();
		this.vehicule = v;
		this.mission = false;
	}

	@Override
	public int calculScore(FireDto fire, FacilityDto facility, Path path) {
		
		
		// Score with liquidType
		LiquidType liquidType = vehicule.getLiquidType();
		float score_liquidType = liquidType.getEfficiency( fire.getType() ) * 100;
	    float score_fireIntensity = 1/fire.getIntensity();
	    float score_distance = 1/path.distancePoint() * 10^6;
	    
	    float score = score_liquidType + score_fireIntensity + score_distance;
	    
	    if ( this.getVehiculeDto().getType().getFuelConsumption() * path.distancePoint() < this.getVehiculeDto().getFuel() ) {
	    	score *= -1;
	    }

	  

		return (int)score;
		}
	
	public static void main(String[] args) {
		LiquidType liquidType = LiquidType.WATER;
		FireDto fire = new FireDto();
		fire.setType("B_Gasoline");
		System.out.println(liquidType);
	}
	
}