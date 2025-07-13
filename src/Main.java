import java.util.ArrayList;
// Main class that initializes game object and starts game with passed params.
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