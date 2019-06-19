package model;

import java.util.ArrayList;

import controller.ToolCardController;
import databeest.DbPlayerCollector;
import helpers.DiceHolderType;
import javafx.scene.paint.Color;

public class PlayerModel {

	// instance variables

	private int playerid;
	private String username;
	private int seqnr; // dit is later voor de volgorde van de beurtenprivate String username;
	private int gameid;
	private String status;
	private boolean isCurrentPlayer;
	private int payStones;// moeten wss paystone models worden ~ Rens
	private int patid; // patterncard_idpatterncard
	private String stringcolor;
	private Color color;
	private int score;
	private int movesAllowed1 = 1;
	private int movesAllowed2 = 1;
	private int turnPlace;
	private GameModel gm;
	private int amountOfDiceOnFrame;
	
	private int counter = 0;
	private int counter2 = 0;
	private int amount;
	private int[] totalEyes[];
	private ArrayList<Integer> dienumbers;
	private ArrayList<String> diecolors;
	
	private DiceHolderType dht;// welke diceholder er bij deze speler hoort dus welke speler is het ~ Rens
	private DbPlayerCollector dpc;
	private ToolCardController tcc;
	
	
	public PlayerModel(DbPlayerCollector dpc, GameModel gm, ToolCardController tcc) {
		this.tcc = tcc;
		this.gm = gm;
		this.dpc = dpc;
	}

	public void getDatabaseInfo(DbPlayerCollector dpc) {
//		playerid = dpc.getPlayerID(username, gameid);
		username = dpc.getUsername(playerid);
		seqnr = dpc.getSeqnr(playerid);
		status = dpc.getStatus(playerid);
		isCurrentPlayer = dpc.getIfCurrentPlayer(playerid);
		patid = dpc.getPatternCardID(playerid);
		score = dpc.getScore(playerid);
		stringcolor = dpc.getColor(playerid);
//		System.out.println("my color = " + stringcolor);
		if (stringcolor != null) {
			switch (stringcolor) {
			case "geel":
				color = Color.YELLOW;
				break;
			case "groen":
				color = Color.GREEN;
				break;
			case "rood":
				color = Color.RED;
				break;
			case "blauw":
				color = Color.BLUE;
				break;

			case "paars":
				color = Color.PURPLE;
				break;
			}
		}
		else {
			System.out.println("huh??");
			color = Color.WHITE;
		}
	}

	public int getPlayerId() {
		return playerid;
	}

	public void setPlayerId(int playerid) {
		this.playerid = playerid;
//		System.out.println("player id set to:" + playerid);
	}
	
	public int getMovesAllowed1() {
		return movesAllowed1;
	}
	
	public void doMove1() {		//roep deze aan nadat je een actie hebt uitgevoerd
		movesAllowed1 = movesAllowed1 -1;
		tcc.doMove();
	}
	
	public void giveMove1() {		//roep deze aan als je een move mag doen
		movesAllowed1 = movesAllowed1 +1;
	}
	
	public int getMovesAllowed2() {
		return movesAllowed2;
	}
	
	public void doMove2() {		//roep deze aan nadat je een actie hebt uitgevoerd
		movesAllowed2 = movesAllowed2 -1;
		tcc.doMove();
	}
	
	public void giveMove2() {		//roep deze aan als je een move mag doen
		movesAllowed2 = movesAllowed2 +1;
	}
	
	public void setPayStones(int payStones) {
		this.payStones = payStones;
	}

	public int getPayStones() {
		return payStones;
	}

	public boolean isCurrentPlayer() {
		return isCurrentPlayer;
	}
	
	//milan
	//updates the database with current player
	
	public void setCurrentPlayer(boolean isCurrentPlayer) {
		this.isCurrentPlayer = isCurrentPlayer;
		int i = 0;
		if (isCurrentPlayer) {
			i = 1;
			System.out.println("Player: " + username + " is now current player of game: " + gameid);
			dpc.setGameTurn(gameid, playerid);
		} else {
			i = 0;
			System.out.println("Player: " + username + " is no longer current player of game: "+ gameid);
		}
		
		dpc.setCurrentPlayer(playerid, i);
	}

	//Deze blijft loopen, zet uit aub
	public Color getObjectiveColor() {
//		System.out.println("kleurtje model: " + color);
		return color;
	}

	public int getSeqnr() {
		seqnr = dpc.getSeqnr(playerid);
		return seqnr;
	}

