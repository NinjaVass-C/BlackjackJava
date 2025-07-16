import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Represents a deck for blackjack. Consists of a standard 52 card playing card deck,
 * multiplied by the amount of decks desired.
 *
 * Author: Connor Vass
 * Version: 1.0
 * Date: July 13, 2025
 */

public class Deck {
    // track amount of decks used.
    private int deckNo;
    // used to randomize shuffles
    private int cardsNeededToShuffle;
    // amount of cards in a standard deck
    private final int cardsInDeck = 52;
    // track remaining cards.
    private ArrayList<Card> activeDeck = new ArrayList<>();

    /**
     * Non-Default constructor used to determine how many decks are needed,
     * then loads and shuffles deck.
     * @param initialDeckNo amount of standard decks combined.
     */
    public Deck(int initialDeckNo) {
        this.deckNo = initialDeckNo;
        shuffle();
    }

    /**
     * Loads the deck with cards, then shuffles them with
     * Collection shuffle
     * // spade = 0, club = 1, diamond = 2, heart = 3
     */
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
        cardsNeededToShuffle = this.determineWhenToShuffle();
    }

    /**
     * Deals cards out to all active players and dealer at beginning of each
     * cycle
     * @return Card[] initial cards dealt out to players and dealer
     */
    // @todo account for more than one player
    public Card[] dealDeck() {
        Card[] initialDeal = new Card[4];
        int count = 0;
        for (Iterator<Card> iterator = activeDeck.iterator(); iterator.hasNext();) {
            initialDeal[count] = iterator.next();
            iterator.remove();
            count++;
            if(count == 4){
                break;
            }
        }
        return initialDeal;
    }

    /**
     * Deal individual cards out to players or dealer for hits, doubles, and\
     * dealer runouts.
     * @return Card card dealt from deck
     */
    public Card dealCard() {
        Card returnedCard = activeDeck.getFirst();
        activeDeck.removeFirst();
        return returnedCard;
    }

    /**
     * Generate a random number between 50-75% of the cards
     * of 52 * amount of decks to simulate casino shuffles
     */
    private int determineWhenToShuffle() {
        int totalCards = cardsInDeck * deckNo;
        int shuffleInterval = (int) Math.floor(0.5 + Math.random() * (0.75 - 0.5));
        int targetCards = totalCards - shuffleInterval;
        return targetCards;
    }

    /**
     * Used in game.java to determine when shuffles are needed.
     */
    public void needToShuffle() {
        if (activeDeck.size() <= cardsNeededToShuffle) {
            System.out.println("SHUFFLING DECK");
            this.shuffle();
        }
    }
}
