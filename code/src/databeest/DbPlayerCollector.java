package databeest;

import java.util.ArrayList;

//Stef
public class DbPlayerCollector {
	
	//instance variables
	private DataBaseApplication dbApplication;

	
	public DbPlayerCollector(DataBaseApplication dbApplication)
	{
		this.dbApplication = dbApplication;
	}
	
	public int amountOfPaystones(int playerId)
	{
		int amount = 0;
		amount = dbApplication.getPlayerPayStones(playerId);
		
		return amount;
	}

	public int getPlayerID(String username, int gameID) {
		int PlayerID = dbApplication.getPlayerID(username, gameID);
		return PlayerID;
	}

	public int getSeqnr(int playerID) {
		int seqnr = dbApplication.getSeqnr(playerID);
		return seqnr;
	}

	public String getStatus(int playerID) {
		String status = dbApplication.getStatus(playerID);
		return status;
		
	}

	public boolean getIfCurrentPlayer(int playerID) {
		boolean isCurrentPlayer = false;
		int getCurrentPlayer = dbApplication.getIsCurrentPlayer(playerID);
		if(getCurrentPlayer == 1) {
			isCurrentPlayer = true;
		}
		return isCurrentPlayer;
	}

	public int getPatternCardID(int playerID) {
		int pcid = dbApplication.getPaternCardNumber(playerID);
		return pcid;
	}

	public int getScore(int playerID) {
		int score = dbApplication.getScore(playerID);
		return score;
	}

	public String getColor(int playerID) {
		String color = dbApplication.getPlayerColor(playerID);
		return color;
	}

	public String getUsername(int playerid) {
		String username = dbApplication.getplayerUsername(playerid);
		return username;
	}

	public void setSeqnr(int playerid, int seqnr) {
//		System.out.println("Updating database so " + playerid + "'s seqnr becomes: " + seqnr);
		String query = "UPDATE `mwmastbe_db2`.`player` SET `seqnr` = '" + seqnr + "' WHERE (`idplayer` = '" + playerid + "');";
		dbApplication.insertQuery(query);
	}

	public void setCurrentPlayer(int playerid, int i) {
		String query = "UPDATE `mwmastbe_db2`.`player` SET `isCurrentPlayer` = '" + i + "' WHERE (`idplayer` = '" + playerid + "');";
		dbApplication.insertQuery(query);
	}

	public void setGameTurn(int gameid, int playerid) {
		String query = "UPDATE `mwmastbe_db2`.`game` SET `turn_idplayer` = '" + playerid + "' WHERE (`idgame` = '" + gameid + "');";
		dbApplication.insertQuery(query);
	}
	
	public int getDiceAmountOnFrame(int playerid) {
		int amount = dbApplication.getDiceAmountOnFrame(playerid);
		return amount;
	}

	public void setScore(int playerid, int score) {
		String query = "UPDATE `mwmastbe_db2`.`player` SET `score` = '" + score + "' WHERE (`idplayer` = '" + playerid + "');";
		dbApplication.insertQuery(query);
	}

//	public int getSharedObjectivePoints(int i, int gameid, int playerid) {	//i is de objectivecard
//		int points = 0;
//		switch (i) {	//Voor objective kaart [i], voeg punten toe..
//		
//		case 1:	//Tintvarieteit, Sets van één van elke waarde (5 punten)
//		
//			amount = getDiceAmountOnFrame(playerid);
//			totalEyes[] = new int[amount];
//			dienumbers = new ArrayList<>(); 
//			diecolors = new ArrayList<>(); 
//
//			for(int x = 1; x < 6; x++) {
//				for(int y = 1; x < 5; y++) {
//					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y));
//					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
//				}
//			}
//			
//			for(int z = 0; z < amount; z++) {
//				int dienumber = dienumbers.get(z);
//				String diecolor = diecolors.get(z);
//				int eyes = getEyes(dienumber, gameid, diecolor);
//				totalEyes[z] = eyes;
//			}
//			for (int a = 0; a < amount; a++) {
//				if (totalEyes[a]== 1) {
//					counter++;
//				}
//			}
//			for (int a = 0; a < amount; a++) {
//				if (totalEyes[a]== 2) {
//				counter2++;
//					if(counter2 > counter) {
//					counter = counter2;
//					}
//				}
//			}
//			counter2 = 0;
//			for (int a = 0; a < amount; a++) {
//				if (totalEyes[a]== 3) {
//				counter2++;
//					if(counter2 > counter) {
//					counter = counter2;
//					}
//				}
//			}
//			counter2 = 0;
//			for (int a = 0; a < amount; a++) {
//				if (totalEyes[a]== 4) {
//				counter2++;
//					if(counter2 > counter) {
//					counter = counter2;
//					}
//				}
//			}
//			counter2 = 0;
//			for (int a = 0; a < amount; a++) {
//				if (totalEyes[a]== 5) {
//				counter2++;
//					if(counter2 > counter) {
//					counter = counter2;
//					}
//				}
//			}
//			counter2 = 0;
//			for (int a = 0; a < amount; a++) {
//				if (totalEyes[a]== 6) {
//				counter2++;
//					if(counter2 > counter) {
//					counter = counter2;
//					}
//				}
//			}
//			points = counter * 5;
//			break;
//		case 2: //Halfdonkere tinten, sets van waardes 3 & 4 ( 2 punten)
//			
//			amount = getDiceAmountOnFrame(playerid);
//			
//			totalEyes = new int[amount];
//			dienumbers = new ArrayList<>(); 
//			diecolors = new ArrayList<>(); 
//
//			for(int x = 1; x < 6; x++) {
//				for(int y = 1; x < 5; y++) {
//					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y));
//					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
//				}
//			}
//			
//			for(int z = 0; z < amount; z++) {
//				int dienumber = dienumbers.get(z);
//				String diecolor = diecolors.get(z);
//				int eyes = getEyes(dienumber, gameid, diecolor);
//				totalEyes[z] = eyes;
//			}
//			
//			break;	
//		case 3:	//Tintvarieteit per kolom ( 4 punten)
//
//
//			break;
//		case 4:	//kleurvarieteit per kolom (5 punten)
//
//			break;
//		case 5: // Donkere tinten, sets van waardes 5 & 6 (2 punten)
//
//			break;
//		case 6:	// kleurvarieteit, sets van een van elke kleur ( 4 punten)
//
//			break;
//		case 7: //kleurvarieteit per rij, rijen zonder herhaalde kleuren( 6 punten)
//
//			break;
//		case 8:	//kleurdiagonalen, aantal diagonaal aangrenzende stene in dezelfde kleur ( # punten )
//
//			break;
//		case 9:	// lichte tinten, waardes van 1 & 2 ( 2punten)
//
//			break;
//
//		case 10:	// tintvarieteit per rij ( 5 punten)
//
//			break;
//		default:
//			points = 0;
//			break;
//		}
//		return points;
//	}
	
	public int getEyes(int dienumber, int idgame, String color) {
		int i = dbApplication.getEyes(dienumber, idgame, color);
		return i;
	}

	public int getDieNumberinos(int playerid, int x, int y) {
		int dienumber = dbApplication.getDieNumberinos(playerid, x, y);
		return dienumber;
	}

	public String getDieColorinos(int playerid, int x, int y) {
		String diecolor = dbApplication.getDieColorinos(playerid, x, y);
		return diecolor;
	}
	
}
