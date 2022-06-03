package com.fighter.brain.mission.LogMission;

import java.util.ArrayList;
import java.util.List;

import com.fighter.model.dto.FireDto;

public class LogMission {
	
	private static List<FireDto> fire  = new ArrayList();
	
	public static boolean debutMission( FireDto fire ) {
		System.out.println(LogMission.fire.size());
		LogMission.fire.add( fire );
		System.out.println("OUI");
		return true;
	}

	public static boolean finMission( FireDto fire ) {
		return LogMission.fire.remove( fire );
	}

	public static List<FireDto> getFire() {
		return fire;
	}
}