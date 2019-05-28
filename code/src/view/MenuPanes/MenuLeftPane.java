package view.MenuPanes;

import controller.LoginController;
import controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.GamePanes.GamePane;

public class MenuLeftPane extends VBox{
	

	
	private MenuInvitePane resultsPane;
	private MenuResultsPane invitePane;
	private MenuController menuController;
	private LoginController loginController;
	
	public MenuLeftPane(MenuController menuController, LoginController loginController) {
		this.menuController = menuController;
		this.loginController = loginController;
		setUp();
	}

	private void setUp() {
		setPaneSize();
		tijdelijkAanduiding();
		createPanes();

	}
	
	private void createPanes() {		
		resultsPane = new MenuInvitePane(menuController, loginController);
		invitePane = new MenuResultsPane();
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(invitePane, resultsPane);
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}
	
	private void tijdelijkAanduiding() {
//		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7), null, null)));
	}
}
