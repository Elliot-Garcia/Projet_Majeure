package com.fighter.brain.mission.LogMission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fighter.model.ConstantsModel;
import com.fighter.model.calcul.Path;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.fighter.model.modelVehicule.Vehicule;

public class LogMission {
	
	private static List<FireDto> fire  = new ArrayList();
	public  static List<Vehicule> ownVehicule = new ArrayList();
	public  static Map<Integer, JsonNode> directions = new HashMap();
	
	public static boolean debutMission( VehiculeDto vehicule, FireDto fire ) {
		LogMission.fire.add( fire );
		return true;
	}
	public static void debutMissionPath( VehiculeDto vehicule, Path chemin) {
		LogMission.directions.put(vehicule.getId(), chemin.getPoints());
	}

	public static boolean finMission( VehiculeDto vehicule, FireDto fire ) {
		LogMission.directions.remove(vehicule.getId());
		return LogMission.fire.remove( fire );
	}
	public static void finMissionPath( VehiculeDto vehicule) {
		LogMission.directions.remove(vehicule.getId());
	}

	public static List<FireDto> getFire() {
		return fire;
	}
	
	public static Map<Integer, JsonNode> getPath() {
		return directions;
	}
	
	public static void initOwnVehicle( List<VehiculeDto> vehicules ) {

		LogMission.ownVehicule = new ArrayList();
		for ( VehiculeDto vehiculeDto : vehicules ) {
			if( ConstantsModel.getListFacility().contains( vehiculeDto.getFacilityRefID() ) ) {
				Vehicule vehicule = new Vehicule(vehiculeDto);
				LogMission.ownVehicule.add(vehicule);
			}
		}
	}
	
}