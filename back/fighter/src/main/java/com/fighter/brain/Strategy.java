package com.fighter.brain;

import com.fighter.brain.mission.Mission;
import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;
import com.fighter.model.modelVehicule.Vehicule;


public class Strategy {
	private FireDto feu;
	private FacilityDto[] caserne;
	private VehiculeDto[] vehicule;
	
	public Strategy(FireDto feu, FacilityDto[] caserne, VehiculeDto[] vehicule) {
		this.caserne = caserne; 
		this.feu = feu;
		this.vehicule = vehicule;
	}
	
	public int calculScore(FireDto fi, FacilityDto[] fa, VehiculeDto[] v) {
		
		int score = 20;
		String type_feu = fi.getType();
		for(int i = 0; i<v.length; i++) {
			if (v[i].getFacilityRefID() == 84) {
				//calcul score
				if(score>10) {
					launchMission(v[i], fi);
					break; //Pour le moment, en lancer qu'un seul
				}
				
			};
		}

		//if(res>10) {
		//	launchMission(v, fi);
		//}
		return score;
	}
	
	public boolean launchMission(VehiculeDto v, FireDto fi) {
		//mission(v, fi); //Voir la fonction à appeler
		//Envoie vehicule et feu à la mission
		InterfaceVehicule vehicule = new Vehicule(v);
		Mission mission = new Mission(vehicule, fi);
		mission.debutMission();

		return true;
	}
}
