package model;

//Stef

public class Account {
	
	private String currentAccount;
	
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
