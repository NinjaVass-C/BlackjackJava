// src/components/Controls.jsx
import { useState } from "react"; // <-- import useState
import '../styles/controls.css'

export default function Controls({
                                     onAddHand,
                                     onStartHand,
                                     onAction,
                                     onResolve,
                                     gameActive,
                                     toggleCount
                                 }) {

    const [anteAmt, setAnteAmt] = useState("");

    if (!gameActive) return null;

    const handleAddHand = () => {
        const ante = parseInt(anteAmt, 10);
        if (!isNaN(ante) && ante > 0) {
            onAddHand(ante);
            setAnteAmt(""); // optional: clear input after adding
        } else {
            alert("Please enter a valid ante amount");
        }
    };

    return (
        <div className="controlButtons">
            <div>
                Wager:
                <input
                    className="anteAmt"
                    required
                    type="number"
                    value={anteAmt}                  // <-- controlled input
                    onChange={(e) => setAnteAmt(e.target.value)} // <-- update state
                />
                <button
                    className="addHandButton"
                    onClick={handleAddHand}           // <-- call handler
                >
                    Add Hand
                </button>
            </div>

            <div>
                <button
                    className="startHandButton"
                    onClick={onStartHand}
                >
                    Start Hand
                </button>
            </div>

            <div className="playerActions">
                <button
                    className="hitButton"
                    onClick={() => onAction("HIT")}
                >
                    Hit
                </button>
                <button
                    className="standButton"
                    onClick={() => onAction("STAND")}
                >
                    Stand
                </button>
                <button
                    className="doubleButton"
                    onClick={() => onAction("DOUBLE")}
                >
                    Double
                </button>
                <button
                    className="splitButton"
                    onClick={() => onAction("SPLIT")}
                >
                    Split
                </button>
            </div>

            <div>
                <button
                    className="resolveRound"
                    onClick={onResolve}
                >
                    Resolve Round
                </button>
            </div>
            <div>
                <button
                    className="toggleCount"
                    onClick={toggleCount}
                >
                    Toggle Card Counter
                </button>
            </div>
        </div>
    );
}
