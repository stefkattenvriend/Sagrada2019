package databeest;

//Stef
public class DbPlayerCollector {
	
	//instance variables
	private DataBaseApplication dbApplication;

	public DbPlayerCollector(DataBaseApplication dbApplication)
	{
		this.dbApplication = dbApplication;
	}
	
	public int amountOfPaystones(int playerId)
	{
		int amount = 0;
		amount = dbApplication.getPlayerPayStones(playerId);
		
		return amount;
	}
	
}
