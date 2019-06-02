package view.GamePanes;
import controller.DiceHolderController;
import controller.GameController;
import controller.LayerController;
import controller.LoginController;
import controller.PatterncardController;
import controller.PayStoneController;
import controller.PlayerController;
import controller.PointsController;
import controller.TurnController;
//joery
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.GameModel;
import view.MyScene;

public class GamePane extends StackPane {
	
	//constants
	public final static double windowMaxWidth = 1280;
	public final static double windowMaxHeight = 800;
	
	//instance
	private PlayerPane playerPane;
	private CardDisplayPane cardDisplayPane;
	private EnemyPane enemyPane;
	private GameController gc;
	private DiceHolderController dhc;
	private PatterncardController pcc;
	private BorderPane gamePane;
	private LayerController lyc;
	private MyScene myScene;
	private PointsController pc;
	private TurnController tc;
	private LoginController logc;
	private PayStoneController psc;
	private GameModel gameModel;

	
	public GamePane(GameController gameController, MyScene myScene, LoginController loginController, PayStoneController psc, GameModel gameModel) {
		logc = loginController;
		this.psc = psc;
		this.gc = gameController;
		this.myScene = myScene;
		this.dhc = gc.getDiceHolderController();
		this.pcc = gc.getPatterncardController();
		this.lyc = gc.getLayerController();
		this.pc = gc.getPointsController();
		this.tc = gc.getTurnController();
		this.gameModel = gameModel;
		
		
		setScreenSize();
		setUp();
		gc.setGamepane(this);
	}

	private void setUp() {
		gamePane = new BorderPane();
		playerPane = new PlayerPane(dhc, pcc, myScene, gc, pc, tc, psc);
		cardDisplayPane = new CardDisplayPane(gc.getCardsController(), gc);
		enemyPane = new EnemyPane(gc);
		gamePane.setLeft(cardDisplayPane);
		gamePane.setCenter(playerPane);
		gamePane.setRight(enemyPane);
		
		getChildren().add(gamePane);
	}
	

	
	public void setGamePane() {
		getChildren().clear();
		getChildren().add(gamePane);
	}
	
	public PlayerPane getPlayerPane() {
		return playerPane;
	}

	private void setScreenSize() {
		setPrefSize(windowMaxWidth, windowMaxHeight);
	}

	public void updatePC() {
		playerPane.updatePC();
		enemyPane.updatePC();
		
	}

	public void setMyColor(Color color) {
		playerPane.setMyColor(color);
	}
	
	public void updatePCid(int i) {
		playerPane.updatePCid(i);
		
	}

	public void redrawDice() {
		playerPane.redrawDice();
		enemyPane.redrawDice();
	}

}
