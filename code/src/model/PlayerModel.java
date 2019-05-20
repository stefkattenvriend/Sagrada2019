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
		this.setPayStones(4); // Dit is om te testen
	}
	
	public void setPayStones(int payStones)
	{
		this.payStones = payStones;
	}
	
	public int getPayStones()
	{
		return payStones;
	}

}
