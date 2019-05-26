package databeest;

import java.util.ArrayList;
import model.PatterncardModel;

public class DbPatternCardInfoCollector {
    
    private DataBaseApplication dataBaseApplication;
    
    public DbPatternCardInfoCollector(DataBaseApplication dataBaseApplication) {
        this.dataBaseApplication = dataBaseApplication;
    }
    
    public ArrayList<PatterncardModel> getPatternCard(int pcnumber) {
        return dataBaseApplication.getPaternCard(pcnumber);
    }
    
}