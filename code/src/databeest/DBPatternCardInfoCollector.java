package databeest;

import model.PatterncardModel;
import javafx.scene.paint.Color;

public class DBPatternCardInfoCollector {
	
	private DataBaseApplication dataBaseApplication;
	
	public DBPatternCardInfoCollector(DataBaseApplication dataBaseApplication) {
		this.dataBaseApplication = dataBaseApplication;
	}
	
	public void getPatternCardInfo(int pcnumber, int x, int y, PatterncardModel pcm) {
		int value = dataBaseApplication.getPaternCardValue(pcnumber, x, y);//fout zit in deze denk in ~Rens
		String PatternColor = dataBaseApplication.getPaternCardColor(pcnumber, x, y);
		
		if (value != 0) {
			pcm.setNumber(value);
		}
		if (PatternColor != null) {
			
			switch(PatternColor) {
			case "geel":
				pcm.setColor(Color.YELLOW);
				break;
			case "groen":
				pcm.setColor(Color.GREEN);
				break;
				
			case "rood" :
				pcm.setColor(Color.RED);
				break;
				
			case "blauw" : 
				pcm.setColor(Color.BLUE);
				break;
				
			case "paars" :
				pcm.setColor(Color.PURPLE);
				break;
			}
			
		}else {
			pcm.setColor(Color.WHITE);
		}
	}
}
