package view.MenuPanes;

import java.util.ArrayList;

import controller.LoginController;
import controller.MenuController;
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

public class MenuGamesPane extends FlowPane{
	
	private ScrollPane gamesList;
	
	private MyScene myScene;
	private VBox list;
	private boolean clicked = false;
	private MenuController menuController;
	private LoginController loginController;
	private ArrayList<MenuDropdown> games;
	private ArrayList<Integer> gameIDs;
	private Label title;
	
	public MenuGamesPane(MyScene myScene, MenuController menuController, LoginController loginController) {
		this.myScene = myScene;
		this.menuController = menuController;
		this.loginController = loginController;
		menuController.setActiveGamesPane(this);
		setPaneSize();
		createActiveGamesList();
		setBackground(new Background(new BackgroundFill(Color.rgb(183, 215, 225), null, null))); //tijdelijk

	}
	
	private void createActiveGamesList() {
		title = new Label();
		title.setText("Games");
		title.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
		title.setTextFill(Color.BLUE);
		
		gamesList = new ScrollPane();
		gamesList.setMinSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 60);
		gamesList.setMaxSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 60);
		gamesList.setFitToWidth(true);
		gamesList.setFitToHeight(true);
		
		list = new VBox();
		list.setMinWidth(MenuPane.paneWidth - 80); // hoogte van lijst moet automatisch bijgewerkt worden met binding of listner.
		list.setMaxWidth(MenuPane.paneWidth - 80);
		gamesList.setContent(list);
		
		games = new ArrayList<MenuDropdown>();
		
		gameIDs = menuController.getActivePlayerGames(loginController.getCurrentAccount());
		
		for(int i = 0; i < gameIDs.size(); i++) {// vult verzameling met alle knoppen
			games.add(new MenuDropdown(menuController, true, "Sagrada " + gameIDs.get(i), false, null, false, false, null, loginController, null, this));
			
		}
		
		for(int x = 0; x < games.size(); x++) { //voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(x));
		}
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(title, gamesList);
		
	}
	
	public void updateGamePane() {
		System.out.println("actieve games opruimen..");
		getChildren().clear();
		list.getChildren().clear();
		games.clear();
		System.out.println("actieve games indelen..");
		gameIDs = menuController.getActivePlayerGames(loginController.getCurrentAccount());
		
		for(int i = 0; i < gameIDs.size(); i++) {// vult verzameling met alle knoppen
			games.add(new MenuDropdown(menuController, true, "Sagrada " + gameIDs.get(i), false, null, false, false, null, loginController, null, this));
			
		}
		
		for(int x = 0; x < games.size(); x++) { //voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(x));
		}
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(title, gamesList);
	}

	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3) - 40, (GamePane.windowMaxHeight / 2));
		setMaxSize((GamePane.windowMaxWidth / 3) - 40, (GamePane.windowMaxHeight / 2));
	}
}
