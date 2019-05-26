package view.GamePanes;

import controller.GameController;
import helpers.DiceHolderType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class EnemyPane extends BorderPane{
	
	private ChatPane chatPane;
	private EnemyWindow enemyWindow1;
	private EnemyWindow enemyWindow2;
	private EnemyWindow enemyWindow3;
	private FlowPane flowPane;
	
	private GameController gameController;
	
	
	public EnemyPane(GameController gamecontroller) {
		gameController = gamecontroller;
		setUp();
		setBackground(controller.Main.ENEMYPANE); //aanduiding voor pane
	}

	private void setUp() {
		setPaneSize();
		
		chatPane = new ChatPane(gameController.getChatController(), gameController.getLoginController());
		flowPane = new FlowPane();
		enemyWindow1 = new EnemyWindow(DiceHolderType.ENEMY1, gameController);
		enemyWindow2 = new EnemyWindow(DiceHolderType.ENEMY2, gameController);
		enemyWindow3 = new EnemyWindow(DiceHolderType.ENEMY3, gameController);
		flowPane.getChildren().addAll(enemyWindow1, enemyWindow2, enemyWindow3);
		setLeft(flowPane);
		setRight(chatPane);
	}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}
	
	

}
