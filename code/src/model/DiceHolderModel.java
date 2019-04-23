package model;

public class DiceHolderModel {

	private DiceModel die;
	private int size;
	//Color moet later worden opgehaald uit kaarten
	//x en y coordinaten?
	//int owner
	
	public DiceHolderModel(DiceModel die, int size) {
		setDie(die);
		setSize(size);
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
