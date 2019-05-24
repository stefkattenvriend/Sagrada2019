package view.GamePanes;

import controller.CardsController;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class CardPane extends FlowPane{
	private int cardNr;
	private CardsController cc;
	
	public CardPane(ImageView background, boolean toolCard, CardsController cardsController, int cardNr) {
		cc = cardsController;
		this.cardNr = cardNr;
		setPrefSize((GamePane.windowMaxWidth / 6), (GamePane.windowMaxHeight - 40) / 3);
		this.getChildren().addAll(background);
		background.fitHeightProperty().bind(this.heightProperty());
		if(toolCard) {
			this.setOnMouseClicked(e -> cc.useCard(cardNr));
		}
	}
	
	public void addPlayerPayStone() {
		PlayerPayStone pps = new PlayerPayStone(this);
		this.getChildren().addAll(pps);
	}
	
	public int getCardNr() {
		return cardNr;
	}
	
}
