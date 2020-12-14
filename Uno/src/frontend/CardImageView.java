/*
 * 
 */
package frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cards.Card;

/**
 * CardImageView sets the current type of uno card in the CardBox.
 *
 */
public class CardImageView extends ImageView{
    private Card card;
    
    public CardImageView(Image image, Card card){
        super(image);
        this.card = card;               
    }
    
    public Card getCard(){
        return card;
    }
    
    public CardImageView(Card card) throws FileNotFoundException{
       	super(new Image(new FileInputStream("images/blue-eight.png")));
    	setFitWidth(68);
        setFitHeight(98);
        this.card = card;
     }
}
