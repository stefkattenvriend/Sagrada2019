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
		title = new Label();
		title.setText("Spelers");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		btnPane = new FlowPane();
		btnPane.setPrefSize(200, 40);
		btnPane.setAlignment(Pos.CENTER);
		createGame = new Button("Create game");
		createGame.setPrefSize(100, 30);
		createGame.setOnAction(e -> turnOn());
		btnPane.getChildren().add(createGame);
		playersList = new ScrollPane();
		playersList.setMinSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3)) - 150);
		playersList.setMaxSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3)) - 150);
		playersList.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		playersList.setFitToWidth(true);
		playersList.setFitToHeight(true);

		listInput = new VBox();
		listInput.setMinWidth(MenuPane.paneWidth - 80); 														// binding of listner.
		listInput.setMaxWidth(MenuPane.paneWidth - 80);
		playersList.setContent(listInput);
		menuItems = new ArrayList<MenuDropdown>();
		
		databeest.getPlayers();
		
		for (int i = 0; i < players.size(); i++) {// vult verzameling met alle knoppen
			menuItems.add(new MenuDropdown(menuController, false, players.get(i), false)); // spelersnaam moet uit database komen
		}

		for (int x = 0; x < menuItems.size(); x++) { // voegt alle knoppen toe aan de lijst
			listInput.getChildren().add(menuItems.get(x));
		}

		setAlignment(Pos.TOP_CENTER);
		getChildren().addAll(title, btnPane, playersList);

	}

	private void turnOn() {
		getChildren().clear();
		cancel = new Button("afbreken");
		cancel.setPrefSize(100, 30);
		cancel.setOnAction(e -> turnOff());
		btnPane.getChildren().clear();
		btnPane.getChildren().addAll(cancel);
		btnPane.setAlignment(Pos.CENTER);
		getChildren().addAll(title, btnPane, playersList);
		menuItems.clear();
		listInput.getChildren().clear();

		for (int i = 0; i < players.size(); i++) {// vult verzameling met alle knoppen
			menuItems.add(new MenuDropdown(menuController, false, players.get(i), true)); // spelersnaam moet uit database worden getrokken
		}

		for (int x = 0; x < menuItems.size(); x++) { // voegt alle knoppen toe aan de lijst
			listInput.getChildren().add(menuItems.get(x));
		}
	}

	private void turnOff() {
		getChildren().clear();
		btnPane.getChildren().clear();
		btnPane.getChildren().add(createGame);
		getChildren().addAll(title, btnPane, playersList);
		menuItems.clear();
		listInput.getChildren().clear();

		for (int i = 0; i < players.size(); i++) {// vult verzameling met alle knoppen
			menuItems.add(new MenuDropdown(menuController, false, players.get(i), false)); // spelersnaam moet uit database worden getrokken
		}

		for (int x = 0; x < menuItems.size(); x++) { // voegt alle knoppen toe aan de lijst
			listInput.getChildren().add(menuItems.get(x));
		}
	}

	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3));
		setMaxSize(MenuPane.paneWidth, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3));
	}
}
