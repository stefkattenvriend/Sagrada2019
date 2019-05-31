package view;
import controller.LoginController;
import controller.MasterController;
import controller.PlayerController;
//joery
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import view.GamePanes.GamePane;
import view.MenuPanes.MenuPane;

public class MyScene extends Scene{
	
	private MasterController mc;
	private LoginController lc;
	private MenuPane menuPane;
	private GamePane gamePane;
	private LoginPane loginPane;
	private Pane root;
	private PlayerController playercontroller;
//	private Stage stage;
	
	public MyScene(MasterController mc, PlayerController playercontroller) {
		super(new Pane());
		this.mc = mc;
		this.playercontroller = playercontroller;
//		this.stage = stage;
		this.
		lc = mc.getLoginController();
		
		root = new Pane();
		
		loginPane = new LoginPane(this, lc);
		

		// hier moeten ook nog de registratie panes worden aangemaakt.
		// daarna is pas de menupane te zien. Er wordt geswitched met de method: setNewRoot.
		// via de constructor worden de aangemaakte classes doorgegeven.
		
		setRoot(root);
		setNewRoot(loginPane);
		
	}

	public void setNewRoot(Pane pane) {
		root.getChildren().clear();
		root.getChildren().add(pane);
	}
	
	public void goToMenuPane() {
		setNewRoot(menuPane);
		//als je terug gaat van layerpane naar menupane worden invitepane en activeGamesPane blijvend leeg.
	}
	
	
	public void setMenuPane()
	{
		if(lc.isLoggedIn()) {
			menuPane = new MenuPane(mc.getMenuController(), lc, this);
		}
		
		setNewRoot(menuPane);
//		mc.getUtc().setGameRunning(false);
		mc.getStage().setHeight(menuPane.windowMaxHeight);
		mc.getStage().setWidth(menuPane.windowMaxWidth);
		mc.getStage().centerOnScreen();
	}

	public void setGamePane() {
		gamePane = new GamePane(mc.getGameController(), this, lc, mc.getPayStoneController(), playercontroller);
		setNewRoot(gamePane);
		mc.getStage().setHeight(gamePane.windowMaxHeight);
		mc.getStage().setWidth(gamePane.windowMaxWidth);
		mc.getStage().centerOnScreen();
	}
	
	public void setLayerPane() {
		
		LayerPane pcardChooser = new LayerPane(mc.getGameController().getLayerController(), mc.getGameController().getPatterncardController(), lc, this);
		
		setNewRoot(pcardChooser);
		mc.getStage().setHeight(gamePane.windowMaxHeight);
		mc.getStage().setWidth(gamePane.windowMaxWidth);
		mc.getStage().centerOnScreen();
	}
	
	public void setLoginPane() {
		if(!lc.isLoggedIn()) {
			setNewRoot(loginPane);
			mc.getStage().setHeight(loginPane.windowMaxHeight);
			mc.getStage().setWidth(loginPane.windowMaxWidth);
			mc.getStage().centerOnScreen();
		}

		
	}
}
