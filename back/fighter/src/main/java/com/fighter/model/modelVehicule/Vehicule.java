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
	    float score_fireIntensity = fire.getIntensity();
	    float score_distance = (float) (1 / ( path.distancePoint() ) * Math.pow(10, 6) );
	    
	    float score = score_liquidType + score_fireIntensity + score_distance;
	    
	    System.out.println(this.getVehiculeDto().getType().getFuelConsumption() );
	    System.out.println(		path.distancePoint());
	    System.out.println(this.getVehiculeDto().getFuel() );
	    
	    
	    if ( this.getVehiculeDto().getType().getFuelConsumption() * path.distancePoint() < this.getVehiculeDto().getFuel() ) {
	    	score = -1;
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