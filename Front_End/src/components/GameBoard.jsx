import {useState} from "react";
import PlayerHand from "./PlayerHand"
import DealerHand from "./DealerHand.jsx"
import Controls from "./Controls.jsx"
import {addHand, getDealerHand, playerAction, resolveRound, startGame, startHand} from "../service/gameService";

export default function GameBoard() {
    const [gameActive, setGameActive] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [playerHands, setPlayerHands] = useState([]);
    const [dealerHand, setDealerHand] = useState([]);
    const [chips, setChips] = useState(0);
    const [handIndex, setHandIndex] = useState(0);
    const [data, setData] = useState([]);

    const handleStart = async () => {
        //for now, pass hard coded values for simplicity
        try {
            setLoading(true);
            const dto = {
                chipNo: 500,
                deckNo: 6
            }
            const data = await startGame(dto);
            await setData(data);
            await updateGameState(data)
        } catch (err) {
            setError("Failed to start game");
            setLoading(false);
        } finally {
            setLoading(false);
            setGameActive(true);
        }
    }
    const handleAddHand = async (ante) => {
        try {
            setLoading(true);
            const data = await addHand(ante);
            await updateGameState(data)
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
            await updateGameState(data)
        } catch (err) {
            setError("Unable to start hand");
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const handleGetDealerHand = async () => {
        try {
            setLoading(true);
            const data = await getDealerHand();
            await setDealerHand(data.dealerHand)
        } catch (err) {
            setError("Unable to get dealer hand");
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const handlePlayerAction = async (action) => {
        try {
            setLoading(true);
            const data = await playerAction(action)
            await updateGameState(data)
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
            await updateGameState(data)
        } catch (err) {
            setError("Unable to resolve round")
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const updateGameState = async (data) => {
        setChips(data.chips)
        setPlayerHands(data.playerHands)
        setDealerHand(data.dealerHand)
        setHandIndex(data.activeHandIndex)
    }

    return (
        <div className="game-board">

            {!gameActive ? (
                <button onClick={handleStart}>Start Game</button>
            ) : (
                <>
                    <div>Api Testing Start</div>
                    {/*<DealerHand hand={dealerHand} />*/}
                    {/*<PlayerHand hands={playerHands} />*/}
                    {/*<Controls*/}
                    {/*    onAddHand={handleAddHand}*/}
                    {/*    onStartHand={handleStartHand}*/}
                    {/*    onAction={handlePlayerAction}*/}
                    {/*    onResolve={handleResolveRound}*/}
                    {/*/>*/}
                    <p>Chips: {chips}</p>
                    <p>Data: {JSON.stringify(data)}</p>
                </>
            )}
        </div>
    );
}
