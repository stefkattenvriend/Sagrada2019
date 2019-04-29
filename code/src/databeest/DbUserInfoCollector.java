package databeest;

//Stef
public class DbUserInfoCollector {
	
	private String query;
	public DbUserInfoCollector()
	{
		
	}
	
	public String CreateAccount(String username, String password)
	{
		query = "INSERT INTO mwmastbe_db2.account (username, password) VALUES ('" + username + "', '" + password + "');";
		return query;
	}
}
