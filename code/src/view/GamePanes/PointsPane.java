package view.GamePanes;
import controller.PointsController;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.MenuPanes.MenuPane;

//joery
public class PointsPane extends VBox {
	
	private double personalAttributesHeight = 75;
	private PointsController pc;
	private Label textAmount;
	
	public PointsPane(PointsController pc) {
		this.pc = pc;
		setUp();
	}

	private void setUp() {
		setPaneSize();
		aanduiding();
//		Label label = new Label("Your score is:");
		
		
	}

	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3) / 3, personalAttributesHeight);	

	}
	
	public void aanduiding() { // deze method wordt uiteindelijk verwijderd
//		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText(" Score:");
		text.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
		text.setMinWidth(MenuPane.paneWidth / 3);
		
		textAmount = new Label();
		
		//TODO het weergeven van de points
		textAmount.setText(pc.getTotalPoints(this)); //moet worden gelinkt aan points berekening
		
		
		textAmount.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
		textAmount.setMinWidth(MenuPane.paneWidth / 3);
		getChildren().addAll(text, textAmount);
	}
	
	public void setTextAmount() {
		textAmount.setText(pc.getTotalPoints(this));
	}
}
