package com.fighter.model;

import java.util.ArrayList;
import java.util.List;

public class ConstantsModel {

	private static final List<Integer> listFacility = initList();
			
	private static List<Integer> initList(){
		List<Integer> listFacility = new ArrayList();
		listFacility.add(339);
		return listFacility;
	}
	
	public static List<Integer> getListFacility() {
		return ConstantsModel.listFacility;
	}
}
