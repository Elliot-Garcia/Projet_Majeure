package com.fighter.brain;

import java.util.List;

import com.fighter.brain.mission.Mission;
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
	
	public Strategy(FireDto feu, List<FacilityDto> casernes, List<VehiculeDto> vehicules) {
		this.caserne = caserne; 
		this.feu = feu;
		this.vehicules = vehicules;
	}
	
	public boolean launchMission(VehiculeDto v, FireDto fi) {
		//mission(v, fi); //Voir la fonction à appeler
		//Envoie vehicules et feu à la mission
		InterfaceVehicule vehicules = new Vehicule(v);
		System.out.println("fire " + fi.getLon() + " " + fi.getLat());
		System.out.println("car " + v.getLon() + " " + v.getLat());
		
		Mission mission = new Mission(vehicules, fi);
		System.out.println("debut ");
		mission.debutMission();


		return true;
	}
}
