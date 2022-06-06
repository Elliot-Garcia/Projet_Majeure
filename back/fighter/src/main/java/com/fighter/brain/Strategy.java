package com.fighter.brain;

import java.util.List;

import com.fighter.brain.mission.Mission;
import com.fighter.brain.mission.LogMission.LogMission;
import com.fighter.model.calcul.Path;
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
		
	
	public boolean assignVehicule() {
		
		System.out.println("++++++++++++ New assignement +++++++++++++++");
		
		int max = -1;
		Vehicule vehiculeMax = null;
		
		for ( Vehicule vehicule : LogMission.ownVehicule ) {
			Path path = new Path( vehicule.getVehiculeDto().getLon(), vehicule.getVehiculeDto().getLat(), this.feu.getLon(), this.feu.getLat());
			int score = vehicule.calculScore(this.feu, vehicule.findFacility(), path);
			System.out.println("Score :" + score);
			System.out.println("Busy :" + vehicule.getMission() + " " + vehicule);
			
			
		
			if (score > max && !vehicule.getMission()) {
				max = score;
				vehiculeMax = vehicule;
			}
		}
		
		if (vehiculeMax != null) {
			this.launchMission(vehiculeMax, feu);
			System.out.println("------------------------- MISSION --------------------------------");
			return(true);
		}
		System.out.println("------------------------- NO MISSION --------------------------------");
		return false;
	}
	
	private boolean launchMission(Vehicule v, FireDto fi) {


		System.out.println("-- Debut Mission --");
	
		//Create a Runnable is charge of executing cyclic actions 
		Mission mission = new Mission(v, fi);
				
		// A Runnable is held by a Thread which manage lifecycle of the Runnable
		Thread thread  = new Thread(mission);
				
		// The Thread is started and the method run() of the associated DisplayRunnable is launch
		thread.start();
		System.out.println("-- Thread launched --" + v);
		
		return true;
	}
}
