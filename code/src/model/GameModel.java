package model;

public class GameModel {
	
	//instance 
	private int gameRound = 1; //nu hardcoded, in toekomst wordt dit uit database gehaald.
	
	public GameModel() {
		
	}
	
	public int getGameRound() {
		return gameRound;
	}
}
