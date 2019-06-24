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

//					.getConnection("jdbc:mysql://databases.aii.avans.nl/mwmastbe_db2?user=rcaasper&password=Ab12345"); // TODO
																														// hier
																														// moet
					.getConnection("jdbc:mysql://databases.aii.avans.nl/2019_soprj4_sagrada_her?user=42IN04SOn&password=normalisatie");																									// de

																														// uiteindelijke
																														// inloggegevens
																														// komen
																														// voor
																														// de
																														// database
																														// van
																														// school

		} catch (SQLException ex) {
			// handle any errors
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
			// System.out.println(rs);// Deze maakt de 1 in de console :D
			stmt.close();
		} catch (SQLException e) {
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
		}

		if (dbUsername != null) {
			return true;
		} else {
			return false;
		}

	}

	public boolean myTurn(String username, int gameId) {
		Statement stmt = null;
		String query = "SELECT isCurrentPlayer FROM player WHERE idplayer = '" + this.getPlayerID(username, gameId)
				+ "';";
		int ifPlayer = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ifPlayer = rs.getInt(1);
			}
			stmt.close();
			if (ifPlayer != 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
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
		}
		return players;
	}

	public ArrayList<String> getChallenger(String currentAccount) { // alle uitdagers van de ingelogde speler #joery
		Statement stmt = null;
		ArrayList<String> challengers = new ArrayList<>();
		String query = "SELECT p2.username as uitdager From player p1 Inner join player p2 On p1.game_idgame = p2.game_idgame where p1.username = '"
				+ currentAccount
				+ "' and p1.playstatus_playstatus = 'uitgedaagde' And p2.playstatus_playstatus = 'uitdager' Order by p1.game_idgame;";

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				challengers.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
		}

		return challengers;
	}

	public ArrayList<String> getInviteGameID(String currentAccount) { // alle gameID's van de openstaande invites#joery
		Statement stmt = null;
		ArrayList<String> inviteGameID = new ArrayList<>();
		String query = "SELECT p2.game_idgame as gameid From player p1 Inner join player p2 On p1.game_idgame = p2.game_idgame where p1.username = '"
				+ currentAccount
				+ "' and p1.playstatus_playstatus = 'uitgedaagde' And p2.playstatus_playstatus = 'uitdager' Order by p1.game_idgame;";

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				inviteGameID.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
		}

		return inviteGameID;
	}

	public ArrayList<String> getCurrentPlayerStatus(String currentAccount, String gameID) { // huidige status van speler
																							// van een uniek nog niet
																							// aangemaakt spel.
		Statement stmt = null;
		ArrayList<String> currentPlayerStatus = new ArrayList<>();
		String query = "SELECT playstatus_playstatus from player where username = '" + currentAccount
				+ "' and game_idgame = '" + gameID + "';";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				currentPlayerStatus.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
		}
		return currentPlayerStatus;
	}

	public ArrayList<String> getAcceptedGame(String currentAccount) { // alle uitdagers van de ingelogde speler #joery
		Statement stmt = null;
		ArrayList<String> statusAccepted = new ArrayList<>();
		String query = "SELECT playstatus_playstatus as status from player where username = '" + currentAccount
				+ "' AND playstatus_playstatus = 'geaccepteerd' order by game_idgame;";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				statusAccepted.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
		}
		return statusAccepted;
	}

	public ArrayList<String> getPlayersInGame(int gameID, String currentAccount) { 
		
		Statement stmt = null;
		ArrayList<String> playersInGame = new ArrayList<>();
		String query = "SELECT username FROM player WHERE game_idgame = '" + gameID + "' AND username != '"
				+ currentAccount + "';";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				playersInGame.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
		}
		return playersInGame;
	}

	public ArrayList<String> getPlayerStatus(String gameID, String currentAccount) {
		Statement stmt = null;
		ArrayList<String> playerStatus = new ArrayList<>();
		String query = "SELECT playstatus_playstatus FROM player WHERE game_idgame = '" + gameID + "' AND username != '"
				+ currentAccount + "';";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				playerStatus.add(rs.getString(1));

			}
			stmt.close();
		} catch (Exception e) {
		}
		return playerStatus;
	}

	public int getPlayerID(String gameID, String currentAccount) { // alle afwachtend op reactie games van de ingelogde
																	// speler #joery
		Statement stmt = null;
		String query = "SELECT idplayer FROM player WHERE game_idgame = '" + gameID + "' AND username = '"
				+ currentAccount + "';";
		int playerID = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				playerID = rs.getInt(1);

			}
			stmt.close();
		} catch (Exception e) {
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
		}
		return idToolCards;
	}

	// Stef
	public int getPlayerPayStones(int playerId) {
		Statement stmt = null;
		String query = "SELECT COUNT(idfavortoken) FROM gamefavortoken WHERE idplayer = " + playerId + ";";
		int paystones = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				paystones = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
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
	
		}
		return chat;
	}

	public ArrayList<Integer> getChatPlayers(String query) {
		Statement stmt = null;
		ArrayList<Integer> chatPlayers = new ArrayList<>();

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// return string in console
			while (rs.next()) {

				chatPlayers.add(rs.getInt(1));

			}
			stmt.close();
		} catch (SQLException e) {
		}
		return chatPlayers;
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

				PatterncardModel patterncardModel = new PatterncardModel(pcnumber, rs.getInt(2), rs.getInt(3), pcolor,
						rs.getInt(5));

				value.add(patterncardModel);
			}
			stmt.close();
		} catch (SQLException e) {
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
		}
		return score;
	}

	public int getAmountOfPlayers(int gameID) {
		Statement stmt = null;
		String query = "SELECT COUNT(idplayer) FROM player WHERE game_idgame = " + gameID + ";";
		int amount = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				amount = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
		}
		return amount;
	}

	public int[] GetPlayerIDs(int gameID) {
		Statement stmt = null;
		int[] playerIDs = new int[this.getAmountOfPlayers(gameID)];
		String query = "SELECT idplayer FROM player WHERE game_idgame = " + gameID + ";";
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
		}
		return diceList;
	}
	
	public ArrayList<DiceModel> getDiceFromRound(int gameid, int round) {
		Statement stmt = null;
		String query = "SELECT * FROM gamedie WHERE idgame = " + gameid + " AND ROUND = " + round + ";";
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
		}
		return chatID;
	}

	public void UpdateDiceLocation(int x, int y, int playerid, int dienumber, String color, int gameID) {
		Statement stmt = null;
		String query = "UPDATE playerframefield SET dienumber = " + dienumber + ", diecolor = '" + color
				+ "' WHERE player_idplayer = " + playerid + " AND position_x = " + x + " AND position_y = " + y
				+ " AND idgame = " + gameID + ";";

		try {
			stmt = m_Conn.createStatement();
			int rs = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
		}
	}

	// Haalt op welke games gestart zijn (iedereen heeft het verzoek geaccepteerd)
	public ArrayList<Integer> getStartedGames(String username) {

		Statement stmt = null;
		ArrayList<Integer> startedGames = new ArrayList<>();
		String query = "SELECT game_idgame AS idgame1, (SELECT game_idgame FROM player WHERE username = '" + username
				+ "' AND game_idgame = idgame1) AS personalgames, COUNT(idplayer) AS geaccepteerd, (SELECT COUNT(idplayer) FROM player WHERE game_idgame = idgame1) AS totaal_spelers FROM player WHERE (playstatus_playstatus = 'geaccepteerd' OR playstatus_playstatus = 'uitdager') GROUP BY game_idgame; ";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (rs.getInt(1) == rs.getInt(2) && rs.getInt(3) == rs.getInt(4)) {
					startedGames.add(rs.getInt(1));
				}
			}
			stmt.close();
		} catch (SQLException e) {
		}

		return startedGames;
	}

	// Haalt op welke games NOG NEIT gestart zijn (afwachtend op reactie)
	public ArrayList<Integer> getWaitedGames(String username) {

		Statement stmt = null;
		ArrayList<Integer> waitedGames = new ArrayList<>();
		String query = "SELECT game_idgame AS idgame1, (SELECT game_idgame FROM player WHERE username = '" + username
				+ "' AND game_idgame = idgame1) AS personalgames, COUNT(idplayer) AS geaccepteerd, (SELECT COUNT(idplayer) FROM player WHERE game_idgame = idgame1) AS totaal_spelers, (SELECT COUNT(idplayer) FROM player WHERE game_idgame = idgame1 AND playstatus_playstatus = 'geweigerd') AS magniet FROM player WHERE (playstatus_playstatus = 'geaccepteerd' OR playstatus_playstatus = 'uitdager') GROUP BY game_idgame;";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (rs.getInt(1) == rs.getInt(2) && rs.getInt(3) < rs.getInt(4) && rs.getInt(5) == 0) {
					waitedGames.add(rs.getInt(1));
				}
			}
			stmt.close();
		} catch (SQLException e) {
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
					PlayerFieldFrameList.add(new PlayerFieldFrameModel(rs.getInt(1), rs.getInt(2), rs.getInt(3),
							rs.getInt(5), rs.getString(6)));
				}

			}
			stmt.close();
		} catch (SQLException e) {
		}
		return PlayerFieldFrameList;

	}
	
	public ArrayList<Integer> getFinishedGames(String username) {

		Statement stmt = null;
		ArrayList<Integer> finishedGames = new ArrayList<>();
		String query = "SELECT game_idgame AS idgame1, (SELECT game_idgame FROM player WHERE username = '" + username
				+ "' AND game_idgame = idgame1) AS personalgames, COUNT(idplayer) AS geaccepteerd, (SELECT COUNT(idplayer) FROM player WHERE game_idgame = idgame1) AS totaal_spelers FROM player WHERE (playstatus_playstatus = 'uitgespeeld') GROUP BY game_idgame; ";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (rs.getInt(1) == rs.getInt(2) && rs.getInt(3) == rs.getInt(4)) {
					finishedGames.add(rs.getInt(1));
				}
			}
			stmt.close();
		} catch (SQLException e) {
		}

		return finishedGames;
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
		}
		return pcnumber;
	}

	public void setStoneToCard(int gameId, int playerId, int toolcardId, int amount) {
		Statement stmt = null;
		String query = "UPDATE gamefavortoken SET gametoolcard = " + toolcardId + " WHERE idgame = " + gameId
				+ " AND gametoolcard IS NULL AND idplayer = " + playerId + " LIMIT " + amount;
		try {
			stmt = m_Conn.createStatement();
			int rs = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
		}
	}

	public void addStones(int gameId) {
		Statement stmt = null;
		int idfavortoken = 0;
		while (idfavortoken < 24) {
			String query = "INSERT INTO gamefavortoken(idfavortoken, idgame) VALUES (" + idfavortoken + ", " + gameId
					+ ")";
			try {
				stmt = m_Conn.createStatement();
				int rs = stmt.executeUpdate(query);
				stmt.close();
				idfavortoken++;
			} catch (SQLException e) {
			}
		}
	}

	public void addStonesToPlayer(int gameId, int playerId, int amount) {
		Statement stmt = null;
		String query = "UPDATE gamefavortoken SET idplayer = " + playerId + " WHERE idgame = " + gameId
				+ " AND idplayer IS NULL LIMIT " + amount;
		try {
			stmt = m_Conn.createStatement();
			stmt.close();
		} catch (SQLException e) {
		}
	}

	public int getStones(int playerId, int gameId) {
		Statement stmt = null;
		String query = "SELECT count(idplayer) FROM gamefavortoken WHERE idplayer = " + playerId + " AND idgame = "
				+ gameId + " AND gametoolcard IS NULL;";
		int amount = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				amount = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
		}
		return amount;
	}

	public int getPrice(int toolCardNr, int idgame) {
		Statement stmt = null;
		String query = "SELECT count(gametoolcard) FROM gamefavortoken WHERE gametoolcard = " + toolCardNr
				+ " AND idgame = " + idgame;
		int amount = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				amount = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
		}

		if (amount < 1) {
			return 1;
		} else {
			return 2;
		}
	}

	public int[] getPcChoiche(int playerid) {
		Statement stmt = null;
		String query = "SELECT patterncard_idpatterncard FROM patterncardoption WHERE player_idplayer = " + playerid
				+ ";";
		int[] pcIds = new int[4];
		int i = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				pcIds[i] = rs.getInt(1);
				i++;

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return pcIds;
	}

	public int getDifficulty(int idPatternCards) {
		Statement stmt = null;
		String query = "SELECT difficulty FROM patterncard WHERE idPatternCard = " + idPatternCards + ";";
		int diff = 0;
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				diff = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return diff;
	}

	public int getStonesOnCard(int cardId, int gameId) {
		Statement stmt = null;
		String query = "SELECT count(idfavortoken) FROM gamefavortoken WHERE gametoolcard = '" + cardId
				+ "' AND idgame = '" + gameId + "';";
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

	public int getAmountOfGameDie(int gameid) {
		Statement stmt = null;
		String query = "SELECT 90-COUNT(round) FROM gamedie WHERE idgame = '" + gameid + "';";
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

	public ArrayList<String> getDieColors(int gameid, int round) {
		Statement stmt = null;
		String query = "";
		if (round == 0) {
			String roundTrack = "null";
			query = "SELECT diecolor FROM gamedie WHERE idgame = '" + gameid + "' AND round is " + roundTrack + ";";
		} else {
			query = "SELECT diecolor FROM gamedie WHERE idgame = '" + gameid + "' AND round = " + round + ";";
		}

		ArrayList<String> dieColors = new ArrayList<>();

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				dieColors.add(rs.getString(1));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dieColors;
	}

	public ArrayList<Integer> getDieNumbers(int gameid, int round) {
		Statement stmt = null;
		String query = "";
		if (round == 0) {
			String roundTrack = "null";
			query = "SELECT dienumber FROM gamedie WHERE idgame = '" + gameid + "' AND round is " + roundTrack + ";";
		} else {
			query = "SELECT dienumber FROM gamedie WHERE idgame = '" + gameid + "' AND round = " + round + ";";
		}

		ArrayList<Integer> dieNumbers = new ArrayList<>();

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				dieNumbers.add(rs.getInt(1));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dieNumbers;
	}

	public void addDieToRound(int eyes, int round, int gameid, int dienumber, String string) {
		String query = "UPDATE `gamedie` SET `eyes` = " + eyes + ", `round` = " + round
				+ " WHERE (`idgame` = " + gameid + ") and (`dienumber` = " + dienumber + ") and (`diecolor` = '"
				+ string + "');";
		Statement stmt = null;

		try {
			stmt = m_Conn.createStatement();

			int rs = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Integer> getNormalPatterncards() {
		Statement stmt = null;
		String query = "SELECT idpatterncard FROM patterncard WHERE standard = 1";
		ArrayList<Integer> idpatterncards = new ArrayList<>();

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				idpatterncards.add(rs.getInt(1));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return idpatterncards;
	}

	public void addDieToRoundTrack(int round, int gameid, Integer integer, String string) {
		String query = "UPDATE `gamedie` SET `roundtrack` = " + round + " WHERE (`idgame` = " + gameid
				+ ") and (`dienumber` = " + integer + ") and (`diecolor` = '" + string + "');";
		Statement stmt = null;

		try {
			stmt = m_Conn.createStatement();
			int rs = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void giveCard(int cardId, int playerId) {
		String query = "INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES (" + cardId
				+ ", " + playerId + ")";
		Statement stmt = null;

		try {
			stmt = m_Conn.createStatement();
			int rs = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public int countCards() {
		Statement stmt = null;
		String query = "SELECT count(idpatterncard) FROM patterncard WHERE standard = 1";
		int idpatterncards = 0;

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				idpatterncards = rs.getInt(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return idpatterncards;
	}

	public ArrayList<Integer> getPlayerPatternCards(int playerId) {
		Statement stmt = null;
		String query = "SELECT patterncard_idpatterncard FROM patterncardoption WHERE player_idplayer = " + playerId;
		ArrayList<Integer> idpatterncards = new ArrayList<>();

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				idpatterncards.add(rs.getInt(1));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return idpatterncards;
	}

	public int getOffer(int gameID) {
		Statement stmt = null;
		String query = "SELECT COUNT(idgame) FROM gamedie WHERE idgame = " + gameID
				+ " AND eyes is not null;";
		int idpatterncards = 0;

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				idpatterncards = rs.getInt(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return idpatterncards;
	}

	public int getDiceAmountOnFrame(int playerid) {
		Statement stmt = null;
		String query = "SELECT COUNT(dienumber) FROM playerframefield where player_idplayer = " + playerid + " AND dienumber IS NOT NULL;";
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

	public ArrayList<DiceModel> getDiceOffer(int idgame, int round) {
		Statement stmt = null;
		String query = "SELECT * FROM gamedie WHERE idgame = " + idgame + " AND round = " + round + ";";
		ArrayList<DiceModel> offer = new ArrayList<DiceModel>();
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String colorstring = rs.getString(3);
				Color dcolor = Color.WHITE;

				if (colorstring != null) {

					switch (colorstring) {
					case "geel":
						dcolor = Color.YELLOW;
						break;
					case "groen":
						dcolor = Color.GREEN;
						break;

					case "rood":
						dcolor = Color.RED;
						break;

					case "blauw":
						dcolor = Color.BLUE;
						break;

					case "paars":
						dcolor = Color.PURPLE;
						break;
					}

					if (rs.getInt(2) != 0 && rs.getString(3) != null && rs.getInt(4) != 0) {
						offer.add(new DiceModel(rs.getInt(2), dcolor, rs.getInt(4)));
					}
				}
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return offer;

	}
	
	public String getWinner(int gameID){
		String winner = "";
		Statement stmt = null;
		String query = "select score, username from player where game_idgame = '" + gameID + "' AND playstatus_playstatus = 'uitgespeeld' order by score desc limit 1;";
		
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				winner = rs.getString(2);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return winner;
	}

	public ArrayList<DiceModel> getRoundTrack(int gameID) {
		Statement stmt = null;
		String query = "SELECT * FROM gamedie WHERE idgame = " + gameID + " AND roundtrack <= (SELECT MAX(roundtrack) FROM gamedie WHERE idgame = " + gameID + ");";
		ArrayList<DiceModel> roundtrack = new ArrayList<DiceModel>();
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int i = 0;
			
			while (rs.next()) {

				String colorstring = rs.getString(3);
				Color dcolor = Color.WHITE;

				if (colorstring != null) {

					switch (colorstring) {
					case "geel":
						dcolor = Color.YELLOW;
						break;
					case "groen":
						dcolor = Color.GREEN;
						break;

					case "rood":
						dcolor = Color.RED;
						break;

					case "blauw":
						dcolor = Color.BLUE;
						break;

					case "paars":
						dcolor = Color.PURPLE;
						break;
					}

					if (rs.getInt(2) != 0 && rs.getString(3) != null && rs.getInt(4) != 0 && rs.getInt(6) != 0) {
						roundtrack.add(new DiceModel(rs.getInt(2), dcolor, rs.getInt(4)));
						roundtrack.get(i).setRoundtrack(rs.getInt(6));
						i++;
					}
				}
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return roundtrack;
	}

	public int getEyes(int dienumber, int idgame, String color) {
		String query = "SELECT eyes FROM gamedie WHERE dienumber = " + dienumber + " AND diecolor = '" + color + "' AND idgame = "+idgame+";";
		Statement stmt = null;

		int eyes = 0;

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				eyes = rs.getInt(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return eyes;
	}

	public int getDieNumberinos(int playerid, int x, int y) {
		String query = "SELECT dienumber FROM playerframefield WHERE player_idplayer = " + playerid + " AND position_x = " + x + " AND position_y =  " + y + ";";
		Statement stmt = null;

		int dienumber = 0;

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				dienumber = rs.getInt(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dienumber;
	}

	public String getDieColorinos(int playerid, int x, int y) {
		String query = "SELECT diecolor FROM playerframefield WHERE player_idplayer = " + playerid + " AND position_x = " + x + " AND position_y =  " + y + ";";
		Statement stmt = null;

		String diecolor = "";

		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				diecolor = rs.getString(1);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return diecolor;
	}
	
	public void updateDiceEyes(int eyes, int gameId, int dieNumber, String dieColor) {
		String query = "UPDATE gamedie SET eyes = " + eyes + " WHERE idgame = " + gameId + " AND dienumber = " + dieNumber + " AND diecolor LIKE '" + dieColor + "'";
		Statement stmt = null;
		try {
			stmt = m_Conn.createStatement();
			int rs = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void updateDiceColor(int gameId, int dienr, String color, int eyes) {
		String query = "UPDATE gamedie SET eyes = " + eyes + " WHERE idgame = " + gameId + " AND dienumber = " + dienr + " AND diecolor LIKE '" + color + "'";
		Statement stmt = null;
		try {
			stmt = m_Conn.createStatement();
			int rs = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
