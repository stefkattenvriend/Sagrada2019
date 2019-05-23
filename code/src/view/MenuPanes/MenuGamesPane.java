package view.MenuPanes;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.MyScene;
import view.GamePanes.GamePane;

public class MenuGamesPane extends FlowPane{
	
	private ScrollPane gamesList;
	private ScrollBar scrollBar;
	private Pane listInput;
	private MyScene myScene;
	private Button openGameInfo;
	private Button closeGameInfo;
	private Pane gameInfoPane;
	private VBox list;
	private boolean clicked = false;
	
	public MenuGamesPane(MyScene myScene) {
		this.myScene = myScene;
		setPaneSize();
		createActiveGamesList();
		setBackground(new Background(new BackgroundFill(Color.RED, null, null))); //tijdelijk
	}
	
	private void createActiveGamesList() {
		Label title = new Label();
		title.setText("Games");
		title.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
		
		gamesList = new ScrollPane();
		gamesList.setMinSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 60);
		gamesList.setMaxSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 60);
		gamesList.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		gamesList.setFitToWidth(true);
		gamesList.setFitToHeight(true);
	
		list = new VBox();
//		list.setMinSize(MenuPane.paneWidth - 80, 100);
//		list.setMaxSize(MenuPane.paneWidth - 80, 100);//hiermee wordt de hoogte van de pane bepaald. dit moet automatisch bijgewerkt worden.
		
		ArrayList<MenuDropdown> games = new ArrayList<MenuDropdown>();
		
		for(int i = 0; i < 10; i++) {// vult verzameling met alle knoppen
			games.add(new MenuDropdown("Game " + Integer.toString(i)));
			
		}
		
		for(int x = 0; x < games.size(); x++) { //voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(x));
		}
		
		list.setMinSize(MenuPane.paneWidth - 80, (games.size() * 100)); // hoogte van lijst moet automatisch bijgewerkt worden met binding of listner.
		list.setMaxSize(MenuPane.paneWidth - 80, (games.size() * 100));
		
		gamesList.setContent(list);
		
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(title, gamesList);
		
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight / 2);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight / 2);
	}
}
