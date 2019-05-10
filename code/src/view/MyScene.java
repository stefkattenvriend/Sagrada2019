package view;
import controller.DiceHolderController;
import controller.LoginController;
import controller.MasterController;
import controller.PatterncardController;
//joery
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import view.GamePanes.GamePane;

public class MyScene extends Scene{
	
	private MasterController mc;
	private LoginController lc;
	private MenuPane menuPane;
	private GamePane gamePane;
	private LoginPane loginPane;
	
	public MyScene(MasterController mc) {
		super(new Pane());
		this.mc = mc;
		
		gamePane = new GamePane(mc.gm);
		menuPane = new MenuPane(this, gamePane);
		loginPane = new LoginPane(lc);
		// hier moeten ook nog de registratie panes worden aangemaakt.
		// daarna is pas de menupane te zien. Er wordt geswitched met de method: setNewRoot.
		// via de constructor worden de aangemaakte classes doorgegeven.
		
		setNewRoot(menuPane);
	}

	public void setNewRoot(Pane pane) {
		setRoot(pane);
	}
}
