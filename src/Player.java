import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
    Class for player, tracks their chip amounts, current hand, and their moves.
 */
public class Player {
    private Hand hand = new Hand("Player");
    private int chips = 500;
    private boolean bust = false;

    public Player(int initialChips) {
        this.chips = initialChips;
    }

    public int getChips() {
        return this.chips;
    }

    public boolean getBust() {
        return this.bust;
    }

    public void Win (int winnings) {
        chips += winnings * 2;
    }

    public void Push (int push) {
        chips += push;
    }

    public int Wager() {
        System.out.println("What would you like to wager (Max 500)? (Current Chips: " + chips + ") ");
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            try {
                int amt = input.nextInt();
                // @todo add global setting for table limits
                // for now just do typical limits.
                if (amt >= 25 && amt <= 500 && amt <= chips) {
                    chips -= amt;
                    return amt;
                } else {
                    throw new Exception("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                System.out.println("What would you like to wager (Max 500)? (Current Chips: " + chips + ") ");
                input.nextLine();
            }
        }
        return 0;
    }

    // update the players hand with new cards.
    // @todo make flow better with new Hand class
    // @todo payout for blackjack
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
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        System.out.println("Would you like to hit or stand?");
        while (!valid) {
            try {
                String response = input.nextLine();
                if (response.equalsIgnoreCase("stand")) {
                    return false;
                } else if (response.equalsIgnoreCase("hit")) {
                    boolean check = this.hit(activeDeck.dealCard());
                    printCards();
                    if (check) {
                        bust = true;
                        return bust;
                    }
                    this.turn(activeDeck);
                } else {
                    throw new Exception("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                printCards();
                System.out.println("Would you like to hit or stand?");
            }
        }
        return true;
    }

}
