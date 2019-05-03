package databeest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseApplication
{
	private Connection m_Conn;

	public DataBaseApplication()
	{
		m_Conn = null;
	}

	public boolean loadDataBaseDriver(String driverName)
	{
		try
		{
			// Load the JDBC driver
			Class.forName(driverName);
		} catch (ClassNotFoundException e)
		{
			// Could not find the database driver
			System.out.println("ClassNotFoundException : " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean makeConnection()
	{
		try
		{
			m_Conn = DriverManager
					.getConnection("jdbc:mysql://databases.aii.avans.nl/mwmastbe_db2?user=rcaasper&password=Ab12345");
			System.out.println("So far, so good...");
		} catch (SQLException ex)
		{
			// handle any errors
			System.out.println("Houston, we've had a problem...");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}
	
	//doe een query
	public void doSomeQuerying()
	{
		Statement stmt = null;
		String query = "SELECT * FROM color LIMIT 3;";
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console
			while (rs.next())
			{
				String name = rs.getString(1);
//				String satOf = rs.getString(2);
				System.out.println(" - " + name);
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void doSomeUpdating()
	{
		Statement stmt = null;
		String query = "INSERT INTO `reis` (`reisnr`, `vertrekdatum`, `reisduur`, `prijs`) VALUES\r\n" + 
				"(39, '2006-01-01 00:00:00', 10, 4.50);";
		
		try
		{
			stmt = m_Conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			System.out.println(rs);
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	//Stef
	// voor een insert Query
	public void insertQuery(String query)
	{
		Statement stmt = null;

		try
		{
			stmt = m_Conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			System.out.println(rs);
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	// voor een read Query (SELECT FROM ofzo)
	public ResultSet readQuery(String query)
	{
		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			stmt = m_Conn.createStatement();
			rs = stmt.executeQuery(query);
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		return rs;
	}
	
}
