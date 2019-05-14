package model;

public class PlayerModel {
	
	// instance variables
	private int payStones;
	private int seqnr; //dit is later voor de volgorde van de beurten
	
	
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
