package view;
import javafx.scene.layout.TilePane;

public class CardDisplayPane extends TilePane{

	private CardPane[] toolCards;
	private CardPane[] targetCards;
	
	public CardDisplayPane() {
		setUp();
		setBackground(controller.Main.CARDDISPLAYPANE); // aanduiding voor pane
	}

	private void setUp() {
		setPaneSize();
		toolCards = new CardPane[3];
		targetCards = new CardPane[3];	
	}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}
}
