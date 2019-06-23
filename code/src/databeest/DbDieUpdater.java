package databeest;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import model.DiceModel;
import model.PlayerFieldFrameModel;

public class DbDieUpdater {
	private DataBaseApplication dba;

	public DbDieUpdater(DataBaseApplication dba) {
		this.dba = dba;
	}

	public void updateDieLocation(int x, int y, DiceModel dm, int playerid, int gameID) {

		String color = null;

		if (dm.getDieColor() == Color.RED) {
			color = "rood";
		}
		if (dm.getDieColor() == Color.BLUE) {
			color = "blauw";
		}
		if (dm.getDieColor() == Color.YELLOW) {
			color = "geel";
		}
		if (dm.getDieColor() == Color.PURPLE) {
			color = "paars";
		}
		if (dm.getDieColor() == Color.GREEN) {
			color = "groen";
		}

		dba.UpdateDiceLocation(x, y, playerid, dm.getDieNumber(), color, gameID);
	}

	public void updateDieEyes(int eyes, int gameId, DiceModel dm) {
		String color = null;

		if (dm.getDieColor() == Color.RED) {
			color = "rood";
		}
		if (dm.getDieColor() == Color.BLUE) {
			color = "blauw";
		}
		if (dm.getDieColor() == Color.YELLOW) {
			color = "geel";
		}
		if (dm.getDieColor() == Color.PURPLE) {
			color = "paars";
		}
		if (dm.getDieColor() == Color.GREEN) {
			color = "groen";
		}

		dba.updateDiceEyes(eyes, gameId, dm.getDieNumber(), color);
	}

	public void putDieOnRoundtrack(int round, int gameID, ArrayList<DiceModel> die) {

		ArrayList<PlayerFieldFrameModel> usedDice = dba.getPlayerFrame(gameID);

		ArrayList<DiceModel> dice = dba.getDiceFromRound(gameID, round);

		for (int i = 0; i < dice.size(); i++) {
			
			if (dice.get(i).getRoundtrack() == 0) {//ligt de steen al op roundtrack
				
				boolean used = false;
				
				for (int j = 0; j < usedDice.size(); j++) {
					
					if (dice.get(i).getDieNumber() == usedDice.get(j).getDienumber() && dice.get(i).getDieColor() == usedDice.get(j).getDiecolor()) {
						
						used = true;
			
					}
				}
				
				if (!used) {
						String color;

						switch (dice.get(i).getDieColor().toString()) {
						case ("0xff0000ff"):// red
							color = "rood";
							break;
						case ("0x0000ffff"):// blue
							color = "blauw";
							break;
						case ("0x008000ff"):// green
							color = "groen";
							break;
						case ("0xffff00ff"):// yellow
							color = "geel";
							break;
						case ("0x800080ff"):// purple
							color = "paars";
							break;
						default:
							color = null;
							break;
						}
						dba.addDieToRoundTrack(round, gameID, dice.get(i).getDieNumber(), color);
					}
			}
		}

	}

}
