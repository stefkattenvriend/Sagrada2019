package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EnemyWindow extends Pane{
	
	public EnemyWindow() {
		setUp();
	}
	
	private void setUp() {
		aanduiding();
		setMinSize((GamePane.windowMaxWidth / 3) / 2, GamePane.windowMaxHeight / 3);
		setMaxSize((GamePane.windowMaxWidth / 3) / 2, GamePane.windowMaxHeight / 3);
	}
	
	private void aanduiding() { // deze method wordt uiteindelijk verwijderd
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText("ENEMY");
		getChildren().addAll(text);
	}
}
