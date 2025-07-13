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
        @param addedCards updates dealer hand with starting cards
     */
    public void updateHand(Card[] addedCards) {
        hand.updateHand(addedCards);
    }

    /**
     * Displays the first card to the player in the console
     * Temp method: Will be removed in FRONT_END
     */
    // @todo if cards equal 21, display blackjack.
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
    public boolean dealToEnd(Deck activeDeck, boolean playerBust) {
        // for now, treat aces as 1. @todo fix for 11 handle
        if (!playerBust) {
            while (this.getHandValue() < 17) {
                this.printCards();
                hand.addCard(activeDeck.dealCard());
            }
        }
        this.printCards();
        return this.getHandValue() > 21;
    }

    /**
     * Function that checks if hand value is equal to 21,
     * Only called on original dealt hand
     * @return boolean hand is equal to blackjack
     */
    public boolean autoBlackjack() {
        return hand.getHandValue() == 21;
    }

}
