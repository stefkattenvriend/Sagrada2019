package view.MenuPanes;

import controller.MenuController;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.MyScene;
import view.GamePanes.GamePane;

public class MenuRightPane extends VBox{
	
	private MenuGamesPane menuGamesPane;
	private MenuWaitingPane menuWaitingPane;
	private MyScene myScene;
	private MenuController menuController;
	
	public MenuRightPane(MyScene myScene, MenuController menuController) {
		this.myScene = myScene;
		this.menuController = menuController;
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		tijdelijkAanduiding();
		createPanes();
	}
	
	private void createPanes() {
		menuGamesPane = new MenuGamesPane(myScene, menuController);
		
		menuWaitingPane = new MenuWaitingPane();
		
		getChildren().addAll(menuGamesPane, menuWaitingPane);
		
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}
	
	private void tijdelijkAanduiding() {
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
	}
}
