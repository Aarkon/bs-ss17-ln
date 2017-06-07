package rps.locksconditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import rps.synchronizedthreads.RPSType;
import rps.synchronizedthreads.Table;

public class RefereeLC extends Thread {

	private ReentrantLock lock;
	private List<Thread> schedule;
	private Table table;
	private PlayerLC player0;
	private PlayerLC player1;
	protected Map<String, Integer> results;

	public RefereeLC(Table table, PlayerLC player0, PlayerLC player1, ReentrantLock lock, List<Thread> schedule) {
		this.table = table;
		this.player0 = player0;
		this.player1 = player1;
		this.lock = lock;
		this.schedule = schedule;

		results = new HashMap<>();
		results.put("Player0 wins: ", 0);
		results.put("Player1 wins: ", 0);
		results.put("Draws: ", 0);
		results.put("Rounds played: ", 0);
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			if (schedule.get(0) == this) {
				lock.lock();
				// reschedule all tokens but the just used one:
				schedule.add(schedule.remove(0));
				judge();
				lock.unlock();
			}
		}
		
	}

	private void judge() {
		// System.out.println("judge is judging: " + beats());
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
