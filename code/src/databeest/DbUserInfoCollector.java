package databeest;

//Stef
public class DbUserInfoCollector {
	
	private DataBaseApplication dataBaseApplication;
	
	private String query;
	
	
	public DbUserInfoCollector(DataBaseApplication dataBaseApplication)
	{
		this.dataBaseApplication = dataBaseApplication;
	}
	
	public void CreateAccount(String username, String password)
	{
		query = "INSERT INTO account (username, password) VALUES ('" + username + "', '" + password + "');";
		dataBaseApplication.insertQuery(query);
	}
	
	
	public boolean CheckUsername(String username)
	{
		if(GetPassword(username) == null)
		{
			return false;
		} else {
			return true;
		}
	}
	
	public String GetPassword(String username)
	{
		String password;
		password = dataBaseApplication.readPassword(username);
		return password;
	}
	
	
}
