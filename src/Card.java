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

    public String toString() {
        String temp;
        switch (rank) {
            case 0:
                temp = "Ace";
                break;
            case 1,2,3,4,5,6,7,8,9:
                temp = Integer.toString(rank + 1);
                break;
            case 10:
                temp = "Jack";
                break;
            case 11:
                temp = "Queen";
                break;
            case 12:
                temp = "King";
                break;
            default:
                throw new AssertionError("Illegal rank");
        }
        return temp + " Of " + suit;
    }
}
