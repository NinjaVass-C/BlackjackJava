// src/components/Controls.jsx
export default function Controls({
                                     onAddHand,
                                     onStartHand,
                                     onAction,
                                     onResolve,
                                     gameActive
                                 }) {
    if (!gameActive) return null; // don't show controls before game starts

    return (
        <div className="controlButtons">
            {/* Add a hand */}
            <div>
                <button
                    className="addHandButton"
                    onClick={() => onAddHand(25)} // example ante
                >
                    Add Hand
                </button>
            </div>

            {/* Start current hand */}
            <div>
                <button
                    className="startHandButton"
                    onClick={onStartHand}
                >
                    Start Hand
                </button>
            </div>

            {/* Player actions */}
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

                </button>
            </div>

            {/* Resolve round */}
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
