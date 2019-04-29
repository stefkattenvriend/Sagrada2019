package model;

import controller.DiceHolderController;

//Rens
public class DiceHolderModel {

	private DiceModel die;
	private int x;
	private int y;
	private int type; //1=raam speler, 2=rondespoor, 3=aanbod, 4=vijand 1, 5=vijand 2, 6=vijand 4(ofwel geeft aan waar op het scherm van de speler deze zichtbaar zal zijn(verander naar enums(Ger))
	private boolean selected = false;
	private DiceHolderController dhc;
	
	public DiceHolderModel(DiceModel die, int x, int y, int type, DiceHolderController dhc) {
		this.die = die;
		this.x = x;
		this.y = y;
		this.type = type;
		this.dhc = dhc;
		
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

	
	
	

	public DiceModel getDie() {
		return die;
	}

	public void setDie(DiceModel die) {
		this.die = die;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean getSelected() {
		return selected;
	}
	
	public void switchSelected() {
		if(selected == false) {
			selected = true;
		}
		else {
			selected = false;
		}
	}
}
