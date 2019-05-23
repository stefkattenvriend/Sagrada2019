package controller;

import view.MyScene;

public class MenuController {
	
	//instance
	private MyScene myScene;
	
	public MenuController(MyScene myScene) {
		this.myScene = myScene;

	}
	
	public void setNewRoot() {
		myScene.setGamePane();
	}
}
