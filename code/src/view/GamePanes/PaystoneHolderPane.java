package view.GamePanes;
import controller.PayStoneController;
import controller.PayStonePlayerThread;
//joery
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PaystoneHolderPane extends Pane{
	
	private double personalAttributesHeight = 75;
	private PayStoneController psc;
	private int amount;
	
	public PaystoneHolderPane(PayStoneController psc, int amount) {
		this.amount = amount;
		this.psc = psc;
		setUp(amount);
	}

	private void setUp(int amount) {
		setPaneSize();	
		setPaystones(amount);
	}


	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3) / 3, personalAttributesHeight);	
	}
	
	private void setPaystones(int amount) {
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText(Integer.toString(amount));
		getChildren().addAll(text);
	}
}
