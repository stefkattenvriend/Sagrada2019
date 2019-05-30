package controller;

import java.util.ArrayList;

import databeest.DbDieCollector;
import model.DiceModel;
import view.GamePanes.DicePane;

public class DiceController {
	
	ArrayList<DiceModel> dmodels;
	ArrayList<DicePane> dpanes = new ArrayList<DicePane>();
	private DbDieCollector ddc;
	
	public DiceController(DbDieCollector ddc, int gameid) {
		this.ddc = ddc;
		setup(gameid);
	}
	
	
	
	private void setup(int gameid) {
		createDieModel(gameid);
		createDicePane();
	}



	public void createDieModel(int gameid) {
		dmodels = ddc.getDiceModdelArray(gameid);
	}
	
	public void createDicePane() {
		DicePane dp;
		
		for (int i = 0; i < dmodels.size(); i++) {
			dp = new DicePane(40, dmodels.get(i).getDieColor(), dmodels.get(i).getEyes());
			dpanes.add(dp);
			}
	}
	
	public DicePane getDicePane(int i) {
		return dpanes.get(i);
	}
	
	public DiceModel getDiceModel(int i) {
		return dmodels.get(i);
	}
	
	public ArrayList<DiceModel> getDMAL() {
		return dmodels;
	}
}
