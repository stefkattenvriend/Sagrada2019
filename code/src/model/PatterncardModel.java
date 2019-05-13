package model;

import databeest.DBPatternCardInfoCollector;
import javafx.scene.paint.Color;

public class PatterncardModel {
	private int patterncardNumber;
	private int x;
	private int y;
	private int number;
	private Color color;
	private DBPatternCardInfoCollector dbpcif;
	
	public PatterncardModel(int number, int x, int y, DBPatternCardInfoCollector dbpcif) {
		this.patterncardNumber = number;
		this.x = x;
		this.y = y;
		this.dbpcif = dbpcif;
		getFromDatabase();
	}
	
	public int getPatterncardNumber() {
		return patterncardNumber;
	}
	public void setPatterncardNumber(int patterncardNumber) {
		this.patterncardNumber = patterncardNumber;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void getFromDatabase() {
		dbpcif.getPatternCardInfo(patterncardNumber, x, y, this);
	}
	
}
