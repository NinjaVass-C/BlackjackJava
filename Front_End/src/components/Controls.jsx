// src/components/Controls.jsx
export default function Controls({
                                     onAddHand,
                                     onStartHand,
                                     onAction,
                                     onResolve,
                                     gameActive
                                 }) {
    if (!gameActive) return null;

    return (
        <div className="controlButtons">
            <div>
                <button
                    className="addHandButton"
                    onClick={() => onAddHand(25)} // example ante
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
                    className="px-4 py-2 bg-purple-600 text-white rounded"
                    onClick={onResolve}
                >
                    Resolve Round
                </button>
            </div>
        </div>
    );
}
