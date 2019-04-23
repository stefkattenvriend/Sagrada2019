package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class PatternCardPane extends Pane{
	
	/* INFO */
	/* met Stackpane is het mogelijk om 
	 * panes op elkaar te plaatsen.
	 * Dit moet nog gebeuren.
	 */
	private StackPane patternCard;
	private TilePane patternCardView;
	private TilePane patternCardDice;
	
	public PatternCardPane() {
		setUp();
	}

	private void setUp() {
		setPaneSize();
		setPatternCard();
		aanduiding();
	}

	private void setPatternCard() {
		patternCard = new StackPane();
		patternCardView = new TilePane();
		patternCardView.setMinSize(GamePane.windowMaxWidth / 3, 330);
		patternCardView.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		
		patternCardDice = new TilePane();
		patternCardDice.setMinSize(GamePane.windowMaxWidth / 3, 330);
//		patternCardDice.getChildren().add(text);
//		patternCardDice.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
		patternCard.getChildren().addAll(patternCardView, patternCardDice);
		getChildren().add(patternCard);
	}

	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3), 330);
	}
	
	public void aanduiding() { // deze method wordt uiteindelijk verwijderd
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText("PatternCard");
		getChildren().addAll(text);
	}
}
