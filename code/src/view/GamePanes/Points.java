package view.GamePanes;
import controller.PointsController;
//joery
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Points extends Pane{
	
	private double personalAttributesHeight = 75;
	
	public Points() {
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
		text.setText(" Points:");
		text.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
		
		Label textAmount = new Label();
		textAmount.setText(" " + 20); //moet worden gelinkt aan points berekening
		textAmount.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
		getChildren().addAll(text, textAmount);
	}
}
