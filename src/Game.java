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
            activeDeck.needToShuffle();
            // get players wager
            activePlayer.Wager();
            // deal deck to player and dealer
            Card[] dealt = activeDeck.dealDeck();
            activePlayer.updateHand(new Card[]{dealt[0], dealt[2]});
            activeDealer.updateHand(new Card[]{dealt[1], dealt[3]});
            // print cards REMOVED IN FRONT_END
            activeDealer.displayFirst();
            activePlayer.printCards();
            // check if player has natural blackjack, if so go to win conditions immediately
            if (activePlayer.autoBlackjack()) {
                activeDealer.printCards();
                this.gameOver(false, false);
                continue;
            }
            // have player do actions
            boolean playerBust = activePlayer.turn(activeDeck);

            if (activeDealer.autoBlackjack()) {
                activeDealer.printCards();
                this.gameOver(false, false);
                continue;
            }

            boolean dealerBust = activeDealer.dealToEnd(activeDeck, playerBust);
            gameOver = this.gameOver(playerBust, dealerBust);
        }
        return false;
    }
    // @todo account for double down
    private boolean gameOver(boolean playerBust, boolean dealerBust) {
        if (playerBust) {
            System.out.println("Player busted!");
            return activePlayer.getChips() <= 0;
        }
        if (dealerBust) {
            System.out.println("Dealer busted!");
            activePlayer.Win();
            return false;
        }
        if (activePlayer.getHandValue() > activeDealer.getHandValue()) {
            System.out.println("Player won!");
            activePlayer.Win();
            return false;
        }

        if (activeDealer.getHandValue() > activePlayer.getHandValue()) {
            System.out.println("Dealer won!");
            return activePlayer.getChips() <= 0;
        }
        System.out.println("Push!");
        activePlayer.Push();
        return false;
    }
}
