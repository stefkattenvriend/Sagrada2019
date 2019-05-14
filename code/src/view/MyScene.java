package view;
import controller.LoginController;
import controller.MasterController;
//joery
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.GamePanes.GamePane;

public class MyScene extends Scene{
	
	private MasterController mc;
	private LoginController lc;
	private MenuPane menuPane;
	private GamePane gamePane;
	private LoginPane loginPane;
	private Pane root;
	private Stage stage;
	
	public MyScene(Stage stage, MasterController mc) {
		super(new Pane());
		this.mc = mc;
		this.stage = stage;
		
		lc = mc.lc;
		
		root = new Pane();
		
		
		menuPane = new MenuPane(this, gamePane);
		loginPane = new LoginPane(this, lc);
		// hier moeten ook nog de registratie panes worden aangemaakt.
		// daarna is pas de menupane te zien. Er wordt geswitched met de method: setNewRoot.
		// via de constructor worden de aangemaakte classes doorgegeven.
		
		setRoot(root);
		setNewRoot(loginPane);
	}

	public void setNewRoot(Pane pane) {
		root.getChildren().clear();
		root.getChildren().add(pane);
	}
	
	public void setMenuPane()
	{
		setNewRoot(menuPane);
		stage.setHeight(menuPane.windowMaxHeight);
		stage.setWidth(menuPane.windowMaxWidth);
		stage.centerOnScreen();
	}
}
