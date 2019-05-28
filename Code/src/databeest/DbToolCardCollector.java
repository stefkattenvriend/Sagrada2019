package databeest;

public class DbToolCardCollector {
	
	private DataBaseApplication dba;
	
	public DbToolCardCollector(DataBaseApplication dba) {
		this.dba = dba;
	}
	
	public int getPrice(int ToolCardNr) {
		return dba.getPrice(ToolCardNr);
	}
}
