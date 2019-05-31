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
	private ArrayList<Integer> gameIDs;
	private ArrayList<String> playersInGame;
	private ArrayList<String> status;
	private ArrayList<MenuDropdown> games;
	private ArrayList<String> accepted;
	private Label title;
	private MenuGamesPane menuGamesPane;
	private boolean gotit = false;
	private ArrayList<String> currentPlayerStatus;
	private ArrayList<String> newAcceptedGames;

	public MenuWaitingPane(MenuController menuController, LoginController loginController,
			MenuGamesPane menuGamesPane) {
		this.menuController = menuController;
		this.loginController = loginController;
		this.menuGamesPane = menuGamesPane;
		menuController.setWaitedGamesPane(this);
//		databeest = menuController.getDataBaseApplication();
//		gameIDs = databeest.getWaitingGames(loginController.getCurrentAccount());
		
		gameIDs = menuController.getWaitedGames();
		
//		accepted = databeest.getAcceptedGame(loginController.getCurrentAccount());
//		newAcceptedGames = new ArrayList<String>();
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

		setUp();

		list.setMinWidth(MenuPane.paneWidth - 80);
		list.setMaxWidth(MenuPane.paneWidth - 80);

		waitingList.setContent(list);

	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3 - 40, GamePane.windowMaxHeight / 2 - 80);
		setMaxSize(GamePane.windowMaxWidth / 3 - 40, GamePane.windowMaxHeight / 2 - 80);
	}

	public void setUp() {

		gameIDs = menuController.getNewWaitedGames();
//		for (int i = 0; i < gameIDs.size(); i++) {// vult verzameling met alle knoppen
//
//			// check of de uitdager in het lijstje staat van gameID
//			// ->view deze mag pas zichtbaar worden als invite is geaccepteerd
//			status = databeest.getPlayerStatus(gameIDs.get(i), loginController.getCurrentAccount());
//			currentPlayerStatus = databeest.getCurrentPlayerStatus(loginController.getCurrentAccount(), gameIDs.get(i));
//			for (int s = 0; s < status.size(); s++) {
//				if (status.get(s).equals("uitdager") && currentPlayerStatus.get(0).equals("uitgedaagde")) {
//
//					gameIDs.remove(i);
//				}
//			}
//		}

		for (int i = 0; i < gameIDs.size(); i++) { // voegt knop toe
			games.add(new MenuDropdown(menuController, false, "Sagrada " + gameIDs.get(i), false, null, true, false,
					this, loginController, null, menuGamesPane));
		}

		for (int x = 0; x < games.size(); x++) { // voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(x));
		}

		setAlignment(Pos.CENTER);
		getChildren().addAll(title, waitingList);
	}

	public void newWaitedGames(ArrayList<Integer> newWaitedGames) {
		gameIDs.clear();
		gameIDs = newWaitedGames;
		updateWaitingPane();
	}
	
	public void updateWaitingPane() {
		getChildren().clear();
		list.getChildren().clear();
		games.clear();

//		gameIDs = databeest.getWaitingGames(loginController.getCurrentAccount());
//		gameIDs = menuController.getNewWaitedGames();
		
//		for (int i = 0; i < gameIDs.size(); i++) {
//
//			// check of de uitdager in het lijstje staat van gameID
//			// ->view deze mag pas zichtbaar worden als invite is geaccepteerd
//			status = databeest.getPlayerStatus(gameIDs.get(i), loginController.getCurrentAccount());
//			currentPlayerStatus = databeest.getCurrentPlayerStatus(loginController.getCurrentAccount(), gameIDs.get(i));
//			
//		for (int s = 0; s < status.size(); s++) {
//				if (status.get(s).equals("uitdager") && !currentPlayerStatus.get(0).equals("geaccepteerd")) {
//
//					gameIDs.remove(i);
//				}
//			}
//
//		}

		for (int i = 0; i < gameIDs.size(); i++) { // voegt knop toe
			games.add(new MenuDropdown(menuController, false, "Sagrada " + gameIDs.get(i), false, null, true, false,
					this, loginController, null, null));
		}

		for (int i = 0; i < games.size(); i++) { // voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(i));
		}

		setAlignment(Pos.CENTER);
		getChildren().addAll(title, waitingList);
	}



}
