package game;

import java.util.List;

import cards.Card;
import cards.UnoDeck;

public class PlayCard {
	
	
	
	public PlayCard(List<Card> player, Card topCard, UnoDeck thisDeck) {
		
	}

	private PlayCard checkCard(List<Card> player, Card topCard, UnoDeck thisDeck) {
		// check if player can play a card:
		for (int i = 0; i < player.size(); i++) {
			// First: check color or number cards:
			if ((topCard.getAction() == null && player.get(i).getAction() == null) && 
					(topCard.getNumber().equals(player.get(i).getNumber()) || 
					topCard.getColor().equals(player.get(i).getColor()))) {
				System.out.println("Player one plays: " + player.get(i));
				// put played card on top of current stack:
				topCard = player.get(i);
				System.out.println("New top card: " + topCard);
				// Remove card from player:
				player.remove(i);
				break;
				} 
			// Second: check action card match:
			if ((topCard.getAction() == null && player.get(i).getAction() != null) &&
					(topCard.getColor().equals(player.get(i).getColor()))) {
				// put played card on top of current stack:
				System.out.println("Player one plays a ACTION card: " + player.get(i));
				topCard = player.get(i);
				System.out.println("New top card: " + topCard);
				// Remove card from player:
				player.remove(i);
				break;	
			}
			if (topCard.getAction() != null) {
				System.out.println("Action on wild card needs to be implemented.");
				break;
			} else {
				// if player can not play a card: take one from the deck:
				System.out.println("Player's deck size: " + player.size());
				player.add(thisDeck.getCards().remove(0));
				System.out.println("Player can't play card and takes one from deck. Player has now " 
				+ player.size() + " cards");
				break;
			}
		}
		return new PlayCard(player, topCard, thisDeck);
	}

}
