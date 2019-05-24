package controller;

import databeest.DataBaseApplication;
import view.MyScene;

public class MenuController {
	
	//instance
	private MyScene myScene;
	private MasterController mc;
	
	public MenuController(MyScene myScene, MasterController mc) {
		this.myScene = myScene;
		this.mc = mc;
		
	}
	
	public void loadGame() {
		mc.getGameController().createGameModel(1);//gehardcode, moet later anders zijn aan game ID gebonden aan button
		myScene.setGamePane();
	}
	
	public void createGame() {
		//neemt username mee
	}
	
	public DataBaseApplication getDataBaseApplication() {
		return mc.getDatabaseApplication();
	}
}
