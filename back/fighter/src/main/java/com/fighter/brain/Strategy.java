package com.fighter.brain;

import java.util.List;

import com.fighter.brain.mission.Mission;
import com.fighter.brain.mission.MissionFeu;
import com.fighter.brain.mission.MissionRavitaillement;
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
		this.caserne = casernes; 
		this.feu = feu;
		this.vehicules = vehicules;
	}
		
	
	public boolean assignVehicule() {
		
		boolean res = false;
		
		int max = -1;
		Vehicule vehiculeMax = null;
		
		for ( Vehicule vehicule : LogMission.ownVehicule ) {
			Path path = new Path( vehicule.getVehiculeDto().getLon(), vehicule.getVehiculeDto().getLat(), this.feu.getLon(), this.feu.getLat());
			int score = vehicule.calculScore(this.feu, vehicule.findFacility(), path);
			//System.out.println("Score :" + score);
			//System.out.println("Busy :" + vehicule.getMission() + " " + vehicule);
		
			if (score > max && !vehicule.getMission()) {
				max = score;
				vehiculeMax = vehicule;
			}
			if (score == -1) {
				missionRavitaillement(vehicule, vehicule.findFacility());
			}
		}
		
		if (vehiculeMax != null) {
			this.missionFeu(vehiculeMax, feu);
			System.out.println("++++++++++++ New assignement +++++++++++++++");
			System.out.println("------------------------- MISSION --------------------------------");
			res = true;
		}
		return res;
	}
	
	private void missionFeu(Vehicule v, FireDto fi) {
		Mission mission = new MissionFeu(v, fi.getLon(), fi.getLat(),fi);
		launchMission(mission);
	}
	private void missionRavitaillement(Vehicule v, FacilityDto f) {
		Mission mission = new MissionRavitaillement(v, f.getLon(), f.getLat());
		launchMission(mission);
	}
	
	private boolean launchMission(Mission mission) {
		// A Runnable is held by a Thread which manage lifecycle of the Runnable
		Thread thread  = new Thread(mission);
				
		// The Thread is started and the method run() of the associated DisplayRunnable is launch
		thread.start();
		System.out.println("-- Thread launched --");
		
		return true;
	}
}
