import java.util.ArrayList;
import java.util.Arrays;
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
    private Hand hand = new Hand("Player");
    private int chips = 500;
    private int ante;
    private boolean hasAutoBlackjack = false;

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
     * Win condition logic for player, used to determine winning amount based
     * off of side bets, natural blackjack, etc.
     */
    public void Win () {
        if (hasAutoBlackjack) {
            chips += (int) Math.floor(ante + (ante * 1.5));
        }
        else {
            chips += ante * 2;
        }
    }

    /**
     * When dealer and player both have same values, a push happens, refunding
     * the player of their bet.
     */
    public void Push () {
        chips += ante;
    }

    /**
     * Start of decision tree for average blackjack player, commits wager to the table,
     * allowing player to be dealt a hand. '
     * To be overhauled in FRONT_END
     * @return int players ante or bet
     */
    public int Wager() {
        this.clearConditions();
        while (true) {
            try {
                System.out.println("What would you like to wager? (Max 500 Min 25 increment 25) (Current Chips: " + chips + ") ");
                ante = input.nextInt();
                // @todo add global setting for table limits
                // for now just do typical limits.
                if (ante >= 25 && ante <= 500 && (ante % 25 == 0) && ante <= chips) {
                    chips -= ante;
                    input.nextLine();
                    return ante;
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
     * update the players hand with new cards.
     * @param addedCards Card[] to players hand
     */
    // @todo make flow better with new Hand class (see game.java)
    public void updateHand(Card[] addedCards) {
        hand.updateHand(addedCards);
    }
    /**
     * Displays the players hand
     * Temp method: Will be removed in FRONT_END
     */
    public void printCards() {
        hand.printHand();
    }

    /**
     *
     * @param hitCard Card added to players hand upon requesting card
     * @return boolean player busted
     */
    public boolean hit(Card hitCard) {
        return hand.addCard(hitCard);
    }

    /**
     * Returns the current combined value of the players hand
     * @return int hand value
     */
    public int getHandValue() {
        return hand.getHandValue();
    }

    /**
     * Second part of decision tree for blackjack player
     * Determine what actions the player is going to take
     * Hit -> Draw a card from dealers deck to try to get closer to 21
     * Stand -> Keep your current hand and face off against dealers
     * Double Down -> Double your current bet and get one card, win/loss is doubled
     *
     * @param activeDeck Deck current deck dealer is using
     * @return boolean player has busted
     */
    public boolean turn(Deck activeDeck) {
        while (true) {
            System.out.println("Would you like to hit, stand, or double down?");
            String response = input.nextLine();
            if (response.equalsIgnoreCase("stand")) {
                return false;
            } else if (response.equalsIgnoreCase("hit")) {
                boolean checkBust = this.hit(activeDeck.dealCard());
                printCards();
                // break out if blackjack
                if (this.getHandValue() == 21) {
                    return false;
                }
                if (checkBust) {
                    return true;
                }
            }
            else if (response.equalsIgnoreCase("double")) {
                if (chips < ante) {
                    System.out.println("Not enough chips to double");
                    continue;
                }
                chips -= ante;
                ante *= 2;
                boolean checkBust = this.hit(activeDeck.dealCard());
                printCards();
                return checkBust;
            }
            else {
                System.out.println("Invalid input");
            }
        }
    }
    /**
     * Function that checks if hand value is equal to 21,
     * Only called on original dealt hand
     * @return boolean hand is equal to blackjack
     */
    public boolean autoBlackjack() {
        hasAutoBlackjack = hand.getHandValue() == 21;
        return hasAutoBlackjack;
    }

    /**
     * removes all conditions to the player related to last hand
     * sidebets, ante, natural blackjack, etc.
     */
    public void clearConditions() {
        ante = 0;
        hasAutoBlackjack = false;
    }

}


