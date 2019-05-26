package view.GamePanes;
import controller.DiceHolderController;
import controller.GameController;
import controller.LayerController;
import controller.PatterncardController;
import controller.PointsController;
//joery
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import view.LayerPane;
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
	
	public GamePane(GameController gameController, MyScene myScene) {
		this.gc = gameController;
		this.myScene = myScene;
		this.dhc = gc.getDiceHolderController();
		this.pcc = gc.getPatterncardController();
		this.lyc = gc.getLayerController();
		this.pc = gc.getPointsController();
		
		
		setScreenSize();
		setUp();
	}

	private void setUp() {
		gamePane = new BorderPane();
		playerPane = new PlayerPane(dhc, pcc, myScene, gc, pc);
		cardDisplayPane = new CardDisplayPane(gc.getCardsController());
		enemyPane = new EnemyPane(gc);
		gamePane.setLeft(cardDisplayPane);
		gamePane.setCenter(playerPane);
		gamePane.setRight(enemyPane);
		
		LayerPane pcardChooser = new LayerPane(lyc, pcc);
		
		//eerste ronde? open dan popup in if-statement
		setNewRoot(pcardChooser);
		
		getChildren().add(gamePane);
	}
	
	private void setNewRoot(Pane pane) {
		if(false) {//hardcoded -> het is de eerste speelronde
			getChildren().addAll(gamePane, pane);	
		}
	}
	
	public PlayerPane getPlayerPane() {
		return playerPane;
	}

	private void setScreenSize() {
		setPrefSize(windowMaxWidth, windowMaxHeight);
	}

}
