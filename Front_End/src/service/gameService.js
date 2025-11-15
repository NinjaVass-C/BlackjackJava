export const startGame = async (dto) => {
    const res = await fetch("/api/game/start", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(dto),
    });
    if (!res.ok) {
        throw new Error("Failed to start game");
    }
    return res.json();
}

export const addHand = async (dto) => {
    const res = await fetch("/api/game/hand/add", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(dto)
    });
    if (!res.ok) {
        throw new Error("Failed to add hand");
    }
    return res.json();
}

export const startHand = async () => {
    const res = await fetch("/api/game/hand/start", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
    });
    if (!res.ok) {
        throw new Error("Failed to start hand(s)");
    }
    return res.json();
}


export const playerAction = async (dto) => {
    const res = await fetch("/api/game/player/action", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(dto),
    });
    if (!res.ok) {
        throw new Error("Failed to send player action");
    }
    return res.json();
}

export const resolveRound = async () => {
    const res = await fetch("/api/game/resolve", {
        method: "GET",
        headers: {"Content-Type": "application/json"},
    });
    if (!res.ok) {
        throw new Error("Failed to send player action");
    }
    return res.json();
}

