package com.fighter.brain.mission;

import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public class Mission implements InterfaceMission {

	private InterfaceVehicule vehicule;
	private FireDto feu;
	
	public Mission(InterfaceVehicule v, FireDto f) {
		this.vehicule = v;
		this.feu = f;
	}
	
	public void debutMission() {
		this.vehicule.getVehiculeDto().setLat(this.feu.getLat());
		this.vehicule.getVehiculeDto().setLon(this.feu.getLon());
		this.vehicule.deplacement(this.feu.getLon(), this.feu.getLat());
		Requester.postVehicule(this.vehicule.getVehiculeDto());

	}
	
	public void finMission() {
		this.vehicule.getVehiculeDto().setLat(this.vehicule.findFacilityById(this.vehicule.getVehiculeDto().getFacilityRefID()).getLat());
		this.vehicule.getVehiculeDto().setLon(this.vehicule.findFacilityById(this.vehicule.getVehiculeDto().getFacilityRefID()).getLon());
		Requester.postVehicule(this.vehicule.getVehiculeDto());
	}
	
	@Override
	public void missionAllerAuFeu(double lonFeu, double latFeu) {
		//Path chemin = new Path(vehicule.getVehiculeDto().getLon(),
				vehicule.getVehiculeDto().getLat(),
				feu.getLon(),
				feu.getLon());
		
	}
	
	@Override
	public void missionRavitaillementEssence(double lonRav, double latRav) {
		
	}
	
	@Override
	public void missionRavitaillementExtincteur(double lonRav, double latRav) {
		
	}
	
	@Override
	public void missionAnnule() {
		
	}
	
	//private void actuMission(Path path) {
		
	//}
	
}
