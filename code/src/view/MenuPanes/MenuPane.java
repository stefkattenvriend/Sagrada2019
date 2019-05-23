package view.MenuPanes;


import controller.LoginController;
import controller.MenuController;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.MyScene;

public class MenuPane extends BorderPane {
	
	//constants
	public final static double paneWidth = MenuPane.windowMaxWidth / 3;
	public final static double windowMaxWidth = 1280;
	public final static double windowMaxHeight = 800;
	
	//instance 
	private MenuController menuController;
	private MenuLeftPane menuLeftPane;
	private MenuCenterPane menuCenterPane;
	private MenuRightPane menuRightPane;
	private LoginController loginController;
	private MyScene myScene;

	public MenuPane(MenuController menuController, LoginController loginController, MyScene myScene) {
		this.menuController = menuController;
		this.loginController = loginController;
		this.myScene = myScene;
		setUp();
	}

	private void setUp() {
		setScreenSize();
		createPanes();
//		setStartButton();
	}
	
	private void createPanes() {
		menuLeftPane = new MenuLeftPane();
		menuCenterPane = new MenuCenterPane(loginController);
		menuRightPane = new MenuRightPane(myScene);
		setLeft(menuLeftPane);
		setCenter(menuCenterPane);
		setRight(menuRightPane);
	}

	public void setScreenSize() {
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
	}
	
	private void setStartButton() {
		Button button = new Button("start");
		button.setPrefSize(80, 40);
		button.setOnAction(e -> menuController.setNewRoot());
		setCenter(button);
	}

}
