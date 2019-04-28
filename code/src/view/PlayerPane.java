package view;
//joery

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class PlayerPane extends VBox{
	
	private FlowPane diceSection;
	private RoundTrackPane roundTrackPane;
	private DiceOfferPane diceOfferPane;
	private HBox personalAttributes;
	private PaystoneHolder paystoneHolder;
	private Points points;
	private TargetColor targetColor;
	private PatternCardPane patternCardPane;
	
	public PlayerPane() {
		setBackground(controller.Main.PLAYERPANE); // aanduiding voor pane
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		setDiceSection();
		setPersonalAttributes();
		setPatternCard();
	}

	private void setPatternCard() {
		patternCardPane = new PatternCardPane();
		getChildren().add(patternCardPane);
	}

	private void setPersonalAttributes() {
		personalAttributes = new HBox();
		paystoneHolder = new PaystoneHolder();
		points = new Points();
		targetColor = new TargetColor();
		personalAttributes.getChildren().addAll(paystoneHolder, points, targetColor);
		personalAttributes.setMinHeight(75);
		getChildren().add(personalAttributes);
	}

	private void setDiceSection() {
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
