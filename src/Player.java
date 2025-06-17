import java.util.ArrayList;
import java.util.Scanner;
/*
    Class for player, tracks their chip amounts, current hand, and their moves.
 */
public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private int chips = 500;

    public Player(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int Wager() {
        System.out.println("What would you like to wager?");
        Scanner input = new Scanner(System.in);
        try {
            int amt = input.nextInt();
            // @todo add global setting for table limits
            // for now just do typical limits.
            if (amt >= 25 && amt <= 500 && amt <= chips) {
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


}
