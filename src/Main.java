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

        // for now, print cards for testing.
        activePlayer.printCards();
        activeDealer.displayFirst();
        activeDealer.printCards();

        activePlayer.hit(activeDeck.dealCard());
    }
}