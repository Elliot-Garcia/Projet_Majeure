package com.fighter.brain.mission.LogMission;

import java.util.ArrayList;
import java.util.List;

import com.fighter.model.ConstantsModel;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.fighter.model.modelVehicule.Vehicule;

public class LogMission {
	
	private static List<FireDto> fire  = new ArrayList();
	
	public  static List<Vehicule> ownVehicule = new ArrayList();
	
	public static boolean debutMission( FireDto fire ) {
		LogMission.fire.add( fire );
		return true;
	}

	public static boolean finMission( FireDto fire ) {
		return LogMission.fire.remove( fire );
	}

	public static List<FireDto> getFire() {
		return fire;
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