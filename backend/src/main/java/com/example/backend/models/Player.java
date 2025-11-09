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
    private ArrayList<PlayerHand> hands;
    private int chips = 500;
    private int activeHandIndex = 0;



    /**
     * Non-Default constructor used for determining player starting stack
     * @param initialChips starting chips for player
     */
    public Player(int initialChips) {
        this.chips = initialChips;
        this.hands = new ArrayList<>();
    }
    // getter
    public int getChips() {
        return this.chips;
    }

    /**
     * Clears all players current hands
     */

    public void WipeCards() {
        hands.clear();
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
        int handCtr = hands.size();
        hands.add(new PlayerHand(ante, handCtr));
        removeChips(ante);
    }

    public PlayerHand getActiveHand() {
        return hands.get(activeHandIndex);
    }

    public void nextHand() {
        if (activeHandIndex <= hands.size()) {
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

    public boolean turnOver() {
        return activeHandIndex >= hands.size();
    }
}


