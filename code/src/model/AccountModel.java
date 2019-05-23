package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
	
	
	//binding
		private StringProperty usernameText = new SimpleStringProperty(this,
				"usernameText", "empty");
		
		// setter
		public void setUsernameText(String newtext) {
			usernameText.set(newtext);
		}

		// getter
		public String getMessageText() {
			return usernameText.get();
		}

		// property accessor
		public final StringProperty usernameTextProperty() {
			return usernameText;
		}
	
	
}
