import Card from "./Card.jsx";
import '../styles/playerhand.css'

export default function PlayerHand({ hands, activeHandIndex }) {
    return (
        <div className="player-hand">
            PLAYER CARDS
            {(hands).map((hand, i) => (
                <div key={i} className={activeHandIndex === i ? "activeHand" : "hand"} >
                    {(hand.playerHand.cards).map((card, j) => (
                        <pre className="card" key={j}>
                            <Card rank={card.rank} suit={card.suit} />
                        </pre>
                    ))}
                    <div className="handValue">
                        Hand Value = {hand.handValue}
                    </div>
                </div>
            ))}
        </div>
    );
}
