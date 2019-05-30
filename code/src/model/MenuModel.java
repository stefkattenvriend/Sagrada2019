package model;

import java.util.ArrayList;

import controller.MasterController;
import databeest.DbMenuCollector;

public class MenuModel {

	private DbMenuCollector menuCollector;

	// runtime
	private ArrayList<String> challengers;
	private ArrayList<String> invitedGameIDs;
	private String currentAccount;
	private ArrayList<Integer> activePlayerGames;
	private ArrayList<Integer> waitedPlayerGames;
	
	public MenuModel(MasterController masterController) {
		this.menuCollector = masterController.getDbMenuCollecter();
		this.currentAccount = masterController.getLoginController().getCurrentAccount();
		setInvitedGameIDs();
		setChallengers();
	}

	// get & set invitedGamesIDs [MenuInvitePane] (OLD)
	public void setInvitedGameIDs() {
		this.invitedGameIDs = menuCollector.getInviteGameID(currentAccount);
	}

	public ArrayList<String> getInvitedGameIDs() {
		return invitedGameIDs;
	}

	// get & set challengers [MenuInvitePane]
	public void setChallengers() {
		this.challengers = menuCollector.getChallanger(currentAccount);
	}

	public ArrayList<String> getChallengers() {
		return challengers;
	}

	// UPDATE METHODS
	public ArrayList<String> getInvitedGameIDsUpdate() {

		return menuCollector.getInviteGameID(currentAccount);
	}

	public ArrayList<String> getChallengersUpdate() {
		return menuCollector.getChallanger(currentAccount);
	}

	public static ArrayList<Integer> getActivePlayerGames() {

		return null;
	public void setActivePlayerGames(ArrayList<Integer> activePlayerGames) {
		this.activePlayerGames = activePlayerGames;
	}
	
	public ArrayList<Integer> getActivePlayerGames() {
		return activePlayerGames;
	}

	public void setWaitedPlayerGames(ArrayList<Integer> waitedPlayerGames) {
		this.waitedPlayerGames = waitedPlayerGames;
	}

	public ArrayList<Integer> getWaitedPlayerGames() {
		return waitedPlayerGames;
	}

}
