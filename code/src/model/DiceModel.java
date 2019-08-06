package model;

import javafx.scene.paint.Color;

//Rens
public class DiceModel {

	private int dieNumber;
	private Color dieColor;
	private int eyes;
	private int roundtrack;
	

	public DiceModel(int dieNumber, Color dieColor, int eyes) {
		this.dieNumber = dieNumber;
		this.dieColor = dieColor;
		this.eyes = eyes;
	}

	public int getDieNumber() {
		return dieNumber;
	}

	public void setDieNumber(int dieNumber) {
		this.dieNumber = dieNumber;
	}

	public Color getDieColor() {
		return dieColor;
	}

	public void setDieColor(Color dieColor) {
		this.dieColor = dieColor;
	}

	public int getEyes() {
		return eyes;
	}

	public void setEyes(int eyes) {
		this.eyes = eyes;
	}

	public int getRoundtrack() {
		return roundtrack;
	}

	public void setRoundtrack(int roundtrack) {
		this.roundtrack = roundtrack;
	}

}
