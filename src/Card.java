/*
    Used to create individual cards, including their suit and
    card number
    0 - Ace
    1-9 - 2-10
    10 - Jack
    11 - Queen
    12 - King
 */
public class Card {
    private int rank;
    private String suit;
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
}
