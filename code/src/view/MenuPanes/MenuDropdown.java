package view.MenuPanes;

import controller.MenuController;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuDropdown extends VBox {// door joery

	private Button btn;
	private boolean clicked = false;
	private BorderPane gameInfoPane;
	private MenuController menuController;
	private boolean yesOrNo;
	private String username;
	private int totalPlayers;
	private boolean isChecked = false;
	private MenuPlayersPane menuPlayersPane;

	public MenuDropdown(MenuController menuController, boolean loadbutton, String btnName, boolean yesOrNo,
		MenuPlayersPane menuPlayersPane) {
		this.menuController = menuController;
		username = btnName;
		this.menuPlayersPane = menuPlayersPane;
		createInfoPane(loadbutton, yesOrNo);
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

	private void createInfoPane(boolean button, boolean yesOrNo) {
		gameInfoPane = new BorderPane();
		gameInfoPane.setMinSize(MenuPane.paneWidth - 60, 60);
		gameInfoPane.setMaxSize(MenuPane.paneWidth - 60, 60);

		if (button) {
			Button loadGame = new Button("Load game");
			loadGame.setMinSize(160, 40);
			loadGame.setMaxSize(160, 40);
			loadGame.setOnAction(e -> menuController.loadGame());
			gameInfoPane.setRight(loadGame);
		}

		if (yesOrNo) {
			CheckBox inviteBtn = new CheckBox("kies");
			inviteBtn.setPrefSize(40, 20);
			inviteBtn.setUserData("Selecteer");
			inviteBtn.setOnAction(e -> selectPlayer());
			gameInfoPane.setLeft(inviteBtn);
		}

	}

	public void selectPlayer() {

		if (!isChecked) { //add selected player to arraylist
			menuPlayersPane.addPlayer(username);
			isChecked = true;
		} else if (isChecked) {//remove deselected player from arraylist
			menuPlayersPane.removePlayer(username);
			isChecked = false;
		}
	}

	public String getSelectedPlayer() {
		return username;
	}

	public boolean isClicked() {
		return isChecked;
	}

}
