package src;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an individual player for a game of blackjack, keeps track of their
 * chip count and hand, as well as handles all decision logic for a standard player turn
 *
 * Author: Connor Vass
 * Version: 1.0
 * Date: July 13, 2025
 */
public class Player {
    // Declare player variables needed
    private ArrayList<PlayerHand> hands = new ArrayList<>();
    private int chips = 500;
    private int ante;
    private boolean hasAutoBlackjack = false;
    private int handCtr = 1;

    // scanner variable for console input (will be removed with front end)
    Scanner input = new Scanner(System.in);

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
     * Start of decision tree for average blackjack player, commits wager(s) to the table,
     * then get dealt hand(s)
     * To be overhauled in FRONT_END
     */
    public void Wager() {
        this.clearHands();

        while (true) {
            try {
                System.out.println("What would you like to wager? (Max 500 Min 25 increment 25) (Current Chips: " + chips + ") ");
                ante = input.nextInt();
                // @todo add global setting for table limits
                // for now just do typical limits.
                if (ante >= 25 && ante <= 500 && (ante % 25 == 0) && ante <= chips) {
                    chips -= ante;
                    hands.add(new PlayerHand(ante, handCtr));
                    handCtr++;
                    input.nextLine();
                    //check if user has enough chips for another hand
                    if (chips > 25) {
                        System.out.println("Would you like to add another hand? (yes)");
                        if (input.nextLine().equals("yes")) {
                            continue;
                        }
                        break;
                    }
                } else {
                    throw new Exception("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }
    }

    /**
     * Second part of decision tree for blackjack player
     * Determine what actions the player is going to take
     * Hit -> Draw a card from dealers deck to try to get closer to 21
     * Stand -> Keep your current hand and face off against dealers
     * Double Down -> Double your current bet and get one card, win/loss is doubled
     * Split -> If both cards are the same suit and rank, splits into two hands.
     * Loops through every hand the player has
     * TO BE REWORKED IN FRONT_END
     *
     * @param activeDeck Deck current deck dealer is using
     * @return boolean player has busted
     */
    public void turn(Deck activeDeck) {
        for (int i = 0; i < hands.size(); i++) {
            PlayerHand hand = hands.get(i);
            boolean turnActive = true;
            hand.printCards();
            if (hand.autoBlackjack()) {
                System.out.println("BLACKJACK!");
                turnActive = false;
            }
            while (turnActive) {
                if (hand.getPlayerHand().getHand().size() == 1) {
                    hand.getPlayerHand().getHand().add(activeDeck.dealCard());
                    hand.printCards();
                    continue;
                }
                System.out.print("Would you like to hit or stand");
                if (hand.getFirstTurn()) {
                    System.out.print(" or double down");
                }
                if (hand.canSplit() && chips >= hand.getAnte()) {
                    System.out.print(" or split");
                }
                System.out.println("?");
                String response = input.nextLine();
                if (response.equalsIgnoreCase("stand")) {
                    turnActive = false;
                } else if (response.equalsIgnoreCase("hit")) {
                    turnActive = hand.hit(activeDeck.dealCard());
                    hand.printCards();
                    // break out if blackjack
                    if (hand.getHandValue() == 21) {
                        turnActive = false;
                    }
                }
                else if (response.equalsIgnoreCase("double") && hand.getFirstTurn()) {
                    if (chips < hand.getAnte()) {
                        System.out.println("Not enough chips to double");
                    }
                    chips -= hand.getAnte();
                    hand.doubleDown(activeDeck.dealCard());
                    hand.printCards();
                    turnActive = false;
                }
                else if (response.equalsIgnoreCase("split") && hand.canSplit()) {
                    hands.add(hand.getHandNumber(), new PlayerHand(hand.getAnte(), handCtr, hand.getSplitCard()));
                    chips -= hand.getAnte();
                    handCtr++;
                }
                else {
                    System.out.println("Invalid input");
                }
            }
        }
    }

    /**
     * Clears all players current hands
     */
    private void clearHands() {
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
    public boolean checkHands() {
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
    public boolean endTurn(int dealerCards) {
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
            System.out.println("CHIPS AT: " + chips);
        }
        System.out.println("Chips: " + chips);
        return chips < 25;
    }
}


