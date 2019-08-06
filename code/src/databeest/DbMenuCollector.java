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
	
	public ArrayList<String> getPlayerStatus(String gameID, String currentAccount){
		return databeest.getPlayerStatus(gameID, currentAccount);
	}
	
	public ArrayList<String> getPlayersInGame(int gameID, String currentAccount){
		return databeest.getPlayersInGame(gameID, currentAccount);
	}
	
	public int getPlayerID(String gameID, String currentAccount){
		return databeest.getPlayerID(gameID, currentAccount);
	}
	
	public ArrayList<Integer> startedGames(String username) {
		return databeest.getStartedGames(username);
	}
	
	public ArrayList<Integer> waitedGames(String username) {
		return databeest.getWaitedGames(username);
	}
	
	public ArrayList<String> getPlayers(){
		return databeest.getPlayers();
	}
	
	public String getWinner(int gameID) {
		return databeest.getWinner(gameID);
	}
	
	public ArrayList<Integer> getFinishedGames(String username){
		return databeest.getFinishedGames(username);
	}
	
	
	
}
