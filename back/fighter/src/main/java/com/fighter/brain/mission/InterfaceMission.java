package com.fighter.brain.mission;

public interface InterfaceMission {

	public void missionAllerAuFeu(double lonFeu, double latFeu);
	public void missionRavitaillementEssence(double lonRav, double latRav);
	public void missionRavitaillementExtincteur(double lonRav, double latRav);
	public void missionAnnule();
	
}
