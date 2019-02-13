import java.util.ArrayList;

/**
 * @author joey pham
 * @date 2 feb 2019
 * @description represents a pile of cards, that you can add, take, and get from.
 * 				 acts as a stack
 */

public class Pile {
	/**
	 * this is the pile of cards
	 */
	private ArrayList < Card > pile;
	
	/**
	 * default constructor
	 */
	public Pile ( ) {
		pile = new ArrayList < Card > ( );
	}
	
	/**
	 * adds card to the beginning of the pile (top of the stack)
	 * @param c - card to be added
	 * @return c - card added
	 */
	public Card addCard ( Card c ) {
		pile.add ( 0, c );
		return c;
	}
	
	/**
	 * gets the top card
	 * @return card - top card of the pile
	 */
	public Card getNextCard ( ) {
		return pile.remove ( 0 );
	}
	
	/**
	 * get card at the given index
	 * @param index - card to get at
	 * @return card taken
	 */
	public Card takeCard ( int index ) {
		return pile.remove ( index );
	}
	
	/**
	 * returns the size of the pile
	 * @return size - size of the pile
	 */
	public int size ( ) {
		return pile.size ( );
	}
	
	/**
	 * returns the string of out all the cards in the pile
	 * @return ret - a list of cards 
	 */
	public String toString ( ) {
		String ret = "";
		for ( int index = 0; index < size ( ); index ++ ) {
			ret = ret + pile.get ( index ) + "\n";
		}
		return ret;
	}
}