package view.MenuPanes;

import controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuDropdown extends VBox {

	private Button btn;
	private boolean clicked = false;
	private FlowPane gameInfoPane;
	private MenuController menuController;
	private boolean yesOrNo;

	public MenuDropdown(MenuController menuController, boolean loadbutton, String btnName, boolean yesOrNo) {
		this.menuController = menuController;
		createInfoPane(loadbutton);
		createButton(btnName);
		getChildren().add(btn);
		
		if (yesOrNo) {
			getChildren().clear();
			getChildren().addAll(btn, gameInfoPane);
			clicked = true;
		}
	}

	private void createButton(String btnName) {
		btn = new Button(btnName);
		btn.setMinSize(MenuPane.paneWidth - 60, 40);
		btn.setMaxSize(MenuPane.paneWidth - 60, 40);
		btn.setOnAction(e -> setUp());
	}

	private void setUp() {

		if (!clicked) {
			getChildren().clear();
			getChildren().addAll(btn, gameInfoPane);
			clicked = true;
		} else if (clicked) {
			getChildren().clear();
			getChildren().addAll(btn);
			clicked = false;
		}

	}

	private void createInfoPane(boolean button) {
		gameInfoPane = new FlowPane();
		gameInfoPane.setMinSize(MenuPane.paneWidth - 60, 60);
		gameInfoPane.setMaxSize(MenuPane.paneWidth - 60, 60);
		gameInfoPane.setAlignment(Pos.BOTTOM_RIGHT);
		gameInfoPane.setHgap(20);

		if (button) {
			Button loadGame = new Button("Load game");
			loadGame.setMinSize(160, 40);
			loadGame.setMaxSize(160, 40);
			loadGame.setOnAction(e -> menuController.setNewRoot());
			gameInfoPane.getChildren().add(loadGame);
		}
		
		if(yesOrNo) {
			RadioButton inviteBtn = new RadioButton();
			gameInfoPane.getChildren().add(inviteBtn);
		}

	}
}
