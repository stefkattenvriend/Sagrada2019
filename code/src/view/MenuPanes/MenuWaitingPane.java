package view.MenuPanes;

import java.util.ArrayList;

import controller.LoginController;
import controller.MenuController;
import databeest.DataBaseApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.MyScene;
import view.GamePanes.GamePane;

public class MenuWaitingPane extends FlowPane {

	private MyScene myScene;
	private VBox list;
	private boolean clicked = false;
	private MenuController menuController;
	private LoginController loginController;
	private DataBaseApplication databeest;
	private ScrollPane waitingList;
	private ArrayList<String> gameIDs;
	private ArrayList<String> playersInGame;
	private ArrayList<String> status;
	private ArrayList<MenuDropdown> games;
	private Label title;
	private boolean gotit = false;

	public MenuWaitingPane(MenuController menuController, LoginController loginController) {
		this.menuController = menuController;
		this.loginController = loginController;
		databeest = menuController.getDataBaseApplication();
		gameIDs = databeest.getWaitingGames(loginController.getCurrentAccount());
		setPaneSize();
		createActiveGamesList();

		setBackground(new Background(new BackgroundFill(Color.rgb(255, 205, 205, 0.8), null, null)));
	}

	private void createActiveGamesList() {
		title = new Label();
		title.setText("Wachten op reactie");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		title.setTextFill(Color.RED);

		waitingList = new ScrollPane();
		waitingList.setMinSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 180);
		waitingList.setMaxSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 180);
		waitingList.setFitToWidth(true);
		waitingList.setFitToHeight(true);

		list = new VBox();

		games = new ArrayList<MenuDropdown>();

		updateWaitingPane();

		list.setMinWidth(MenuPane.paneWidth - 80);
		list.setMaxWidth(MenuPane.paneWidth - 80);

		waitingList.setContent(list);

	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3 - 40, GamePane.windowMaxHeight / 2 - 80);
		setMaxSize(GamePane.windowMaxWidth / 3 - 40, GamePane.windowMaxHeight / 2 - 80);
	}

	public void updateWaitingPane() {
		getChildren().clear();
		list.getChildren().clear();

		for (int i = 0; i < gameIDs.size(); i++) {// vult verzameling met alle knoppen
			
			//check of de uitdager in het lijstje staat van gameID 
			//	->view deze mag pas zichtbaar worden als invite is geaccepteerd
			status = databeest.getPlayerStatus(gameIDs.get(i), loginController.getCurrentAccount());
			for (int s = 0; s < status.size(); s++) {
				if (status.get(s).equals("uitdager")) {
					gotit = true;
					gameIDs.remove(i);
				}
			}
			
			//voegt knop toe
			games.add(new MenuDropdown(menuController, false, "Sagrada " + gameIDs.get(i), false, null, true, false,
					this, loginController)); // spelersnaam
			
		}

		for (int x = 0; x < games.size(); x++) { // voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(x));
		}

		setAlignment(Pos.CENTER);
		getChildren().addAll(title, waitingList);
	}
}
