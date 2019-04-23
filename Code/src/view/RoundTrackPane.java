package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class RoundTrackPane extends Pane{
	
	//instance
	private double roundTrackPaneHeight = 100;
	
	public RoundTrackPane() {
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		aanduiding();
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