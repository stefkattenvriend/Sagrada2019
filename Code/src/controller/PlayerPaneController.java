package controller;

import view.MyScene;
import view.GamePanes.PlayerPane;

public class PlayerPaneController {
	private boolean number8 = false;	//check if toolcard 8 is in effect
	
	public void menuAction(MyScene myScene, TurnController tc, GameController gc) {
		if(number8) {
			gc.getToolCardController().setAllowed(false);
		}
		myScene.goToMenuPane();
		tc.stopThread();
		gc.setGameRunning(false);
//		gc.setGenerateOffer(true);
	}
	
	public void pass(TurnController tc, PlayerPane pp, GameController gc) {
		tc.updatePass(); 
		tc.updateSeqnrAndTurn();
		pp.setPassVisible();
		if(number8) {
			gc.getToolCardController().setAllowed(false);
		}
	}
	
	public void setNumber8(boolean i) {
		number8 = i;
	}
}
