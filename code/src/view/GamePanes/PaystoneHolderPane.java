package view.GamePanes;
import controller.PayStoneController;
//joery
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PaystoneHolderPane extends VBox{
	
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
//		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText(" Betaalstenen:");
		text.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
		
		Label textAmount = new Label();
		textAmount.setText(" " + Integer.toString(amount));
		textAmount.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
		getChildren().addAll(text, textAmount);
	}
}
