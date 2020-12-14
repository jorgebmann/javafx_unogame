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
 * Declaration of top window of scene. Gives a label and a picture to computer player. Sets the cards that 
 * are shown on the back side.
 * 
 */
public class CardBoxTop extends VBox {

    private ObservableList<CardImageView> userCards = FXCollections.observableArrayList();
    private HBox cardsHBox = new HBox();
    private HBox imageBox = new HBox();
    private Player player;
 
    public CardBoxTop(Player player) throws FileNotFoundException {

        this.player = player;
        Label labelName = new Label("Computer2");
        FileInputStream inputstream = new FileInputStream("images/faces/emoji2.png");
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        imageBox.getChildren().addAll(labelName, imageView);
        imageBox.setAlignment(Pos.CENTER);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        this.getChildren().addAll(imageBox, cardsHBox);
        this.setAlignment(Pos.CENTER);

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

    public void setCards() throws FileNotFoundException {
        for (Card card : this.player.getPlayerCards()) {
        	FileInputStream inputstream = new FileInputStream("images/UNO-Back_1.png");
            Image image = new Image(inputstream);
//            ImageView imageView = new ImageView(image);
            CardImageView cardImageView = new CardImageView(image, card);
            cardImageView.setFitWidth(68);
            cardImageView.setFitHeight(98);
            this.userCards.add(cardImageView);
        }

        cardsHBox.getChildren().clear();
        cardsHBox.getChildren().addAll(userCards);
        cardsHBox.setAlignment(Pos.CENTER);
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
}
