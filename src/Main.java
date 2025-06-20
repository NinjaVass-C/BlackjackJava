import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Declare needed starting variables

        // for now, only one player.
        Player activePlayer = new Player(500);
        Dealer activeDealer = new Dealer();
        Deck activeDeck = new Deck(2);
        int ante;
        // do turn by turn calls
        ante = activePlayer.Wager();
        Card[] dealt = activeDeck.dealDeck();
        activePlayer.updateHand(new Card[] {dealt[0], dealt[2]});
        activeDealer.updateHand(new Card[] {dealt[1], dealt[3]});
        boolean stand = false;
        boolean playerBust = false;
        boolean dealerBust = false;
        activeDealer.displayFirst();
        activePlayer.printCards();

        while (!playerBust) {
            stand = activePlayer.turn();
            if (stand) {
                break;
            }
            playerBust = activePlayer.hit(activeDeck.dealCard());
        }
        dealerBust = activeDealer.dealToEnd(activeDeck.getDeck(), playerBust);

        if (dealerBust) {
            System.out.println("You Win");
        }
        else if (playerBust) {
            System.out.println("You Lose");
        }
        else if (activeDealer.getHandValue() > activePlayer.getHandValue()) {
            System.out.println("You Lose");
        } else if (activeDealer.getHandValue() < activePlayer.getHandValue()) {
            System.out.println("You Win");
        }
        else {
            System.out.println("You Push");
        }


    }
}