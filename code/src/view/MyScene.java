package view;
import controller.DiceHolderController;
import controller.PatterncardController;
//joery
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene{
	
	private DiceHolderController dhc;
	private PatterncardController dcc;
	private MenuPane menuPane;
	private GamePane gamePane;
	
	public MyScene(DiceHolderController dhc, PatterncardController dcc) {
		super(new Pane());
		this.dhc = dhc;
		this.dcc = dcc;
		
		gamePane = new GamePane(dhc, dcc);
		menuPane = new MenuPane(this, gamePane);
		// hier moeten ook nog de login en registratie panes worden aangemaakt.
		// daarna is pas de menupane te zien. Er wordt geswitched met de method: setNewRoot.
		// via de constructor worden de aangemaakte classes doorgegeven.
		
		setNewRoot(menuPane);
	}

	public void setNewRoot(Pane pane) {
		setRoot(pane);
	}
}
