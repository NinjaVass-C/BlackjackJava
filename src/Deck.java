import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    // track amount of decks used.
    private int deckNo;

    private int totalCards = 52;
    // track remaining cards.
    private ArrayList<Card> activeDeck = new ArrayList<>();

    public Deck(int initialDeckNo) {
        this.deckNo = initialDeckNo;
        shuffle();
    }
    // shuffle the "main" deck based off the deckNo used
    // spade = 0, club = 1, diamond = 2, heart = 3
    private void shuffle() {
        // declare needed suits and ranks based of amt of decks
        int suitTracker = 0;
        String[] defaultSuits = {"Spades", "Clubs", "Diamonds", "Hearts"};
        String[] suits = new String[4 * deckNo];
        int[] ranks = {0,1,2,3,4,5,6,7,8,9,10,11,12};
        // load new decks
        for (int i = 0; i < suits.length; i++) {
            suits[i] = defaultSuits[suitTracker];
            suitTracker++;
            if (suitTracker > 3) {
                suitTracker = 0;
            }
        }

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                activeDeck.add(new Card(ranks[j], suits[i]));
            }
        }
        Collections.shuffle(activeDeck);
    }
    public ArrayList<Card> getDeck() {
        return activeDeck;
    }

}
