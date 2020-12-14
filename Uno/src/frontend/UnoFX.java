package frontend;

import game.PlayUno;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class implements a starter user interface with 3 computer and one human player.
 * Declaration User Interface: includes emoji, player's uno cards, top card and card deck
 * in the middle 
 * 
 * 
 */
public class UnoFX extends Application {
	
	
	PlayUno playuno = new PlayUno();

	
	//++++++++++ Attributes for FX ++++++++++++++++++

	BorderPane root = new BorderPane();
	CardBoxLeft cardBoxLeft;
	CardBoxTop cardBoxTop;
	CardBoxRight cardBoxRight;
	HumanCardBox humanCardBox;
	CardBoxCenter cardBoxCenter;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		playuno.gameStarter(true);
		
		cardBoxCenter = new CardBoxCenter(playuno.getTopCard());
		humanCardBox = new HumanCardBox(playuno.getPlayerOne());
		cardBoxLeft = new CardBoxLeft(playuno.getPlayerTwo());
		cardBoxTop = new CardBoxTop(playuno.getPlayerThree());
		cardBoxRight = new CardBoxRight(playuno.getPlayerFour());
		
		humanCardBox.setCards(playuno.getPlayerOne());
		cardBoxLeft.setCards();
		cardBoxTop.setCards();
		cardBoxRight.setCards();
		
		root.setCenter(cardBoxCenter);
		root.setLeft(cardBoxLeft);
		root.setTop(cardBoxTop);
		root.setRight(cardBoxRight);
		root.setBottom(humanCardBox);
		
		Scene scene = new Scene(root, 800, 800);
        stage.setTitle("Play UNO. It\'s fun.");
        stage.setScene(scene);

        stage.show();
        
		
	}


	public static void main(String[] args) {
		launch();	
	}


	
}
