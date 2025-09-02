package src;

/**
 * Main class for blackjack project
 * Current use is to create game object, pass initial game parameters,
 * Then print to the user that the game is over.
 * This will be changed with BACK_END, but for now will be used for Version 1.0
 *
 *
 * Author: Connor Vass
 * Version: 1.0
 * Date: July 13, 2025
 */
public class Main {
    public static void main(String[] args) {
        // creates game object
        Game game = new Game();
        // calls startGame to start game loop.
        game.startGame(500, 2);
        // indicate game is over.
        System.out.println("The End");

    }
}