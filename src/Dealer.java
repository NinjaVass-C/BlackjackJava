import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a blackjack dealer for the game of blackjack. There is no choices for the dealer to
 * make in the game, so less logic and more following preset rules (hit on soft 17, etc.)
 *
 * Author: Connor Vass
 * Version: 1.0
 * Date: July 13, 2025
 */


public class Dealer {
    // Creates hand object assigned to dealer
    private Hand hand = new Hand("Dealer");

    public Dealer() {
    }

    /**
     * Displays the first card to the player in the console
     * Temp method: Will be removed in FRONT_END
     */
    public void displayFirst() {
        System.out.println("Dealer shows " + hand.getHand().getFirst());
    }

    /**
     * Displays the dealer hand to the player
     * Temp method: Will be removed in FRONT_END
     */
    public void printCards() {
        hand.printHand();
    }

    /**
     * Returns the current combined value of the dealers hand
     * @return int hand value
     */
    public int getHandValue() {
        return hand.getHandValue();
    }
    // After player stands, deal cards.
    public int dealToEnd(Deck activeDeck, boolean playerBust) {
        if (!playerBust) {
            while (this.getHandValue() < 17) {
                this.printCards();
                hand.addCard(activeDeck.dealCard());
            }
        }
        this.printCards();
        return this.getHandValue();
    }

    /**
     * Returns the dealers hand
     * @return hand object
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Function to clear dealers current hand
     */
    public void WipeCards() {
        hand.getHand().clear();
    }
}
