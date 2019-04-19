package view;

import javafx.scene.layout.BorderPane;

public class GamePane extends BorderPane {
	
	//constants
	public final static double windowMaxWidth = 1280;
	public final static double windowMaxHeight = 800;
	
	//instance
	private PlayerPane playerPane;
	private CardDisplayPane cardDisplayPane; 
	private EnemyPane enemyPane;
	
	public GamePane() {
		setScreenSize();
		setUp();
	}

	private void setUp() {
		playerPane = new PlayerPane();
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
