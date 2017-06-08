package rps.synchronizedthreads;

import java.util.HashMap;
import java.util.Map;

/**
 * Administrates a game of Rock Paper Scissor by taking the role of the consumer
 * in a monitor controlled producer-consumer scenario.
 */
public class Referee extends Thread {
	protected Map<String, Integer> results;
	protected Table table;
	protected Player player0;
	protected Player player1;

	public Referee(Table table, Player player0, Player player1) {
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
	public void run() {
		// TODO make this shit work!!!
		player0.start();
		player1.start();
		while (!isInterrupted() && !player0.isInterrupted() && !player1.isInterrupted()) {
			playRound();
		}
		player0.interrupt();
		player1.interrupt();
	}

	private void playRound() {
		synchronized (player0) {
			player0.notify();
		}
		synchronized (player1) {
			player1.notify();
		}
		judge();
		synchronized (this){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void judge() {
		 System.out.println("judge is judging: " + beats());
		if (beats() == 1) {
			int wins = results.get("Player0 wins: ") + 1;
			results.put("Player0 wins: ", wins);
		} else if (beats() == -1) {
			int wins = results.get("Player1 wins: ") + 1;
			results.put("Player1 wins: ", wins);
		} else {
			int draws = results.get("Draws: ") + 1;
			results.put("Draws: ", draws);
		}
		int roundsPlayed = results.get("Rounds played: ") + 1;
		results.put("Rounds played: ", roundsPlayed);
		table.clear();
		synchronized (player0) {
			player0.notify();
		}
		synchronized (player1) {
			player1.notify();
		}
	}

	/**
	 * Performs a comparison between another instance and determines the winner.
	 *
	 * @param other
	 * @return 1 if player0 wins, -1 if player1 wins, or 0 for a draw
	 */
	protected int beats() {
		RPSType p0Rps = table.getRPS(0);
		RPSType p1Rps = table.getRPS(1);
		switch (p0Rps) {
		case ROCK:
			if (p1Rps == RPSType.ROCK) {
				return 0;
			} else if (p1Rps == RPSType.PAPER) {
				return -1;
			} else {
				return 1;
			}
		case PAPER:
			if (p1Rps == RPSType.ROCK) {
				return 1;
			} else if (p1Rps == RPSType.PAPER) {
				return 0;
			} else {
				return -1;
			}
		case SCISSOR:
			if (p1Rps == RPSType.ROCK) {
				return -1;
			} else if (p1Rps == RPSType.PAPER) {
				return 1;
			} else {
				return 0;
			}
		default:
			return 0;
		}
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
