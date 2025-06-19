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
    // print dealers cards
    public void printCards() {
        System.out.print("Dealer has: ");
        for (Card card : hand) {
            System.out.print(card + " | ");
        }
        System.out.println();
    }
}
