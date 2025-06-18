import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // for now, only one player.
        Player activePlayer;
        Dealer activeDealer;
        Deck activeDeck = new Deck(2);
        Card[] dealt = activeDeck.dealDeck();
        for (Card card : dealt) {
            System.out.println(card);
        }
    }
}