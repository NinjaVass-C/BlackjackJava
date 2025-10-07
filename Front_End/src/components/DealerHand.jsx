import Card from "./Card.jsx";

export default function PlayerHand({ hand, turnOver }) {

    if (turnOver === true) {
        return (
            <div className="dealer-hand">
                {hand.card.map((card, i) => (
                    <Card key={i} rank={card.rank} suit={card.suit} />
                ))}
            </div>
        )
    } else {
        return (
            <div className="dealer-hand">
                <pre className="card">
                    ---------------
                    |             |
                    |             |
                    |             |
                    |             |
                    |             |
                    |             |
                    ---------------
                </pre>
                <Card rank={hand.cards[1].rank} suit = {hand.cards[1].suit} />
    </div>
    )
    }

}