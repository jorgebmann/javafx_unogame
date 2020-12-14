package game;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import cards.UnoDeck;
import cards.Card.Action;
import cards.Card.Color;
import cards.Card.Number;
import cards.Card.Wild;

public class PlayUno {

	private UnoDeck thisDeck;

	private Player playerOne;
	private Player playerTwo;
	private Player playerThree;
	private Player playerFour;

	private List<Player> playerList;

	private Dealer dealer;

	private Card topCard;

	public List<Player> gameStarter(boolean mode) {
		// generate a new shuffled Deck of Cards:
		setThisDeck(new UnoDeck());

		// generate players:
		setPlayerOne(new Player());
		setPlayerTwo(new Player());
		setPlayerThree(new Player());
		setPlayerFour(new Player());
		

		// first player is human, rest is PC:
		playerOne.setHuman(mode);

		// generate players as list:
		playerList = new ArrayList<Player>();

		playerList.add(playerOne);
		playerList.add(playerTwo);
		playerList.add(playerThree);
		playerList.add(playerFour);
		
		
		

		// generate a dealer, such that each player gets its cards:
		setDealer(new Dealer(thisDeck));

		// Give cards to players:
//		System.out.println("Cards in deck before dealing: " + thisDeck.getCards().size());
		dealer.mkPlayer(playerOne);
		dealer.mkPlayer(playerTwo);
		dealer.mkPlayer(playerThree);
		dealer.mkPlayer(playerFour);
//		System.out.println("Cards in deck after dealing: " + thisDeck.getCards().size());
		// generate first card to play:
		setTopCard(thisDeck.getCards().remove(0));
		
		return playerList;


	}

	public void playUno() {
		
		

		
		/** This value initializes the first player */
		int currentPlayer = 0;
		/**
		 * This value initializes the order of player on the table, i.e. clockwise or
		 * anti-clockwise
		 */
		int isReverse = 0; // Value 0: clockwise => 0, 1, 2; Value 1: anti-clock => 0, 2, 1
		RulesHuman rules = new RulesHuman(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard);

		System.out.println("----------Play it------------");

		while (rules.getNumberOfCards(playerList.get(currentPlayer).getPlayerCards()) > 0) {

			/**
			 * Gigantic switch and if-else setting to generate playing rules. Top card can
			 * be an action card or not. Based on top card moves of players are defined if
			 * top card is not action, than we have two cases: A) top card is an action card
			 * that was valid for previous player (e.g. previous player had a skip card and
			 * current player can play a card with the same color as the skip card) B) top
			 * card is a number card and current player can chose between number and action
			 * card Moreover, action card can be skip, reverse, or draw two. In this case,
			 */
			System.out.println("Player: " + currentPlayer + " Cards: "
					+ playerList.get(currentPlayer).getPlayerCards().toString());
			switch (topCard.getAction()) {
			case NONE: {

				switch (topCard.getWild()) {
				case DRAWFOUR: {
					System.out.println("Current player: " + currentPlayer);
					int numberOfCards = rules.handleActionDraw(thisDeck, playerList.get(currentPlayer).getPlayerCards(),
							topCard);
					playerList.get(currentPlayer).setNumberOfCards(numberOfCards);
					topCard.setColor(Color.RED);
					topCard.setWild(Wild.NONE);
					break;
				}
				case WILDCOLOR: {
					topCard = rules.playCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard,
							"afterAction", playerList.get(currentPlayer).isHuman());
					break;
				}
				default:
					break;
				}

				switch (topCard.getNumber()) {
				case NONE: {
					if (rules.checkColorCardAfterAction(playerList.get(currentPlayer).getPlayerCards(), topCard)) {
						// After reverse: Type of action is set to NONE and next player should play a
						// matching color card:
						System.out.println("Current player: " + currentPlayer);
						topCard = rules.playCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard,
								"afterAction", playerList.get(currentPlayer).isHuman());
					} else {
						System.out.println("Current player: " + currentPlayer);
						int numberOfCards = rules.drawCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(),
								topCard);
						playerList.get(currentPlayer).setNumberOfCards(numberOfCards);
					}
					break;
				}
				default: {

					if (rules.checkNumberCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard)) {
						System.out.println("Current player: " + currentPlayer);
						topCard = rules.playCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard,
								"number", playerList.get(currentPlayer).isHuman());
					} else if (rules.checkActionCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(),
							topCard)) {
						System.out.println("Current player: " + currentPlayer);
						topCard = rules.playCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard,
								"action", playerList.get(currentPlayer).isHuman());
					} else {
						System.out.println("Current player: " + currentPlayer);
						int numberOfCards = rules.drawCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(),
								topCard);
						playerList.get(currentPlayer).setNumberOfCards(numberOfCards);
					}
					break;
				}
				} // switch getNumber
				break;
			}

			case SKIP: {
				System.out.println("Current player: " + currentPlayer + "\tReverse is: " + isReverse);
				System.out.println("Player " + currentPlayer + " skips");
				// remove action and number values, so next player can play a color card (or
				// draws a card)
				topCard.setAction(Action.NONE);
				topCard.setNumber(Number.NONE);
				break;
			}
			case DRAWTWO: {
				System.out.println("Current player: " + currentPlayer);
				int numberOfCards = rules.handleActionDraw(thisDeck, playerList.get(currentPlayer).getPlayerCards(),
						topCard);
				playerList.get(currentPlayer).setNumberOfCards(numberOfCards);
				topCard.setAction(Action.NONE);
				topCard.setNumber(Number.NONE);
				break;
			}
			case REVERSE: {
				System.out.println("Game is reversed: " + isReverse + " Current player: " + currentPlayer);
				topCard.setAction(Action.NONE);
				topCard.setNumber(Number.NONE);
				isReverse = 1 - isReverse;
				break;
			}
			} // switch getAction()

			if (playerList.get(currentPlayer).getPlayerCards().size() == 0) {
				System.out.println("Player " + currentPlayer + " won!");
				break;
			}
//			System.out.println("Number of Cards: " + playerList.get(currentPlayer).getPlayerCards().size());
			System.out.println("----------------------");

			currentPlayer = rules.handleNextPlayer(isReverse, currentPlayer, (playerList.size() - 1));

			if (thisDeck.getCards().size() < 5) {
				thisDeck = new UnoDeck();
			}

		} // while

	}

	public UnoDeck getThisDeck() {
		return thisDeck;
	}

	public void setThisDeck(UnoDeck thisDeck) {
		this.thisDeck = thisDeck;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}

	public Player getPlayerThree() {
		return playerThree;
	}

	public void setPlayerThree(Player playerThree) {
		this.playerThree = playerThree;
	}

	public Player getPlayerFour() {
		return playerFour;
	}

	public void setPlayerFour(Player playerFour) {
		this.playerFour = playerFour;
	}
	
	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Card getTopCard() {
		return topCard;
	}

	public void setTopCard(Card topCard) {
		this.topCard = topCard;
	}
	/**
	 * @return the playerList
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * @param playerList the playerList to set
	 */
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}


}
