package model;

public class DiceHolderModel {

	private DiceModel die;
	private int size;
	private int x;
	private int y;
	//int owner
	
	public DiceHolderModel(DiceModel die, int size, int x, int y) {
		setDie(die);
		setSize(size);
		setX(x);
		setY(y);
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
