package model;

import java.util.ArrayList;

import controller.MasterController;
import databeest.DbMenuCollector;

public class MenuModel {

	private DbMenuCollector menuCollector;
	
	private ArrayList<String> challengers;
	private String currentAccount;
	
	public MenuModel(MasterController masterController) {
		this.menuCollector = masterController.getDbMenuCollecter();
		this.currentAccount = masterController.getLoginController().getCurrentAccount();
	}
	
	public void setChallengers() {
		this.challengers = menuCollector.getChallanger(currentAccount);
	}
	
}
