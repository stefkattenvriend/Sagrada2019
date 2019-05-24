package model;

import controller.DiceController;
import databeest.DbGameCollector;
import databeest.DbPlayerCollector;
import helpers.DiceHolderType;

public class GameModel {
	
	//instance 
	private int gameRound = 0; //nu hardcoded, in toekomst wordt dit uit database gehaald.
	private int gameid;
	private boolean inGame;
	private int amountOfPlayers;
	private DbGameCollector dgc;
	private DbPlayerCollector dpc;
	private PlayerModel[] pma;
	private DiceController dc;
	private DiceModel[] dm;
	
	public GameModel(int gameid, DbGameCollector dgc, String username, DbPlayerCollector dpc, int amountOfPlayers) {
		this.gameid = gameid;
		this.dgc = dgc;
		this.dpc = dpc;
		this.amountOfPlayers = amountOfPlayers;
		inGame = true;
		pma = new PlayerModel[amountOfPlayers];
		//addPlayerModel(username);


		//TODO add enemies
		
	}
	
	public void leaveGame() {
		inGame = false;
	}
	
	public int getGameRound() {
		return gameRound;
	}

	public void updateRound() {
		dgc.getRoundNumber(gameid);
	}
	
	public void addPlayer(int i, String username)
	{
		pma[i] = new PlayerModel();
		if(i == 0) {
			pma[i].setDht(DiceHolderType.PLAYERWINDOW);
		}
		if(i == 1) {
			pma[i].setDht(DiceHolderType.ENEMY1);
		}
		if(i == 2) {
			pma[i].setDht(DiceHolderType.ENEMY2);
		}
		if(i == 3) {
			pma[i].setDht(DiceHolderType.ENEMY3);
		}
		pma[i].setUsername(username);
		pma[i].setGameid(gameid);
		pma[i].getDatabaseInfo(dpc);

	}
	
	public PlayerModel getPlayerModel(DiceHolderType type) {
		PlayerModel pm = null;
		for (int i = 0; i < pma.length; i++) {
			if(pma[i].getDht() == type) {
				pm = pma[i];
				return pm;
			}
		}
		
		return pm;
	}
	
	public void addPlayerModel(String username) {
		pma[0] = new PlayerModel();
		pma[0].setDht(DiceHolderType.PLAYERWINDOW);
		pma[0].setUsername(username);
		pma[0].setGameid(gameid);
		pma[0].getDatabaseInfo(dpc);
	}
	
	public void addDiceModels() {
		for (int i = 1; i < 91; i++) {
			
		}
	}
	
	

	public int getGameId() {
		return gameid;
	}
}
