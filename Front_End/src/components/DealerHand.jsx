import Card from "./Card.jsx";
import '../styles/dealerHand.css'
export default function DealerHand({ hand, turnOver }) {
    if (turnOver === true) {
        return (
            <div className="dealer-container">
                <div className="dealer-hand">
                    DEALER CARDS
                    {hand.cards.map((card, i) => (
                        <pre className="card" key={i}>
                            <Card key={i} rank={card.rank} suit={card.suit} />
                        </pre>
                    ))}
                    <div className="handValue"> Hand Value = {hand.handValue}</div>
                </div>
            </div>
        )
    } else {
        return (
            <div className="dealer-container">
                <div className="dealer-hand">
                    DEALER CARDS
                    <pre className="card">
{`-------------
|             |
|             |
|             |
|             |
|             |
|             |
|             |
|             |
--------------`}
                </pre>
                    <Card rank={hand.cards[1].rank} suit = {hand.cards[1].suit} />
                </div>
            </div>
    )
    }

}