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
	
	
	public PlayerController(DbPlayerCollector dbPlayerCollector, GameModel gm, ToolCardController tcc)
	{
		this.tcc = tcc;
		playerModel = new PlayerModel(dbPlayerCollector, gm, this.tcc);
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
