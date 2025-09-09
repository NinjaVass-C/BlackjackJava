package com.example.backend.models;

/**
 * Represents a deck for blackjack. Consists of a standard 52 card playing card deck,
 * multiplied by the amount of decks desired.
 *
 * Author: Connor Vass
 * Version: 2.0
 * Date: Sept 9th, 2025
 */


public class PlayerAction {
    public enum Action {
        HIT,
        STAND,
        DOUBLE,
        SPLIT
    }
}
