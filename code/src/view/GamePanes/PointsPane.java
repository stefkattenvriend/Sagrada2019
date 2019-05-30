package view.GamePanes;
import controller.PointsController;
//joery
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PointsPane extends Pane{
	
	private double personalAttributesHeight = 75;
	private PointsController pc;
	
	public PointsPane(PointsController pc) {
		this.pc = pc;
		setUp();
	}

	private void setUp() {
		setPaneSize();
		Label label = new Label("Your score is:");
		
		
	}

	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3) / 3, personalAttributesHeight);	

	}
	
	public void aanduiding() { // deze method wordt uiteindelijk verwijderd
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText("Points");
		getChildren().addAll(text);
	}
}
