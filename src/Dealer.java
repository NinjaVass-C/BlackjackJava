import java.util.ArrayList;
import java.util.Arrays;

public class Dealer {
    private Hand hand = new Hand("Dealer");

    public Dealer() {
    }

    public void updateHand(Card[] addedCards) {
        hand.updateHand(addedCards);
    }

    // show dealers cards after deal,
    // @todo if cards equal 21, display blackjack.
    public void displayFirst() {
        System.out.println("Dealer shows " + hand.getHand().getFirst());
    }

    public void printCards() {
        hand.printHand();
    }

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
}
