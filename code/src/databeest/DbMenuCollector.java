package databeest;

import java.util.ArrayList;

public class DbMenuCollector {
	
	//instance
	private DataBaseApplication databeest;
	
	//constructor
	public DbMenuCollector(DataBaseApplication dataBaseApplication) {
		this.databeest = dataBaseApplication;
	}
	
	//getters
	public ArrayList<String> getChallanger(String currentAccount) {
		
		return databeest.getChallenger(currentAccount);
	}
	
	public ArrayList<String> getInviteGameID(String currentAccount){
		
		return databeest.getInviteGameID(currentAccount);
	}	
	
	
}
