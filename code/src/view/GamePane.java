package view;
import controller.DiceHolderController;
import controller.PatterncardController;
//joery
import javafx.scene.layout.BorderPane;

public class GamePane extends BorderPane {
	
	//constants
	public final static double windowMaxWidth = 1280;
	public final static double windowMaxHeight = 800;
	
	//instance
	private PlayerPane playerPane;
	private CardDisplayPane cardDisplayPane; 
	private EnemyPane enemyPane;
	private DiceHolderController dhc;
	private PatterncardController dcc;
	
	public GamePane(DiceHolderController dhc, PatterncardController dcc) {
		this.dhc = dhc;
		this.dcc = dcc;
		setScreenSize();
		setUp();
	}

	private void setUp() {
		playerPane = new PlayerPane(dhc, dcc);
		cardDisplayPane = new CardDisplayPane();
		enemyPane = new EnemyPane();
		setLeft(cardDisplayPane);
		setCenter(playerPane);
		setRight(enemyPane);
	}
	
	private void setScreenSize() {
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
	}

}
