package game;

import java.util.List;
import java.util.Scanner;

import cards.Card;
import cards.UnoDeck;

/**
 * Game rules for FX user interface. Rule handling on FX interface differs from from rule handling 
 * on playing on console (i.e. enter card to play in console, etc.). Thus, we have two different classes 
 * for rule handling on user interface and console entering. 
 * 
 */
public class RulesForFX {

	/** new deck of cards from UnoDeck class */
	private UnoDeck unoDeck;

	/** List of cards for a player */
	private List<Card> player;

	/** top card: */
	private Card topCard;

	private String cardType;

	/**
	 * @param unoDeck: current shuffled deck of cards.
	 * @param player: a human or computer player with cards.
	 * @param topCard: card on top in the middle of table.
	 * @param cardType: cardType can be action, number, color, or wild.
	 */
	public RulesForFX(UnoDeck unoDeck, List<Card> player, Card topCard, String cardType) {
		super();
		this.setUnoDeck(unoDeck);
		this.setPlayer(player);
		this.setTopCard(topCard);
		this.setCardType(cardType);
	}

	public RulesForFX(UnoDeck thisDeck, List<Card> playerCards, Card topCard2) {
	
	}


	public boolean checkNumberCard(UnoDeck unoDeck, List<Card> player, Card topCard) {
		// check if player can play number card:
		for (int i = 0; i < player.size(); i++) {
			if (topCard.getNumber().equals(player.get(i).getNumber())
					|| topCard.getColor().equals(player.get(i).getColor())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkActionCard(UnoDeck unoDeck, List<Card> player, Card topCard) {
		// check action card match:
		for (int i = 0; i < player.size(); i++) {
			if (topCard.getColor().equals(player.get(i).getColor())) {
				return true;
			}
		}
		return false;
	}

	public Card playCard(UnoDeck unoDeck, List<Card> player, Card topCard, String cardType, boolean isHuman) {

		if (isHuman) {
			System.out.println("Top card: " + topCard + "\nNumber of cards on hand: " + player.size());
			System.out.println("Which Card? " + player.toString());
			Scanner scanner = new Scanner(System.in);
			int n = scanner.nextInt();
			Card chosenCard = player.get(n);
			System.out.println("You chose " + chosenCard);
			// TODO: Check if this card can be played
			topCard = chosenCard;
			System.out.println("New top card: " + topCard);
			// Remove card from player:
			player.remove(n);

		} else {
			for (int i = 0; i < player.size(); i++) {
				if (player.get(i).getWild().equals(Card.Wild.DRAWFOUR)
						|| player.get(i).getWild().equals(Card.Wild.WILDCOLOR)) {
					System.out.println("Top card: " + topCard);
					System.out.println("Player plays a WILD card: " + player.get(i));
					topCard = player.get(i);
					System.out.println("New top card: " + topCard);
					// Remove card from player:
					player.remove(i);
					break;
				}
				if (cardType == "number" && (topCard.getNumber().equals(player.get(i).getNumber())
						|| topCard.getColor().equals(player.get(i).getColor()))) {
					System.out.println("Top card: " + topCard);
					System.out.println("Player plays a NUMBER card: " + player.get(i));
					topCard = player.get(i);
					System.out.println("New top card: " + topCard);
					// Remove card from player:
					player.remove(i);
					break;
				}
				if (cardType == "action" && (topCard.getColor().equals(player.get(i).getColor()))) {
					System.out.println("Top card: " + topCard);
					System.out.println("Player plays a ACTION card: " + player.get(i));
					topCard = player.get(i);
					System.out.println("New top card: " + topCard);
					// Remove card from player:
					player.remove(i);
					break;
				}
				if (cardType == "afterAction" && (topCard.getColor().equals(player.get(i).getColor()))) {
					System.out.println("Top card: " + topCard);
					System.out.println("Player plays a Number card based on color (on reverse or drawtwo or drawfour card): "
									+ player.get(i));
					topCard = player.get(i);
					System.out.println("New top card: " + topCard);
					// Remove card from player:
					player.remove(i);
					break;
				}
			} // for
		} // else
		System.out.println("Player's deck size: " + player.size());
		return topCard;
	}

	public Card drawCard(UnoDeck unoDeck, List<Card> player, Card topCard) {
		// if player can not play a card: take one from the deck:
		System.out.println("Top card: " + topCard);
		System.out.println("Player's deck size: " + player.size());
		Card toAdd = unoDeck.getCards().remove(0);
		player.add(toAdd);
		
		player.add(unoDeck.getCards().remove(0));
		System.out.println("Player can't play card and takes one from deck. Player has now " + player.size() + " cards");
		return toAdd;
	}

	public int getNumberOfCards(List<Card> player) {
//		System.out.println("Number of Cards on hand: " + player.size());
		return player.size();
	}

	public Card handleActionDraw(UnoDeck unoDeck, List<Card> player, Card topCard) {
		if (topCard.getAction().equals(Card.Action.DRAWTWO)) {
			System.out.println(topCard.getAction() + " Player draws two cards.");
			player.add(unoDeck.getCards().remove(0));
			player.add(unoDeck.getCards().remove(0));
		}
		if (topCard.getWild().equals(Card.Wild.DRAWFOUR)) {
			System.out.println(topCard.getWild() + " Player draws four cards.");
			player.add(unoDeck.getCards().remove(0));
			player.add(unoDeck.getCards().remove(0));
			player.add(unoDeck.getCards().remove(0));
			Card toAdd = unoDeck.getCards().remove(0);
			player.add(toAdd);
			return toAdd;
		}
		System.out.println("Number of cards on hand: " + player.size());
		checkActionCard(unoDeck, player, topCard);
		Card toAdd = unoDeck.getCards().remove(0);
		return toAdd;
	}

	public int handleNextPlayer(int isReverse, int currentPlayer, int playerListSize) {
		int nextPlayer = 99;
		if (isReverse == 0 && currentPlayer == playerListSize) {
			nextPlayer = 0;
		} else if (isReverse == 0) {
			nextPlayer = currentPlayer + 1;
		}
		if (isReverse == 1 && currentPlayer == 0) {
			nextPlayer = playerListSize;
		} else if (isReverse == 1) {
			nextPlayer = currentPlayer - 1;
		}
		return nextPlayer;
	}

	public boolean checkColorCardAfterAction(List<Card> player, Card topCard) {
		// check if player can play color-matching number card:
		for (int i = 0; i < player.size(); i++) {
			if (topCard.getColor().equals(player.get(i).getColor())) {
				return true;
			}
		}
		return false;
	}

	public UnoDeck getUnoDeck() {
		return unoDeck;
	}

	public void setUnoDeck(UnoDeck unoDeck) {
		this.unoDeck = unoDeck;
	}

	public List<Card> getPlayer() {
		return player;
	}

	public void setPlayer(List<Card> player) {
		this.player = player;
	}

	public Card getTopCard() {
		return topCard;
	}

	public void setTopCard(Card topCard) {
		this.topCard = topCard;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}
