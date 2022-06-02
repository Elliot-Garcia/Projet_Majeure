package com.fighter.model.modelVehicule;

import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.VehiculeDto;

public interface InterfaceVehicule {
	
	public boolean deplacement();
	public boolean ravitaillement();
	public void gestionCrew();
	public FacilityDto findFacilityById(int id);
	public VehiculeDto getVehiculeDto();
	
}
