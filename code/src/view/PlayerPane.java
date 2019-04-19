package view;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class PlayerPane extends VBox{
	
	private FlowPane diceSection;
	private RoundTrackPane roundTrackPane;
	private DiceOfferPane diceOfferPane;
	
	public PlayerPane() {
		super();
		setBackground(controller.Main.PLAYERPANE); // aanduiding voor pane
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		diceSection = new FlowPane();
		roundTrackPane = new RoundTrackPane();
		diceOfferPane = new DiceOfferPane();

		diceSection.getChildren().addAll(roundTrackPane, diceOfferPane);
		getChildren().add(diceSection);
	}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}

}
