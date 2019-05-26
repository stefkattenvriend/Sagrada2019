package view.MenuPanes;

import java.util.ArrayList;

import controller.DatabaseController;
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

public class MenuPlayersPane extends VBox {

	private ScrollPane playersList;
	private VBox listInput;
	private MenuController menuController;
	private boolean loadbutton;
	private boolean turnOn;
	private ArrayList<MenuDropdown> menuItems;
	private Button cancel;
	private Button createGame;
	private Label title;
	private FlowPane btnPane;
	private DataBaseApplication databeest;
	private ArrayList<String> players;
	private ArrayList<String> selectedPlayers;
	private Label message;

	public MenuPlayersPane(MenuController menuController) {
		this.menuController = menuController;
		databeest = menuController.getDataBaseApplication();
		players = databeest.getPlayers();
		setPaneSize();
		createPlayersList(false);
		setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null))); // tijdelijk
	}

	private void createPlayersList(boolean turnon) {
		this.turnOn = turnon;
		message = new Label();
		title = new Label();
		title.setText("Spelers");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		
		btnPane = new FlowPane();
		btnPane.setPrefSize(200, 40);
		btnPane.setAlignment(Pos.CENTER);
		createGame = new Button("Daag uit");
		createGame.setPrefSize(100, 30);
		createGame.setOnAction(e -> turnOn());
		btnPane.getChildren().add(createGame);
		
		playersList = new ScrollPane();
		playersList.setMinSize(MenuPane.paneWidth - 60,
				(MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3)) - 150);
		playersList.setMaxSize(MenuPane.paneWidth - 60,
				(MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3)) - 150);
		playersList.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		playersList.setFitToWidth(true);
		playersList.setFitToHeight(true);

		listInput = new VBox();
		listInput.setMinWidth(MenuPane.paneWidth - 80); // binding of listner.
		listInput.setMaxWidth(MenuPane.paneWidth - 80);
		playersList.setContent(listInput);
		menuItems = new ArrayList<MenuDropdown>();

		databeest.getPlayers();

		for (int i = 0; i < players.size(); i++) {// vult verzameling met alle knoppen
			menuItems.add(new MenuDropdown(menuController, false, players.get(i), false, this)); // spelersnaam moet uit
			// database komen
		}

		for (int x = 0; x < menuItems.size(); x++) { // voegt alle knoppen toe aan de lijst
			listInput.getChildren().add(menuItems.get(x));
		}

		setAlignment(Pos.TOP_CENTER);
		getChildren().addAll(title, btnPane, message, playersList);
	}

	private void turnOn() {
		getChildren().clear();
		cancel = new Button("afbreken");
		cancel.setPrefSize(100, 30);
		cancel.setOnAction(e -> turnOff());
		btnPane.getChildren().clear();
		btnPane.getChildren().addAll(cancel);
		btnPane.setAlignment(Pos.CENTER);
		getChildren().addAll(title, btnPane, message, playersList);
		menuItems.clear();
		listInput.getChildren().clear();

		for (int i = 0; i < players.size(); i++) {// vult verzameling met alle knoppen
			menuItems.add(new MenuDropdown(menuController, false, players.get(i), true, this)); // spelersnaam moet uit
			// database worden getrokken
		}

		for (int x = 0; x < menuItems.size(); x++) { // voegt alle knoppen toe aan de lijst
			listInput.getChildren().add(menuItems.get(x));
		}

		// get selected player and add username in arraylist.
		selectedPlayers = new ArrayList<String>();

	}

	private void turnOff() {
		getChildren().clear();
		btnPane.getChildren().clear();
		btnPane.getChildren().add(createGame);
		getChildren().addAll(title, btnPane, message, playersList);
		menuItems.clear();
		listInput.getChildren().clear();

		for (int i = 0; i < players.size(); i++) {// vult verzameling met alle knoppen
			menuItems.add(new MenuDropdown(menuController, false, players.get(i), false, this)); // spelersnaam moet uit
			// database worden getrokken
		}

		for (int x = 0; x < menuItems.size(); x++) { // voegt alle knoppen toe aan de lijst
			listInput.getChildren().add(menuItems.get(x));
		}
		
		selectedPlayers.clear();
//		message.setText(" ");
	}

	public final void addPlayer(String username) {
		if(selectedPlayers.size() < 3) {
			selectedPlayers.add(username);
			System.out.println("added " + username);
		} else if (selectedPlayers.size() >= 3) {
			message.setText("Je kunt niet meer dan 3 spelers uitnodigen");
			message.setTextFill(Color.RED);
		} 
		
		
	}

	public final void removePlayer(String username) {
			for (int i = 0; i < selectedPlayers.size(); i++) {
				if (selectedPlayers.get(i).equals(username)) {
					System.out.println("removed " + selectedPlayers.get(i));
					selectedPlayers.remove(i);
					
					if(selectedPlayers.size() < 3) {
						message.setText(" ");
					}
					
				}
			}
	}

	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3));
		setMaxSize(MenuPane.paneWidth, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3));
	}
}
