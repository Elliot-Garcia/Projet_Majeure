package com.fighter.model.modelVehicule;

import com.fighter.model.dto.VehiculeDto;

public class Vehicule extends AbstractVehicule {

	Vehicule() {
		super();
	}
	public Vehicule(VehiculeDto v) {
		super();
		this.vehicule = v;
	}

	@Override
	public int Score() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
