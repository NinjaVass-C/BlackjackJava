package com.example.backend.models;

import java.util.ArrayList;

/**
 * Represents an individual player for a game of blackjack, keeps track of their
 * chip count and hand, as well as handles all decision logic for a standard player turn
 *
 * Author: Connor Vass
 * Version: 2.0
 * Date: Sept 3rd, 2025
 */
public class Player {
    // Declare player variables needed
    private ArrayList<PlayerHand> hands = new ArrayList<>();
    private int chips = 500;
    private int handCtr = 1;
    private int activeHandIndex = 0;



    /**
     * Non-Default constructor used for determining player starting stack
     * @param initialChips starting chips for player
     */
    public Player(int initialChips) {
        this.chips = initialChips;
    }
    // getter
    public int getChips() {
        return this.chips;
    }

    /**
     * Clears all players current hands
     */
    public void clearHands() {
        hands.clear();
        handCtr = 1;
    }

    /**
     * Gets every hand instance that the player currently has
     * @return ArrayList<PlayerHand> all players current hands
     */
    public ArrayList<PlayerHand> getHands() {
        return hands;
    }

    /**
     * Checks to see if player busted all hands
     * @return boolean if all hands are bust, dealer needs to know to not draw cards
     */
    public boolean checkAllHandsBust() {
        boolean allBust = true;
        for (PlayerHand hand : hands) {
            if (hand.getHandValue() < 21) {
                allBust = false;
                break;
            }
        }
        return allBust;
    }

    /**
     * Used to see how many hands user will play before dealing cards
     */
    public void addHand(int ante) {
        hands.add(new PlayerHand(ante));
        removeChips(ante);
    }

    public PlayerHand getActiveHand() {
        return hands.get(activeHandIndex);
    }

    public void nextHand() {
        if (activeHandIndex < handCtr) {
            activeHandIndex++;
        }
    }

    public void removeChips(int ante) {
        chips -= ante;
    }

    public void addChips(int ante) {
        chips += ante;
    }

    public int getActiveHandIndex() {
        return activeHandIndex;
    }
}


