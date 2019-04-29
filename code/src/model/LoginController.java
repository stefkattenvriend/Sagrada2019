package model;

//Stef
public class LoginController {
	
	private Account account;

	public LoginController()
	{
		account = new Account();
	}
	
	public void login(String username, String password)
	{
		account.CheckLogin(username, password);
		
	}
	
	public void register()
	{
		
	}
	
}
