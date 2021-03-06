package view.GamePanes;
import controller.DiceHolderController;
import helpers.DiceHolderType;
//joery
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class DiceOfferPane extends FlowPane{
		
		private DiceHolderController dhc;
	
	public DiceOfferPane(DiceHolderController dhc) {
		this.dhc = dhc;
		setUp();
		
		
	}
	
	private void setUp() {
		setPaneSize();
		//aanduiding();//engels
		addDiceHolders();
		
		addDie();
		}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, 200);
		setMaxSize(GamePane.windowMaxWidth / 3, 200);
	}
	
	private void aanduiding() { //TODO deze method wordt uiteindelijk verwijderd
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText("DiceOfferPane");
		getChildren().addAll(text);
	}
	
	private void addDiceHolders() {
		for (int i = 1; i < 10; i++) {
			double size = ((GamePane.windowMaxWidth / 3) / 5) - 1; 
			this.getChildren().add(dhc.CreateDiceHolder(size, i, 0, DiceHolderType.OFFER));
			}
	}
	
	private void addDie() {//test methode wordt later verwijderdt
		dhc.addDie(DiceHolderType.OFFER, 1, 0, 6);
		dhc.addDie(DiceHolderType.OFFER, 2, 0, 73);
		dhc.addDie(DiceHolderType.OFFER, 3, 0, 42);
		dhc.addDie(DiceHolderType.OFFER, 4, 0, 90);
	}

	public void redrawDice() {
		this.getChildren().clear();
			for (int x = 1; x < 10; x++) {
					this.getChildren().add(dhc.getPlayerWindowDiceHolders(x, 0, DiceHolderType.OFFER));	
		}
	}
	}