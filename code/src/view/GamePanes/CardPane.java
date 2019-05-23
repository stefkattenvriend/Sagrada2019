package view.GamePanes;

import controller.CardsController;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CardPane extends Pane{
	private int cardNr;
	private CardsController cc;
	
	public CardPane(ImageView background, boolean toolCard, CardsController cardsController, int cardNr) {
		cc = cardsController;
		this.cardNr = cardNr;
		setPrefSize((GamePane.windowMaxWidth / 6), (GamePane.windowMaxHeight - 40) / 3);
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		this.getChildren().addAll(background);
		background.fitHeightProperty().bind(this.heightProperty());
		if(toolCard) {
			this.setOnMouseClicked(e -> cc.useCard(cardNr));
		}
	}
	
	public int getCardNr() {
		return cardNr;
	}
}
