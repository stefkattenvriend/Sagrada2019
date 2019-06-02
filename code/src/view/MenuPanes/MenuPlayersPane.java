package view.MenuPanes;

import java.util.ArrayList;

import controller.LoginController;
import controller.MenuController;
import databeest.DataBaseApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuPlayersPane extends VBox {// door joery

	private ScrollPane playersList;
	private VBox listInput;
	private MenuController menuController;
	private boolean loadbutton;
	private boolean turnOn;
	private ArrayList<MenuDropdown> menuItems;
	private Button cancel;
	private Button invitePlayer;
	private Label title;
	private FlowPane btnPane;
	private DataBaseApplication databeest;
	private ArrayList<String> players;
	private ArrayList<String> selectedPlayers;
	private Label message;
	private Button createGame;
	private LoginController loginController;
	private MenuWaitingPane menuWaitingPane;
	private boolean alreadyPlaying = false;

	public MenuPlayersPane(MenuController menuController, LoginController loginController,
			MenuWaitingPane menuWaitingPane) {
		this.menuController = menuController;
		this.loginController = loginController;
		this.menuWaitingPane = menuWaitingPane;
		databeest = menuController.getDataBaseApplication();
		players = databeest.getPlayers();

		setPaneSize();
		createPlayersList(false);
		setBackground(new Background(new BackgroundFill(Color.rgb(208, 215, 206), null, null))); // tijdelijk
	}

	private void createPlayersList(boolean turnon) {
		selectedPlayers = new ArrayList<String>(); // heeft de invited players in zich
		selectedPlayers.add(loginController.getCurrentAccount());
		this.turnOn = turnon;

		// title
		title = new Label();
		title.setText("Spelers");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		title.setTextFill(Color.GREEN);

		// buttons
		btnPane = new FlowPane();
		btnPane.setPrefSize(200, 40);
		btnPane.setAlignment(Pos.CENTER);
		invitePlayer = new Button("Daag uit");
		invitePlayer.setPrefSize(100, 30);
		invitePlayer.setOnAction(e -> turnOn());
		btnPane.getChildren().add(invitePlayer);

		// update message
		message = new Label();

		// the list with buttons
		playersList = new ScrollPane();
		playersList.setMinSize(MenuPane.paneWidth - 60,
				(MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3)) - 150);
		playersList.setMaxSize(MenuPane.paneWidth - 60,
				(MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3)) - 150);
		playersList.setFitToWidth(true);
		playersList.setFitToHeight(true);
		listInput = new VBox();
		listInput.setMinWidth(MenuPane.paneWidth - 80); // binding of listner.
		listInput.setMaxWidth(MenuPane.paneWidth - 80);
		playersList.setContent(listInput);
		menuItems = new ArrayList<MenuDropdown>();

		databeest.getPlayers();

		for (int i = 0; i < players.size(); i++) {// check of eigen gebruikersnaam er tussen staat
			if (players.get(i).equals(loginController.getCurrentAccount())) {
				players.remove(i);
			}
		}

		for (int i = 0; i < players.size(); i++) {// vult verzameling met alle knoppen met bijbehorende username
			menuItems.add(new MenuDropdown(menuController, false, players.get(i), false, this, false, false, null,
					loginController, null, null));

		}

		for (int x = 0; x < menuItems.size(); x++) { // voegt alle knoppen toe aan de lijst
			listInput.getChildren().add(menuItems.get(x));
		}

		setAlignment(Pos.TOP_CENTER);
		getChildren().addAll(title, btnPane, message, playersList);
	}

	private void turnOn() { // verandert zicht om te inviten
		getChildren().clear();
		cancel = new Button("afbreken");
		cancel.setPrefSize(100, 30);
		cancel.setOnAction(e -> turnOff());

		createGame = new Button("Uitnodigen");
		createGame.setPrefSize(100, 30);
		createGame.setOnAction(e -> getUsernames());

		btnPane.getChildren().clear();
		btnPane.getChildren().addAll(cancel, createGame);
		btnPane.setAlignment(Pos.CENTER);
		getChildren().addAll(title, btnPane, message, playersList);
		menuItems.clear();
		listInput.getChildren().clear();
		message.setText(" ");
		for (int i = 0; i < players.size(); i++) {// vult verzameling met alle knoppen
			menuItems.add(new MenuDropdown(menuController, false, players.get(i), true, this, false, false, null,
					loginController, null, null)); // spelersnaam moet uit
			// database worden getrokken
		}

		for (int x = 0; x < menuItems.size(); x++) { // voegt alle knoppen toe aan de lijst
			listInput.getChildren().add(menuItems.get(x));
		}

	}

	private void getUsernames() { // checkt of er niet te weinig of te veel spelers zijn geselecteerd.
		ArrayList<String> waitingGames = new ArrayList<>();
		waitingGames = databeest.getWaitingGames(loginController.getCurrentAccount());
		ArrayList<String> playersInGame = new ArrayList<>();

		if (selectedPlayers.size() == 1) {
			message.setText("Je hebt geen spelers geselecteerd.");
			message.setTextFill(Color.RED);
		} else if (selectedPlayers.size() > 0 && selectedPlayers.size() <= 4) {

			if (selectedPlayers.size() == 2) {
				message.setText("Uitnodiging is verzonden!");
				message.setTextFill(Color.GREEN);
			} else {
				message.setText("Uitnodigingen zijn verzonden!");
				message.setTextFill(Color.GREEN);
			}

			if (alreadyPlaying) {
				message.setText("Je moet eerst wachten tot je een reactie hebt gekregen");
				message.setTextFill(Color.RED);
				alreadyPlaying = false;
			} else {
				menuController.newGame(selectedPlayers);
				turnOff();
			}

		} else if (selectedPlayers.size() > 4) {
			message.setText("Je hebt te veel spelers geselecteerd.");
			message.setTextFill(Color.RED);
		}

		// de array 'selectedPlayers' is nu gevuld met de uitgenodigde spelers.
		menuWaitingPane.updateWaitingPane();

	}

	private void turnOff() { // na 'uitnodigen' of 'afbreken' wordt de normale spelerslijst weergegeven.
		getChildren().clear();
		btnPane.getChildren().clear();
		btnPane.getChildren().add(invitePlayer);
		getChildren().addAll(title, btnPane, message, playersList);
		menuItems.clear();
		listInput.getChildren().clear();

		for (int i = 0; i < players.size(); i++) {// vult verzameling met alle knoppen
			menuItems.add(new MenuDropdown(menuController, false, players.get(i), false, this, false, false, null,
					loginController, null, null));
		}

		for (int x = 0; x < menuItems.size(); x++) { // voegt alle knoppen toe aan de lijst
			listInput.getChildren().add(menuItems.get(x));
		}

		selectedPlayers.clear();
		selectedPlayers.add(loginController.getCurrentAccount());
	}

	public final void addPlayer(String username) { // voegt speler toe in arraylist
		ArrayList<String> waitingGames = new ArrayList<>();
		waitingGames = databeest.getWaitingGames(loginController.getCurrentAccount());
		ArrayList<String> playersInGame = new ArrayList<>();

		selectedPlayers.add(username);

		if (selectedPlayers.size() > 4) {
			message.setText("Je kunt niet meer dan 3 spelers uitnodigen");
			message.setTextFill(Color.RED);
		}

		for (int a = 0; a < waitingGames.size(); a++) {
			playersInGame = databeest.getPlayersInGame(Integer.parseInt(waitingGames.get(a)),
					loginController.getCurrentAccount());

			for (int b = 0; b < playersInGame.size(); b++) {

				if (playersInGame.get(b).equals(username)) {
					message.setText("Je hebt al een game open staan met " + username);
					message.setTextFill(Color.RED);
					alreadyPlaying = true;
				}
			}
		}

	}

	public final void removePlayer(String username) { // verwijderd speler uit arraylist
		for (int i = 0; i < selectedPlayers.size(); i++) {
			if (selectedPlayers.get(i).equals(username)) {
//				System.out.println("removed " + selectedPlayers.get(i));
				selectedPlayers.remove(i);

				if (selectedPlayers.size() <= 4) {
					message.setText(" ");
				}

			}
		}
	}

	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth - 40, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3) - 40);
		setMaxSize(MenuPane.paneWidth - 40, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3) - 40);
	}
}
