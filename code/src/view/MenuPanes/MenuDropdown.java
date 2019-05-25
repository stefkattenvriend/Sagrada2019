package view.MenuPanes;

import controller.MenuController;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuDropdown extends VBox {

	private Button btn;
	private boolean clicked = false;
	private BorderPane gameInfoPane;
	private MenuController menuController;
	private boolean yesOrNo;
	private String username;
	private int totalPlayers;
	private boolean isChecked = false;
	private MenuPlayersPane menuPlayersPane;

	public MenuDropdown(MenuController menuController, boolean loadbutton, String btnName, boolean yesOrNo, MenuPlayersPane menuPlayersPane) {
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

//			inviteBtn.selectedProperty().addListener(new ChangeListener<T>hange);
			gameInfoPane.setLeft(inviteBtn);
		}

	}

	public void selectPlayer() {

		if (!isChecked) {
//			System.out.println(username + " is in!");
			menuPlayersPane.addPlayer(username);
			isChecked = true;
		} else if (isChecked) {
			//remove from arraylist
//			System.out.println(username + " is out..");
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
