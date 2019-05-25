package view.GamePanes;

import controller.DiceHolderController;
import controller.PatterncardController;
import helpers.DiceHolderType;
import helpers.PatterncardType;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class EnemyWindow extends StackPane {

	private TilePane diceHolders;
	private TilePane patternCard;
	private DiceHolderController dhc;
	private DiceHolderType dht;
	private PatterncardController pcc;

	public EnemyWindow(DiceHolderType dht, DiceHolderController dhc, PatterncardController pcc) {
		diceHolders = new TilePane();
		patternCard = new TilePane();
		this.getChildren().add(patternCard);
		this.getChildren().add(diceHolders);
		this.pcc = pcc;
		this.dht = dht;
		this.dhc = dhc;
		setUp();
	}

	private void setUp() {
		// aanduiding();
		setMinSize((GamePane.windowMaxWidth / 3) / 2, GamePane.windowMaxHeight / 3);
		setMaxSize((GamePane.windowMaxWidth / 3) / 2, GamePane.windowMaxHeight / 3);
		addDiceHolders();
		addPatternCard();
	}

	private void aanduiding() { // deze method wordt uiteindelijk verwijderd
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); // aanduiding van
																									// chatvak
		Label text = new Label();
		text.setText("ENEMY");
		getChildren().addAll(text);
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

		int pcnumber = 5;
		PatterncardType pct;
		
		switch(dht) {
		
		case ENEMY1:
			pct = PatterncardType.ENEMY1;
			break;
			
		case ENEMY2 :
			pct = PatterncardType.ENEMY2;
			break;
			
		case ENEMY3: 
			pct = PatterncardType.ENEMY3;
			break;
		
		default:
			//dus als er geen pattern card wordt meegegeven
			return;
		}

		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 6; j++) {
				double size = (((GamePane.windowMaxWidth / 3) / 2) / 5) - 1;
				patternCard.getChildren()
						.add(pcc.PatterncardCreate(j, i, pcnumber, (int) size, pct));
			}
		}
	}
}
