package com.fighter.controller.direction;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fighter.brain.mission.LogMission.LogMission;

@Service
public class DirectionService {

	private Map<Integer, JsonNode> paths;
	
	DirectionService() {
		this.paths = LogMission.getPath();
	}
	
	public JsonNode findPath(Integer idVehicule) {
		return this.paths.get(idVehicule);
	}
	
}
