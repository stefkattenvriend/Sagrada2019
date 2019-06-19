package controller;

import databeest.DbPlayerCollector;
import javafx.scene.paint.Color;
import model.GameModel;
import model.PlayerModel;

public class PlayerController {
	
	//instance variables
	private PlayerModel playerModel;
	private DbPlayerCollector dbPlayerCollector;
	private ToolCardController tcc;
	private CardsController cc;
	
	
	public PlayerController(DbPlayerCollector dbPlayerCollector, GameModel gm, ToolCardController tcc, CardsController cc)
	{
		this.tcc = tcc;
		this.cc = cc;
		playerModel = new PlayerModel(dbPlayerCollector, gm, this.tcc, cc);
		this.dbPlayerCollector = dbPlayerCollector;
	}
	
	public void setPlayerId(int playerid)
	{
		playerModel.setPlayerId(playerid);
	}
	
	public void setCurrentPlayer(Boolean current)
	{
		playerModel.setCurrentPlayer(current);
		System.out.println("update player to: " + current);
	}

	public boolean isCurrentPlayer()
	{
		return playerModel.isCurrentPlayer();
	}
	
	public Color getPersonalObjective()
	{
		return playerModel.getObjectiveColor();
	}
	
	public int getPayStones()
	{
		
		playerModel.setPayStones(dbPlayerCollector.amountOfPaystones(playerModel.getPlayerId()));
		return playerModel.getPayStones();
	}
	
	public int getPlayerID() {
		return playerModel.getPlayerId();
	}
	
	public String getPlayerName() {
		return playerModel.getUsername();
	}
	
	public void setScore(int playerid) {
		dbPlayerCollector.setScore(playerid, playerModel.getScore());
	}
	
	public PlayerModel getPM() {
		return playerModel;
	}
}
