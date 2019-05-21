package controller;

import java.util.ArrayList;

import databeest.DbPatternCardInfoCollector;
import helpers.PatterncardType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.PatterncardModel;

public class PatterncardController {

	private ArrayList<PatterncardModel> pcmodels = new ArrayList<PatterncardModel>();
	private int Patternnumber = 0;
	
	private DbPatternCardInfoCollector DatabasePTCCollector;
	
	
	public PatterncardController(DbPatternCardInfoCollector DatabasePTCCollector) {
		this.DatabasePTCCollector = DatabasePTCCollector;
	}
	
	
	public PatterncardModel getPcModel(int i) {
		
		PatterncardModel model = pcmodels.get(i);
		
		return model;
	}
	
	public int getPcModelsSize() {
		return pcmodels.size();
	}
	
	public void CreatePatternCard(int Patternnumber, PatterncardType pct) {
		
		
		for (int i = 1; i < 6; i++) {
			for (int j = 1; j < 5; j++) {
				pcmodels.add(new PatterncardModel(Patternnumber, i, j, pct, DatabasePTCCollector));
			}	
		}
	}
	
	
	public BorderPane PatterncardCreate(int x, int y, int PatterncardNumber, int size, PatterncardType pct) {
		BorderPane pane = new BorderPane();
		pane.setPrefSize(85, 85);
		//pane.setPrefSize(arg0, arg1);
		
		CreatePatternCard(PatterncardNumber, pct);
		
		for (int i = 0; i < pcmodels.size(); i++) {
			if (pcmodels.get(i).getPatterncardNumber() == PatterncardNumber && pcmodels.get(i).getX() == x && pcmodels.get(i).getY() == y) {
				if(pcmodels.get(i).getNumber() >= 1 && pcmodels.get(i).getNumber() <= 6) {
					Text center = new Text(Integer.toString(pcmodels.get(i).getNumber()));
					center.setScaleX(7);
					center.setScaleY(7);
					pane.setCenter(center);
					break;
				}
				else if(pcmodels.get(i).getColor() != null) {
					Pane center = new Pane();
					center.setPrefSize(80, 80);
					center.setBackground(new Background( new BackgroundFill(pcmodels.get(i).getColor(), null, null)));
					pane.setCenter(center);
					break;
				}
				else {
					break;
				}
				
			}
		}
		
		return pane;
	}
}
