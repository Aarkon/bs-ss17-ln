package rps;

/**
 * Main Class to start the Rock Paper Scissors game.
 */
public class RPSRunner {
    // Time in milliseconds:
    private static final int TIME = 1000;
    private static int roundCount = 0;

    private static Table table = new Table();
    private static Player player0 = new Player(table, 0);
    private static Player player1 = new Player(table, 1);
    private static Referee referee = new Referee(table, player0, player1);

    private static Countdown countdown = new Countdown(TIME);

    public static void main(String[] args) throws InterruptedException {
        referee.startSession();
    }

    private static void round() throws InterruptedException {
        roundCount++;
        System.out.println(roundCount);
        player0.play();
        player1.play();
        referee.judge();
    }

    private static void evaluate() {
        System.out.println("Evaluate");
    }
}
