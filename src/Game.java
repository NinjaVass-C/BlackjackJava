public class Game {
    private boolean gameOver = false;
    private Player activePlayer;
    private Dealer activeDealer;
    private Deck activeDeck;
    // Create game objects
    public boolean startGame(int startChips, int deckNo) {
        activePlayer = new Player(startChips);
        activeDealer = new Dealer();
        activeDeck = new Deck(deckNo);
        return this.gameLoop(false);
    }

    private boolean gameLoop(boolean isOver) {
        // do turn by turn calls
        while (!gameOver) {
            int ante = activePlayer.Wager();
            System.out.println("PLAYER BET: " + ante);
            Card[] dealt = activeDeck.dealDeck();
            activePlayer.updateHand(new Card[]{dealt[0], dealt[2]});
            activeDealer.updateHand(new Card[]{dealt[1], dealt[3]});

            activeDealer.displayFirst();
            activePlayer.printCards();

            if (activePlayer.autoBlackjack()) {
                activeDealer.printCards();
                this.gameOver(false, false, ante);
                continue;
            }
            boolean playerBust = activePlayer.turn(activeDeck);
            if (activeDealer.autoBlackjack()) {
                activeDealer.printCards();
                this.gameOver(false, false, ante);
                continue;
            }
            boolean dealerBust = activeDealer.dealToEnd(activeDeck, playerBust);
            gameOver = gameOver(playerBust, dealerBust, ante);
        }
        return false;
    }
    // @todo account for double down
    private boolean gameOver(boolean playerBust, boolean dealerBust, int ante) {
        if (playerBust) {
            System.out.println("Player busted!");
            return activePlayer.getChips() <= 0;
        }
        if (dealerBust) {
            System.out.println("Dealer busted!");
            activePlayer.Win(ante);
            return false;
        }
        if (activePlayer.getHandValue() > activeDealer.getHandValue()) {
            System.out.println("Player won!");
            activePlayer.Win(ante);
            return false;
        }

        if (activeDealer.getHandValue() > activePlayer.getHandValue()) {
            System.out.println("Dealer won!");
            return activePlayer.getChips() <= 0;
        }
        System.out.println("Push!");
        activePlayer.Push(ante);
        return false;
    }
}
