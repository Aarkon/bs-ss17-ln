package rps;

/**
 * Main Class to start the Rock Paper Scissors game.
 */
public class RPSRunner {
    public static void main(String[] args) {
        Table table = new Table();
        Player player0 = new Player(table, 0);
        Player player1 = new Player(table, 1);
    }
}
