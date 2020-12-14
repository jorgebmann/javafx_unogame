package frontend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import cards.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Declaration of center window of scene. Shows current top card and card deck.
 * 
 */
public class CardBoxCenter extends VBox {


	private ObservableList<CardImageView> topCards = FXCollections.observableArrayList();
	private ObservableList<CardImageView> stackCards = FXCollections.observableArrayList();
	private HBox stackBox = new HBox();
	private HBox topCardBox = new HBox();

	public CardBoxCenter(Card topCard) throws FileNotFoundException {

		if (topCard.getWild().equals(Card.Wild.DRAWFOUR)) {
			FileInputStream inputstream = new FileInputStream("images/wildFour-one.png");
//			System.out.println("images/" + topCard.getWild().toString() + ".png");
			Image image = new Image(inputstream);
			CardImageView cardImageView = new CardImageView(image, topCard);
			cardImageView.setFitWidth(68);
			cardImageView.setFitHeight(98);
			this.topCards.add(cardImageView);
		}
		if (topCard.getWild().equals(Card.Wild.WILDCOLOR)) {
			FileInputStream inputstream = new FileInputStream("images/wild-one.png");
//			System.out.println("images/" + topCard.getWild().toString() + ".png");
			Image image = new Image(inputstream);
			CardImageView cardImageView = new CardImageView(image, topCard);
			cardImageView.setFitWidth(68);
			cardImageView.setFitHeight(98);
			this.topCards.add(cardImageView);
		}
		if (topCard.getAction() != Card.Action.NONE) {
//			System.out.println("images/" + topCard.toColorView() + "-" + topCard.toActionView() + ".png");
			FileInputStream inputstream = new FileInputStream(
					"images/" + topCard.toColorView() + "-" + topCard.toActionView() + ".png");
			Image image = new Image(inputstream);
			CardImageView cardImageView = new CardImageView(image, topCard);
			cardImageView.setFitWidth(68);
			cardImageView.setFitHeight(98);
			this.topCards.add(cardImageView);
		}
		if (topCard.getNumber() != Card.Number.NONE) {
//			System.out.println("images/" + topCard.toColorView() + "-" + topCard.toNumberView() + ".png");
			CardImageView cardImageView = new CardImageView(new Image(
					new FileInputStream("images/" + topCard.toColorView() + "-" + topCard.toNumberView() + ".png")),
					topCard);
			cardImageView.setFitWidth(68);
			cardImageView.setFitHeight(98);
			this.topCards.add(cardImageView);

		}

		FileInputStream inputstream = new FileInputStream("images/UNO-Back_1.png");
		Image image = new Image(inputstream);
//		ImageView imageView = new ImageView(image);
		CardImageView cardImageView = new CardImageView(image, topCard);
		cardImageView.setFitWidth(68);
		cardImageView.setFitHeight(98);
		this.stackCards.add(cardImageView);

		topCardBox.getChildren().clear();
		topCardBox.getChildren().addAll(topCards);
		topCardBox.setAlignment(Pos.CENTER);

		stackBox.getChildren().clear();
		stackBox.getChildren().addAll(stackCards);
		stackBox.setAlignment(Pos.CENTER);

		this.getChildren().addAll(topCardBox, stackBox);
		this.setAlignment(Pos.CENTER);
//        this.setPadding(new Insets(230, 0, 230, 0));

		this.setMaxSize(800, 800);
	}

	public void addCard(Card newCard) throws FileNotFoundException {

		if (newCard.getWild().equals(Card.Wild.DRAWFOUR)) {
			FileInputStream inputstream = new FileInputStream("images/wildFour-one.png");
//			System.out.println("images/" + newCard.getWild().toString() + ".png");
			Image image = new Image(inputstream);
			CardImageView cardImageView = new CardImageView(image, newCard);
			cardImageView.setFitWidth(68);
			cardImageView.setFitHeight(98);
			this.topCards.set(0, cardImageView);
			topCardBox.getChildren().clear();
			topCardBox.getChildren().addAll(topCards);
			topCardBox.setAlignment(Pos.CENTER);
		}
		if (newCard.getWild().equals(Card.Wild.WILDCOLOR)) {
			FileInputStream inputstream = new FileInputStream("images/wild-one.png");
//			System.out.println("images/" + newCard.getWild().toString() + ".png");
			Image image = new Image(inputstream);
			CardImageView cardImageView = new CardImageView(image, newCard);
			cardImageView.setFitWidth(68);
			cardImageView.setFitHeight(98);
			this.topCards.set(0, cardImageView);
			topCardBox.getChildren().clear();
			topCardBox.getChildren().addAll(topCards);
			topCardBox.setAlignment(Pos.CENTER);
		}
		if (newCard.getAction() != Card.Action.NONE) {
//			System.out.println("images/" + newCard.toColorView() + "-" + newCard.toActionView() + ".png");
			FileInputStream inputstream = new FileInputStream(
					"images/" + newCard.toColorView() + "-" + newCard.toActionView() + ".png");
			Image image = new Image(inputstream);
			CardImageView cardImageView = new CardImageView(image, newCard);
			cardImageView.setFitWidth(68);
			cardImageView.setFitHeight(98);
			this.topCards.set(0, cardImageView);
			topCardBox.getChildren().clear();
			topCardBox.getChildren().addAll(topCards);
			topCardBox.setAlignment(Pos.CENTER);
		}
		if (newCard.getNumber() != Card.Number.NONE) {
//			System.out.println("images/" + newCard.toColorView() + "-" + newCard.toNumberView() + ".png");
			CardImageView cardImageView = new CardImageView(new Image(
					new FileInputStream("images/" + newCard.toColorView() + "-" + newCard.toNumberView() + ".png")),
					newCard);
			cardImageView.setFitWidth(68);
			cardImageView.setFitHeight(98);
			this.topCards.set(0, cardImageView);
			topCardBox.getChildren().clear();
			topCardBox.getChildren().addAll(topCards);
			topCardBox.setAlignment(Pos.CENTER);

		}

	}


}
