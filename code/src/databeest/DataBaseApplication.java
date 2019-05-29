package databeest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import model.DiceModel;
import model.PatterncardModel;
import model.PlayerFieldFrameModel;

public class DataBaseApplication {
	private Connection m_Conn;

	public DataBaseApplication() {
		m_Conn = null;
	}

	public boolean loadDataBaseDriver(String driverName) {
		try {
			// Load the JDBC driver
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			System.out.println("ClassNotFoundException : " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean makeConnection() {
		try {
			m_Conn = DriverManager
					.getConnection("jdbc:mysql://databases.aii.avans.nl/mwmastbe_db2?user=rcaasper&password=Ab12345");
			System.out.println("So far, so good...");
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("Houston, we've had a problem...");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}


	// Stef
	// voor een insert Query
	public void insertQuery(String query) {
		Statement stmt = null;

		try {
			stmt = m_Conn.createStatement();

			int rs = stmt.executeUpdate(query);
			System.out.println(rs);// Deze maakt de 1 in de console :D
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// Alle read query's

	public String readPassword(String username) {
		String password = null;
		String query;
		Statement stmt = null;
		query = "SELECT * FROM account WHERE username = '" + username + "';";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console
			while (rs.next()) {
				password = rs.getString(2).toLowerCase();
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return password;
	}

	public boolean CheckIfUsernameExists(String username) {
		String dbUsername = null;
		String query;
		Statement stmt = null;
		query = "SELECT * FROM account WHERE username = '" + username + "';";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				dbUsername = rs.getString(1);
			}

			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		if (dbUsername != null) {
			return true;
		} else {
			return false;
		}

	}
	
	public int getHighestGameID() {
		Statement stmt = null;
		String query = "SELECT max(idgame) FROM game;";
		int gameid = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console
			while (rs.next()) {
				gameid = rs.getInt(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return gameid;
	}

	public ArrayList<String> getPlayers() {
		Statement stmt = null;
		ArrayList<String> players = new ArrayList<>();
		String query = "SELECT username FROM account";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				players.add(rs.getString(1));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return players;
	}
	
	public ArrayList<String> getChallenger(String currentAccount){ //alle uitdagers van de ingelogde speler #joery
		Statement stmt = null;
		ArrayList<String> challengers = new ArrayList<>();
		String query = "SELECT p2.username as uitdager From player p1 Inner join player p2 On p1.game_idgame = p2.game_idgame where p1.username = '" + currentAccount + "' and p1.playstatus_playstatus = 'uitgedaagde' And p2.playstatus_playstatus = 'uitdager' Order by p1.game_idgame;";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {

				challengers.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return challengers;
	}
	
	
	public ArrayList<String> getInviteGameID(String currentAccount){ // alle gameID's van de openstaande invites#joery
		Statement stmt = null;
		ArrayList<String> inviteGameID = new ArrayList<>();
		String query = "SELECT p2.game_idgame as gameid From player p1 Inner join player p2 On p1.game_idgame = p2.game_idgame where p1.username = '" + currentAccount + "' and p1.playstatus_playstatus = 'uitgedaagde' And p2.playstatus_playstatus = 'uitdager' Order by p1.game_idgame;";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {

				inviteGameID.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return inviteGameID;
	}
	
	public ArrayList<String> getCurrentPlayerStatus(String currentAccount, String gameID){ // huidige status van speler van een uniek nog niet aangemaakt spel.
		Statement stmt = null;
		ArrayList<String> currentPlayerStatus = new ArrayList<>();
		String query = "SELECT playstatus_playstatus from player where username = '" + currentAccount + "' and game_idgame = '" + gameID + "';";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {

				currentPlayerStatus.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return currentPlayerStatus;
	}
	
	public ArrayList<String> getAcceptedGame(String currentAccount){ //alle uitdagers van de ingelogde speler #joery
		Statement stmt = null;
		ArrayList<String> statusAccepted = new ArrayList<>();
		String query = "SELECT playstatus_playstatus as status from player where username = '" + currentAccount + "' AND playstatus_playstatus = 'geaccepteerd' order by game_idgame;";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {

				statusAccepted.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return statusAccepted;
	}
	
	public ArrayList<String> getWaitingGames(String currentAccount){ //alle afwachtend op reactie games van de ingelogde speler #joery
		Statement stmt = null;
		ArrayList<String> waitingGames = new ArrayList<>();
		String query = "SELECT p1.game_idgame as gameid, p1.playstatus_playstatus From player p1 where p1.username = '" + currentAccount + "' Order by p1.game_idgame;";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {

				waitingGames.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return waitingGames;
	}
	
	public ArrayList<String> getPlayersInGame(String gameID, String currentAccount){ //alle afwachtend op reactie games van de ingelogde speler #joery
		Statement stmt = null;
		ArrayList<String> playersInGame = new ArrayList<>();
		String query = "SELECT username FROM player WHERE game_idgame = '" + gameID + "' AND username != '" + currentAccount + "';";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {

				playersInGame.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return playersInGame;
	}
	
	public ArrayList<String> getPlayerStatus(String gameID, String currentAccount){ //alle afwachtend op reactie games van de ingelogde speler #joery
		Statement stmt = null;
		ArrayList<String> playerStatus = new ArrayList<>();
		String query = "SELECT playstatus_playstatus FROM player WHERE game_idgame = '" + gameID + "' AND username != '" + currentAccount + "';";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {

				playerStatus.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return playerStatus;
	}
	
	public int getPlayerID(String gameID, String currentAccount){ //alle afwachtend op reactie games van de ingelogde speler #joery
		Statement stmt = null;
		String query = "SELECT idplayer FROM player WHERE game_idgame = '" + gameID + "' AND username = '" + currentAccount + "';";
		int playerID = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {

				playerID = rs.getInt(1);

			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return playerID;
	}

	public ArrayList<String> getColor() {
		Statement stmt = null;
		ArrayList<String> colors = new ArrayList<>();
		String query = "SELECT * FROM color";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console
			while (rs.next()) {

				colors.add(rs.getString(1));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return colors;
	}

	public ArrayList<Integer> getToolCards(int gameId) {
		Statement stmt = null;
		ArrayList<Integer> idToolCards = new ArrayList<>();
		String query = "SELECT idtoolcard FROM gametoolcard WHERE idgame = " + gameId + ";";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console
			while (rs.next()) {
				idToolCards.add(rs.getInt(1));
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return idToolCards;
	}

	public ArrayList<Integer> getObjectiveCards(int gameId) {
		Statement stmt = null;
		ArrayList<Integer> idToolCards = new ArrayList<>();
		String query = "SELECT idpublic_objectivecard FROM sharedpublic_objectivecard WHERE idgame = " + gameId;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console
			while (rs.next()) {
				idToolCards.add(rs.getInt(1));
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return idToolCards;
	}

	// TODO verwijder dit ofzo
	// public ArrayList<String> getPlayer() {
	// Statement stmt = null;
	// ArrayList<String> player = new ArrayList<>();
	// //try etc..
	// return player;
	// }

	// Stef
	public int getPlayerPayStones(int playerId) {
		Statement stmt = null;
		String query = "SELECT COUNT(idfavortoken) FROM mwmastbe_db2.gamefavortoken WHERE idplayer = " + playerId + ";";
		int paystones = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				paystones = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return paystones;
	}

	public ArrayList<String> getChat(String query) {
		Statement stmt = null;
		ArrayList<String> chat = new ArrayList<>();

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console
			while (rs.next()) {

				chat.add(rs.getString(3));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return chat;
	}

	public ArrayList<String> getChatDate(String query) {
		Statement stmt = null;
		ArrayList<String> chat = new ArrayList<>();

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console
			while (rs.next()) {

				chat.add(rs.getString(1));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return chat;
	}

	// Rens ~ playerstats

	public int getPlayerHighscore(String username) {

		int highscore = 0;
		Statement stmt = null;
		String query = "SELECT MAX(score) FROM player WHERE username = " + username + ";";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console voor test
			while (rs.next()) {
				int highscoredb = rs.getInt(1);
				if (highscoredb != 0) {
					highscore = highscoredb;
				}
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return highscore;
	}

	public int getRoundNumber(int idgame) {
		Statement stmt = null;
		String query = "SELECT MAX(roundtrack) FROM gamedie WHERE idgame = " + idgame + ";";
		int round = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				round = rs.getInt(1) + 1;

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return round;
	}

	public int getPlayerID(String username, int gameID) {
		Statement stmt = null;
		String query = "SELECT idplayer FROM player WHERE game_idgame = " + gameID + " AND username = '" + username
				+ "';";
		int PlayerID = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				PlayerID = rs.getInt(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return PlayerID;
	}

	public int getSeqnr(int playerID) {
		Statement stmt = null;
		String query = "SELECT seqnr FROM player WHERE idplayer = " + playerID + ";";
		int seqnr = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				seqnr = rs.getInt(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return seqnr;
	}

	public String getStatus(int playerID) {
		Statement stmt = null;
		String query = "SELECT playstatus_playstatus FROM player WHERE idplayer = " + playerID + ";";
		String status = null;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				status = rs.getString(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	public int getIsCurrentPlayer(int playerID) {
		Statement stmt = null;
		String query = "SELECT isCurrentPlayer FROM player WHERE idplayer = " + playerID + ";";
		int icp = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				icp = rs.getInt(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return icp;
	}

	public ArrayList<PatterncardModel> getPaternCard(int pcnumber) {
		
		ArrayList<PatterncardModel> value = new ArrayList<PatterncardModel>();
		Statement stmt = null;
		String query = "SELECT * FROM patterncardfield WHERE patterncard_idpatterncard = " + pcnumber
				+ " ORDER BY position_y asc, position_x asc;";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			
			while (rs.next()) {

				String colorstring = rs.getString(4);
				Color pcolor = Color.WHITE;

				if (colorstring != null) {

					switch (colorstring) {
					case "geel":
						pcolor = Color.YELLOW;
						break;
					case "groen":
						pcolor = Color.GREEN;
						break;

					case "rood":
						pcolor = Color.RED;
						break;

					case "blauw":
						pcolor = Color.BLUE;
						break;

					case "paars":
						pcolor = Color.PURPLE;
						break;
					}

				}

				PatterncardModel patterncardModel = new PatterncardModel(pcnumber, rs.getInt(2), rs.getInt(3), pcolor, rs.getInt(5));
				
				value.add(patterncardModel);
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return value;
		}
		return value;
	}

	public int getScore(int playerID) {
		Statement stmt = null;
		String query = "SELECT score FROM player WHERE idplayer = " + playerID + ";";
		int score = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				score = rs.getInt(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return score;
	}

	public int getAmountOfPlayers(int gameID) {
		Statement stmt = null;
		String query = "SELECT COUNT(idplayer) FROM mwmastbe_db2.player WHERE game_idgame = " + gameID + ";";
		int amount = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				amount = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return amount;
	}

	public int[] GetPlayerIDs(int gameID) {
		Statement stmt = null;
		int[] playerIDs = new int[this.getAmountOfPlayers(gameID)];
		String query = "SELECT idplayer FROM mwmastbe_db2.player WHERE game_idgame = " + gameID + ";";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int i = 0;
			// return string in console
			while (rs.next()) {
				playerIDs[i] = rs.getInt(1);
				i++;
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return playerIDs;
	}
	
	public int getPaternCardNumber(int playerID) {
        Statement stmt = null;
        String query = "SELECT patterncard_idpatterncard FROM player WHERE idplayer = " + playerID + ";";
        int pcid = 0;
        try {
            stmt = m_Conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                pcid = rs.getInt(1);

            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pcid;
    }

	public String getPlayerColor(int playerID) {

		Statement stmt = null;
		String query = "SELECT private_objectivecard_color FROM player WHERE idplayer = " + playerID + ";";
		String color = null;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				color = rs.getString(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return color;

	}

	public String getplayerUsername(int playerID) {
		Statement stmt = null;
		String query = "SELECT username FROM player WHERE idplayer = " + playerID + ";";
		String username = null;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				username = rs.getString(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return username;
	}

	public ArrayList<DiceModel> getDice(int gameid) {
		Statement stmt = null;
		String query = "SELECT * FROM gamedie WHERE idgame = " + gameid + ";";
		ArrayList<DiceModel> diceList = new ArrayList<DiceModel>();
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Color dieColor = Color.BLACK;

				switch (rs.getString(3)) {
				case "geel":
					dieColor = Color.YELLOW;
					break;
				case "groen":
					dieColor = Color.GREEN;
					break;

				case "rood":
					dieColor = Color.RED;
					break;

				case "blauw":
					dieColor = Color.BLUE;
					break;

				case "paars":
					dieColor = Color.PURPLE;
					break;
				}

				diceList.add(new DiceModel(rs.getInt(2), dieColor, rs.getInt(4)));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return diceList;
	}

	
	
	public ArrayList<Integer> getChatIDs(String query) {
		Statement stmt = null;
		ArrayList<Integer> chatID = new ArrayList<>();

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console
			while (rs.next()) {

				chatID.add(rs.getInt(1));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return chatID;
	}
	
	public void UpdateDiceLocation(int x, int y, int playerid, int dienumber, String color, int gameID) {
		Statement stmt = null;
		String query = "UPDATE playerframefield SET dienumber = " + dienumber + ", diecolor = '" + color + "' WHERE player_idplayer = " + playerid + " AND position_x = " + x + " AND position_y = " + y + " AND idgame = " + gameID + ";";

		try {
			stmt = m_Conn.createStatement();
			int rs = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Haalt op welke games gestart zijn (iedereen heeft het verzoek geaccepteerd)
	public ArrayList<Integer> getStartedGames(){
		
		Statement stmt = null;
		ArrayList<Integer> startedGames = new ArrayList<>();
		String query = "SELECT game_idgame AS gameid, COUNT(idplayer) AS geaccepteerd, (SELECT COUNT(idplayer) FROM player WHERE game_idgame = gameid) AS totaal_spelers FROM player WHERE playstatus_playstatus = 'geaccepteerd' OR playstatus_playstatus = 'uitdager' GROUP BY game_idgame;"; 
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (rs.getInt(2) == rs.getInt(3)) {
					startedGames.add(rs.getInt(1));
				}
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return startedGames;
	}
	
	//Haalt op welke games NOG NEIT gestart zijn (afwachtend op reactie)
	public ArrayList<Integer> getWaitedGames(){
		
		Statement stmt = null;
		ArrayList<Integer> waitedGames = new ArrayList<>();
		String query = "SELECT game_idgame AS gameid, COUNT(idplayer) AS geaccepteerd, (SELECT COUNT(idplayer) FROM player WHERE game_idgame = gameid) AS totaal_spelers FROM player WHERE playstatus_playstatus = 'geaccepteerd' OR playstatus_playstatus = 'uitdager' GROUP BY game_idgame;"; 
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (rs.getInt(2) != rs.getInt(3)) {
					waitedGames.add(rs.getInt(1));
				}
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return waitedGames;
	}

	public ArrayList<PlayerFieldFrameModel> getPlayerFrame(int gameid) {
		Statement stmt = null;
		String query = "SELECT * FROM playerframefield WHERE idgame = " + gameid + ";";
		ArrayList<PlayerFieldFrameModel> PlayerFieldFrameList = new ArrayList<PlayerFieldFrameModel>();
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				if (rs.getInt(5) != 0 && rs.getString(6) != null) {
					PlayerFieldFrameList.add(new PlayerFieldFrameModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(5), rs.getString(6)));
				}
				

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return PlayerFieldFrameList;
		
	}

	public int numberOfPatternCards() {
	        Statement stmt = null;
	        String query = "SELECT COUNT(idpatterncard) FROM patterncard;";
	        int pcnumber = 0;
	        try {
	            stmt = m_Conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query);

	            while (rs.next()) {
	                pcnumber = rs.getInt(1);

	            }
	            stmt.close();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return pcnumber;
	    }

}
