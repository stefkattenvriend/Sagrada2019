package databeest;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import model.DiceModel;
import model.PlayerModel;

public class DbDieUpdater {
	private DataBaseApplication dba;

	public DbDieUpdater(DataBaseApplication dba) {
		this.dba = dba;
	}

	public void updateDieLocation(int x, int y, DiceModel dm, int playerid, int gameID) {
		
		String color = null;
		
		if(dm.getDieColor() == Color.RED) {
			color = "rood";
		}
		if(dm.getDieColor() == Color.BLUE) {
			color = "blauw";
		}
		if(dm.getDieColor() == Color.YELLOW) {
			color = "geel";
		}
		if(dm.getDieColor() == Color.PURPLE) {
			color = "paars";
		}
		if(dm.getDieColor() == Color.GREEN) {
			color = "groen";
		}
		
		dba.UpdateDiceLocation(x, y, playerid, dm.getDieNumber(), color, gameID);
	}

}
