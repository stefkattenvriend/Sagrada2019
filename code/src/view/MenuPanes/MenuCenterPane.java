package view.MenuPanes;

import controller.LoginController;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.GamePanes.GamePane;

public class MenuCenterPane extends VBox{
	
	private MenuWelcomePane welcomePane;
	private MenuPlayersPane playersPane;
	private LoginController loginController;
	
	public MenuCenterPane(LoginController loginController) {
		this.loginController = loginController;
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		tijdelijkAanduiding();
		createPanes();
	}
	
	private void createPanes() {
		welcomePane = new MenuWelcomePane(loginController);
		playersPane = new MenuPlayersPane();
		
		getChildren().addAll(welcomePane, playersPane);
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}
	
	private void tijdelijkAanduiding() {
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
	}
}
