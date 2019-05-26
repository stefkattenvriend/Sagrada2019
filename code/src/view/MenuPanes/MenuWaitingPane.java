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

public class MenuWaitingPane extends FlowPane{
	
	private MyScene myScene;
	private VBox list;
	private boolean clicked = false;
	private MenuController menuController;
	private ScrollPane waitingList;
	
	public MenuWaitingPane() {
		setPaneSize();
		createActiveGamesList();
		setBackground(new Background(new BackgroundFill(Color.rgb(255, 205, 205, 0.8), null, null))); //tijdelijk
	}
	
	private void createActiveGamesList() {
		Label title = new Label();
		title.setText("Wachten op reactie");
		title.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
		title.setTextFill(Color.RED);
		
		waitingList = new ScrollPane();
		waitingList.setMinSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 180);
		waitingList.setMaxSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 180);
//		gamesList.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		waitingList.setFitToWidth(true);
		waitingList.setFitToHeight(true);
		
		list = new VBox();
//		list.setMinSize(MenuPane.paneWidth - 80, 100);
//		list.setMaxSize(MenuPane.paneWidth - 80, 100);//hiermee wordt de hoogte van de pane bepaald. dit moet automatisch bijgewerkt worden.
		
		ArrayList<MenuDropdown> games = new ArrayList<MenuDropdown>();
		
		for(int i = 0; i < 2; i++) {// vult verzameling met alle knoppen
			games.add(new MenuDropdown(menuController, false, "Sagrada " + i, false, null, true, false));
			
		}
		
		for(int x = 0; x < games.size(); x++) { //voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(x));
		}
		
		list.setMinWidth(MenuPane.paneWidth - 80); // hoogte van lijst moet automatisch bijgewerkt worden met binding of listner.
		list.setMaxWidth(MenuPane.paneWidth - 80);
		
		waitingList.setContent(list);
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(title, waitingList);

		
	}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3 - 40, GamePane.windowMaxHeight / 2 - 80);
		setMaxSize(GamePane.windowMaxWidth / 3 - 40, GamePane.windowMaxHeight / 2 - 80);
	}
}
