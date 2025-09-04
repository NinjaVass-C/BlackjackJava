package com.example.backend.models;

/**
 * Used to create individual cards, including their suit and
 * card number
 *  0 - Ace
 *  1-9 - 2-10
 *  10 - Jack
 *  11 - Queen
 *  12 - King
 *  Suits - (Hearts, Clubs, Spades, Diamonds)
 *  Author: Connor Vass
 *  Version: 2.0
 *  Date: Sept 3rd, 2025
 */
public class Card {
    private int rank;
    private String suit;

    /**
     *
     * @param rank int for rank of card
     * @param suit String for suit of card
     */
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * used to compute the value of each card
     * @return int value of card
     */
    public int getValue() {
        switch (rank) {
            case 0:
                return 11;
            case 1,2,3,4,5,6,7,8,9:
                return this.rank + 1;
            case 10,11,12:
                return 10;
            default:
                throw new AssertionError("Illegal rank");
        }
    }

    /**
     * Gets card suit
     * @return String card suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Gets card rank
     * @return int card rank
     */
    public int getRank() {
        return rank;
    }
}
