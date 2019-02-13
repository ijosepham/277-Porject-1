import java.util.Scanner;

/**
 * @author joey pham
 * @date 2 feb 2019
 * @description represents a card that contains a rank and a suit
 */

public class Game {
	/**
	 * deck that the user has
	 */
	private Deck playerDeck = new Deck ( );
	/**
	 * deck that the program uses
	 */
	private Deck programDeck = new Deck ( );
	/**
	 * pile where cards played go to each round 
	 */
	private Pile pile = new Pile ( );
	
	/**
	 * current player card played
	 */
	private Card playerCard;
	/**
	 * current program card played
	 */
	private Card programCard;
	/**
	 * rank of the current player card
	 */
	private int playerRank;
	/**
	 * rank of the current program card
	 */
	private int programRank;
	/**
	 * difference between the player's card rank and the program's card rank
	 */
	private int rankDifference;
	
	/**
	 * used to keep track if either player decides to challenge
	 */
	private boolean extraRound = false; 
	
	/**
	 * default constructor
	 */
	public Game ( ) {
		dealCards ( );
	}
	
	/**
	 * creates a normal 52 card deck, then randomly distributes 26 cards
	 * to the player, and then randomly again to the program
	 */
	public void dealCards ( ) {
		// this makes a normal 52card deck
		for ( int suit = 1; suit < 5; suit ++ ) { // for suits 1-4
			for ( int rank = 1; rank < 14; rank ++ ) { // for ranks 1-13
				pile.addCard ( new Card ( suit, rank ) );
			}
		}
		
		int randomIndex = 0;
		Card randomCard = new Card ( );
		
		// then this pulls 26 random cards from the pile and gives it to the player
		for ( int i = 0; i < 26; i ++ ) {
			randomIndex = ( int ) ( Math.random ( ) * pile.size ( ) ) ; // get a random index to pull from
			randomCard = pile.takeCard ( randomIndex ); // get that card at the random index
			playerDeck.addCard ( randomCard ); // put the random card  into the player's hand
		}
		
		// gives the last 26 cards to the program in a random order
		for ( int i = 0; i < 26; i ++ ) {
			randomIndex = ( int ) ( Math.random ( ) * pile.size ( ) ) ;
			randomCard = pile.takeCard ( randomIndex );
			programDeck.addCard ( randomCard );
		}
		
	}
	
	/**
	 * each player plays their next card and then updates the rank difference
	 */
	public void goToBattle ( ) {
		// player plays a card
		playerCard = playerDeck.nextCard ( ); 
		playerRank = playerCard.rankValue ( );
		System.out.println ( "Player card:  " + playerCard + " (" + playerRank + ")" );
		
		// program plays a card
		programCard = programDeck.nextCard ( );
		programRank = programCard.rankValue ( );
		System.out.println ( "Program card: " + programCard + " (" + programRank + ")" + "\n" );
		
		// then we update the rank difference so we can compare them later
		rankDifference = playerRank - programRank; // is '+' if player rank is higher, '-' if program is higher
		
		// add these cards to the pile
		pile.addCard ( programCard );
		pile.addCard ( playerCard );		
	}
	
	/**
	 * arrives here if the rankdifference between both players' cards are 0 (card ranks are the same)
	 * first checks that both aren't out of cards before the war starts, if so, that player loses.
	 * then checks if either player doesn't have enough cards to play. if either player doesn't have 
	 *     enough cards, plays whatever cards they have left and uses the last card played to compare.
	 * if both players have enough for a full war, go on normally.
	 */
	public void goToWar ( ) {
		extraRound = true; // no challenging during war
		System.out.println ( "War Time" ); 

		// if the player doesnt have any cards to play war, they lose
		if ( playerDeck.size ( ) == 0 ) {
			System.out.println ( "Player is out of cards." );
			collectCards ( "Program", programDeck );
		} else if ( programDeck.size ( ) == 0 ) { // if program doesnt have any cards
			System.out.println ( "Program is out of cards." );
			collectCards ( "Player", playerDeck );
		} else { // if both players still have some cards remaining
			if ( playerDeck.size ( ) < 4 ) { // this checks if the user doesn't have enough cards to play, but isn't empty
				// for example: if player only has 3 cards left, he puts down 2 cards, 
				//              then later uses his 3rd card to compare ranks
				for ( int i = 0; i < playerDeck.size ( ) - 1; i ++ ) { 
					pile.addCard ( playerDeck.nextCard ( ) );
				}
				
				for ( int i = 0; i < 3; i ++ ) { // program plays the normal 3 cards
					pile.addCard ( programDeck.nextCard ( ) );
				}
			} else if ( programDeck.size ( ) < 4 ) { // same thing, but if the program is the one short of cards
				
				for ( int i = 0; i < programDeck.size ( ) - 1; i ++ ) {
					pile.addCard ( programDeck.nextCard ( ) );
				}
				
				for ( int i = 0; i < 3; i ++ ) {
					pile.addCard ( playerDeck.nextCard ( ) );
				}
			} else { // gets here if both players have at least 4 cards
				// each player puts down 3 cards
				for ( int i = 0; i < 3; i ++ ) { 
					pile.addCard ( playerDeck.nextCard ( ) );
					pile.addCard ( programDeck.nextCard ( ) );
				}
			}
			// after putting down 3 cards (or whatever amount), players use their next card to compare ranks
			takeTurn ( ); // this plays their next cards and comapres ranks
		}
	}
	
