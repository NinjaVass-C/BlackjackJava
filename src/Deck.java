import java.util.ArrayList;
import java.util.Random;

public class Deck {
    // track amount of decks used.
    private int deckNo;

    private int totalCards = 52;
    // track remaining cards.
    private ArrayList<Card> activeDeck;

    public Deck(int initialDeckNo) {
        this.deckNo = initialDeckNo;
        shuffle();
    }
    // shuffle the "main" deck based off the deckNo used
    // spade = 0, club = 1, diamond = 2, heart = 3
    public void shuffle() {
        // get total cards in deck
        totalCards *= deckNo;
        // generate suits and numbers for deck
        int[] numbersRemaining = new int [13];
        int[] suitsRemaining = new int[3];
        for (int i = 0; i < 13; i++) {
            numbersRemaining[i] = deckNo * 4;
        }
        for (int i = 0; i < 4; i++) {
            suitsRemaining[i] = totalCards / 4;
        }
        // start adding to arraylist
        int suitTracker = 0;
        for (int cards = 0; cards < totalCards; cards++) {
            Card tempCard;
            int tempNumber;
            String tempSuit;
            suitTracker++;
            if(suitTracker > 3) {
                suitTracker = 0;
            }

            int randomNo = (int) (Math.random() * 13);
            tempSuit = switch (suitTracker) {
                case 0 -> "Spades";
                case 1 -> "Clubs";
                case 2 -> "Hearts";
                case 3 -> "Diamonds";
                default -> throw new AssertionError("Error generating suit");
            };
            tempCard = new Card(randomNo, tempSuit);
            activeDeck.add(tempCard);
        }



    }


}
