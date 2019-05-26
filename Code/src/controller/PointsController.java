package controller;

import java.util.ArrayList;

import model.DiceHolderModel;
import model.DiceModel;
import view.GamePanes.PersonalObjectiveCardPane;

//Tjess Wjest
public class PointsController {
	private int personalObjectivePoints;
	private int sharedObjectivePoints;
	private int totalPoints;
	ArrayList<DiceModel> dice;
//	private PersonalObjectiveCardPane pocp;

	
	public PointsController(ArrayList<DiceHolderModel> diceHolder) {
		for(int i=0; i<diceHolder.size(); i++) {
			dice.add(diceHolder.get(i).getDie());
		}
//		getPersonalObjectivePoints(dice);
		getSharedObjectivePoints();
		getTotalPoints();
	}
	
//	private void getPersonalObjectivePoints(ArrayList<DiceModel> dice) {
//		for(int i = 0; i<dice.size(); i++) {
//			if(dice.get(i).getPaint().equals(pocp.getColor())) {
//				personalObjectivePoints = personalObjectivePoints + dice.get(i).getEyes();
//			}
//		}
//		
//	}
	
	private void getSharedObjectivePoints() {
		
	}
	
	private int getTotalPoints() {
		totalPoints = personalObjectivePoints + sharedObjectivePoints; // + betaalstenen - lege plekken
		return totalPoints;
	}
}
