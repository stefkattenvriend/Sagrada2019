package model;

public class PlayerModel {
	
	// instance variables
	private int payStones;
	private int seqnr; //dit is later voor de volgorde van de beurten
	private int playerid;
	private String username;
	private int gameid;
	private String status;
	private boolean isCurrentPlayer;
	private String color;
	private int patid; //patterncard_idpatterncard
	private int score;
	
	public PlayerModel()
	{
		
	}
	
	
	public int getPlayerId()
	{
		return playerid;
	}
	
	public void setPlayerId(int playerid)
	{
		this.playerid = playerid;
	}
	
	public void setPayStones(int payStones)
	{
		this.payStones = payStones;
	}
	
	public int getPayStones()
	{
		return payStones;
	}

	public boolean isCurrentPlayer() 
	{
		return isCurrentPlayer;
	}

	public void setCurrentPlayer(boolean isCurrentPlayer) 
	{
		this.isCurrentPlayer = isCurrentPlayer;
	}
	
	public String getObjectiveColor()
	{
		return color;
	}
	

}
