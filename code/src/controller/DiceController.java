package controller;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import model.DiceModel;
import view.GamePanes.DicePane;

public class DiceController {
	
	ArrayList<DiceModel> dmodels = new ArrayList<DiceModel>();
	ArrayList<DicePane> dpanes = new ArrayList<DicePane>();
	
	
	
	public DiceModel createDieModel(Color color, int eyes, int size) {
		DiceModel dm;
		
		
		
		dm = new DiceModel(size, color, eyes);
		
		dmodels.add(dm);
		
		return dm;
	}
	
	public DicePane createDicePane(DiceModel dm) {
		DicePane dp;
		
		dp = new DicePane(dm.getSize(), dm.getPaint(), dm.getEyes());
		
		dpanes.add(dp);
		
		return dp;
	}
	
	public ArrayList<DiceModel> getDMAL() {
		return dmodels;
	}
}
