package cards.expand;

import cards.Card;

/**
 * Number Card inherits everything from Card. It will be collected by UnoDeck.
 * 
 */
public class NumberCard extends Card {

	public NumberCard(Color color, Number number, Action action, Wild wild) {
		super(color, number, action, wild);
		
	}
}
