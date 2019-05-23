package controller;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import model.DiceModel;
import view.GamePanes.DicePane;

public class DiceController {
	
	ArrayList<DiceModel> dmodels = new ArrayList<DiceModel>();
	ArrayList<DicePane> dpanes = new ArrayList<DicePane>();
	
	
	
	public DiceModel createDieModel(int color, int eyes) {
		Color paint;
		DiceModel dm;
		
		switch(color) {
		case 1:
			paint = Color.RED;
			break;
			
		case 2:
			paint = Color.BLUE;
			break;
			
		case 3:
			paint = Color.GREEN;
			break;
			
		case 4:
			paint = Color.YELLOW;
			break;
			
		case 5:
			paint = Color.PURPLE;
			break;
			
		default:
			paint = Color.WHITE;
			
		}
		
		dm = new DiceModel(40, paint, eyes, false);
		
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
