export default function Card({ rank, suit }) {
    const suitSymbols = {
        Hearts: "♥",
        Diamonds: "♦",
        Clubs: "♣",
        Spades: "♠"
    };

    const isRed = suit === "HEARTS" || suit === "DIAMONDS";

    return (
        <div className={`card ${isRed ? "red" : "black"}`}>
            <pre className="card">
                {`-------------
                |             |
                |   ${rank}   |
                |             |
                |             |
                |  ${suit}    |
                |             |
                --------------`}
            </pre>
        </div>
    );
}