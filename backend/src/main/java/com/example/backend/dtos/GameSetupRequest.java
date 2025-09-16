package com.example.backend.dtos;

public class GameSetupRequest {
    private int chipNo;
    private int deckNo;

    public GameSetupRequest(int chipNo, int deckNo) {
        this.chipNo = chipNo;
        this.deckNo = deckNo;
    }
    public int getChipNo() {
        return chipNo;
    }
    public int getDeckNo() {
        return deckNo;
    }
}
