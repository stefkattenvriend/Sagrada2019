package controller;

import view.GamePanes.ToolCard1Pane;

import java.util.ArrayList;

import model.DiceModel;

// gemaakt door TESS!!!!!!!!!!
public class ToolCard {

	private int selectedCard;

	// maak die selected

	public void useCard(int number, ArrayList<DiceModel> dice) {
		if (number == 1) {
			// na het kiezen dobbelsteen, waarde 1 verhogen of verlagen (bij 1 niet -1 bij 6
			// niet +1)
			ToolCard1Pane card1Pane = new ToolCard1Pane();
			if (card1Pane.chosen == 1) {
				if (dice.get(0).getEyes() == 1) {
					// pop up dat dit niet kan
					return;
				}
				dice.get(0).setEyes(dice.get(0).getEyes() - 1);
			}
			if (card1Pane.chosen == 2) {
				if (dice.get(0).getEyes() == 6) {
					// pop up dat dit niet kan
					return;
				}
				dice.get(0).setEyes(dice.get(0).getEyes() + 1);
			}
		}
		if (number == 2) {
			// verplaats dobbelsteen in raam. kleur voorwaardes negeren
		}
		if (number == 3) {
			// verplaats dobbelsteen in raam voorwaarde voor waardes negeren
		}
		if (number == 4) {
			// verplaats 2 dobbelstenen
		}
		if (number == 5) {
			// dobbelsteen kiezen daarna wisselen met eentje van roundtrack
		}
		if (number == 6) {
			// nadat dobbelsteen kiest opnieuw werpen
			// [een klasse].werp(dobbelsteen);
		}
		if (number == 7) {
			// Werp alle dobbelstenen in het aanbod opnieuw, enkel bij 2e beurt
		}
		if (number == 8) {
			// na eerste beurt gelijk nieuwe dobbelsteen kiezen tweede beurt overslaan.
		}
		if (number == 9) {
			// nadat dobbelsteen gekozen, leggen in een vak dat niet grenst aan andere steen
		}
		if (number == 10) {
			// nadat dobbelsteen gekozen, mag draaien naar tegenovergestelde zijde
			// dobbelsteen.waarde = 7 - dobbelsteen.waarde;
		}
		if (number == 11) {
			// nadat dobbelsteen gekozen, nieuwe dobbelsteen pakken uit zak en waarde kiezen
			// op je bord of in het aanbodleggen
		}
		if (number == 12) {
			// verplaats 2 dobbelstenen van dezelfde kleur die overeenkomen met een steen op
			// het rondespoor
		}

	}
}
