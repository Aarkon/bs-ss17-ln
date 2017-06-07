package rps;

import java.util.HashMap;
import java.util.Map;

/**
 * Administrates a game of Rock Paper Scissor by taking the role of the consumer
 * in a monitor controlled producer-consumer scenario.
 */
public class Referee extends Thread {
	private Map<String, Integer> results;
	private Table table;
	private Player player0;
	private Player player1;

	public Referee(Table table, Player player0, Player player1) {
		this.table = table;
		this.player0 = player0;
		this.player1 = player1;

		results = new HashMap<>();
		results.put("Player0 wins: ", 0);
		results.put("Player1 wins: ", 0);
		results.put("Draws: ", 0);
	}

	synchronized void judge() {
		if (player0.beats(player1) == 1) {
			int wins = results.get("Player0 wins: ") + 1;
			results.put("Player0 wins: ", wins);
		} else if (player0.beats(player1) == -1) {
			int wins = results.get("Player1 wins: ") + 1;
			results.put("Player1 wins: ", wins);
		} else {
			int draws = results.get("Draws: ") + 1;
			results.put("Draws: ", draws);
		}
		synchronized (table) {
			table.clear();
			table.notify();
		}
	}

	@Override
	public void run() {
		player0.start();
		player1.start();
		while (!interrupted()) {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (table.full(this)) {
				judge();
				synchronized (player0) {
					player0.notify();
				}
				synchronized (player1) {
					player1.notify();
				}
			}
		}

	}
}
