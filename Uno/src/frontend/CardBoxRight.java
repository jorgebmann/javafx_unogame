package frontend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import cards.Card;
import game.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Declaration of right window of scene. Gives a label and a picture to computer
 * player. Sets the cards that are shown on the back side.
 * 
 */
public class CardBoxRight extends HBox {

	private ObservableList<CardImageView> userCards = FXCollections.observableArrayList();
	private VBox cardsVBox = new VBox();
	private VBox imageBox = new VBox();
	private Player player;

	public CardBoxRight(Player player) throws FileNotFoundException {

		this.player = player;
		Label labelName = new Label("Computer3");
		FileInputStream inputstream = new FileInputStream("images/faces/emoji3.png");
		Image image = new Image(inputstream);
		ImageView imageView = new ImageView(image);
		imageBox.getChildren().addAll(labelName, imageView);
		imageBox.setAlignment(Pos.CENTER);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		this.getChildren().addAll(imageBox, cardsVBox);
		this.setAlignment(Pos.CENTER);

	}

	public void removeCard(Card card) {
		for (CardImageView cardImageView : userCards) {
			if (cardImageView.getCard().equals(card)) {
				userCards.remove(cardImageView);
				cardsVBox.setSpacing(cardsVBox.getSpacing() + 10);
				cardsVBox.getChildren().clear();
				cardsVBox.getChildren().addAll(userCards);
				break;
			}
		}
	}

	public void setCards() throws FileNotFoundException {
		for (Card card : this.player.getPlayerCards()) {
			FileInputStream inputstream = new FileInputStream("images/UNO-Back-right.png");
			Image image = new Image(inputstream);
			CardImageView cardImageView = new CardImageView(image, card);
			cardImageView.setFitWidth(98);
			cardImageView.setFitHeight(68);
			this.userCards.add(cardImageView);
		}
		cardsVBox.getChildren().clear();
		cardsVBox.getChildren().addAll(userCards);
		cardsVBox.setAlignment(Pos.CENTER);
	}

	public void addCard(Card card) throws FileNotFoundException {
		FileInputStream inputstream = new FileInputStream("images/UNO-Back-right.png");
		Image image = new Image(inputstream);
		CardImageView cardImageView = new CardImageView(image, card);
		cardImageView.setFitWidth(98);
		cardImageView.setFitHeight(68);
		this.userCards.add(cardImageView);
		cardsVBox.setSpacing(-1 * ((userCards.size() - 6) * 20));
		cardsVBox.getChildren().clear();
		cardsVBox.getChildren().addAll(userCards);
	}

}
