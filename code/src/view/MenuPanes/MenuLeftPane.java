package view.MenuPanes;

import controller.LoginController;
import controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.MyScene;
import view.GamePanes.GamePane;

public class MenuLeftPane extends VBox{
	

	
	private MenuInvitePane resultsPane;
	private MenuResultsPane invitePane;
	private MenuController menuController;
	private LoginController loginController;
	private MenuWaitingPane menuWaitingPane;
	private MenuLogoutPane logoutPane;
	private MyScene myScene;
	
	public MenuLeftPane(MenuController menuController, LoginController loginController, MenuWaitingPane menuWaitingPane, MyScene myscene) {
		this.menuController = menuController;
		this.loginController = loginController;
		this.menuWaitingPane = menuWaitingPane;
		this.myScene = myscene;
		setUp();
	}

	private void setUp() {
		setPaneSize();
		tijdelijkAanduiding();
		createPanes();

	}
	
	private void createPanes() {		
		resultsPane = new MenuInvitePane(menuController, loginController, menuWaitingPane);
		invitePane = new MenuResultsPane(menuController);
		logoutPane = new MenuLogoutPane(loginController, myScene);
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(logoutPane, invitePane, resultsPane);
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}
	
	private void tijdelijkAanduiding() {
		setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7), null, null)));
	}
}
