package view.GamePanes;

import controller.DiceHolderController;
import controller.GameController;
import controller.PatterncardController;
import helpers.DiceHolderType;
import helpers.PatterncardType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.PlayerModel;

public class EnemyWindow extends VBox {

	private TilePane diceHolders;
	private TilePane patternCard;
	private VBox enemyInfo;// komt de enemy info in: Naam, publieke score, betaalstenen ~ Rens
	private StackPane layers;
	private DiceHolderController dhc;
	private DiceHolderType dht;
	private PatterncardController pcc;
	private GameController gc;
	private PlayerModel enemy;
	private boolean currentPlayer = true;
	
	private Label turn;
	

	public EnemyWindow(DiceHolderType dht, GameController gc) {
		turn = new Label();
		layers = new StackPane();
		diceHolders = new TilePane();
		patternCard = new TilePane();
		enemyInfo = new VBox();
		layers.getChildren().add(patternCard);
		layers.getChildren().add(diceHolders);
		this.getChildren().add(layers);
		this.gc = gc;
		this.pcc = gc.getPatterncardController();// later weghalen
		this.dhc = gc.getDiceHolderController();
		this.dht = dht;
		setUp();
	}

	private void setUp() {
		// aanduiding();
		setMinSize((GamePane.windowMaxWidth / 3) / 2, GamePane.windowMaxHeight / 3);
		setMaxSize((GamePane.windowMaxWidth / 3) / 2, GamePane.windowMaxHeight / 3);
		getPlayerModel();
		createEnemyInfo();
		addDiceHolders();
		addPatternCard();
	}

	private void createEnemyInfo() {// enemy info moet later worden afgemaakt(en worden geupdate) ~ Rens
		enemyInfo.setPrefSize((GamePane.windowMaxWidth / 3) / 2, (GamePane.windowMaxHeight / 3) / 3);
//		setColor("-fx-background-color: rgba(0, 255, 0, 0.6);");
		enemyInfo.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
//		enemyInfo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		if (enemy != null) {
			Label text = new Label();
			text.setText(enemy.getUsername());
			
//			turn.setText("Aan de beurt: nee");
			if(gc.getTurnController().getCurrentplayer()==enemy) {
//				turn.setText("Aan de beurt: ja");
//				enemyInfo.setStyle("-fx-background-color: rgba(0, 255, 0, 0.7);");
				currentPlayer = true;
			}
			if(gc.getTurnController().getCurrentplayer()!=enemy) {
//				turn.setText("Aan de beurt: nee");
//				enemyInfo.setStyle("-fx-background-color: rgba(255, 0, 0, 0.7);");
//				text.setText(enemy.getUsername());
				currentPlayer = false;
			}
			else {
				System.out.println("enemy = null :(");
			}
			
			enemyInfo.getChildren().addAll(text, turn);
			
		}else {
//			System.out.println("hier");
			turn.setText("Howdy Doody");
		}
		
		if(currentPlayer) {
			enemyInfo.setBorder(new Border(new BorderStroke(Color.BLACK, null, null, new BorderWidths(5))));
			turn.setTextFill(Color.YELLOW);
//			System.out.println("howdeedoodie");
		}
		else {
			enemyInfo.setBorder(null);
			turn.setTextFill(Color.LIME);
//			System.out.println("howdeedeedie");
			
		this.getChildren().add(enemyInfo);
		}
	}

	public void updateColor() {
		
		
		getPlayerModel();
	}
	
	public void setColor(Color color) {
		enemyInfo.setBackground(new Background(new BackgroundFill(color, null, null)));
	}

	private void getPlayerModel() {
		for (int i = 0; i < gc.getGm().getPma().length; i++) {
			if (dht == gc.getGm().getPma()[i].getDht()) {
				this.enemy = gc.getGm().getPma()[i];
				
			}
			
//			if (gc.getGm().getPma()[i].isCurrentPlayer()) {
//				setColor(Color.LIGHTGREEN);
//			} else if(!gc.getGm().getPma()[i].isCurrentPlayer()) {
//				setColor(Color.PINK);
//			}
		}

	}


	private void addDiceHolders() {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 6; j++) {
				double size = (((GamePane.windowMaxWidth / 3) / 2) / 5) - 1;
				diceHolders.getChildren().add(dhc.CreateDiceHolder(size, j, i, dht));
			}
		}
	}

	private void addPatternCard() {

		patternCard.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		// patternCard.setMaxSize((GamePane.windowMaxWidth / 3) / 2,
		// GamePane.windowMaxHeight / 6);

		int pcnumber;

		if (enemy != null) {
			pcnumber = enemy.getPatid();
		} else {
			pcnumber = 0;
		}

		if (pcnumber != 0) {
			PatterncardType pct;

			switch (dht) {

			case ENEMY1:
				pct = PatterncardType.ENEMY1;
				break;

			case ENEMY2:
				pct = PatterncardType.ENEMY2;
				break;

			case ENEMY3:
				pct = PatterncardType.ENEMY3;
				break;

			default:
				// dus als er geen pattern card wordt meegegeven
				return;
			}

			for (int i = 1; i < 5; i++) {
				for (int j = 1; j < 6; j++) {
					double size = (((GamePane.windowMaxWidth / 3) / 2) / 5) - 1;
					patternCard.getChildren().add(pcc.PatterncardCreate(j, i, pcnumber, (int) size, pct));
				}
			}

		} else {
			return;
		}

	}

	public void updatePC() {
		patternCard.getChildren().clear();
		addPatternCard();
	}

	public void redrawDice() {
		diceHolders.getChildren().clear();
		for (int y = 1; y < 5; y++) {
			for (int x = 1; x < 6; x++) {
					diceHolders.getChildren().add(dhc.getPlayerWindowDiceHolders(x, y, dht));
			}	
		}
	}
}
