import Card from "./Card.jsx";

export default function PlayerHand({ hands }) {
    return (
        <div className="player-hand">
            PLAYER CARDS
            {(hands).map((hand, i) => (
                <div key={i} className="hand" style={{ border: "1px solid black", padding: "10px" }}>
                    {(hand.playerHand.cards).map((card, j) => (
                        <pre className="card" key={j}>
                            <Card rank={card.rank} suit={card.suit} />
                        </pre>
                    ))}
                </div>
            ))}
        </div>
    );
}
