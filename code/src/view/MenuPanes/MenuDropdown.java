package view.MenuPanes;

import java.util.ArrayList;

import controller.LoginController;
import controller.MenuController;
import databeest.DataBaseApplication;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
	private boolean waitPane;
	private MenuWaitingPane menuWaitingPane;
	private LoginController loginController;
	private DataBaseApplication databeest;
	private ArrayList<String> players;
	private ArrayList<String> status;
	private String gameID;
	private int playerID;
	private MenuInvitePane menuInvitePane;
	private MenuGamesPane menuGamesPane;

	public MenuDropdown(MenuController menuController, boolean gamesPane, String btnName, boolean playersPane,
			MenuPlayersPane menuPlayersPane, boolean waitPane, boolean invitesPane, MenuWaitingPane menuWaitingPane,
			LoginController loginController, MenuInvitePane menuInvitePane, MenuGamesPane menuGamesPane) {
		this.menuController = menuController;
		username = btnName;
		this.menuPlayersPane = menuPlayersPane;
		this.waitPane = waitPane;
		this.menuWaitingPane = menuWaitingPane;
		this.loginController = loginController;
		this.menuInvitePane = menuInvitePane;
		this.menuGamesPane = menuGamesPane;
		databeest = menuController.getDataBaseApplication();
		createInfoPane(gamesPane, playersPane, waitPane, invitesPane);
		createButton(btnName);
		getChildren().add(btn);

		if (playersPane) {
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

	private void createInfoPane(boolean gamePane, boolean playersPane, boolean waitPane, boolean invitesPane) {
		gameInfoPane = new BorderPane();
		gameInfoPane.setMinSize(MenuPane.paneWidth - 60, 60);
		gameInfoPane.setMaxSize(MenuPane.paneWidth - 60, 60);

		if (gamePane) {
			String splitBtnName[] = username.split(" ");
			gameID = splitBtnName[1];
			Button loadGame = new Button("Open game");
			loadGame.setMinSize(160, 40);
			loadGame.setMaxSize(160, 40);
			loadGame.setOnAction(e -> menuController.loadGame(gameID));
			
			players = databeest.getPlayersInGame(gameID, loginController.getCurrentAccount());
			BorderPane inGamePlayers = new BorderPane();
			inGamePlayers.setPrefSize(MenuPane.paneWidth - 60, 60);

			VBox playersList = new VBox();
			Label p1 = new Label();
			Label p2 = new Label();
			Label p3 = new Label();

			for (int i = 0; i < players.size(); i++) {
				if (players.get(i) != null) {
					if (i == 0) {
						p1.setText(players.get(i));
					} else if (i == 1) {
						p2.setText(players.get(i));
					} else if (i == 2) {
						p3.setText(players.get(i));
					}
				}
			}

			playersList.getChildren().addAll(p1, p2, p3);

			VBox gap = new VBox();
			gap.setPrefWidth(20);
			
//			inGamePlayers.getChildren().addAll(gap, playersList, loadGame);
			inGamePlayers.setLeft(playersList);
			inGamePlayers.setRight(loadGame);
			gameInfoPane.setCenter(inGamePlayers);
		}

		if (playersPane) {
			CheckBox inviteBtn = new CheckBox("Kies");
			inviteBtn.setPrefSize(40, 20);
//			inviteBtn.setUserData("kies");
			inviteBtn.setOnAction(e -> selectPlayer());
			gameInfoPane.setLeft(inviteBtn);
		}

		if (waitPane) {
			String splitBtnName[] = username.split(" ");
			gameID = splitBtnName[1];
			players = databeest.getPlayersInGame(gameID, loginController.getCurrentAccount());
			status = databeest.getPlayerStatus(Integer.parseInt(gameID), loginController.getCurrentAccount());
			FlowPane inGamePlayers = new FlowPane();
			inGamePlayers.setPrefSize(MenuPane.paneWidth - 60, 60);

			VBox playersList = new VBox();
			Label p1 = new Label();
			Label p2 = new Label();
			Label p3 = new Label();

			for (int i = 0; i < players.size(); i++) {
				if (players.get(i) != null) {
					if (i == 0) {
						p1.setText(players.get(i));
					} else if (i == 1) {
						p2.setText(players.get(i));
					} else if (i == 2) {
						p3.setText(players.get(i));
					}
				}
			}

			playersList.getChildren().addAll(p1, p2, p3);

			VBox gap = new VBox();
			gap.setPrefWidth(20);

			VBox statusList = new VBox();
			Label s1 = new Label();
			Label s2 = new Label();
			Label s3 = new Label();

			int countAccept = 0;
			
			for (int i = 0; i < status.size(); i++) {
				if (status.get(i) != null) {
					
					if (i == 0) {
						if(status.get(i).equals("uitgedaagde")) {
							s1.setText("wachten op reactie..");
						} else if(status.get(i).equals("uitdager")) {
							s1.setText("heeft jou uitgenodigd");
							countAccept++;
						} else if(status.get(i).equals("geaccepteerd")) {
						s1.setText(status.get(i));
						countAccept++;
						} else {
							s1.setText(status.get(i));
						}
					} else if (i == 1) {
						if(status.get(i).equals("uitgedaagde")) {
							s2.setText("wachten op reactie..");
						} else if(status.get(i).equals("uitdager")) {
							s2.setText("heeft jou uitgenodigd");
							countAccept++;
						} else if(status.get(i).equals("geaccepteerd")){
						s2.setText(status.get(i));
						countAccept++;
						} else {
							s2.setText(status.get(i));
						}
					} else if (i == 2) {
						if(status.get(i).equals("uitgedaagde")) {
							s3.setText("wachten op reactie..");
						} else if(status.get(i).equals("uitdager")) {
							s3.setText("heeft jou uitgenodigd");
							countAccept++;
						} else if(status.get(i).equals("geaccepteerd")) {
							s3.setText(status.get(i));
							countAccept++;
						} else {
							s3.setText(status.get(i));
						}
					}
				}
				
//				if(countAccept == players.size()) {
//					
////					menuGamesPane.addGame(gameID);
////					menuWaitingPane.newAcceptedGame(gameID);
//					
//					System.out.println(gameID + "IEDEREEN HEEFT GEACCEPT");
//				}
//				System.out.println(countAccept);
				
			}

			statusList.getChildren().addAll(s1, s2, s3);
			inGamePlayers.getChildren().addAll(playersList, gap, statusList);
			gameInfoPane.setLeft(inGamePlayers);
		}

		if (invitesPane) {
			String splitBtnName[] = username.split(" ");
			gameID = splitBtnName[3];
			playerID = databeest.getPlayerID(gameID, loginController.getCurrentAccount());

			HBox choicePane = new HBox();
			choicePane.setPrefSize(MenuPane.paneWidth - 60, 60);
			Button accept = new Button("accepteer");
			accept.setPrefSize((MenuPane.paneWidth - 60) / 2, 60);
			accept.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
			accept.setOnAction(e -> accept(playerID));
			
			Button ignore = new Button("weiger");
			ignore.setPrefSize((MenuPane.paneWidth - 60) / 2, 60);
			ignore.setBackground(new Background(new BackgroundFill(Color.INDIANRED, null, null)));
			ignore.setOnAction(e -> decline(playerID));
			
			choicePane.getChildren().addAll(accept, ignore);
			gameInfoPane.setCenter(choicePane);
		}

	}

	private void accept(int pID) {
		menuController.acceptInvite(pID);
		getChildren().clear();
		menuInvitePane.updateInvitePane();
		getChildren().add(btn);
	}

	private void decline(int pID) {
		menuController.declineInvite(pID);
		getChildren().clear();
		menuInvitePane.updateInvitePane();
		getChildren().add(btn);
	}

	public void selectPlayer() {

		if (!isChecked) { // add selected player to arraylist
			menuPlayersPane.addPlayer(username);
			isChecked = true;
		} else if (isChecked) {// remove deselected player from arraylist
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

	public void getPlayersInGame(String gameID, String playername) {

	}
	
	public String getAcceptedGameID() {
		return gameID;
	}
}
