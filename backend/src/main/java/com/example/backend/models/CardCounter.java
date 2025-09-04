package com.example.backend.models;

/**
 * Utility class used to track card count for blackjack
 * Counting cards is a typical strategy for blackjack players, utilizing the fact
 * that there is only a certain amount of each card in the deck. This follows
 * basic counting algorithms to do that
 *
 *
 * Author: Connor Vass
 * Version: 2.0
 * Date: Sept 3rd, 2025
 */

public class CardCounter {
    private int runningCount = 0;
    private final int cardsInDeck = 52;

    /**
     * Whenever a card is drawn, the running count of the deck is changed.
     * @param drawnCardValue int value of card drawn
     */
    public void updateRunningCount(int drawnCardValue) {
        switch(drawnCardValue) {
            case 2,3,4,5,6:
                runningCount++;
                break;
            case 10, 11:
                runningCount--;
                break;
            default:
                break;
        }
    }

    /**
     * When deck is shuffled, count is reset
     */
    public void resetRunningCount() {
        runningCount = 0;
    }

    /**
     * Does the calculations for the true count of current deck
     *
     * @param cardsRemaining int amount of cards remaining
     * @return int true count
     */
    public int getTrueCount(int cardsRemaining) {
        return (int)Math.floor(runningCount / (double)(cardsRemaining / cardsInDeck));
    }
}
