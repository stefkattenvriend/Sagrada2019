package model;

//Stef

public class AccountModel {
	
	// Instance variables
	private String currentAccount;
	
	// Constructor
	public AccountModel()
	{
		currentAccount = null;
	}
	
	public void setCurrentAccount(String username)
	{
		this.currentAccount = username;
	}
	
	public String getCurrentAccount()
	{
		return currentAccount;
	}
	
	
}
