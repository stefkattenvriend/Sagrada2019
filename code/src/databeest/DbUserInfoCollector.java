package databeest;

import java.sql.ResultSet;
import java.sql.SQLException;

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
		query = "INSERT INTO mwmastbe_db2.account (username, password) VALUES ('" + username + "', '" + password + "');";
		dataBaseApplication.insertQuery(query);
	}
	
	public boolean CheckUsername(String username)
	{
		query = "SELECT * FROM mwmastbe_bd2.account WHERE username = '" + username + "';";
		ResultSet rs;
		rs = dataBaseApplication.readQuery(query);
		
		try {
			if(rs.getString(0) != null)
			{
				System.out.println(rs.getString(0));
				return true;
			}

		} catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	
}
