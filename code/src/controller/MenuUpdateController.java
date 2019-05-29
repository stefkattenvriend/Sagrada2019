package controller;

import databeest.DataBaseApplication;
import view.MenuPanes.MenuPane;

public class MenuUpdateController {
	
	private MasterController mc;
	private DataBaseApplication databeest;
	private MenuPane menuPane;
	private boolean update = false;
	private MenuController menuController;
	
	public MenuUpdateController(MasterController mc) {
		this.mc = mc;	
//		this.menuPane = menuPane;
		databeest = mc.getDatabaseApplication();
		this.menuController = mc.getMenuController();
		
		
	}
	
	public void checker() {
		menuController.checkForChange();
	}
	
}
