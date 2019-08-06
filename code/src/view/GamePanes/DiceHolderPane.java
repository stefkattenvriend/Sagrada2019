package view.GamePanes;

import controller.DiceHolderController;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DiceHolderPane extends BorderPane {

	private DiceHolderController dhc;
	private Button numHigher;
	private Button numLower;
	private Button confirm;
	private BorderPane numPlusAndMin;
	private BorderPane colPlusAndMin;
	private double size;
	private Button colHigher;
	private Button colLower;

	public DiceHolderPane(double size, DiceHolderController dhc, int id, boolean diceUpdate) {
		super();
		this.size = size;
		this.dhc = dhc;
		this.setPrefSize(size, size);
		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(3))));
		this.setOnMouseEntered(e -> dhc.DiceHolderHover(this));
		this.setOnMouseExited(e -> dhc.DiceHolderLeaveHover(this));
		this.setOnMouseClicked(e -> dhc.DiceHolderClick(this));

		if (diceUpdate) {
			placeDice(id);
		}
		
	}

	public void updateDiceHolderPane() {
		getChildren().clear();
	}

	public void placeDice(int id) {
		this.setCenter(dhc.getDice(id));

	}

	public void addPlusAndMinusAndColor() {
		numPlusAndMin = new BorderPane();
		numPlusAndMin.setPrefSize(size / 10, size);
		
		//start number buttons
		numHigher = new Button("+");
		numHigher.setMaxSize(size / 10, size / 2);
		numHigher.setFont(Font.font("Verdana", 10));
		numPlusAndMin.setTop(numHigher);

		numLower = new Button("-");
		numLower.setMaxSize(size / 10, size / 2);
		numLower.setFont(Font.font("Verdana", 10));
		numPlusAndMin.setBottom(numLower);

		setLeft(numPlusAndMin);
		
		numHigher.setOnMouseClicked(e -> dhc.higherClicked(false));
		numLower.setOnMouseClicked(e -> dhc.lowerClicked(false));
		
		//start color buttons
		colPlusAndMin = new BorderPane();
		colPlusAndMin.setPrefSize(size / 10, size);
		
		colHigher = new Button("+");
		colHigher.setMaxSize(size / 10, size / 2);
		colHigher.setFont(Font.font("Verdana", 10));
		colPlusAndMin.setTop(colHigher);

		colLower = new Button("-");
		colLower.setMaxSize(size / 10, size / 2);
		colLower.setFont(Font.font("Verdana", 10));
		colPlusAndMin.setBottom(colLower);

		setRight(colPlusAndMin);
		
		colHigher.setOnMouseClicked(e -> dhc.higherClicked1());
		colLower.setOnMouseClicked(e -> dhc.lowerClicked1());
	
		//start confirm button
		confirm = new Button();
		confirm.setMaxSize(size, size / 10);
		
		setBottom(confirm);
		confirm.setOnMouseClicked(e -> confirm());
	}

	public void addPlusAndMinus(int dienr) {
		if (dienr != 6) {
			numHigher = new Button("+");
			numHigher.setPrefSize(size / 10, size);
			numHigher.setFont(Font.font("Verdana", 10));
			this.setRight(numHigher);
			numHigher.setOnMouseClicked(e -> higherClicked());
		}
		if (dienr != 1) {
			numLower = new Button("-");
			numLower.setPrefSize(size / 10, size);
			numLower.setFont(Font.font("Verdana", 10));
			this.setLeft(numLower);
			numLower.setOnMouseClicked(e -> lowerClicked());
		}
	}


	private void higherClicked() {
		this.getChildren().removeAll(numHigher, numLower);
		dhc.higherClicked(true);
	}

	private void lowerClicked() {
		this.getChildren().removeAll(numHigher, numLower);
		dhc.lowerClicked(true);
	}
	
	private void confirm() {
		this.getChildren().removeAll(colPlusAndMin, numPlusAndMin, confirm);
		dhc.confirm();
	}

}
