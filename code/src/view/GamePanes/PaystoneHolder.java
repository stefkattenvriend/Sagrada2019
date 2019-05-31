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

public class PaystoneHolder extends Pane{
	
	private double personalAttributesHeight = 75;
	private PayStoneController psc;
	
	public PaystoneHolder(PayStoneController psc) {
		this.psc = psc;
		setUp();
		PayStonePlayerThread psp = new PayStonePlayerThread(psc, this);
		Thread p1 = new Thread(psp);
	}

	private void setUp() {
		setPaneSize();	
	}

	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3) / 3, personalAttributesHeight);	
	}
	
	public void setPaystones(int amount) {
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText(Integer.toString(amount));
		getChildren().addAll(text);
	}
}
