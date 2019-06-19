package view.GamePanes;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.MyScene;

public class EndPane extends Pane{
	//Joery
	
	private MyScene myScene;
	
	public EndPane(MyScene myScene) {
		this.myScene = myScene;
		
		setUp();
	}

	private void setUp() {
		
		Button backToGame = new Button();
		backToGame.setOnAction(e -> myScene.setMenuPane());
		
		this.setPrefSize(GamePane.windowMaxWidth, GamePane.windowMaxHeight);

		this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
		this.getChildren().addAll(backToGame);
		
	}
}
