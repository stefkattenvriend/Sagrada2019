package view.MenuPanes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
		
		Button startgame = new Button("Start game");
		startgame.setMinSize(MenuPane.paneWidth - 60, 40);
		startgame.setMaxSize(MenuPane.paneWidth - 60, 40);
		startgame.setOnAction(e -> myScene.setGamePane());
		
		VBox list = new VBox();
		list.setMinSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 60);
		list.setMaxSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 60);
//		list.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		list.getChildren().addAll(startgame, gamesList);
		gamesList.setContent(list);
		

		
		
		
		
		
//		gamesList.setCenter(listInput);
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(title, gamesList);
		
	}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight / 2);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight / 2);
	}
}
