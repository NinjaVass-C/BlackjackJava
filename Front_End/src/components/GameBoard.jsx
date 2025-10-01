import {useState} from "react";
import PlayerHand from "/PlayerHand"
import DealerHand from "/DealerHand.jsx"
import Controls from "/Controls.jsx"
import {addHand, getDealerHand, playerAction, resolveRound, startGame, startHand} from "../service/gameService";

export default function GameBoard() {
    const [gameActive, setGameActive] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);


    const handleStart = () => {
        //for now, pass hard coded values for simplicity
        try {
            setLoading(true);
            const dto = {
                chipNo: 500,
                deckNo: 6
            }
            const data = startGame(dto);
        } catch (err) {
            setError("Failed to start game");
            setLoading(false);
        } finally {
            setLoading(false);
            setGameActive(true);
        }
    }
    const handleAddHand = (ante) => {
        try {
            setLoading(true);
            const data = addHand(ante);
        } catch (err) {
            setError("Unable to add hand")
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const handleStartHand = () => {
        try {
            setLoading(true);
            const data = startHand();
        } catch (err) {
            setError("Unable to start hand");
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const handleGetDealerHand = () => {
        try {
            setLoading(true);
            const data = getDealerHand();
        } catch (err) {
            setError("Unable to get dealer hand");
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }

    const handlePlayerAction = (action) => {
        try {
            setLoading(true);
            const data = playerAction(action)
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
            const data = resolveRound();
        } catch (err) {
            setError("Unable to resolve round")
            setLoading(false);
        } finally {
            setLoading(false);
        }
    }
}
