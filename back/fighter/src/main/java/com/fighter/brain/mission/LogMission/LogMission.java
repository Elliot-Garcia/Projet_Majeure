package com.fighter.brain.mission.LogMission;

import java.util.List;

import com.fighter.model.dto.FireDto;

public class LogMission {
	public static List<FireDto> fire;
	
	public static boolean debutMission( FireDto fire ) {
		return LogMission.fire.add(fire);
	}
	
	public static boolean finMission( FireDto fire ) {
		return LogMission.fire.remove( fire );
	}
}