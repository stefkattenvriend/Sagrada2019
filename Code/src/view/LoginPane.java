package view;

import controller.LoginController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
// DOORRR Tesssssss
public class LoginPane extends VBox{
	//constants
	public final static double windowMaxWidth = 300;
	public final static double windowMaxHeight = 400;
	
	// instance variables
	private Button loginButton;
	private Button registerButton;
	private LoginController controller;
	private MyScene myScene;
	private RegisterPane registerPane;
	private TextField accountField;
	private TextField passwordField;
	
	public LoginPane(MyScene myScene, LoginController controller) {
		this.controller = controller;
		this.myScene = myScene;
		registerPane = new RegisterPane(myScene, controller);
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		accountField = new TextField();
		passwordField = new TextField();
		accountField.setMinSize(100, 30);
		accountField.setMaxSize(100, 30);
		passwordField.setMinSize(100, 30);
		passwordField.setMaxSize(100, 30);
		Label accountLabel = new Label("Account: ");
		Label passwordLabel = new Label("Wachtwoord: ");
		accountLabel.setTextFill(Color.DARKRED);
		accountLabel.setFont(Font.font("", FontPosture.ITALIC, 20));
		passwordLabel.setTextFill(Color.DARKRED);
		passwordLabel.setFont(Font.font("", FontPosture.ITALIC, 20));
		createButtons();
		getChildren().addAll(accountLabel, accountField, passwordLabel, passwordField, loginButton, registerButton);
	}


	private void createButtons() {
		loginButton = new Button("Inloggen");
		registerButton = new Button("Registreren");
		loginButton.setOnAction(e -> login());
		
		//TODO
//		registreerButton.setOnAction();     > Moet naar registreerPane gaan
		//tijdelijk:
		registerButton.setOnAction(e -> register());
	}
	
	private void login()
	{
		if(controller.login(accountField.getText(), passwordField.getText()))
		{
			myScene.setMenuPane();
		}
		else 
		{
			System.out.println("kan niet inloggen");
		}
		
	}
	
	private void register()
	{
		myScene.setNewRoot(registerPane);
	}
	

}
