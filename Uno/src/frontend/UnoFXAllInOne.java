package frontend;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cards.Card;
import cards.UnoDeck;
import cards.Card.Action;
import cards.Card.Color;
import cards.Card.Number;
import cards.Card.Wild;
import game.Dealer;
import game.Player;
import game.RulesForFX;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This Class implements starting point of user interface and the first move of
 * a computer player Declaration User Interface includes emoji, player's uno
 * cards, top card and card deck in the middle First move is to draw a card.
 */
public class UnoFXAllInOne extends Application {

	// ++++++++++ Attributes for play uno ++++++++++++++++++
	private UnoDeck thisDeck;
	private Player playerOne;
	private Player playerTwo;
	private Player playerThree;
	private Player playerFour;
	private List<Player> playerList;
	private Dealer dealer;
	private Card topCard;

	// ++++++++++ Attributes for FX ++++++++++++++++++
	private BorderPane root = new BorderPane();
	private CardBoxLeft cardBoxLeft;
	private CardBoxTop cardBoxTop;
	private CardBoxRight cardBoxRight;
	private HumanCardBox humanCardBox;
	private CardBoxCenter cardBoxCenter;

	@Override
	public void start(Stage stage) throws Exception {

		gameStarter();

		cardBoxCenter = new CardBoxCenter(getTopCard());
		humanCardBox = new HumanCardBox(getPlayerOne());
		cardBoxLeft = new CardBoxLeft(getPlayerTwo());
		cardBoxTop = new CardBoxTop(getPlayerThree());
		cardBoxRight = new CardBoxRight(getPlayerFour());

		humanCardBox.setCards(getPlayerOne());
		cardBoxLeft.setCards();
		cardBoxTop.setCards();
		cardBoxRight.setCards();

		root.setCenter(cardBoxCenter);
		root.setLeft(cardBoxLeft);
		root.setTop(cardBoxTop);
		root.setRight(cardBoxRight);
		root.setBottom(humanCardBox);

		startTask();
		Scene scene = new Scene(root, 800, 800);
		stage.setTitle("Play UNO. It\'s fun.");
		stage.setScene(scene);

		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public void startTask() {
		// Create a Runnable
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					playUno();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		// Run the task in a background thread
		Thread backgroundThread = new Thread(task);
		// Terminate the running thread if the application exits
		backgroundThread.setDaemon(true);
		// Start the thread
		backgroundThread.start();
	}

	public void gameStarter() {
		// generate a new shuffled Deck of Cards:
		setThisDeck(new UnoDeck());

		// generate players:
		setPlayerOne(new Player());
		setPlayerTwo(new Player());
		setPlayerThree(new Player());
		setPlayerFour(new Player());

		// first player is human, rest is PC:
		playerFour.setHuman(true);

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

	}

	public void playUno() throws FileNotFoundException {

//		Card topCard = new Card(Color.NONE, Number.NONE, Action.NONE, Wild.WILDCOLOR);
		Card topCard = new Card(Color.BLUE, Number.TWO, Action.NONE, Wild.NONE);
		setTopCard(topCard);

		/** This value initializes the first player */
		int currentPlayer = 0;
		/**
		 * This value initializes the order of player on the table, i.e. clockwise or
		 * anti-clockwise
		 */
		int isReverse = 0; // Value 0: clockwise => 0, 1, 2; Value 1: anti-clock => 0, 2, 1
		RulesForFX rules = new RulesForFX(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard);

		System.out.println("----------Play it------------");

//		while (rules.getNumberOfCards(playerList.get(currentPlayer).getPlayerCards()) > 0) {

		/**
		 * Gigantic switch and if-else setting to generate playing rules. Top card can
		 * be an action card or not. Based on top card moves of players are defined if
		 * top card is not action, than we have two cases: A) top card is an action card
		 * that was valid for previous player (e.g. previous player had a skip card and
		 * current player can play a card with the same color as the skip card) B) top
		 * card is a number card and current player can chose between number and action
		 * card Moreover, action card can be skip, reverse, or draw two. In this case,
		 */
		System.out.println(
				"Player: " + currentPlayer + " Cards: " + playerList.get(currentPlayer).getPlayerCards().toString());

		switch (topCard.getAction()) {
		case NONE: {

			switch (topCard.getWild()) {
			case DRAWFOUR: {
				System.out.println("Current player: " + currentPlayer);
				Card toAdd = rules.handleActionDraw(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard);
				playerList.get(currentPlayer).setNumberOfCards(4);
				topCard.setColor(Color.RED);
				topCard.setWild(Wild.NONE);
				if (currentPlayer == 0) {
					humanCardBox.addCard(toAdd);
				}
				if (currentPlayer == 1) {
					cardBoxLeft.addCard(toAdd);
				}
				if (currentPlayer == 2) {
					cardBoxTop.addCard(toAdd);
				}
				if (currentPlayer == 3) {
					cardBoxRight.addCard(toAdd);
				}

				break;
			}
			case WILDCOLOR: {
				topCard = new Card(Color.RED, Number.NONE, Action.NONE, Wild.WILDCOLOR);
				topCard = rules.playCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard,
						"afterAction", playerList.get(currentPlayer).isHuman());
				if (currentPlayer == 0) {
					humanCardBox.removeCard(topCard);
					cardBoxCenter.addCard(topCard);

				}
				if (currentPlayer == 1) {
					cardBoxLeft.removeCard(topCard);
					cardBoxCenter.addCard(topCard);

				}
				if (currentPlayer == 2) {
					cardBoxTop.removeCard(topCard);
					cardBoxCenter.addCard(topCard);
				}
				if (currentPlayer == 3) {
					cardBoxRight.removeCard(topCard);
					cardBoxCenter.addCard(topCard);
				}

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
					cardBoxCenter.addCard(topCard);

				} else {
					System.out.println("Current player: " + currentPlayer);
					Card toAdd = rules.drawCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard);
					playerList.get(currentPlayer)
							.setNumberOfCards(playerList.get(currentPlayer).getNumberOfCards() + 1);
					cardBoxLeft.addCard(toAdd);
				}
				break;
			}
			default: {

				if (rules.checkNumberCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard)) {
					System.out.println("Current player: " + currentPlayer);
					topCard = rules.playCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard,
							"number", playerList.get(currentPlayer).isHuman());
					if (currentPlayer == 0) {
//							System.out.println("Enter usereventhandler.");
						UserEventHandler newEvent = new UserEventHandler(humanCardBox, cardBoxCenter);
						humanCardBox.activateHandler(newEvent);

					}
					if (currentPlayer == 1) {
						cardBoxLeft.removeCard(topCard);
						cardBoxCenter.addCard(topCard);

					}
					if (currentPlayer == 2) {
						cardBoxTop.removeCard(topCard);
						cardBoxCenter.addCard(topCard);

					}
					if (currentPlayer == 3) {
						cardBoxRight.removeCard(topCard);
						cardBoxCenter.addCard(topCard);
					}

				} else if (rules.checkActionCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard)) {
					System.out.println("Current player: " + currentPlayer);
					topCard = rules.playCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard,
							"action", playerList.get(currentPlayer).isHuman());
					cardBoxLeft.removeCard(topCard);
					cardBoxCenter.addCard(topCard);
				} else {
					System.out.println("Current player: " + currentPlayer);
					Card toAdd = rules.drawCard(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard);
					playerList.get(currentPlayer).addPlayerCards(toAdd);
					;
					cardBoxLeft.addCard(toAdd);
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
			Card toAdd = rules.handleActionDraw(thisDeck, playerList.get(currentPlayer).getPlayerCards(), topCard);
			playerList.get(currentPlayer).setNumberOfCards(2);
			topCard.setAction(Action.NONE);
			topCard.setNumber(Number.NONE);
			cardBoxLeft.addCard(toAdd);
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
//				break;
		}
//			System.out.println("Number of Cards: " + playerList.get(currentPlayer).getPlayerCards().size());
		System.out.println("----------------------");

		currentPlayer = rules.handleNextPlayer(isReverse, currentPlayer, (playerList.size() - 1));

		if (thisDeck.getCards().size() < 5) {
			thisDeck = new UnoDeck();
		}

//		} // while

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
