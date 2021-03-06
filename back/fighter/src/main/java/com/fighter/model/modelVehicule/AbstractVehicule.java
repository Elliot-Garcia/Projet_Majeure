package com.fighter.model.modelVehicule;

import com.fighter.model.calcul.Path;
import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.project.model.dto.Coord;

public abstract class AbstractVehicule implements InterfaceVehicule {
	
	protected VehiculeDto vehicule;
	protected boolean mission;
	
	AbstractVehicule() {
		this.vehicule = new VehiculeDto();
		this.mission = false;
	}
	
	@Override
	public void missionTrue() {
		this.mission = true;
	}
	
	@Override
	public void missionFalse() {
		this.mission = false;
	}

	@Override
	public boolean getMission() {
		return this.mission;
	}
	
	@Override
	public void deplacement(double lon, double lat, float distance) {
		this.vehicule.setLon(lon);
		this.vehicule.setLat(lat);
		gestionConsommationEssence(distance);
		Requester.putVehicule(this.vehicule);
	}
	
	@Override
	public void gestionConsommationEssence(float distance) {
		float consommation = this.vehicule.getType().getFuelConsumption()*distance/100;
		this.vehicule.setFuel(this.vehicule.getFuel()-consommation);
	}

	@Override
	public void gestionCrew() {
		// TODO Auto-generated method stub

	}
	
	@Override 
	public FacilityDto findFacility() {
		return findFacilityById(this.vehicule.getFacilityRefID());
	}
	
	private FacilityDto findFacilityById(int id) {
		int idFacility = this.vehicule.getFacilityRefID();
		FacilityDto facility = Requester.requestFacilityByID(idFacility);
		return facility;
	}
	
	abstract public int calculScore(FireDto fire, FacilityDto facility, Path path);
	
	@Override
	public VehiculeDto getVehiculeDto() {
		return this.vehicule;
	}

}
