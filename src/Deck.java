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
    // counts cards
    private CardCounter cardCounter = new CardCounter();

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
        cardCounter.resetRunningCount();
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
     * cycle based on the amount of player hands
     * @return ArrayList<Card> initial cards dealt out to players and dealer
     */
    public ArrayList<Card> dealDeck(int playerHands) {
        ArrayList<Card> dealCards = new ArrayList<>();
        int neededCards = 2 + (2 * playerHands);
        int count = 0;
        for (Iterator<Card> iterator = activeDeck.iterator(); iterator.hasNext();) {
            Card dealtCard = iterator.next();
            dealCards.add(dealtCard);
            cardCounter.updateRunningCount(dealtCard.getValue());
            iterator.remove();
            count++;
            if(count == neededCards){
                break;
            }
        }
        return dealCards;
    }

    /**
     * Deal individual cards out to players or dealer for hits, doubles, and\
     * dealer runouts.
     * @return Card card dealt from deck
     */
    public Card dealCard() {
        Card returnedCard = activeDeck.getFirst();
        cardCounter.updateRunningCount(returnedCard.getValue());
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

    /**
     * Returns the true count of the deck, giving players
     * who are practicing counting an idea of where they are at
     * @return int the true count
     */
    public int getTrueCount() {
        return cardCounter.getTrueCount(activeDeck.size());
    }


    /**
     * Used for testing deck deals manually
     */
    public ArrayList<Card> testDealDeck(int playerHands) {
        ArrayList<Card> dealCards = new ArrayList<>();
        int neededCards = 2 + (2 * playerHands);
        int count = 0;
        for (Iterator<Card> iterator = activeDeck.iterator(); iterator.hasNext();) {
            Card dealtCard = iterator.next();
            dealCards.add(dealtCard);
            cardCounter.updateRunningCount(dealtCard.getValue());
            iterator.remove();
            count++;
            if(count == neededCards){
                break;
            }
        }
        dealCards.remove(0);
        dealCards.remove(2);
        dealCards.add(0, new Card(1, "Spades"));
        dealCards.add(3, new Card(1, "Spades"));
        return dealCards;
    }
}
