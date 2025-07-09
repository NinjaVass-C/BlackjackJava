import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        boolean isRunning = true;

        while (isRunning) {
            isRunning = game.startGame(500, 2);
        }
        System.out.println("The End");

    }
}