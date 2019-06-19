package databeest;

import java.util.ArrayList;

//Stef
public class DbPlayerCollector {

	// instance variables
	private DataBaseApplication dbApplication;
	int amount;
	int counter = 0;
	int counter2;

	public DbPlayerCollector(DataBaseApplication dbApplication) {
		this.dbApplication = dbApplication;
	}

	public int amountOfPaystones(int playerId) {
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
		if (getCurrentPlayer == 1) {
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
		// System.out.println("Updating database so " + playerid + "'s seqnr becomes: "
		// + seqnr);
		String query = "UPDATE `player` SET `seqnr` = '" + seqnr + "' WHERE (`idplayer` = '" + playerid + "');";
		dbApplication.insertQuery(query);
	}

	public void setCurrentPlayer(int playerid, int i) {
		String query = "UPDATE `player` SET `isCurrentPlayer` = '" + i + "' WHERE (`idplayer` = '" + playerid + "');";
		dbApplication.insertQuery(query);
	}

	public void setGameTurn(int gameid, int playerid) {
		String query = "UPDATE `game` SET `turn_idplayer` = '" + playerid + "' WHERE (`idgame` = '" + gameid + "');";
		dbApplication.insertQuery(query);
	}

	public int getDiceAmountOnFrame(int playerid) {
		int amount = dbApplication.getDiceAmountOnFrame(playerid);
		return amount;
	}

	public void setScore(int playerid, int score) {
		String query = "UPDATE `player` SET `score` = '" + score + "' WHERE (`idplayer` = '" + playerid + "');";
		dbApplication.insertQuery(query);
	}

	public int getSharedObjectivePoints(int i, int gameid, int playerid) { // i is de objectivecard
		int points = 0;
		switch (i) { // Voor objective kaart [i], voeg punten toe..

		case 1: // Tintvarieteit, Sets van één van elke waarde (5 punten)

			amount = getDiceAmountOnFrame(playerid);
			int[] totalEyes = new int[amount];
			ArrayList<Integer> dienumbers = new ArrayList<>();
			ArrayList<String> diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y));
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}

