package view.MenuPanes;

import controller.LoginController;
import controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.GamePanes.GamePane;

public class MenuCenterPane extends VBox{
	
	private MenuWelcomePane welcomePane;
	private MenuPlayersPane playersPane;
	private LoginController loginController;
	private MenuController menuController;
	private MenuWaitingPane menuWaitingPane;
	
	public MenuCenterPane(LoginController loginController, MenuController menuController, MenuWaitingPane menuWaitingPane) {
		this.loginController = loginController;
		this.menuController = menuController;
		this.menuWaitingPane = menuWaitingPane;
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		tijdelijkAanduiding();
		createPanes();
	}
	
	private void createPanes() {
		welcomePane = new MenuWelcomePane(loginController);
		playersPane = new MenuPlayersPane(menuController, loginController, menuWaitingPane);
		setAlignment(Pos.CENTER);
		getChildren().addAll(welcomePane, playersPane);
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}
	
	private void tijdelijkAanduiding() {
		setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7), null, null)));
	}
}
