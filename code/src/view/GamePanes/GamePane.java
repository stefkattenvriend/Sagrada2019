package view.GamePanes;
import controller.DiceHolderController;
import controller.GameController;
import controller.LayerController;
import controller.PatterncardController;
//joery
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import view.LayerPane;

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
	
	public GamePane(GameController gameController) {
		this.gc = gameController;
		this.dhc = gc.getDiceHolderController();
		this.pcc = gc.getPatterncardController();
		this.lyc = gc.getLayerController();
		
		setScreenSize();
		setUp();
	}

	private void setUp() {
		gamePane = new BorderPane();
		playerPane = new PlayerPane(dhc, pcc);
		cardDisplayPane = new CardDisplayPane();
		enemyPane = new EnemyPane();
		gamePane.setLeft(cardDisplayPane);
		gamePane.setCenter(playerPane);
		gamePane.setRight(enemyPane);
		
		LayerPane pcardChooser = new LayerPane(lyc, pcc);
		
		//eerste ronde? open dan popup in if-statement
		setNewRoot(pcardChooser);
		
//		getChildren().add(gamePane);
	}
	
	private void setNewRoot(Pane pane) {
		if(true) {//hardcoded -> het is de eerste speelronde
			getChildren().addAll(gamePane, pane);	
		}
	}

	private void setScreenSize() {
		setPrefSize(windowMaxWidth, windowMaxHeight);
	}

}
