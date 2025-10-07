package com.example.backend.models;


/**
 * Represents a blackjack dealer for the game of blackjack. There is no choices for the dealer to
 * make in the game, so less logic and more following preset rules (hit on soft 17, etc.)
 *
 * Author: Connor Vass
 * Version: 2.0
 * Date: Sept 3rd, 2025
 */


public class Dealer {
    // Creates hand object assigned to dealer
    private Hand cards = new Hand();

    public Dealer() {
    }

    /**
     * Returns the current combined value of the dealers hand
     * @return int hand value
     */
    //@todo consider refactor
    public int getHandValue() {
        return cards.getHandValue();
    }
    // After player stands, deal cards.
    public int dealToEnd(Deck activeDeck, boolean playerBust) {
        if (!playerBust) {
            while (this.getHandValue() < 17) {
                cards.addCard(activeDeck.dealCard());
            }
        }
        return this.getHandValue();
    }

    /**
     * Returns the dealers hand
     * @return hand object
     */
    public Hand getCards() {
        return cards;
    }

    /**
     * Function to clear dealers current hand
     */
    public void WipeCards() {
        cards.getCards().clear();
    }
}
