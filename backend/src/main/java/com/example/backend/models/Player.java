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

    //@todo add to a gameService for player
//    public void Wager() {
//        this.clearHands();
//
//        while (true) {
//            try {
//                System.out.println("What would you like to wager? (Max 500 Min 25 increment 25) (Current Chips: " + chips + ") ");
//                ante = input.nextInt();
//                // @todo add global setting for table limits
//                // for now just do typical limits.
//                if (ante >= 25 && ante <= 500 && (ante % 25 == 0) && ante <= chips) {
//                    chips -= ante;
//                    hands.add(new PlayerHand(ante, handCtr));
//                    handCtr++;
//                    input.nextLine();
//                    //check if user has enough chips for another hand
//                    if (chips > 25) {
//                        System.out.println("Would you like to add another hand? (yes)");
//                        if (input.nextLine().equals("yes")) {
//                            continue;
//                        }
//                        break;
//                    }
//                } else {
//                    throw new Exception("Invalid input");
//                }
//            } catch (Exception e) {
//                System.out.println("Invalid input");
//                input.nextLine();
//            }
//        }
//    }

    //@todo add this to a game service
//    public void turn(Deck activeDeck) {
//        for (int i = 0; i < hands.size(); i++) {
//            PlayerHand hand = hands.get(i);
//            boolean turnActive = true;
//            hand.printCards();
//            if (hand.autoBlackjack()) {
//                System.out.println("BLACKJACK!");
//                turnActive = false;
//            }
//            while (turnActive) {
//                if (hand.getPlayerHand().getHand().size() == 1) {
//                    hand.getPlayerHand().getHand().add(activeDeck.dealCard());
//                    hand.printCards();
//                    continue;
//                }
//                System.out.print("Would you like to hit or stand");
//                if (hand.getFirstTurn()) {
//                    System.out.print(" or double down");
//                }
//                if (hand.canSplit() && chips >= hand.getAnte()) {
//                    System.out.print(" or split");
//                }
//                System.out.println("?");
//                String response = input.nextLine();
//                if (response.equalsIgnoreCase("stand")) {
//                    turnActive = false;
//                } else if (response.equalsIgnoreCase("hit")) {
//                    turnActive = hand.hit(activeDeck.dealCard());
//                    hand.printCards();
//                    // break out if blackjack
//                    if (hand.getHandValue() == 21) {
//                        turnActive = false;
//                    }
//                }
//                else if (response.equalsIgnoreCase("double") && hand.getFirstTurn()) {
//                    if (chips < hand.getAnte()) {
//                        System.out.println("Not enough chips to double");
//                    }
//                    chips -= hand.getAnte();
//                    hand.doubleDown(activeDeck.dealCard());
//                    hand.printCards();
//                    turnActive = false;
//                }
//                else if (response.equalsIgnoreCase("split") && hand.canSplit()) {
//                    hands.add(hand.getHandNumber(), new PlayerHand(hand.getAnte(), handCtr, hand.getSplitCard()));
//                    chips -= hand.getAnte();
//                    handCtr++;
//                }
//                else {
//                    System.out.println("Invalid input");
//                }
//            }
//        }
//    }

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
     * Win conditions for all players current hands,
     * using the dealers passed cards to check for
     * all possible situations.
     *
     * @param dealerCards ending cards dealer had
     * @return boolean player has enough to keep betting
     */
    public boolean compareAgainstDealer(int dealerCards) {
        for (PlayerHand hand : hands) {
            if (hand.autoBlackjack()) {
                chips += hand.Win();
            }
            else if (hand.getHandValue() > dealerCards && hand.getHandValue() <= 21) {
                chips += hand.Win();
            }
            else if (hand.getHandValue() == dealerCards && hand.getHandValue() <= 21) {
                chips += hand.Push();
            }
            else if (hand.getHandValue() < dealerCards && dealerCards > 21) {
                chips += hand.Win();
            }
        }
        return chips < 25;
    }

    /**
     * Used to see how many hands user will play before dealing cards
     */
    public void addHand(int ante) {
        hands.add(new PlayerHand(ante));
        chips -= ante;
    }
}


