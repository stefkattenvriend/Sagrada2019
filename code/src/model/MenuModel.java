package model;

import java.util.ArrayList;

import controller.MasterController;
import databeest.DbGameCollector;
import databeest.DbMenuCollector;

public class MenuModel {

	private DbMenuCollector menuCollector;
	private DbGameCollector gameCollector;

	// runtime
	private String currentAccount;
	private ArrayList<String> challengers;
	private ArrayList<String> invitedGameIDs;
	private ArrayList<Integer> activePlayerGames;
	private ArrayList<Integer> waitedPlayerGames;
	
	public MenuModel(MasterController masterController) {
		this.menuCollector = masterController.getDbMenuCollecter();
		this.gameCollector = masterController.getDbGameCollector();
		this.currentAccount = masterController.getLoginController().getCurrentAccount();
		setInvitedGameIDs();
		setChallengers();
		setActiveGames();
		setWaitedGames();
		
	}

	public void setInvitedGameIDs() {
		this.invitedGameIDs = menuCollector.getInviteGameID(currentAccount);
	}

	public ArrayList<String> getInvitedGameIDs() {
		return invitedGameIDs;
	}

	public void setChallengers() {
		this.challengers = menuCollector.getChallanger(currentAccount);
	}

	public ArrayList<String> getChallengers() {
		return challengers;
	}

	
	// START UPDATE METHODS
	
	public ArrayList<String> getInvitedGameIDsUpdate() {

		return menuCollector.getInviteGameID(currentAccount);
	}

	public ArrayList<String> getChallengersUpdate() {
		return menuCollector.getChallanger(currentAccount);
	}
	
	public ArrayList<Integer> getActiveGamesUpdate(){
		return gameCollector.startedGames(currentAccount);
	}
	
	public ArrayList<Integer> getWaitedGamesUpdate(){
		return gameCollector.waitedGames(currentAccount);
	}
	
	// END UPDATE METHODS

	
	public void setActiveGames(){
		this.activePlayerGames = gameCollector.startedGames(currentAccount);
	}
	
	public ArrayList<Integer> getActiveGames(){
		return activePlayerGames;
	}
	
	public void setWaitedGames(){
		this.waitedPlayerGames = gameCollector.waitedGames(currentAccount);
	}
	
	public ArrayList<Integer> getWaitedGames(){
		return waitedPlayerGames;
	}
	
	public ArrayList<String> getPlayerStatus(String gameID){
		return menuCollector.getPlayerStatus(gameID, currentAccount);
	}
	
	public ArrayList<String> getPlayersInGame(String gameID){
		return menuCollector.getPlayersInGame(gameID, currentAccount);
	}
	
	public int getPlayerID(String gameID) {
		return menuCollector.getPlayerID(gameID, currentAccount);
	}

}
