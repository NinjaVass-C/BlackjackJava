package com.example.backend.dtos;

import com.example.backend.models.PlayerAction;

public class PlayerActionRequest {
    private PlayerAction.Action action;
    public PlayerActionRequest(PlayerAction.Action action) {
        this.action = action;
    }
    public PlayerAction.Action getAction() {
        return action;
    }
}
