package model;

//Stef

public class Account {
	
	// Instance variables
	private String currentAccount;
	
	// Constructor
	public Account()
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
