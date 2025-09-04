package com.example.backend.models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a hand for an individual player or dealer, consists of
 * an arraylist of cards and manages hand logic.
 *
 * Author: Connor Vass
 * Version: 2.0
 * Date: Sept 3rd, 2025
 */
public class Hand {
    // creates arraylist for cards
    private ArrayList<Card> hand = new ArrayList<>();
    // used to tell difference between dealer/player
    public Hand () {
    }

    /**
     * Used to compute the value of the users hand
     * All card values are the same as their rank except
     * Aces, which can be 1 or 11
     * @return int value of hand
     */
    public int getHandValue() {
        int total = 0;
        // used for changeable ace logic
        int aceCounter = 0;
        for (Card card : hand) {
            int value = card.getValue();
            // check for aces
            if (value == 11) {
                aceCounter++;
            }
            total += value;
        }
        while (total > 21 && aceCounter > 0) {
                total -= 10;
                aceCounter--;
        }
        return total;
    }

    /**
     * Returns the player/dealers hand
     * @return ArrayList of cards that make up hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }


    /**
     * Used to deal a card to the player or dealer,
     * then check to ensure they are still <= 21
     * @param card dealt card
     * @return boolean check if player/dealer busted
     */
    public boolean addCard(Card card) {
        hand.add(card);
        return this.getHandValue() < 21;
    }


}
