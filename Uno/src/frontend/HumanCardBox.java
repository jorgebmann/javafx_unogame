package frontend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import cards.Card;
import game.Player;

/**
 * Box for human player. Cards are shown. TODO: player can chose a card to play
 * with a mouse click.
 * 
 */
public class HumanCardBox extends VBox {

	protected ObservableList<CardImageView> userCards = FXCollections.observableArrayList();
	private HBox cardsHBox = new HBox();
	private HBox imageBox = new HBox();
    private Player player;

	public HumanCardBox(Player player2) throws FileNotFoundException {

		this.player = player2;
		Label labelName = new Label("Your name");
		FileInputStream inputstream = new FileInputStream("images/faces/emoji4.png");
		Image image = new Image(inputstream);
		ImageView imageView = new ImageView(image);
		imageBox.getChildren().add(imageView);
		imageBox.setAlignment(Pos.CENTER);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		this.getChildren().addAll(imageBox, cardsHBox);
		this.setAlignment(Pos.CENTER);

	}

	public void setCards(Player player) throws IOException {
		this.player = player;
//		System.out.println(player.getPlayerCards().toString());

		// get the card from the deck:

		int i = 0;
		for (Card card : player.getPlayerCards()) {

			if (player.getPlayerCards().get(i).getWild().equals(Card.Wild.DRAWFOUR)) {
				FileInputStream inputstream = new FileInputStream("images/wildFour-one.png");
//				System.out.println("images/" + player.getPlayerCards().get(i).getWild().toString() + ".png");
				Image image = new Image(inputstream);
				CardImageView cardImageView = new CardImageView(image, card);
				cardImageView.setFitWidth(68);
				cardImageView.setFitHeight(98);
				this.userCards.add(cardImageView);
			}
			if (player.getPlayerCards().get(i).getWild().equals(Card.Wild.WILDCOLOR)) {
				FileInputStream inputstream = new FileInputStream("images/wild-one.png");
//				System.out.println("images/" + player.getPlayerCards().get(i).getWild().toString() + ".png");
				Image image = new Image(inputstream);
				CardImageView cardImageView = new CardImageView(image, card);
				cardImageView.setFitWidth(68);
				cardImageView.setFitHeight(98);
				this.userCards.add(cardImageView);
			}
			if (player.getPlayerCards().get(i).getAction() != Card.Action.NONE) {
//				System.out.println("images/" + player.getPlayerCards().get(i).toColorView() + "-"
//						+ player.getPlayerCards().get(i).toActionView() + ".png");
				FileInputStream inputstream = new FileInputStream(
						"images/" + player.getPlayerCards().get(i).toColorView() + "-"
								+ player.getPlayerCards().get(i).toActionView() + ".png");
				Image image = new Image(inputstream);
				CardImageView cardImageView = new CardImageView(image, card);
				cardImageView.setFitWidth(68);
				cardImageView.setFitHeight(98);
				this.userCards.add(cardImageView);
			}
			if (player.getPlayerCards().get(i).getNumber() != Card.Number.NONE) {
//				System.out.println("images/" + player.getPlayerCards().get(i).toColorView() + "-"
//						+ player.getPlayerCards().get(i).toNumberView() + ".png");
				CardImageView cardImageView = new CardImageView(
						new Image(new FileInputStream("images/" + player.getPlayerCards().get(i).toColorView() + "-"
								+ player.getPlayerCards().get(i).toNumberView() + ".png")),
						card);
				cardImageView.setFitWidth(68);
				cardImageView.setFitHeight(98);
				this.userCards.add(cardImageView);

			}
			i++;
		}
		cardsHBox.getChildren().clear();
		cardsHBox.getChildren().addAll(userCards);
		cardsHBox.setAlignment(Pos.TOP_CENTER);
	}
	
	public void removeCard(Card card) {
        for (CardImageView cardImageView : userCards) {
            if (cardImageView.getCard().equals(card)) {
                userCards.remove(cardImageView);
                cardsHBox.setSpacing(cardsHBox.getSpacing() + 10);
                cardsHBox.getChildren().clear();
                cardsHBox.getChildren().addAll(userCards);
                break;
            }
        }
        
    }
	
    public void addCard(Card card) throws FileNotFoundException {
    	FileInputStream inputstream = new FileInputStream("images/UNO-Back_1.png");
        Image image = new Image(inputstream);
        CardImageView cardImageView = new CardImageView(image, card);
        cardImageView.setFitWidth(68);
        cardImageView.setFitHeight(98);
        this.userCards.add(cardImageView);
        cardsHBox.setSpacing(-1 * ((userCards.size() - 6) * 20));
        cardsHBox.getChildren().clear();
        cardsHBox.getChildren().addAll(userCards);
    }
    
    public void activateHandler(UserEventHandler userEventHandler) {

        for (ImageView imageView : userCards) {
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, userEventHandler);
        }
        
    }
    
    public void removeHandler(UserEventHandler userEventHandler){
        for (ImageView imageView : userCards) {
            imageView.removeEventHandler(MouseEvent.MOUSE_CLICKED, userEventHandler);
        }
    }

}
