package com.example.backend.models;

import java.util.ArrayList;

/**
 * Used to store an instance of a player hand, as players should have the option
 * to play multiple hands during a single rotation of the blackjack table, as long
 * as they have the money available
 *
 * Author: Connor Vass
 * Version: 2.0
 * Date: Sept 3rd, 2025
 */

public class PlayerHand {
    // Declare player variables needed
    private Hand hand = new Hand();
    private int ante;
    private int handNumber;
    private boolean hasAutoBlackjack = false;
    private boolean hasDoubled = false;
    private boolean hasBlackJack = false;
    private boolean hasBust;
    private boolean firstTurn = true;
    // Non-Default constructor
    public PlayerHand(int initialAnte, int initialHandNumber) {
        ante = initialAnte;
        handNumber = initialHandNumber;
    }
    public PlayerHand(int initialAnte, int initialHandNumber, Card splitCard) {
        ante = initialAnte;
        handNumber = initialHandNumber;
        hand.addCard(splitCard);
    }
    // Used to create empty hands before starting game
    public PlayerHand(int initialAnte) {
        ante = initialAnte;
    }

    /**
     * Win condition logic for player, used to determine winning amount based
     * off of side bets, natural blackjack, etc.
     */
    public int Win () {
        if (hasAutoBlackjack) {
            return  (int) Math.floor(ante + (ante * 1.5));
        }
        else {
            return ante * 2;
        }
    }

    /**
     * When dealer and player both have same values, a push happens, refunding
     * the player of their bet.
     */
    public int Push () {
        return ante;
    }

    /**
     *
     * @param hitCard Card added to players hand upon requesting card
     * @return boolean player busted
     */
    public boolean hit(Card hitCard) {
        firstTurn = false;
        hasBust = hand.addCard(hitCard);
        if (!hasBust) {
            // checking for hand active,
            hasBlackJack = getHandValue() != 21;
        }
        return !hasBust;
    }

    /**
     * Returns the current combined value of the players hand
     * @return int hand value
     */
    public int getHandValue() {
        return hand.getHandValue();
    }

    /**
     * Function that checks if hand value is equal to 21,
     * Only called on original dealt hand
     * @return boolean hand is equal to blackjack
     */
    public boolean hasAutoBlackjack() {
        hasAutoBlackjack = hand.getHandValue() == 21;
        return hasAutoBlackjack;
    }

    /**
     * Doubles the current ante of the hand, then draws a card.
     * @param activeCard card drawn for double down
     */
    public void doubleDown(Card activeCard) {
        hasDoubled = true;
        ante *=2;
        this.hit(activeCard);
    }

    /**
     * Checks to see if both cards in players hand are the same, if
     * so allows player to split hands.
     * @return boolean can split
      */
    public boolean canSplit() {
        ArrayList<Card> cards = hand.getHand();
        if (cards.size() > 2) {
            return false;
        }
        return (cards.get(0).getSuit().equals(cards.get(1).getSuit()) && (cards.get(0).getRank() == cards.get(1).getRank()));
    }

    /**
     * Gets the current ante
     * @return inte ante
     */
    public int getAnte() {
        return ante;
    }

    /**
     * Check to see if its players first turn.
     * @return boolean first turn for hand
     */
    public boolean getFirstTurn() {
        return firstTurn;
    }

    /**
     * Used to get instance of hand
     * @return Hand players current hand
     */
    public Hand getPlayerHand() {
        return hand;
    }

    /**
     * Gets the hand number
     * @return int current hand number
     */
    public int getHandNumber() {
        return handNumber;
    }

    public Card getSplitCard(Card replacementCard) {
        Card splitCard = hand.getHand().getFirst();
        hand.getHand().removeFirst();
        hand.addCard(replacementCard);
        return splitCard;
    }

    public boolean getAutoBlackjack() {
        return hasAutoBlackjack;
    }

    public boolean getBust() {
        return getHandValue() > 21;
    }
}
