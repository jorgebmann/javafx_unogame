package cards;

/**
 * Definition of a Uno Card, including its color, number, and/or action
 * Number Cards have a color and a number.
 * Action Cards have a color and an action
 * Wild cards have a specific action.
 * 
 */
public class Card {
	
	
	public enum Color {
		NONE, RED, BLUE, GREEN, YELLOW;
	}
	
	public enum Number {
		NONE, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
	}
	
	public enum Action {
		NONE, SKIP, REVERSE, DRAWTWO;
	}
	
	public enum Wild {
		NONE, WILDCOLOR, DRAWFOUR;
	}
		
	//enum Color, Number, and Actions have an attribute name:
	// Variable names are "protected", because they get extended by NumberCard, ActionCard, and WildCard
	protected Color color; 
	
	protected Number number;
	
	protected Action action;
	
	protected Wild wild;

	/**
	 * Constructor of color, number, and action: 
	 * TODO: Constructor for color, number in NumberCard and action in ActionCard
	 */
	public Card(Color color, Number number, Action action, Wild wild) {
		super();
		this.color = color;
		this.number = number;
		this.action = action;
		this.wild = wild;
	}

	/**
	 * @return the wild
	 */
	public Wild getWild() {
		return wild;
	}

	/**
	 * @param wild the wild to set
	 */
	public void setWild(Wild wild) {
		this.wild = wild;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the number
	 */
	public Number getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Number number) {
		this.number = number;
	}

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	
    public String toColorView() {

        switch (this.color) {
            case RED:
                return "red";
            case YELLOW:
                return "yellow";
            case GREEN:
                return "green";
            case BLUE:
                return "blue";
		default:
			break;
        }
        return null;
    }
    
    public String toNumberView() {

        switch (this.number) {
            case ZERO:
                return "zero";
            case ONE:
                return "one";
            case TWO:
                return "two";
            case THREE:
                return "three";
            case FOUR:
                return "four";
            case FIVE:
                return "five";
            case SIX:
                return "six";
            case SEVEN:
                return "seven";
            case EIGHT:
                return "eight";
            case NINE:
                return "nine";
		default:
			break;
        }
        return null;
    }
    
    public String toActionView() {

        switch (this.action) {
            case SKIP:
                return "skip";
            case REVERSE:
                return "reverse";
            case DRAWTWO:
                return "drawTwo";

		default:
			break;
        }
        return null;
    }
    
    
	@Override
	public String toString() {
		return "Card [color=" + color + ", number=" + number + ", action=" + action + ", wild=" + wild + "]";
	}

	
}
