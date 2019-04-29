package view;
import controller.DiceHolderController;
import controller.PatterncardController;
//joery
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene{
	
	private DiceHolderController dhc;
	private PatterncardController dcc;
	
	public MyScene(DiceHolderController dhc, PatterncardController dcc) {
		super(new Pane());
		this.dhc = dhc;
		this.dcc = dcc;
		
		setRoot(new GamePane(dhc, dcc));//de root begint natuurlijk eerst met het menu.
	}
}
