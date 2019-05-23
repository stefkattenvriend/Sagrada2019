package databeest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		String query = "SELECT * FROM account LIMIT 3;";// Je query
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console
			while (rs.next())
			{
				String name = rs.getString(1);	// De eerste kolom van de resultaten van jou query.
				String pw = rs.getString(2);	// De tweede kolom van jouw resultaten.
//				String drie = rs.getString(3);  // Eventueel de derde kolom.
				System.out.println("name: 	  - " + name + "\npassword: - " + pw + "\n");
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
		String query = "INSERT INTO `chatline` (`player_idplayer`, `time`, `message`) VALUES\r\n" + 
				"(39, '2006-01-01 00:00:00', 'hello');";
		
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
			System.out.println(rs);// Deze maakt de 1 in de console :D
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}

	
	
	// Alle read query's
	
	public String readPassword(String username) 
	{
		String password = null;
		String query;
		Statement stmt = null;
		query = "SELECT * FROM account WHERE username = '" + username + "';";
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console
			while (rs.next())
			{
				password = rs.getString(2).toLowerCase();
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return password;
	}
	
	public boolean CheckIfUsernameExists(String username)
	{
		String dbUsername = null;
		String query;
		Statement stmt = null;
		query = "SELECT * FROM account WHERE username = '" + username + "';";
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next())
			{
				dbUsername = rs.getString(1);
			}
			
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		
		if (dbUsername != null) 
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}
	
	public int getPaternCardValue(int pcnumber, int x, int y) {
		
			int value = 0;
			Statement stmt = null;
			String query = "SELECT * FROM patterncardfield WHERE patterncard_idpatterncard = " + pcnumber + " AND position_x = " + x + " AND position_y = " +  y + ";";
			try
			{
				stmt = m_Conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				//return string in console voor test
				while (rs.next())
				{
					int valuedb = rs.getInt(5);
					if(valuedb != 0){
						//System.out.println(x + " : " + y + " - value: " + valuedb);
						value = valuedb;
					}
				}
				stmt.close();
			} catch (SQLException e)
			{
				System.out.println(e.getMessage());
				return value;
			}
		
		return value;

	}
	
	public String getPaternCardColor(int pcnumber, int x, int y) {
			
			String color = null;
			Statement stmt = null;
			String query = "SELECT * FROM patterncardfield WHERE patterncard_idpatterncard = " + pcnumber + " AND position_x = " + x + " AND position_y = " +  y + ";";
			try
			{
				stmt = m_Conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				//return string in console voor test
				while (rs.next())
				{
					String colordb = rs.getString(4);
					if(colordb != null) {
						//System.out.println(x + " : " + y +  " - color: " + colordb);
						color = colordb;
					}
					
				}
				stmt.close();
			} catch (SQLException e)
			{
				System.out.println(e.getMessage());
			}
			return color;
		}
	
	//milan
	public int getGameid()
	{
		Statement stmt = null;
		String query = "SELECT max(idgame) FROM game;";
		int gameid = 0;
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console
			while (rs.next())
			{
				gameid = rs.getInt(1);
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return gameid;
	}
	
	public ArrayList<String> getColor()
	{
		Statement stmt = null;
		ArrayList<String> colors = new ArrayList<>();
		String query = "SELECT * FROM color";
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console
			while (rs.next())
			{
				
				colors.add(rs.getString(1));
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return colors;
	}
	
	public ArrayList<Integer> getToolCards()
	{
		Statement stmt = null;
		ArrayList<Integer> idToolCards = new ArrayList<>();
		String query = "SELECT idtoolcard FROM gametoolcard WHERE idgame = " + this.getGameid() + ";";
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console
			while (rs.next())
			{
				System.out.println(rs.getInt(1));
				idToolCards.add(rs.getInt(1));
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return idToolCards;
	}
	
	public ArrayList<Integer> getObjectiveCards()
	{
		Statement stmt = null;
		ArrayList<Integer> idToolCards = new ArrayList<>();
		String query = "SELECT idpublic_objectivecard FROM sharedpublic_objectivecard WHERE idgame = " + this.getGameid();
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console
			while (rs.next())
			{
				System.out.println(rs.getInt(1));
				idToolCards.add(rs.getInt(1));
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return idToolCards;
	}
	
	public ArrayList<String> getPlayer() {
		Statement stmt = null;
		ArrayList<String> player = new ArrayList<>();
		//try etc..
		return player;
	}

	
	
	//Stef
	public int getPlayerPayStones(int playerId)
	{
		Statement stmt = null;
		String query = "SELECT COUNT(idfavortoken) FROM mwmastbe_db2.gamefavortoken WHERE idplayer = " + playerId + ";";
		int paystones = 0;
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next())
			{
				paystones = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return paystones;
	}

	public ArrayList<String> getChat(String query)
	{
		Statement stmt = null;
		ArrayList<String> chat = new ArrayList<>();
		
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console
			while (rs.next())
			{
				
				chat.add(rs.getString(3));
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return chat;
	}
	
	public ArrayList<String> getChatDate(String query)
	{
		Statement stmt = null;
		ArrayList<String> chat = new ArrayList<>();
		
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console
			while (rs.next())
			{
				
				chat.add(rs.getString(1));
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return chat;
	}
	
	//Rens ~ playerstats
	
	public int getPlayerHighscore(String username) {
		
		int highscore = 0;
		Statement stmt = null;
		String query = "SELECT MAX(score) FROM player WHERE username = " + username + ";";
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//return string in console voor test
			while (rs.next())
			{
				int highscoredb = rs.getInt(1);
				if(highscoredb != 0) {
					highscore = highscoredb;
				}
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return highscore;
	}
	
	public int getRoundNumber(int idgame)
	{
		Statement stmt = null;
		String query = "SELECT MAX(roundtrack) FROM gamedie WHERE idgame = " + idgame + ";";
		int round = 0;
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while (rs.next())
			{
				round = rs.getInt(1) + 1;
				
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return round;
	}

	public int getPlayerID(String username, int gameID) {
		Statement stmt = null;
		String query = "SELECT idplayer FROM player WHERE game_idgame = " + gameID + " AND username = " + username + ";";
		int PlayerID = 0;
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while (rs.next())
			{
				PlayerID = rs.getInt(1);
				
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return PlayerID;
	}

	public int getSeqnr(int playerID) {
		Statement stmt = null;
		String query = "SELECT seqnr FROM player WHERE idplayer = " + playerID + ";";
		int seqnr = 0;
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while (rs.next())
			{
				seqnr = rs.getInt(1);
				
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return seqnr;
	}

	public String getStatus(int playerID) {
		Statement stmt = null;
		String query = "SELECT playstatus_playstatus FROM player WHERE idplayer = " + playerID + ";";
		String status = null;
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while (rs.next())
			{
				status = rs.getString(1);
				
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return status;
	}

	public int getIsCurrentPlayer(int playerID) {
		Statement stmt = null;
		String query = "SELECT isCurrentPlayer FROM player WHERE idplayer = " + playerID + ";";
		int icp = 0;
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while (rs.next())
			{
				icp = rs.getInt(1);
				
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return icp;
	}

	public int getPaternCardNumber(int playerID) {
		Statement stmt = null;
		String query = "SELECT patterncard_idpatterncard FROM player WHERE idplayer = " + playerID + ";";
		int pcid = 0;
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while (rs.next())
			{
				pcid= rs.getInt(1);
				
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return pcid;
	}

	public int getScore(int playerID) {
		Statement stmt = null;
		String query = "SELECT score FROM player WHERE idplayer = " + playerID + ";";
		int score = 0;
		try
		{
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while (rs.next())
			{
				score = rs.getInt(1);
				
				
			}
			stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return score;
	}
	
}
