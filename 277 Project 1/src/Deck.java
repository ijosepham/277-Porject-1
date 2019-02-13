import java.util.ArrayList;

/**
 * @author joey pham
 * @date 2 feb 2019
 * @description represents a deck of cards. allows user to take, add, remove
 * 				 and play cards. acts as a queue
 */

public class Deck {
	/**
	 * this is the deck of cards
	 */
	private ArrayList < Card > deck = new ArrayList < Card > ( );
	
	/**
	 * adds the given card to the end of the bottom of the deck
	 * @param c - card to add to the deck
	 * @return c - card added
	 */
	public Card addCard ( Card c ) {
		deck.add ( c );
		return c;
	}
	
	/**
	 * get a card at a given index
	 * @param index - index to grab the card from
	 * @return card - card taken from the deck
	 */
	public Card takeCard ( int index ) {
		return deck.remove ( index );
	}
	
	/**
	 * takes the top card from the deck 
	 * @return card - top card of the deck
	 */
	public Card nextCard ( ) {
		return takeCard ( 0 );
	}
	
	/**
	 * peeks at the card at the given index
	 * @param index - index to peek the card at
	 * @return card - the card peeked at
	 */
	public Card getCard ( int index ) {
		return deck.get ( index );
	}
	
	/**
	 * gives the size of the deck
	 * @return size - size of the deck
	 */
	public int size ( ) {
		return deck.size ( );
	}
	
	/**
	 * returns the string of out all the cards in the deck
	 * @return ret - a list of cards 
	 */
	public String toString ( ) {
		String ret = "";
		for ( int index = 0; index < size ( ); index ++ ) { // iterate through the deck
			ret = ret + deck.get ( index ) + "\n"; // and add each card to the string
		}
		return ret;
	}
}
