package view.GamePanes;
import controller.DiceHolderController;
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
	private DiceHolderController dhc;
	private PatterncardController dcc;
	private BorderPane gamePane;
	
	public GamePane(DiceHolderController dhc, PatterncardController dcc) {
		this.dhc = dhc;
		this.dcc = dcc;
		setScreenSize();
		setUp();
	}

	private void setUp() {
		gamePane = new BorderPane();
		playerPane = new PlayerPane(dhc, dcc);
		cardDisplayPane = new CardDisplayPane();
		enemyPane = new EnemyPane();
		gamePane.setLeft(cardDisplayPane);
		gamePane.setCenter(playerPane);
		gamePane.setRight(enemyPane);
		
		LayerPane pcardChooser = new LayerPane();
		
		//eerste ronde? open dan popup in if-statement
		setNewRoot(pcardChooser);
		
		getChildren().add(gamePane);
	}
	
	private void setNewRoot(Pane pane) {
		/*if(false) {//hardcoded -> het is de eerste speelronde
			getChildren().addAll(gamePane, pane);	
		}*/
	}

	private void setScreenSize() {
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
	}

}