	/**
	 * the winner collects all the cards from the pile
	 * @param winner - string of the winner
	 * @param winnerDeck - deck of the winner
	 */
	public void collectCards ( String winner, Deck winnerDeck ) { 
		// initial value of pile cause it wont be constant if used in the forloop
		int pileSize = pile.size ( ); 
		
		if ( winner == "Player" ) { // if player won
			System.out.println ( "Player collected " + pileSize + " cards. " );	
		} else { // if program won
			System.out.println ( "\n" + "Program collected " + pileSize + " cards. " );	
		}
		
		// winner collects cards
		for ( int i = 0; i < pileSize; i ++ ) { // loop through the pile
			winnerDeck.addCard ( pile.getNextCard ( ) ); // and add the pile's cards into the winner's deck
		}
		
		System.out.println ( "Player deck size: " + playerDeck.size ( ) );
		System.out.println ( "Program deck size: " + programDeck.size ( ) + "\n" );
		
		extraRound = false; // resets
	}
	
	/**
	 * in a battle, if the program loses, this method determines if the program plays another card.
	 * in my case, the program only plays another card if the difference is high enough
	 * for example, if the player got a 10, and program got a 2, program will contest
	 */
	public void ifProgramChallenges ( ) {
		if ( programDeck.size ( ) == 0 ) { // if the program is out of cards
			System.out.println ( "Program is out of cards." ); // promopt
			collectCards ( "Player", playerDeck ); // player wins
		} else {
			if ( rankDifference > 6 ) { // if rank diff > 6, program contests
				Card extraCard = programDeck.nextCard ( ); // pull next card
				int extraCardRank = extraCard.rankValue ( ); 
				
				pile.addCard ( extraCard );
				
				System.out.println ( "Program decides to play another card." );
				System.out.println ( "Extra card: " + extraCard + " (" + extraCardRank + ")" ); // displays the extra card
				
				programRank += extraCardRank; // adds the extra rank to the original rank
				System.out.println ( "Total program rank: " + programRank ); // prints out the total rank
				
				rankDifference = playerRank - programRank; // update rank difference again
				extraRound = true; // wont prompt either user to contest after this
				compareRanks ( ); // comapres with the new value
			} else { // pc doesnt take the risk of contesting and gives the win
				System.out.println ( "Program decides to not play another card." );
				collectCards ( "Player", playerDeck ); // player collects
			}
		}
	}
	
	/**
	 * this prompts the user if they want to take the risk and challenge
	 * if yes, player plays the next card and then comapres rank.
	 * if no, player loses the round and program collects the pile
	 */
	public void ifPlayerChallenges( ) {
		if ( playerDeck.size ( ) == 0 ) { // if player is out of cards
			System.out.println ( "Player is out of cards." );
			collectCards ( "Program", programDeck ); // program wins
		} else { // if player still has cards left, asks to contest
			boolean challenges = askPlayer ( );
			if ( challenges ) { // if player chooses to contest
				Card extraCard = playerDeck.nextCard ( ); // plays next card
				int extraCardRank = extraCard.rankValue ( ); // store card rank
				
				pile.addCard ( extraCard );
				
				System.out.println ( "\n" + "Extra card: " + extraCard + " (" + extraCardRank + ")" ); // dispalys
				
				playerRank += extraCardRank; // adds extra to the total
				System.out.println ( "Total player rank: " + playerRank + "\n" );
				
				rankDifference = playerRank - programRank;
				extraRound = true; // flips boolean
				compareRanks ( ); // compares
			} else {
				extraRound = false;
				collectCards ( "Program", programDeck );
			}
		}
	}
	
	/**
	 * asks the user if they want to challenge the program
	 * @return boolean - whether the player wants to challenge
	 */
	public boolean askPlayer ( ) {
		Scanner in = new Scanner ( System.in );

		System.out.print ( "Play another card? (Y/N): " ); // prompt
		String input = in.next ( ); // takes in user input
		
		// input checker until they either say "y" or "n"
		while ( ! input.equalsIgnoreCase( "y" ) && ! input.equalsIgnoreCase( "n" ) ) { 
			System.out.print ( "\n" + "(Y/N): " );
			input = in.next ( );
		}
		if ( input.equalsIgnoreCase ( "y" ) ) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * compares the ranks between the players' cards.
	 * if round 1, higher rank wins, asks lower rank if they want to contest
	 * if round 2, lower rank wins, and no one is allwoed to contest
	 * users can challenge during war
	 */
	public void compareRanks ( ) {
		// *** ROUND 1: HIGHER RANK WINS ***
		// *** ROUND 2: LOWER  RANK WINS ***
		if ( rankDifference > 0 ) { // player's rank is higher
			if ( extraRound == false ) { // if first round
				ifProgramChallenges ( ); // pc decides to contest or not
			} else { // if second round
				collectCards ( "Program", programDeck ); // program wins cause total is lower then player's
			}
		} else if ( rankDifference < 0 ) { // player got a lower rank
			if ( extraRound == false ) {
				ifPlayerChallenges ( ); // ask user if they wanna contest
			} else {
				collectCards ( "Player", playerDeck );
			}
		} else if ( rankDifference == 0 ) { // if both ranks are the same
			goToWar ( ); // go to war!
		}
	}
	
	/**
	 * players play a card and then compare ranks
	 */
	public void takeTurn ( ) {
		goToBattle ( );
		compareRanks ( );
	}
	
	/**
	 * returns player deck
	 * @return deck - player deck
	 */
	public Deck playerDeck ( ) {
		return playerDeck;
	}
}
