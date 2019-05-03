package controller;

import databeest.DbUserInfoCollector;
import model.Account;

//Stef
public class LoginController {
	
	private DbUserInfoCollector dbUserInfoCollector;
	
	private Account account;

	public LoginController(DbUserInfoCollector dbUserInfoCollector)
	{
		this.dbUserInfoCollector = dbUserInfoCollector;
		
		account = new Account();
	}
	
	public void login(String username, String password)
	{
		if(this.CheckLogin(username, password))
		{
			account.setCurrentAccount(username);
		}
		
	}

	private boolean CheckLogin(String username, String password)
	{
		// username moet voorkomen in de database.
		if(this.CheckIfExist(username))
		{
			// password moet samen met username in de zelfde rij voorkomen.
			System.out.println("Succes: Username '"+ username + "' does exist");
			return true;
		}
		else 
		{
			System.out.println("Error: Username '"+ username + "' does not exist");
			return false;
		}
	}

	private boolean CheckIfExist(String username)
	{
		return dbUserInfoCollector.CheckUsername(username);
	}
	
	public void CreateAccount(String username, String password) 
	{
		dbUserInfoCollector.CreateAccount(username, password);
	}
}
