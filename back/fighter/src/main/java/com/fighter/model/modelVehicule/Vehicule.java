package com.fighter.model.modelVehicule;

import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
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
	public int calculScore(FireDto fire, FacilityDto facility) {
		int score = 0;
		
		// Score with liquidType
		LiquidType liquidType = vehicule.getLiquidType();
		int score_liquidType = (int) (-0.5);
		
		return score;
		}
	
	public static void main(String[] args) {
		LiquidType liquidType = LiquidType.WATER;
		
		System.out.println(liquidType);
	}
	
}