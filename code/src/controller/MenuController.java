package controller;

import view.MyScene;

public class MenuController {
	
	//instance
	private MyScene myScene;
	private MasterController mc;
	
	public MenuController(MyScene myScene, MasterController mc) {
		this.myScene = myScene;
		this.mc = mc;

	}
	
	public void setNewRoot() {
		mc.getGameController().createGameModel(1);//gehardcode, moet later anders zijn aan game ID gebonden aan button
		myScene.setGamePane();
	}
}
