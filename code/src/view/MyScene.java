package view;
import controller.GameController;
import controller.LoginController;
import controller.MasterController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.GameModel;
import view.GamePanes.EndPane;
import view.GamePanes.GamePane;
import view.MenuPanes.MenuPane;

//joery
public class MyScene extends Scene{
	
	private MasterController mc;
	private LoginController lc;
	private GameController gc;
	private MenuPane menuPane;
	private GamePane gamePane;
	private LoginPane loginPane;
	private Pane root;
	private GameModel gameModel;
//	private Stage stage;
	
	public MyScene(MasterController mc, GameModel gameModel) {
		super(new Pane());
		this.mc = mc;
		this.gameModel = gameModel;
//		this.stage = stage;
		lc = mc.getLoginController();
		gc = mc.getGameController();
		
		root = new Pane();
		
		loginPane = new LoginPane(this, lc);
		

		// hier moeten ook nog de registratie panes worden aangemaakt.
		// daarna is pas de menupane te zien. Er wordt geswitched met de method: setNewRoot.
		// via de constructor worden de aangemaakte classes doorgegeven.
		
		mc.getStage().setTitle("Sagrada");
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
		gamePane = new GamePane(mc.getGameController(), this, lc, mc.getPayStoneController(), gameModel);
		setNewRoot(gamePane);
		mc.getStage().setHeight(gamePane.windowMaxHeight);
		mc.getStage().setWidth(gamePane.windowMaxWidth);
		mc.getStage().centerOnScreen();
	}
	
	public void setLayerPane() {
		
		LayerPane pcardChooser = new LayerPane(mc.getGameController().getLayerController(), mc.getGameController().getPatterncardController(), lc, this, gc);
		
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
	
	public void setEndPane()
	{
		setNewRoot(new EndPane(this, gc));
		mc.getStage().setHeight(gamePane.windowMaxHeight);
		mc.getStage().setWidth(gamePane.windowMaxWidth);
		mc.getStage().centerOnScreen();
	}
}
