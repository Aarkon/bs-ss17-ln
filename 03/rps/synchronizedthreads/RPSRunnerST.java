package rps.synchronizedthreads;

/**
 * Main Class to start the Rock Paper Scissors game.
 */
public class RPSRunnerST extends Thread {
	// Time in milliseconds:
	private static final int TIME = 10;

	private static TableST table = new TableST();
	private static Player player0 = new Player(table, 0);
	private static Player player1 = new Player(table, 1);
	private static Referee referee = new Referee(table, player0, player1);

	public static void main(String[] args) throws InterruptedException {
		player0.setReferee(referee);
		player1.setReferee(referee);
		player0.start();
		player1.start();
		referee.start();
		sleep(TIME);
		player0.interrupt();
		player1.interrupt();
		referee.interrupt();
		referee.join();
		evaluate();
	}

	private static void evaluate() {
		System.out.println("Evaluation!");
		referee.getResults().forEach((string, number) -> {
			System.out.println(string + number);
		});
	}
}
