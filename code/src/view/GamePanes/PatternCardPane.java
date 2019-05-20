package view.GamePanes;
import controller.DiceHolderController;
import controller.PatterncardController;
import helpers.DiceHolderType;
import helpers.PatterncardType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class PatternCardPane extends Pane{//misschien hernoemen naar bord pane? pattern card moet zich op het bord bevinden toch? ~Rens
	
	private StackPane patternCard;
	private TilePane patternCardView;
	private TilePane patternCardDice;
	private DiceHolderController dhc;
	private PatterncardController pcc;
	
	public PatternCardPane(DiceHolderController dhc, PatterncardController pcc) {
		this.dhc = dhc;
		this.pcc = pcc;
		setUp();
	}

	private void setUp() {
		setPaneSize();
		setPatternCard();
		addDiceHolders();
		addPatternCard(4);
		//aanduiding();
	}

	private void setPatternCard() {
		patternCard = new StackPane();
		patternCardView = new TilePane();
		patternCardView.setMinSize(GamePane.windowMaxWidth / 3, 330);
		patternCardView.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		patternCardDice = new TilePane();
		patternCardDice.setMinSize(GamePane.windowMaxWidth / 3, 330);
		patternCard.getChildren().addAll(patternCardView, patternCardDice);
		getChildren().add(patternCard);
	}

	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3), 330);
	}
	
	private void addDiceHolders() {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 6; j++) {
				double size = ((GamePane.windowMaxWidth / 3) / 5) - 1; 
			patternCardDice.getChildren().add(dhc.CreateDiceHolder(size, j, i, DiceHolderType.PLAYERWINDOW));
			}	
		}
	}
	
	private void addPatternCard(int pcnumber) {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 6; j++) {
				double size = ((GamePane.windowMaxWidth / 3) / 5) - 1; 
			patternCardView.getChildren().add(pcc.PatterncardCreate(j, i, pcnumber, (int)size, PatterncardType.PLAYER));
			}	
		}
	}
	
	public void aanduiding() { // deze method wordt uiteindelijk verwijderd
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText("PatternCard");
		getChildren().addAll(text);
	}
}
