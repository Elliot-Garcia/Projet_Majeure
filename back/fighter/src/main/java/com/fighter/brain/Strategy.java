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
	
	/**
	 * check is there are some avaliable vehicle for a mission
	 * @return true : yes, false : no
	 */
	private boolean isVehicleDispo() {
		for ( Vehicule vehicule : LogMission.ownVehicule ) {
			if (!vehicule.getMission()) {
				return true;
			}
		}
		return false;
	}
		
	/**
	 * assigne mission to a vehicule
	 * @return ture : done
	 */
	public boolean assignVehicule() {
		
		if (!this.isVehicleDispo()) {
			return false;
		}
		
		boolean res = false;
		
		int max = -1;
		Vehicule vehiculeMax = null;
		
		for ( Vehicule vehicule : LogMission.ownVehicule ) {

			Path path = new Path( vehicule.getVehiculeDto().getLon(), vehicule.getVehiculeDto().getLat(), this.feu.getLon(), this.feu.getLat());
			int score = vehicule.calculScore(this.feu, vehicule.findFacility(), path);
			
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
	
	/**
	 * launch a mission with fire mission setings
	 * @param v vehicle
	 * @param fi fireDto
	 */
	private void missionFeu(Vehicule v, FireDto fi) {
		Mission mission = new MissionFeu(v, fi.getLon(), fi.getLat(),fi);
		launchMission(mission);
	}
	
	/**
	 * Launch a mission with suplise mission setings
	 * @param v
	 * @param f
	 */
	private void missionRavitaillement(Vehicule v, FacilityDto f) {
		Mission mission = new MissionRavitaillement(v, f.getLon(), f.getLat());
		launchMission(mission);
	}
	
	/**
	 * Launch the misson in a Thread
	 * @param mission
	 * @return true
	 */
	private boolean launchMission(Mission mission) {
		// A Runnable is held by a Thread which manage lifecycle of the Runnable
		Thread thread  = new Thread(mission);
				
		// The Thread is started and the method run() of the associated DisplayRunnable is launch
		thread.start();
		System.out.println("-- Thread launched --");
		
		return true;
	}
}
