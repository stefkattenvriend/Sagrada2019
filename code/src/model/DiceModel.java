package model;

import javafx.scene.paint.Color;

//Rens
public class DiceModel {
	
	private int size;
	private Color paint;
	private int eyes;
	
	public DiceModel(int size, Color paint, int eyes) {
		this.size = size;
		this.paint = paint;
		this.eyes = eyes;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getPaint() {
		return paint;
	}

	public void setPaint(Color paint) {
		this.paint = paint;
	}

	public int getEyes() {
		return eyes;
	}

	public void setEyes(int eyes) {
		this.eyes = eyes;
	}

}
