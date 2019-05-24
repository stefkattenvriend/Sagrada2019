package controller;

import model.GameModel;

public class GameUpdateController {
	//beurt
	//chat
	//
	
	private MasterController mc;
	private GameModel gm;
	
	public GameUpdateController(MasterController mc) {
		this.mc = mc;
	}

	public void testRun() {
		if(gm != null) {
			gm.updateRound();
		}
	
		
		
	}

	public void setGameModel(GameModel gm) {
		
		this.gm = gm;
	}
}
