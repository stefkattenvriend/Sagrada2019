package controller;

import java.util.Scanner;

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
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("enter an integer");
		String input = keyboard.toString();

		switch(input) {
		case "freeze": 
			GamePane.
			break;
		case "test":
			System.out.println("test");
			break;
		}
		
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
		LoginController loginController = new LoginController(dbUserInfoCollector);
		
		
		
		
		// login test
//		loginController.CheckLogin("bart", "");
		
//		loginController.CreateAccount("mooie", "makker");
		
		
		
		
		launch(args);
		
		//Debug
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		MasterController mc = new MasterController();
	
		MyScene myScene = new MyScene(stage, mc); //gamecontroller wordt aangemaakt datgeen wat er nu instaat wordt in gamecontroller aangemaakt.
		
		// Basic stage stuff
		stage.setResizable(false);
		stage.setScene(myScene);
		stage.show();		
	}

}
