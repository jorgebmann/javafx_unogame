package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cards.expand.ActionCard;
import cards.expand.NumberCard;
import cards.expand.WildCard;

/**
 * UnoDeck generates a unique Deck of cards. It will be later on shuffled and
 * used in the game.
 * 
 * We have 108 cards: 19 blue cards - 0 to 9; 19 red cards - 0 to 9; 19 yellow
 * cards - 0 to 9; 19 green cards - 0 to 9; 8 “Draw Two” cards- two of each
 * color; 8 “Skip” cards- two of each color; 8 “Reverse” cards- two of each
 * color; 4 “Wild” cards; 4 “Wild Draw Four” cards.
 * 
 */
public class UnoDeck {

	private List<Card> unoDeck;

	public UnoDeck() {
		unoDeck = new ArrayList<Card>();
		makeDeck();
	}

	private void makeDeck() {

		for (Card.Color color : Card.Color.values()) {
			// Numbers 1-9 are double (2 * 9 Numbers * 4 Colors = 72 ) plus Number 0 is not
			// twice = 76
			// so we have 76 NumberCards
			for (Card.Number number : Card.Number.values()) {
				if (number != Card.Number.NONE && color != Card.Color.NONE) {
					unoDeck.add(new NumberCard(color, number, Card.Action.NONE, Card.Wild.NONE)); // Card.Action.NONE
					int i = 0;
					// exlcude double counting if Number == ZERO:
					while (number.ordinal() != 0 && number.ordinal() != 1 && color.ordinal() != 0 && i < 2) {
//				System.out.print(number.ordinal() + " ");
						unoDeck.add(new NumberCard(color, number, Card.Action.NONE, Card.Wild.NONE)); // Card.Action.NONE
						i++;
					}
				}
			}

			
			// 3 Actions * 4 Colors * 2 = 24 ActinCards:
			for (Card.Action action : Card.Action.values()) {
				for (int i = 0; i < 2; i++) {
					if (action != Card.Action.NONE && color != Card.Color.NONE) {
						unoDeck.add(new ActionCard(color, Card.Number.NONE, action, Card.Wild.NONE)); // Card.Number.NONE
					}	
				}
			}
			
			for (Card.Wild wild : Card.Wild.values()) {
				for (int i = 0; i < 1; i++) {
					if (wild != Card.Wild.NONE) {
						unoDeck.add(new WildCard(Card.Color.NONE, Card.Number.NONE, Card.Action.NONE, wild));
					}
				}
			}
			
			Collections.shuffle(unoDeck, new Random());

		}
	}

	public List<Card> getCards() {
		return unoDeck;
	}

}
