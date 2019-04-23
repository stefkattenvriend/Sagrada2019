package model;

import javafx.scene.paint.Color;

//Rens
public class DiceModel {
	
	private int size;
	private Color paint;
	private int eyes;
	private boolean interactable;
	private boolean selected;
	private DiceHolderModel dh;
	
	public DiceModel(int size, Color paint, int eyes, boolean interactable, DiceHolderModel dh) {
		setSize(size);
		setPaint(paint);
		setEyes(eyes);
		setInteractable(interactable);
		setDh(dh);
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

	public boolean isInteractable() {
		return interactable;
	}

	public void setInteractable(boolean interactable) {
		this.interactable = interactable;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public DiceHolderModel getDh() {
		return dh;
	}

	public void setDh(DiceHolderModel dh) {
		this.dh = dh;
	}
}
