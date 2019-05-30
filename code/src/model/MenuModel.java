package model;

import java.util.ArrayList;

import controller.MasterController;
import databeest.DbMenuCollector;

public class MenuModel {

	private DbMenuCollector menuCollector;

	// runtime
	private ArrayList<String> challengers;
	private ArrayList<String> invitedGameIDs;

	// update
//	private ArrayList<String> challengers_NEW;
//	private ArrayList<String> invitedGameIDs_NEW;

	private String currentAccount;

	public MenuModel(MasterController masterController) {
		this.menuCollector = masterController.getDbMenuCollecter();
		this.currentAccount = masterController.getLoginController().getCurrentAccount();
		setInvitedGameIDs();
		setChallengers();
	}

	// get & set challengers [MenuInvitePane]
	public void setChallengers() {
		this.challengers = menuCollector.getChallanger(currentAccount);
	}

	public ArrayList<String> getChallengers() {
		return challengers;
	}

	// get & set invitedGamesIDs [MenuInvitePane] (OLD)
	public void setInvitedGameIDs() {
		this.invitedGameIDs = menuCollector.getInviteGameID(currentAccount);
	}

	public ArrayList<String> getInvitedGameIDs() {
		return invitedGameIDs;
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
	}

}
