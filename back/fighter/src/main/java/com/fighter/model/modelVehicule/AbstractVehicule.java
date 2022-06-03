package com.fighter.model.modelVehicule;

import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;

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
	public void deplacement(double lon, double lat) {
		System.out.println("carAVD " + vehicule.getLon() + " " + vehicule.getLat());
		this.vehicule.setLon(lon);
		this.vehicule.setLat(lat);
		System.out.println("carAPD " + vehicule.getLon() + " " + vehicule.getLat());
		Requester.postVehicule(this.vehicule);
	}

	@Override
	public boolean ravitaillement() {
		// TODO Auto-generated method stub
		return false;
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
	
	abstract public int calculScore(FireDto fire, FacilityDto facility);
	
	@Override
	public VehiculeDto getVehiculeDto() {
		return this.vehicule;
	}

}
