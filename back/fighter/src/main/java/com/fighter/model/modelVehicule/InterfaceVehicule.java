package com.fighter.model.modelVehicule;

import com.fighter.model.calcul.Path;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;

public interface InterfaceVehicule {
	
	public void missionTrue();
	public void missionFalse();
	public boolean getMission();
	public void deplacement(double lon, double lat, float distance);
	public int calculScore(FireDto fire, FacilityDto facility, Path path);
	public void gestionCrew();
	public void gestionConsommationEssence(float distance);
	public FacilityDto findFacility();
	public VehiculeDto getVehiculeDto();
	
}
