package view.GamePanes;
//joery
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PaystoneHolder extends Pane{
	
	private double personalAttributesHeight = 75;
	
	public PaystoneHolder() {
		setUp();
	}

	private void setUp() {
		setPaneSize();
		aanduiding();	
	}

	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3) / 3, personalAttributesHeight);	
	}
	
	public void aanduiding() { // deze method wordt uiteindelijk verwijderd
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText("paystoneholder");
		getChildren().addAll(text);
	}
}
