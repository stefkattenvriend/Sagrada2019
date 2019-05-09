package controller;

import databeest.DbUserInfoCollector;
import model.Account;
import view.LoginPane;

//Stef
public class LoginController {
	
	// Instance variables
	private DbUserInfoCollector dbUserInfoCollector;
	private Account account;
	private LoginPane loginPane;

	// Constructor
	public LoginController(DbUserInfoCollector dbUserInfoCollector)
	{
		this.dbUserInfoCollector = dbUserInfoCollector;
		loginPane = new LoginPane(this);
		account = new Account();
	}
	
	//Als accountgegevens kloppen, moet het inloggen
	public void login(String username, String password)
	{
		if(this.CheckLogin(username, password))
		{
			account.setCurrentAccount(username);
			System.out.println("Login gelukt");
		}
	}
	
	// Log uit
	public void logout()
	{
		account.setCurrentAccount(null);
		System.out.println("Logout gelukt");
	}

	
	// moet kijken of de username en password samen voorkomen in de tabel
	public boolean CheckLogin(String username, String password)
	{
		// username moet voorkomen in de database.
		if(this.CheckIfExist(username) && dbUserInfoCollector.GetPassword(username) == password)
		{
			// password moet samen met username in de zelfde rij voorkomen.
			System.out.println("Username komt overeen met password");
			return true;
		}
		else 
		{
			System.out.println("Onjuist gebruikersnaam of password");
			return false;
		}
	}

	// kijkt of de string alleen uit letters en cijfers bestaat
	public boolean IsAlphaNumeric(String s)
	{
		return s != null && s.toLowerCase().matches("^[a-z0-9]*$");
	}
	
	
	// kijkt of username bestaat
	private boolean CheckIfExist(String username)
	{
		return dbUserInfoCollector.CheckUsername(username);
	}
	

	// maakt een account aan als het aan de eisen voldoet
	public boolean CreateAccount(String username, String password) 
	{
		if (username.length() > 2 && password.length() > 2 && IsAlphaNumeric(username) && IsAlphaNumeric(password)) 
		{
			if (CheckIfExist(username.toLowerCase())) 
			{
				System.out.println("Username bestaat al!");
				return false;
			} else 
			{
				dbUserInfoCollector.CreateAccount(username.toLowerCase(), password.toLowerCase());
				System.out.println("Account aangemaakt");
				
				// log meteen in
				login(username, password);
				return true;
			}
		} else 
		{
			System.out.println("Gebruik 3 of meer letters of cijfers");
			return false;
		}
		
	}
}
