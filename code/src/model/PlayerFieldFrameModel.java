package model;

import javafx.scene.paint.Color;

public class PlayerFieldFrameModel {
	private int playerid;
	private int x;
	private int y;
	private int dienumber;
	private Color diecolor;
	
	public PlayerFieldFrameModel(int id, int x, int y, int dienumber, String color) {
		this.playerid = id;
		this.x = x;
		this.y = y;
		this.dienumber = dienumber;
		
		switch (color) {
		case "geel":
			diecolor = Color.YELLOW;
			break;
		case "groen":
			diecolor = Color.GREEN;
			break;

		case "rood":
			diecolor = Color.RED;
			break;

		case "blauw":
			diecolor = Color.BLUE;
			break;

		case "paars":
			diecolor = Color.PURPLE;
			break;
		}
		
	}

	public int getPlayerid() {
		return playerid;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDienumber() {
		return dienumber;
	}

	public Color getDiecolor() {
		return diecolor;
	}
}
