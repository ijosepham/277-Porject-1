import java.util.Scanner;

/**
 * @author joey pham
 * @date 2 feb 2019
 * @description a player plays a game of war against the program. the one with all the cards wins
 */

public class War {
	public static void main ( String [ ] args ) {
		Game game = new Game ( );
		boolean gameOver = false;
		do {
			game.takeTurn ( );
			gameOver = checkGameOver ( game );
		} while ( ! gameOver );
	}
	
	/**
	 * checks if the game is over by looking at the player's deck size, whether they have
	 * 		all the cards or none of the cards
	 * @param game - game of war to access the player deck
	 * @return boolean - t if game over or player decides to stop playing, f if not
	 */
	public static boolean checkGameOver ( Game game ) {
		if ( game.playerDeck ( ).size ( ) == 0 ) {
			System.out.println ( "Program wins." );
			return true; // if player lost, game over
		} else if ( game.playerDeck ( ).size ( ) == 52 ) {
			System.out.println ( "Player wins. " );
			return true; // if player won, game over
		} else { // if player hasn't lost or won, ask if they wanna keep playing
			return playRound ( );
		}
	}
	
	/**
	 * asks the user if they'd like to play another battle
	 * @return boolean - f if the game isnt over, t if it is over
	 */
	public static boolean playRound ( ) {
		Scanner in = new Scanner ( System.in );
		System.out.print ( "Play another round? (Y/N): " );
		String input = in.next ( );
		
		while ( ! input.equalsIgnoreCase( "y" ) && ! input.equalsIgnoreCase( "n" ) ) { 
			System.out.print ( "\n" + "(Y/N): " );
			input = in.next ( );
		}
		
		System.out.print ( "\n" );
		
		if ( input.equalsIgnoreCase( "y" ) ) {
			return false;
		} else {
			return true;
		}
	}
}