import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a hand for an individual player or dealer, consists of
 * an arraylist of cards and manages hand logic.
 *
 * Author: Connor Vass
 * Version: 1.0
 * Date: July 13, 2025
 */
public class Hand {
    // creates arraylist for cards
    private ArrayList<Card> hand = new ArrayList<>();
    // used to tell difference between dealer/player
    private String identity;
    public Hand (String identity) {
        this.identity = identity;
    }

    /**
     * Used to update hand with starting cards, clears last hand
     * @param addedCards array of cards initially dealt
     */
    public void updateHand(Card[] addedCards) {
        hand.clear();
        hand.addAll(Arrays.asList(addedCards));
    }

    /**
     * Used to compute the value of the users hand
     * All card values are the same as their rank except
     * Aces, which can be 1 or 11
     * @return int value of hand
     */
    //@todo fix ace handling to properly change to 1 when needed
    public int getHandValue() {
        int total = 0;
        for (Card card : hand) {
            int value = card.getValue();
            // check for aces
            if (value == 11 && (total + value) > 21) {
                total += 1;
            }
            else {
                total += card.getValue();
            }

        }
        return total;
    }

    /**
     * Returns the player/dealers hand
     * @return ArrayList of cards that make up hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Function used to print currently shown cards,
     * Will be removed in FRONT_END
     */
    public void printHand() {
        System.out.print(identity + " has: ");
        for (Card card : this.hand) {
            System.out.print(card + " | ");
        }
        System.out.println();
    }

    /**
     * Used to deal a card to the player or dealer,
     * then check to ensure they are still <= 21
     * @param card dealt card
     * @return boolean check if player/dealer busted
     */
    public boolean addCard(Card card) {
        hand.add(card);
        return this.getHandValue() > 21;
    }

}
