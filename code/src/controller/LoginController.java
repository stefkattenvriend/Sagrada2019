package controller;

import databeest.DbUserInfoCollector;
import model.Account;

//Stef
public class LoginController {
	
	// Instance variables
	private DbUserInfoCollector dbUserInfoCollector;
	private Account account;

	// Constructor
	public LoginController(DbUserInfoCollector dbUserInfoCollector)
	{
		this.dbUserInfoCollector = dbUserInfoCollector;
		
		account = new Account();
	}
	
	//Als accountgegevens kloppen, moet het inloggen
	public void login(String username, String password)
	{
		if(this.CheckLogin(username, password))
		{
			account.setCurrentAccount(username);
		}
		
	}

	
	// moet kijken of de username en password samen voorkomen in de tabel
	// TODO password check
	private boolean CheckLogin(String username, String password)
	{
		// username moet voorkomen in de database.
		if(this.CheckIfExist(username) /* && password komt overeen met username */)
		{
			// password moet samen met username in de zelfde rij voorkomen.
			return true;
		}
		else 
		{
			return false;
		}
	}

	
	// kijkt of username bestaat
	private boolean CheckIfExist(String username)
	{
		return dbUserInfoCollector.CheckUsername(username);
	}
	
	
	// moet kijken of de ingevulde username geldig is (bestaat het al? of zitten er gekke tekens in?)
	// returned true als het gelukt is.
	// TODO checksysteem
	public boolean CreateAccount(String username, String password) 
	{
		if (CheckIfExist(username)) 
		{
			System.out.println("Username bestaat al!");
			return false;
		} else 
		{
			dbUserInfoCollector.CreateAccount(username, password);
			System.out.println("Account aangemaakt");
			return true;
		}
		
	}
}
