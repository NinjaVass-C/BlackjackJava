import {useState} from "react";
import PlayerHand from "/PlayerHand"
import DealerHand from "/DealerHand.jsx"
import Controls from "/Controls.jsx"

export default function GameBoard() {
    const [gameState, setGameState] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);


    const handleStart = () => {

    }
}
