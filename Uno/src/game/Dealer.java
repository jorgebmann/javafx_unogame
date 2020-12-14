package game;

import cards.Card;
import cards.UnoDeck;

/**
 * Dealer generates a list of cards for each class player from the shuffled card deck. 
 * Cards for each player are taken from top of card deck. This card is removed from card deck.
 * Dealer can handle multiple players
 * 
 * 
 */
public class Dealer {
	
	/** new deck of cards from UnoDeck class*/
	private UnoDeck unoDeck;
	
	/**
	 * Constructor of the new card deck
	 * @param thisDeck: The current deck of cards.
	 */
	public Dealer(UnoDeck thisDeck) {
		super();
		this.unoDeck = thisDeck;
	}

			
	public void  mkPlayer(Player player) {
		// generate a list of cards from the card deck: add card to player and remove it from deck
		for (int i = 0; i < 6; i++) {
			Card currentCard = unoDeck.getCards().remove(0);  
			//remove index 0 from deck, not i-th index, because index i changes in loop
			player.addPlayerCards(currentCard);
		}
	}

	/**
	 * @return the unoDeck after cards where handed out to each player
	 */
	public UnoDeck getUnoDeck() {
		return unoDeck;
	}			

}
