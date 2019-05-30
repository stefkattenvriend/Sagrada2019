package model;

import java.util.ArrayList;

import controller.MasterController;
import databeest.DbMenuCollector;

public class MenuModel {

	private DbMenuCollector menuCollector;
	
	private ArrayList<String> challengers;
	private String currentAccount;
	private ArrayList<Integer> activePlayerGames;
	private ArrayList<Integer> waitedPlayerGames;
	
	public MenuModel(MasterController masterController) {
		this.menuCollector = masterController.getDbMenuCollecter();
		this.currentAccount = masterController.getLoginController().getCurrentAccount();
		
	}
	
	public void setChallengers() {
		this.challengers = menuCollector.getChallanger(currentAccount);
	}

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
