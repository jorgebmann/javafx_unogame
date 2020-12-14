package frontend;

import java.io.FileNotFoundException;
import cards.Card;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Class implements mouse click event in order to choose a card the user intends to play.
 * 
 * 
 */
public class UserEventHandler implements EventHandler<MouseEvent> {

	private HumanCardBox humanCardBox;
	private CardBoxCenter cardBoxCenter;

	/**
	 * @param humanCardBox
	 * @param cardBoxCenter
	 */
	public UserEventHandler(HumanCardBox humanCardBox, CardBoxCenter cardBoxCenter) {
		super();
		this.humanCardBox = humanCardBox;
		this.cardBoxCenter = cardBoxCenter;
	}

	@Override
	public void handle(MouseEvent event) {

		final CardImageView selectedCard = (CardImageView) event.getSource();
		Card card = selectedCard.getCard();
		for (ImageView imageView : humanCardBox.userCards) {
			imageView.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
		}
		if (card.getColor().equals(Card.Wild.WILDCOLOR) || card.getColor().equals(Card.Wild.DRAWFOUR)) {
			
			//TODO: implement wild color selection

			System.out.println("Wild stuff");

		} else {
//		    Platform.runLater(() -> {
//                    humanCardBox.player.play(card, round.getDiscardPile());
//			System.out.println("Clickevent!");
			humanCardBox.removeCard(card);
			try {
				cardBoxCenter.addCard(card);
			} catch (FileNotFoundException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			}
		}
	}
}
