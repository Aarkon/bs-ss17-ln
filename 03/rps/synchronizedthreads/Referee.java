package rps.synchronizedthreads;

import java.util.HashMap;
import java.util.Map;

/**
 * Administrates a game of Rock Paper Scissor by taking the role of the consumer
 * in a monitor controlled producer-consumer scenario.
 */
public class Referee extends Thread {
	protected Map<String, Integer> results;
	protected TableST table;
	protected final Player player0;
	protected final Player player1;

	public Referee(TableST table, Player player0, Player player1) {
		this.table = table;
		this.player0 = player0;
		this.player1 = player1;

		results = new HashMap<>();
		results.put("Player0 wins: ", 0);
		results.put("Player1 wins: ", 0);
		results.put("Draws: ", 0);
		results.put("Rounds played: ", 0);
	}

	@Override
	public synchronized void run() {
		while (!isInterrupted()) {
			System.out.println("Referee's turn");
			this.judge();
		}
	}

	private void judge() {
		int result = 0;
		try {
			result = table.judge();
		} catch (InterruptedException e) {
			this.interrupt();
		}
		System.out.println("judge is judging: " + result);
		if (result == 1) {
			int wins = results.get("Player0 wins: ") + 1;
			results.put("Player0 wins: ", wins);
		} else if (result == -1) {
			int wins = results.get("Player1 wins: ") + 1;
			results.put("Player1 wins: ", wins);
		} else {
			int draws = results.get("Draws: ") + 1;
			results.put("Draws: ", draws);
		}
		int roundsPlayed = results.get("Rounds played: ") + 1;
		results.put("Rounds played: ", roundsPlayed);
		table.clear();
	}



	/**
	 * Gives insight in the results the Referee collects.
	 *
	 * @return results
	 */
	public Map<String, Integer> getResults() {
		return results;
	}
}
