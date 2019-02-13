/**
 * @author joey pham
 * @date 2 feb 2019
 * @description represents a card that contains a rank and a suit
 */

public class Card {
	/**
	 * represents the rank of the card as an integer
	 */
	private int rankValue;
	/**
	 * represetns the rank of the card as a string
	 */
	private String rankString;
	
	/**
	 * represents the suit of the card as an integer
	 */
	private int suitValue;
	/**
	 * represents the suit of the card as a string
	 */
	private String suitString;
	
	/**
	 * default constructor
	 */
	public Card ( ) {
		suitValue = 1;
		setSuitString ( );
		rankValue = 1;
		setRankString ( );
	}
	
	/**
	 * overloaded constructor that passes in two integers, one for
	 *     suit, and one for num. then suits the string for both
	 * @param suit - integer for the suit value
	 * @param num - integer for the rank value
	 */
	public Card ( int suit, int rank ) {
		suitValue = suit;
		setSuitString ( );
		rankValue = rank;
		setRankString ( );
	}
	
	/**
	 * sets the string for suit corresponding to its value
	 */
	public void setSuitString ( ) {
		switch ( suitValue ) {
			case 1: 
				suitString = "Diamonds";
				break;
			case 2:
				suitString = "Clubs";
				break;
			case 3:
				suitString = "Hearts";
				break;
			case 4:
				suitString = "Spades";
				break;
		}
	}
	
	/**
	 * sets the string for rank corresponding to its value
	 */
	public void setRankString ( ) {
		switch ( rankValue ) {
			case 1:
				rankString = "Ace";
				break;
			case 2:
				rankString = "Two";
				break;
			case 3:
				rankString = "Three";
				break;
			case 4:
				rankString = "Four";
				break;
			case 5:
				rankString = "Five";
				break;
			case 6:
				rankString = "Six";
				break;
			case 7:
				rankString = "Seven";
				break;
			case 8:
				rankString = "Eight";
				break;
			case 9:
				rankString = "Nine";
				break;
			case 10:
				rankString = "Ten";
				break;
			case 11:
				rankString = "Jack";
				break;
			case 12:
				rankString = "Queen";
				break;
			case 13:
				rankString = "King";
				break;
		}
	}
	
	/**
	 * returns the rank value 
	 * @return rankValue - rank as an integer of the card
	 */
	public int rankValue ( ) {
		return rankValue;
	}
	
	/**
	 * returns the rank string 
	 * @return rankString - rank as a string of the card
	 */
	public String rankString ( ) {
		return rankString;
	}
	
	/**
	 * returns the suit value 
	 * @return suitValue - suit as an integer of the card
	 */
	public int suitValue ( ) {
		return suitValue;
	}
	
	/**
	 * returns the suit string
	 * @return suitString - suit as a string of the card  
	 */
	public String suitString ( ) {
		return suitString;
	}
	
	/**
	 * returns a string of the card name
	 * @return s - string of the card name 
	 */
	public String toString ( ) {
		return rankString + " of " + suitString;
	}
}
