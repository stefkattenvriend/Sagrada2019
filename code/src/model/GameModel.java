package model;

public class GameModel {
	
	//instance 
	private int gameRound = 1; //nu hardcoded, in toekomst wordt dit uit database gehaald.
	private int gameid;
	
	public GameModel(int gameid) {
		
	}
	
	public int getGameRound() {
		return gameRound;
	}
}
