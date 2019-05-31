package controller;

import databeest.DbPlayerCollector;
import javafx.scene.paint.Color;
import model.PlayerModel;

public class PlayerController {
	
	//instance variables
	private PlayerModel playerModel;
	private DbPlayerCollector dbPlayerCollector;
	
	public PlayerController(DbPlayerCollector dbPlayerCollector)
	{
		playerModel = new PlayerModel(dbPlayerCollector);
		this.dbPlayerCollector = dbPlayerCollector;
	}
	
	public void setPlayerId(int playerid)
	{
		playerModel.setPlayerId(playerid);
	}
	
	public void setCurrentPlayer(Boolean current)
	{
		playerModel.setCurrentPlayer(current);
	}

	public boolean isCurrentPlayer()
	{
		return playerModel.isCurrentPlayer();
	}
	
	public Color getPersonalObjective()
	{
		System.out.println("colorje = " + playerModel.getObjectiveColor());
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
}
