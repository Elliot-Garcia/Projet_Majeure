package com.fighter.brain.mission;

import com.fighter.model.calcul.Path;
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
		this.vehicule.deplacement(this.feu.getLon(), this.feu.getLat());
		this.vehicule.missionTrue();
	}
	
	public void finMission() {
		this.vehicule.getVehiculeDto().setLat(this.vehicule.findFacility().getLat());
		this.vehicule.getVehiculeDto().setLon(this.vehicule.findFacility().getLon());
		this.vehicule.missionFalse();
		Requester.postVehicule(this.vehicule.getVehiculeDto());
	}
	
	@Override
	public void missionAllerAuFeu(double lonFeu, double latFeu) {
		Path chemin = new Path(vehicule.getVehiculeDto().getLon(),
				vehicule.getVehiculeDto().getLat(),
				feu.getLon(),
				feu.getLon());
		this.vehicule.missionTrue();
		
	}
	
	@Override
	public void missionRavitaillementEssence(double lonRav, double latRav) {
		Path chemin = new Path(vehicule.getVehiculeDto().getLon(),
				vehicule.getVehiculeDto().getLat(),
				vehicule.findFacility().getLon(),
				vehicule.findFacility().getLat());
	}
	
	@Override
	public void missionRavitaillementExtincteur(double lonRav, double latRav) {
		
	}
	
	@Override
	public void missionAnnule() {
		this.vehicule.missionFalse();
	}
	
	//private void actuMission(Path path) {
		
	//}
	
}
