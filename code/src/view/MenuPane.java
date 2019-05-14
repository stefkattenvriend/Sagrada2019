package view;

import javafx.scene.layout.BorderPane;
import view.GamePanes.GamePane;
import javafx.scene.control.Button;

public class MenuPane extends BorderPane {
	
	//constants
	public final static double windowMaxWidth = 1280;
	public final static double windowMaxHeight = 800;
	
	//instance 
	private MyScene myScene;
	private GamePane gamePane;

	public MenuPane(MyScene myScene, GamePane gamePane) {
		this.myScene = myScene;
		this.gamePane = gamePane;
		setUp();
	}

	private void setUp() {
		setScreenSize();
		setStartButton();
		
	}
	
	public void setScreenSize() {
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
	}
	
	private void setStartButton() {
		Button button = new Button("start");
		button.setPrefSize(80, 40);
		button.setOnAction(e -> myScene.setNewRoot(gamePane));
		setCenter(button);
	}

}
