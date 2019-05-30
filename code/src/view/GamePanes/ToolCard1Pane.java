package view.GamePanes;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
//deze heeft tess gemaakt mooi he
//Waarom heet deze ToolCard1, moeten er meerdere komen? ~Rens
public class ToolCard1Pane extends HBox {
	private Button higher;
	private Button lower;
	public int chosen;
	//dobbelsteen doorgeven via ToolCard
	public ToolCard1Pane() {
		higher = new Button("+1");
		lower = new Button("-1");
		higher.setOnMouseClicked(e -> higherClicked());
		lower.setOnMouseClicked(e -> lowerClicked());
		
		//if(dobbelsteen.waarde==1){
	//	lower disabled
	//}
		
//		if(dobbelsteen.waarde ==6) {
//			higher disabled
//		}
		
		getChildren().addAll(higher, lower);
	}

	private void lowerClicked() {
		chosen=1;
		//pane weg
	}

	private void higherClicked() {
		chosen=2;
		//pane weg
	
	}
}
