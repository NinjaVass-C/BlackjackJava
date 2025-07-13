import java.util.ArrayList;
import java.util.Arrays;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    // used to tell difference between dealer/player
    private String identity;
    public Hand (String identity) {
        this.identity = identity;
    }

    public void updateHand(Card[] addedCards) {
        hand.clear();
        hand.addAll(Arrays.asList(addedCards));
    }

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

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void printHand() {
        System.out.print(identity + " has: ");
        for (Card card : this.hand) {
            System.out.print(card + " | ");
        }
        System.out.println();
    }

    public boolean addCard(Card card) {
        hand.add(card);
        return this.getHandValue() > 21;
    }

}
