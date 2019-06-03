package view.MenuPanes;

import controller.LoginController;
import controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import view.GamePanes.GamePane;

public class MenuCenterPane extends VBox {

	private MenuWelcomePane welcomePane;
	private MenuPlayersPane playersPane;
	private LoginController loginController;
	private MenuController menuController;
	private MenuWaitingPane menuWaitingPane;

	public MenuCenterPane(LoginController loginController, MenuController menuController,
			MenuWaitingPane menuWaitingPane) {
		this.loginController = loginController;
		this.menuController = menuController;
		this.menuWaitingPane = menuWaitingPane;
		setUp();
	}

	private void setUp() {
		setPaneSize();
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

}
