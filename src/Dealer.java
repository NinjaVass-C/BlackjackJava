import java.util.ArrayList;
import java.util.Arrays;

public class Dealer {
    private ArrayList<Card> hand = new ArrayList<>();

    public Dealer() {
    }
    public void updateHand(Card[] addedCards) {
        hand.addAll(Arrays.asList(addedCards));
    }
    // show dealers cards after deal,
    // @todo if cards equal 21, display blackjack.
    public void displayFirst() {
        System.out.println("Dealer shows " + hand.getFirst());
    }

    public int getHandValue() {
        int total = 0;
        for (Card card : hand) {
            total += card.getValue();
        }

        return total;
    }
    // After player stands, deal cards.
    public boolean dealToEnd(ArrayList<Card> activeDeck, boolean playerBust) {
        // for now, treat aces as 1. @todo fix for 11 handle
        if (playerBust) {
            hand.add(activeDeck.getFirst());
            activeDeck.remove(activeDeck.getFirst());
        }
        else {
            while (this.getHandValue() < 17) {
                hand.add(activeDeck.getFirst());
                activeDeck.remove(activeDeck.getFirst());
                System.out.println("Dealer has: " + this.getHandValue());
            }
        }
        return this.getHandValue() > 21;
    }
}
