import java.util.ArrayList;
import java.util.Arrays;

public class Dealer {
    private ArrayList<Card> hand = new ArrayList<>();

    public Dealer() {
    }

    public void updateHand(Card[] addedCards) {
        hand.clear();
        hand.addAll(Arrays.asList(addedCards));
    }

    // show dealers cards after deal,
    // @todo if cards equal 21, display blackjack.
    public void displayFirst() {
        System.out.println("Dealer shows " + hand.getFirst());
    }

    public void printCards() {
        System.out.print("Dealer has: ");
        for (Card card : hand) {
            System.out.print(card + " | ");
        }
        System.out.println();
    }

    public int getHandValue() {
        int total = 0;
        for (Card card : hand) {
            total += card.getValue();
        }

        return total;
    }
    // After player stands, deal cards.
    public boolean dealToEnd(Deck activeDeck, boolean playerBust) {
        // for now, treat aces as 1. @todo fix for 11 handle
        if (!playerBust) {
            while (this.getHandValue() < 17) {
                hand.add(activeDeck.dealCard());
                printCards();
            }
        }
        printCards();
        return this.getHandValue() > 21;
    }
}
