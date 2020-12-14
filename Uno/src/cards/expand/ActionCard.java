package cards.expand;

import cards.Card;

/**
 * Action Card inherit from Card. It will be collected by UnoDeck.
 * 
 */
public class ActionCard extends Card {

	public ActionCard(Color color, Number number, Action action, Wild wild) {
		super(color, number, action, wild);
		
	}

}
