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
		int score = 0;
		
		// Score with liquidType
		LiquidType liquidType = vehicule.getLiquidType();
		int score_liquidType = (int) (-0.5 + liquidType.getEfficiency(fire.getType())) * 10;
		
		score += score_liquidType;
		
		// Score with distance
		//int distance = path.dczc();
		int score_distance = (int) (1);
		
		
		return score;
		}
	
	public static void main(String[] args) {
		LiquidType liquidType = LiquidType.WATER;
		FireDto fire = new FireDto();
		fire.setType("B_Gasoline");
		System.out.println(liquidType);
	}
	
}