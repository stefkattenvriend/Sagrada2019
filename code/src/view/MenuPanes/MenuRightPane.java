package view.MenuPanes;

import controller.LoginController;
import controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import view.MyScene;
import view.GamePanes.GamePane;

public class MenuRightPane extends VBox {

	private MenuGamesPane menuGamesPane;
	private MenuWaitingPane menuWaitingPane;
	private MyScene myScene;
	private MenuController menuController;
	private LoginController loginController;

	public MenuRightPane(MyScene myScene, MenuController menuController, LoginController loginController) {
		this.myScene = myScene;
		this.menuController = menuController;
		this.loginController = loginController;
		setUp();
	}

	private void setUp() {
		setPaneSize();
		createPanes();
		setAlignment(Pos.CENTER);
	}

	private void createPanes() {
		menuGamesPane = new MenuGamesPane(myScene, menuController, loginController);
		menuWaitingPane = new MenuWaitingPane(menuController, loginController, menuGamesPane);

		setAlignment(Pos.CENTER);
		getChildren().addAll(menuGamesPane, menuWaitingPane);

	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}



	public MenuWaitingPane getMenuWaitingGame() {
		return menuWaitingPane;
	}
}
