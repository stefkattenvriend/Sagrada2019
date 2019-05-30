package model;

import helpers.DiceHolderType;

//Rens
public class DiceHolderModel {

	private DiceModel die;
	private int x;
	private int y;
	private DiceHolderType type;
	private boolean selected = false;
	private boolean interactable;
	


	public DiceHolderModel(DiceModel die, int x, int y, DiceHolderType type) {
		this.die = die;
		this.x = x;
		this.y = y;
		this.type = type;
		
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
	
	public boolean isInteractable() {
		return interactable;
	}

	public void setInteractable(boolean interactable) {
		this.interactable = interactable;
	}
		
	public DiceModel getDie() {
		return die;
	}

	public void setDie(DiceModel die) {
		this.die = die;
	}

	public DiceHolderType getType() {
		return type;
	}

	public void setType(DiceHolderType type) {
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
