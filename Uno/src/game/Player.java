package game;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

/**
 * This class represent a player of the uno game. It can be human or computer and has a certein number of
 * cards on hand (ArrayList playerCards).
 * Number of cards vary based on class Rules. Cards get added or substracted.
 * 
 */
public class Player {

	private List<Card> playerCards = new ArrayList<Card>();

	private boolean isHuman = false;

	private int numberOfCards;

	/**
	 * @param player
	 */
	public Player() {
		super();
	}

	public List<Card> getPlayerCards() {
		return playerCards;
	}

	public void addPlayerCards(Card playerCard) {
		this.playerCards.add(playerCard);
	}

	/**
	 * @return the numberOfCards
	 */
	public int getNumberOfCards() {
		return numberOfCards;
	}

	/**
	 * @param numberOfCards the numberOfCards to set
	 */
	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}

	/**
	 * @return the isHuman
	 */
	public boolean isHuman() {
		return isHuman;
	}

	/**
	 * @param isHuman the isHuman to set
	 */
	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	@Override
	public String toString() {
		return "Player [playerCards=" + playerCards + "]";
	}

}
