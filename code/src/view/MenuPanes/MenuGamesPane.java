package view.MenuPanes;

import java.util.ArrayList;

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
	
	public MenuGamesPane(MyScene myScene, MenuController menuController) {
		this.myScene = myScene;
		this.menuController = menuController;
		setPaneSize();
		createActiveGamesList();
		setBackground(new Background(new BackgroundFill(Color.rgb(183, 215, 225), null, null))); //tijdelijk
	}
	
	private void createActiveGamesList() {
		Label title = new Label();
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
		
		ArrayList<MenuDropdown> games = new ArrayList<MenuDropdown>();
		
		for(int i = 0; i < 10; i++) {// vult verzameling met alle knoppen
			games.add(new MenuDropdown(menuController, true, "Sagrada " + i, false, null, false, false));
			
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
