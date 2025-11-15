export default function Card({ rank, suit }) {
    let suitSymbol = ""
    const isRed = suit === "Hearts" || suit === "Diamonds";
    switch (suit) {
        case "Hearts":
            suitSymbol = "   ♥      |"
            break;
        case "Diamonds":
            suitSymbol = "   ♦      |"
            break;
        case "Clubs":
            suitSymbol = "   ♣      |"
            break;
        case "Spades":
            suitSymbol = "   ♠      |"
            break;
        default:
            suitSymbol = "   x      |"
            break;
    }
    // I am dumb and forgot to change how this works, maybe later
    switch (rank) {
        case 0:
            rank = "   ACE    |"
            break;
        case 9:
            rank = "  10      |"
            break;
        case 10:
            rank = "  JACK    |"
            break;
        case 11:
            rank = " QUEEN    |"
            break;
        case 12:
            rank = "  KING    |"
            break;
        default:
            rank = "   " + (rank + 1) + "      |"
            break;
    }


    return (
        <div className="card">
            <pre className="card">
{`---------------
|             |
|             |
|   ${rank}          
|             |
|             |
|   ${suitSymbol}          
|             |
|             |
---------------`}
            </pre>
        </div>
    );
}