			for (int z = 0; z < amount; z++) {
				int dienumber = dienumbers.get(z);
				String diecolor = diecolors.get(z);
				int eyes = getEyes(dienumber, gameid, diecolor);
				totalEyes[z] = eyes;
			}
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 1) {
					counter++; // stel er zijn twee keer een 1, dan is counter dus 2;
				}
			}
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 2) {
					counter2++; // stel er is drie keer een 2, dan is counter2 dus 3;
					if (counter2 < counter) { // counter2 hoger dan counter dus counter blijft 2;
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 3) {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 4) {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 5) {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 6) {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			points = counter * 5;
			break;
		case 2: // Halfdonkere tinten, sets van waardes 3 & 4 ( 2 punten)

			amount = getDiceAmountOnFrame(playerid);

			totalEyes = new int[amount];
			dienumbers = new ArrayList<>(); // dienumbers bestaat al in case 1, wordt deze dan geleegd? ¬ Milan Ja, er
											// wordt een nieuwe arraylist overheen gemaakt. Die andere gaat dus weg. ~
											// Stef
			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y));
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}

			for (int z = 0; z < amount; z++) {
				int dienumber = dienumbers.get(z);
				String diecolor = diecolors.get(z);
				int eyes = getEyes(dienumber, gameid, diecolor);
				totalEyes[z] = eyes;
			}

			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 3) {
					counter++;
				}
			}
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 4) {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			points = counter * 2;
			break;
		case 3: // Tintvarieteit per kolom ( 4 punten)
				// Uitleg: Elke kolom heeft 4 dobbelstenen er in, loop daar doorheen met forloop
				// x,
				// kijk of de ogen van deze dobbelstenen allemaal ongelijk zijn, zo ja: voeg 4
				// punten toe.

			amount = 4;
			points = 0;
			totalEyes = new int[amount];
			dienumbers = new ArrayList<>();
			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y)); // wat krijg je als er geen
																					// dobbelsteen staat op die plek..
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}

				for (int z = 0; z < amount; z++) {
					int dienumber = dienumbers.get(z);
					String diecolor = diecolors.get(z);
					int eyes = getEyes(dienumber, gameid, diecolor);
					totalEyes[z] = eyes;
				}
				if (totalEyes[0] != totalEyes[1] && totalEyes[1] != totalEyes[2] && totalEyes[2] != totalEyes[3]
						&& totalEyes[0] != totalEyes[2] && totalEyes[1] != totalEyes[3]
						&& totalEyes[0] != totalEyes[3]) {
					points = points + 4;
				}

			}
			break;
		case 4: // kleurvarieteit per kolom (5 punten)

			amount = 4;
			points = 0;
			diecolors = new ArrayList<>();

			for (int x = 1; x < 6; x++) {
				diecolors.clear();
				for (int y = 1; y < 5; y++) {
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
				if (diecolors.get(0) != diecolors.get(1) && diecolors.get(1) != diecolors.get(2)
						&& diecolors.get(2) != diecolors.get(3) && diecolors.get(0) != diecolors.get(2)
						&& diecolors.get(1) != diecolors.get(3) && diecolors.get(0) != diecolors.get(3)) {
					points = points + 5;
				}
			}

			break;
		case 5: // Donkere tinten, sets van waardes 5 & 6 (2 punten)
			amount = getDiceAmountOnFrame(playerid);

			totalEyes = new int[amount];
			dienumbers = new ArrayList<>();
			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y));
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}

			for (int z = 0; z < amount; z++) {
				int dienumber = dienumbers.get(z);
				String diecolor = diecolors.get(z);
				int eyes = getEyes(dienumber, gameid, diecolor);
				totalEyes[z] = eyes;
			}

			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 5) {
					counter++;
				}
			}
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 6) {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			points = counter * 2;

			break;
		case 6: // kleurvarieteit, sets van een van elke kleur ( 4 punten)

			amount = getDiceAmountOnFrame(playerid);

			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}

			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "geel") {
					counter++;
				}
			}
			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "rood") {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "blauw") {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "groen") {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "paars") {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}

			points = counter * 4;

			break;
		case 7: // kleurvarieteit per rij, rijen zonder herhaalde kleuren( 6 punten)

			amount = 5;
			points = 0;
			diecolors = new ArrayList<>();

			for (int y = 1; y < 5; y++) {
				diecolors.clear();
				for (int x = 1; x < 6; x++) {
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
				if (diecolors.get(0) != diecolors.get(1) && diecolors.get(1) != diecolors.get(2)
						&& diecolors.get(2) != diecolors.get(3) && diecolors.get(3) != diecolors.get(4)
						&& diecolors.get(0) != diecolors.get(2) && diecolors.get(1) != diecolors.get(3)
						&& diecolors.get(2) != diecolors.get(4) && diecolors.get(0) != diecolors.get(3)
						&& diecolors.get(1) != diecolors.get(4) && diecolors.get(0) != diecolors.get(4)) {
					points = points + 6;
				}
			}

			break;
		case 8: // kleurdiagonalen, aantal diagonaal aangrenzende stene in dezelfde kleur ( #
				// punten )
			diecolors = new ArrayList<>();
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}
			if (diecolors.get(0) == diecolors.get(5) || diecolors.get(2) == diecolors.get(5)
					|| diecolors.get(8) == diecolors.get(5) || diecolors.get(10) == diecolors.get(5)
					|| diecolors.get(2) == diecolors.get(7) || diecolors.get(7) == diecolors.get(10)
					|| diecolors.get(10) == diecolors.get(15) || diecolors.get(18) == diecolors.get(15)
					|| diecolors.get(10) == diecolors.get(13) || diecolors.get(13) == diecolors.get(8)
					|| diecolors.get(13) == diecolors.get(16) || diecolors.get(13) == diecolors.get(18)
					|| diecolors.get(1) == diecolors.get(4) || diecolors.get(4) == diecolors.get(9)
					|| diecolors.get(1) == diecolors.get(6) || diecolors.get(6) == diecolors.get(9)
					|| diecolors.get(3) == diecolors.get(6) || diecolors.get(6) == diecolors.get(11)
					|| diecolors.get(11) == diecolors.get(14) || diecolors.get(14) == diecolors.get(9)
					|| diecolors.get(9) == diecolors.get(12) || diecolors.get(12) == diecolors.get(17)
					|| diecolors.get(17) == diecolors.get(14) || diecolors.get(14) == diecolors.get(19)) {
				points = points + 1;
			}

			break;
		case 9: // lichte tinten, waardes van 1 & 2 ( 2punten)
			amount = getDiceAmountOnFrame(playerid);

			totalEyes = new int[amount];
			dienumbers = new ArrayList<>();
			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y));
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}

			for (int z = 0; z < amount; z++) {
				int dienumber = dienumbers.get(z);
				String diecolor = diecolors.get(z);
				int eyes = getEyes(dienumber, gameid, diecolor);
				totalEyes[z] = eyes;
			}

			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 1) {
					counter++;
				}
			}
			
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 2) {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			points = counter * 2;
			break;

		case 10: // tintvarieteit per rij ( 5 punten)
			amount = 5;
			points = 0;
			totalEyes = new int[amount];
			dienumbers = new ArrayList<>();
			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int y = 1; y < 5; y++) {
				for (int x = 1; x < 6; x++) {
					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y)); // wat krijg je als er geen
																					// dobbelsteen staat op die plek..
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}

				for (int z = 0; z < amount; z++) {
					int dienumber = dienumbers.get(z);
					String diecolor = diecolors.get(z);
					int eyes = getEyes(dienumber, gameid, diecolor);
					totalEyes[z] = eyes;
				}
				if (totalEyes[0] != totalEyes[1] && totalEyes[1] != totalEyes[2] && totalEyes[2] != totalEyes[3]
						&& totalEyes[3] != totalEyes[4] && totalEyes[0] != totalEyes[2] && totalEyes[1] != totalEyes[3]
						&& totalEyes[2] != totalEyes[4] && totalEyes[0] != totalEyes[3] && totalEyes[1] != totalEyes[4]
						&& totalEyes[0] != totalEyes[4]) {
					points = points + 5;
				}

			}

			break;
		default:
			points = 0;
			break;
		}
		return points;
	}

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
