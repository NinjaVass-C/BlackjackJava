package com.example.backend.dtos;

import com.example.backend.models.Dealer;
import com.example.backend.models.Hand;
import com.example.backend.models.Player;
import com.example.backend.models.PlayerHand;

import java.util.ArrayList;

public class GameStateResponse {
    private ArrayList<PlayerHand> playerHands;
    private Hand dealerHand;
    private int chips;
    private int activeHandIndex;
    private boolean roundOver;
    public GameStateResponse(ArrayList<PlayerHand> playerHands, Hand dealerHand, int chips, int activeHandIndex, boolean roundOver) {
        this.playerHands = playerHands;
        this.dealerHand = dealerHand;
        this.chips = chips;
        this.activeHandIndex = activeHandIndex;
        this.roundOver = roundOver;
    }

    public ArrayList<PlayerHand> getPlayerHands() {
        return playerHands;
    }
    public Hand getDealerHand() {
        return dealerHand;
    }
    public int getChips() {
        return chips;
    }
    public int getActiveHandIndex() {
        return activeHandIndex;
    }
    public boolean isRoundOver() {
        return roundOver;
    }

}
