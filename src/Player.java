import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
    Class for player, tracks their chip amounts, current hand, and their moves.
 */
public class Player {
    private Hand hand = new Hand("Player");
    private int chips = 500;
    private int ante;
    private boolean hasAutoBlackjack = false;

    // scanner variable for console input (will be removed with front end)
    Scanner input = new Scanner(System.in);

    public Player(int initialChips) {
        this.chips = initialChips;
    }

    public int getChips() {
        return this.chips;
    }

    public void Win () {
        if (hasAutoBlackjack) {
            chips += (int) Math.floor(ante + (ante * 1.5));
        }
        else {
            chips += ante * 2;
        }
    }

    public void Push () {
        chips += ante;
    }

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

    // update the players hand with new cards.
    // @todo make flow better with new Hand class (see game.java)
    public void updateHand(Card[] addedCards) {
        hand.updateHand(addedCards);
    }
    // prints players cards
    public void printCards() {
        hand.printHand();
    }

    // draw a card and check if its is greater than 21.
    public boolean hit(Card hitCard) {
        return hand.addCard(hitCard);
    }

    public int getHandValue() {
        return hand.getHandValue();
    }
    // @todo account for double down
    public boolean turn(Deck activeDeck) {
        while (true) {
            System.out.println("Would you like to hit, stand, or double down?");
            String response = input.nextLine();
            if (response.equalsIgnoreCase("stand")) {
                return false;
            } else if (response.equalsIgnoreCase("hit")) {
                boolean checkBust = this.hit(activeDeck.dealCard());
                printCards();
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

    public boolean autoBlackjack() {
        hasAutoBlackjack = hand.getHandValue() == 21;
        return hasAutoBlackjack;
    }

    public void clearConditions() {
        ante = 0;
        hasAutoBlackjack = false;
    }

}


