package view.GamePanes;
import controller.DiceHolderController;
import helpers.DiceHolderType;
//joery
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class RoundTrackPane extends FlowPane{
	
	//instance
	private double roundTrackPaneHeight = 100;
	private DiceHolderController dhc;
	
	public RoundTrackPane(DiceHolderController dhc) {
		this.dhc = dhc;
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		addDiceHolders();
		aanduiding();
	}
	
	private void addDiceHolders() {
		for (int i = 1; i < 11; i++) {
			double size = ((GamePane.windowMaxWidth / 3) / 10) - 1; 
			getChildren().add(dhc.CreateDiceHolder(size, i, 0, DiceHolderType.ROUNDTRACK));
			}
	}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, roundTrackPaneHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, roundTrackPaneHeight);
	}
	
	private void aanduiding() { // deze method wordt uiteindelijk verwijderd
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText("RoundTrackPane");
		getChildren().addAll(text);
	}
}