	public void setSeqnr(int seqnr) {
		this.seqnr = seqnr;
//		System.out.println("Setting " + playerid + "'s seqnr to: " + seqnr);
		dpc.setSeqnr(playerid, seqnr);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DiceHolderType getDht() {
		return dht;
	}

	public void setDht(DiceHolderType dht) {
		this.dht = dht;
	}

	public int getPatid() {
		return patid;
	}

	public void setPatid(int patid) {
		this.patid = patid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void reloadPcID() {
		patid = dpc.getPatternCardID(playerid);
	}
	
	public int getDiceAmountOnFrame()
	{
		amountOfDiceOnFrame = dpc.getDiceAmountOnFrame(playerid);
		return amountOfDiceOnFrame;

	}
	
	public int getTurn() {
		System.out.println("isCurrentPlayer: " + isCurrentPlayer);
		if(seqnr <= gm.getPma().length) {
			return 1;
		} else {
			return 2;
		}
	}

	public void setPlaceInArrayList(int i) {
		turnPlace = i;
	}

	public int getSharedObjectivePoints(int i, int gameid, int playerid) {	//i is de objectivecard
		int points = 0;
		switch (i) {	//Voor objective kaart [i], voeg punten toe..
		
		case 1:	//Tintvarieteit, Sets van één van elke waarde (5 punten)
		
			amount = dpc.getDiceAmountOnFrame(playerid);
			int totalEyes[] = new int[amount];
			dienumbers = new ArrayList<>(); 
			diecolors = new ArrayList<>(); 

			for(int x = 1; x < 6; x++) {
				for(int y = 1; x < 5; y++) {
					dienumbers.add(dpc.getDieNumberinos(playerid, x, y));
					diecolors.add(dpc.getDieColorinos(playerid, x, y));
				}
			}
			
			for(int z = 0; z < amount; z++) {
				int dienumber = dienumbers.get(z);
				String diecolor = diecolors.get(z);
				int eyes = dpc.getEyes(dienumber, gameid, diecolor);
				totalEyes[z] = eyes;
			}
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a]== 1) {
					counter++;
				}
			}
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a]== 2) {
				counter2++;
					if(counter2 > counter) {
					counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a]== 3) {
				counter2++;
					if(counter2 > counter) {
					counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a]== 4) {
				counter2++;
					if(counter2 > counter) {
					counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a]== 5) {
				counter2++;
					if(counter2 > counter) {
					counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a]== 6) {
				counter2++;
					if(counter2 > counter) {
					counter = counter2;
					}
				}
			}
			points = counter * 5;
			break;
		case 2: //Halfdonkere tinten, sets van waardes 3 & 4 ( 2 punten)
			
			amount = dpc.getDiceAmountOnFrame(playerid);
			
			totalEyes = new int[amount];
			dienumbers = new ArrayList<>(); 
			diecolors = new ArrayList<>(); 

			for(int x = 1; x < 6; x++) {
				for(int y = 1; x < 5; y++) {
					dienumbers.add(dpc.getDieNumberinos(playerid, x, y));
					diecolors.add(dpc.getDieColorinos(playerid, x, y));
				}
			}
			
			for(int z = 0; z < amount; z++) {
				int dienumber = dienumbers.get(z);
				String diecolor = diecolors.get(z);
				int eyes = dpc.getEyes(dienumber, gameid, diecolor);
				totalEyes[z] = eyes;
			}
			
			
			break;	
		case 3:	//Tintvarieteit per kolom ( 4 punten)


			break;
		case 4:	//kleurvarieteit per kolom (5 punten)

			break;
		case 5: // Donkere tinten, sets van waardes 5 & 6 (2 punten)

			break;
		case 6:	// kleurvarieteit, sets van een van elke kleur ( 4 punten)

			break;
		case 7: //kleurvarieteit per rij, rijen zonder herhaalde kleuren( 6 punten)

			break;
		case 8:	//kleurdiagonalen, aantal diagonaal aangrenzende stene in dezelfde kleur ( # punten )

			break;
		case 9:	// lichte tinten, waardes van 1 & 2 ( 2punten)

			break;

		case 10:	// tintvarieteit per rij ( 5 punten)

			break;
		default:
			points = 0;
			break;
		}
		return points;
	}
	
//	public int getSharedObjectivePoints(int i, int gameid, int playerid) {
//		int points = dpc.getSharedObjectivePoints(i, gameid, playerid);
//		return points;
//	}

}
