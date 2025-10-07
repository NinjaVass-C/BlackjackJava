import Card from "./Card.jsx";

export default function PlayerHand({ hand }) {
    return (
        <div className="player-hand">
            {hand.card.map((card, i) => (
                <Card key={i} rank={card.rank} suit={card.suit} />
            ))}
        </div>
    );
}