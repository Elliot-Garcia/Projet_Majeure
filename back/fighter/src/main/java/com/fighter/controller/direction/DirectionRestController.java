package com.fighter.controller.direction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin

@RestController
public class DirectionRestController {
	
	@Autowired
	private final DirectionService sDirection;

	DirectionRestController(DirectionService serviceDirection) {
		this.sDirection = serviceDirection;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/direction")
	public String listDeck(@RequestParam Integer idVehicule) {
		return sDirection.findPath(idVehicule);
	}
	
}
