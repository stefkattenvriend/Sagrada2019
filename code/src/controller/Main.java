package controller;

import databeest.DataBaseApplication;
import databeest.DbUserInfoCollector;
import javafx.application.Application;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.MyScene;

public class Main extends Application{

	//constants (tijdelijk om panelen aan te duiden)
	public final static Background CARDDISPLAYPANE = new Background(new BackgroundFill(Color.GREEN, null, null));
	public final static Background PLAYERPANE = new Background(new BackgroundFill(Color.BLUE, null, null));
	public final static Background ENEMYPANE = new Background(new BackgroundFill(Color.RED, null, null));
	
	//instance variables
	private DbUserInfoCollector dbUserInfoCollector;
	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		DataBaseApplication databeest = new DataBaseApplication();
		if ((databeest.loadDataBaseDriver("com.mysql.cj.jdbc.Driver"))
				&& (databeest.makeConnection()))
		{
			databeest.doSomeQuerying();
			databeest.getPaternCard(1, 1, 1);
			databeest.getPaternCard(1, 2, 1);
			databeest.getPaternCard(1, 3, 1);
			databeest.getPaternCard(1, 4, 1);
			databeest.getPaternCard(1, 5, 1);
//			databeest.doSomeUpdating();
		}

		DbUserInfoCollector dbUserInfoCollector = new DbUserInfoCollector(databeest);
		
		DiceHolderController dhc = new DiceHolderController();
		PatterncardController pcc = new PatterncardController();
		LoginController lc = new LoginController(dbUserInfoCollector);
		
		MyScene myScene = new MyScene(dhc, pcc, lc); //gamecontroller wordt aangemaakt datgeen wat er nu instaat wordt in gamecontroller aangemaakt.

		
		// login test
//		lc.CheckLogin("bart", "");
//		lc.CreateAccount("kees", "kaas");
		
		
		// Basic stage stuff
		stage.setResizable(false);
		stage.setScene(myScene);
		stage.show();		
	}

}
