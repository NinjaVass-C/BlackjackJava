import java.sql.Array;
import java.util.ArrayList;

/**
 * Used to store all game objects required, as well as any logic in between
 * players and dealers IE Passing cards that are being dealt, win logic, etc.
 *
 * Author: Connor Vass
 * Version: 1.0
 * Date: July 13, 2025
 */



public class Game {
    // gameOver boolean for game loop
    private boolean gameOver = false;
    // declare player, dealer, and deck
    private Player activePlayer;
    private Dealer activeDealer;
    private Deck activeDeck;

    /**
     * Give custom values needed to game objects
     * then trigger game loop
     *
     * @param startChips int how many chips player will start with
     * @param deckNo int how many decks will be used
     */
    public void startGame(int startChips, int deckNo) {
        activePlayer = new Player(startChips);
        activeDealer = new Dealer();
        activeDeck = new Deck(deckNo);
        this.gameLoop();
    }

    /**
     * Main game loop for blackjack, Iterates through loop after every
     * hand is played, in following pattern
     * 1. Wagers from players are asked
     * 2. Dealer deals cards to players, then self
     * 3. Players make actions, getting cards or staying
     * 4. Dealer reveals second card, then draws until own hand is greater than equal to 17
     * 5. Win logic occurs
     * Cards are shuffled periodically
     * @return boolean game over
     */
    private boolean gameLoop() {
        // do turn by turn calls
        while (!gameOver) {
            System.out.println("THE COUNT IS: " + activeDeck.getTrueCount());
            activeDeck.needToShuffle();
            // get players wager
            activePlayer.Wager();
            activeDealer.WipeCards();
            // deal deck to player and dealer
            dealInitialCards(activeDeck, activePlayer, activeDealer);
            // print cards REMOVED IN FRONT_END
            activeDealer.displayFirst();
            activePlayer.turn(activeDeck);
            boolean playerBust = activePlayer.checkHands();
            activeDealer.printCards();

            int dealerCards = activeDealer.dealToEnd(activeDeck, playerBust);
            gameOver = activePlayer.endTurn(dealerCards);
        }
        return false;
    }
    /**
     * Helper Function for dealing initial cards
     * Uses passed values for modularity in VERSION 2.0
     */
    public void dealInitialCards (Deck deck, Player player, Dealer dealer) {
        ArrayList<Card> cardsDealt = deck.dealDeck(player.getHands().size());
        int ctr = 0;
        for (PlayerHand pHand : player.getHands()) {
            pHand.getPlayerHand().addCard(cardsDealt.get(ctr++));
        }
        dealer.getHand().addCard(cardsDealt.get(ctr++));

        for (PlayerHand pHand : player.getHands()) {
            pHand.getPlayerHand().addCard(cardsDealt.get(ctr++));
        }
        dealer.getHand().addCard(cardsDealt.get(ctr++));
    }
}
