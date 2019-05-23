package view.MenuPanes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuDropdown extends VBox{
	
	private Button btn;
	private boolean clicked = false;
	private FlowPane gameInfoPane;
	
	public MenuDropdown(String nr) {
		createInfoPane();
		createButton(nr);
		getChildren().addAll(btn);
	}
	
	private void createButton(String nr) {

		btn = new Button(nr);
		btn.setMinSize(MenuPane.paneWidth - 60, 40);
		btn.setMaxSize(MenuPane.paneWidth - 60, 40);
		btn.setOnAction(e -> setUp(gameInfoPane));
	}

	private void setUp(Pane gameInfo) {
		
		if(!clicked) {
			getChildren().addAll(gameInfo);
			clicked = true;
		} else if (clicked) {
			getChildren().clear();
			getChildren().addAll(btn);
			clicked = false;
		}	
	}
	
	private void createInfoPane() {
		Button loadGame = new Button("Load game");
		loadGame.setMinSize(160, 40);
		loadGame.setMaxSize(160, 40);
//		Label text = new Label("test text");
		gameInfoPane = new FlowPane();
		gameInfoPane.setMinSize(MenuPane.paneWidth - 60, 60);
		gameInfoPane.setMaxSize(MenuPane.paneWidth - 60, 60);
		gameInfoPane.setAlignment(Pos.BOTTOM_RIGHT);
		gameInfoPane.getChildren().add(loadGame);
	}
}
