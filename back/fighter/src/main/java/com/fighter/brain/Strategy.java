package com.fighter.brain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fighter.brain.mission.Mission;
import com.fighter.brain.mission.LogMission.LogMission;
import com.fighter.model.ConstantsModel;
import com.fighter.model.calcul.Path;
import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;
import com.fighter.model.modelVehicule.Vehicule;




public class Strategy {

	private FireDto feu;
	private List<FacilityDto> caserne;
	private List<VehiculeDto> vehicules;
	private List<Vehicule> ownVehicule;
	
	
	public Strategy(FireDto feu, List<FacilityDto> casernes, List<VehiculeDto> vehicules) {
		this.caserne = caserne; 
		this.feu = feu;
		this.vehicules = vehicules;
		this.initOwnVehicle();
	}
	
	private void initOwnVehicle() {

		this.ownVehicule = new ArrayList();
		for ( VehiculeDto vehiculeDto : this.vehicules ) {
			if( ConstantsModel.getListFacility().contains( vehiculeDto.getFacilityRefID() ) ) {
				Vehicule vehicule = new Vehicule(vehiculeDto);
				ownVehicule.add(vehicule);
			}
		}
	}
		
	
	public boolean assignVehicule() {
		
		int max = 0;
		Vehicule vehiculeMax = null;

		for ( Vehicule vehicule : this.ownVehicule ) {
			Path path = new Path( vehicule.getVehiculeDto().getLon(), vehicule.getVehiculeDto().getLat(), this.feu.getLon(), this.feu.getLat());
			int score = vehicule.calculScore(this.feu, vehicule.findFacility(), path);
			System.out.println(score);
			
			if (score > max) {
				max = score;
				vehiculeMax = vehicule;
			}
		}
		System.out.println(vehiculeMax);
		if (vehiculeMax != null) {
			 this.launchMission(vehiculeMax.getVehiculeDto(), feu);
			 return(true);
		}
		return false;
	}
	
	private boolean launchMission(VehiculeDto v, FireDto fi) {
		//mission(v, fi); //Voir la fonction à appeler
		//Envoie vehicules et feu à la mission
		InterfaceVehicule vehicules = new Vehicule(v);
		
		Mission mission = new Mission(vehicules, fi);
		System.out.println("debut Mission");
		mission.debutMission();


		return true;
	}
}
