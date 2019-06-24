 package view.MenuPanes;


import controller.LoginController;
import controller.MenuController;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
	private Image image;
	BackgroundImage background;

	public MenuPane(MenuController menuController, LoginController loginController, MyScene myScene) {
		this.menuController = menuController;
		this.loginController = loginController;
		this.myScene = myScene;
		image = new Image("layout_images/menuBackground.png");
		background = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

		setUp();

	}

	private void setUp() {
		setScreenSize();
		createPanes();
		setBackground(new Background(background));
	}
	
	public void createPanes() {	
		menuRightPane = new MenuRightPane(myScene, menuController, loginController);
		menuLeftPane = new MenuLeftPane(menuController, loginController, menuRightPane.getMenuWaitingGame(), myScene);
		menuCenterPane = new MenuCenterPane(loginController, menuController, menuRightPane.getMenuWaitingGame());
		setLeft(menuLeftPane);
		setCenter(menuCenterPane);
		setRight(menuRightPane);
	}

	public void setScreenSize() {
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
	}
	
	public void update(){
		menuRightPane = new MenuRightPane(myScene, menuController, loginController);
		menuLeftPane = new MenuLeftPane(menuController, loginController, menuRightPane.getMenuWaitingGame(), myScene);
		menuCenterPane = new MenuCenterPane(loginController, menuController, menuRightPane.getMenuWaitingGame());
		setLeft(menuLeftPane);
		setCenter(menuCenterPane);
		setRight(menuRightPane);
	}
	
	

}
