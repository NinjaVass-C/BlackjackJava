import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
    Class for player, tracks their chip amounts, current hand, and their moves.
 */
public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private int chips = 500;

    public Player(int initialChips) {
        this.chips = initialChips;
    }

    public int Wager() {
        System.out.println("What would you like to wager?");
        Scanner input = new Scanner(System.in);
        try {
            int amt = input.nextInt();
            // @todo add global setting for table limits
            // for now just do typical limits.
            if (amt >= 25 && amt <= 500 && amt <= chips) {
                chips-=amt;
                return amt;
            } else {
                System.out.println("Invalid input");
                Wager();
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            Wager();
        }

        return 0;
    }

    // update the players hand with new cards.
    public void updateHand(Card[] addedCards) {
        hand.addAll(Arrays.asList(addedCards));
        this.getHandValue();
    }
    // prints players cards
    public void printCards() {
        System.out.print("Player has: ");
        for (Card card : hand) {
            System.out.print(card + " | ");
        }
        System.out.println();
    }

    // draw a card and check if its is greater than 21.
    public boolean hit(Card hitCard) {
        hand.add(hitCard);
        this.printCards();
        int value = this.getHandValue();
        return value > 21;
    }

    public int getHandValue() {
        int total = 0;
        for (Card card : hand) {
            total += card.getValue();
        }
        if (total > 21) {
            System.out.println("You bust");
        }
        return total;
    }

    public boolean turn() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to hit or stand?");
        try {
            String response = input.nextLine();
            if (response.equalsIgnoreCase("stand")) {
                return true;
            }
            else if (response.equalsIgnoreCase("hit")) {
                return false;
            } else {
                throw new Exception("Invalid input");
            }
        }
        catch (Exception e) {
            System.out.println("Invalid input");
            this.turn();
        }
        return false;
    }

}
