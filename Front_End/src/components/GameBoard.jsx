import {useEffect, useState} from "react";
import PlayerHand from "./PlayerHand"
import DealerHand from "./DealerHand.jsx"
import Controls from "./Controls.jsx"
import {addHand, playerAction, resolveRound, startGame, startHand} from "../service/gameService";

export default function GameBoard() {
    const [gameActive, setGameActive] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [playerHands, setPlayerHands] = useState([]);
    const [dealerHand, setDealerHand] = useState([]);
    const [chips, setChips] = useState(0);
    const [handIndex, setHandIndex] = useState(0);
    const [data, setData] = useState([]);
    const [turnOver, setTurnOver] = useState(false)
    const [handRunning, setHandRunning] = useState(false)

    useEffect(() => {
        console.log("Game active changed:", gameActive);
    }, [gameActive]);
    useEffect(() => {
        console.log("Player Hand: ", playerHands)
    }, [playerHands])
    const handleStart = async () => {
        try {
            setLoading(true);
            setError(null);

            const dto = { chipNo: 500, deckNo: 6 };
            const data = await startGame(dto);

            setData(data);
            setGameActive(true);
            updateGameState(data);
        } catch (err) {
            setError("Failed to start game");
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    const handleAddHand = async (ante) => {
        try {
            setLoading(true);
            const dto = {
                "ante": ante
            }
            const data = await addHand(dto);
            updateGameState(data)
        } catch (err) {
            setError("Unable to add hand")
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const handleStartHand = async () => {
        try {
            setLoading(true);
            const data = await startHand();
            updateGameState(data)
        } catch (err) {
            setError("Unable to start hand");
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const handlePlayerAction = async (action) => {
        try {
            setLoading(true);
            const dto = {
                "action": action
            }
            const data = await playerAction(dto)
            updateGameState(data)
        } catch (err) {
            setError("Unable to do action")
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const handleResolveRound = async () => {
        try {
            setLoading(true);
            const data = await resolveRound();
            updateGameState(data)
        } catch (err) {
            setError("Unable to resolve round")
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const updateGameState = (data) => {
        console.log(data)
        setChips(data.chips)
        setPlayerHands(data.playerHands)
        setDealerHand(data.dealerHand)
        setHandIndex(data.activeHandIndex)
        setTurnOver(data.roundOver)
        setHandRunning(data.handRunning)
    }

    return (
        <div className="game-board">

            {!gameActive ? (
                <button onClick={handleStart}>Start Game</button>
            ) : (
                <>
                    <div className="chipNumber">CHIPS: ${chips} </div>
                    <Controls
                        onAddHand={handleAddHand}
                        onStartHand={handleStartHand}
                        onAction={handlePlayerAction}
                        onResolve={handleResolveRound}
                        gameActive={gameActive}
                    />
                </>
            )}
            {handRunning ? (
                <>
                    <PlayerHand hands={playerHands} />
                    <DealerHand hand={dealerHand} turnOver={turnOver} />
                </>
            ) : (
                <>
                </>
            )}
        </div>
    );
}
