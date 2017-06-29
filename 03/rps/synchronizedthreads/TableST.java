package rps.synchronizedthreads;

import java.util.ArrayList;
import java.util.List;

/**
 * The Buffer in the monitor controlled producer-consumer scenario.
 */
public class TableST {
	// Stores the choice of a player at the index of its number. E.G. player 0's
	// choice will be found at index 0.
	List<RPSType> items;
	final int PLAYERCOUNT = 2;

	public TableST() {
		items = new ArrayList<>(PLAYERCOUNT);
		// initialize the list so it has the fields we are later accessing:
		for (int i = 0; i < PLAYERCOUNT; i++) {
			items.add(null);
		}
	}

	/**
	 * Stores whatever the move of the player is.
	 *
	 * @param rps
	 *            The Rock Paper Scissor-Representation
	 * @throws InterruptedException 
	 */
	public synchronized void add(RPSType rps, int playerNumber) throws InterruptedException {
//		System.out.println("got " + rps.toString() + " from player " + playerNumber);
		while (this.items.get(playerNumber) != null) {
			this.wait();
		}
		items.set(playerNumber, rps);
		this.notifyAll();
	}

	/**
	 * Use this to find out if both players have made their moves.
	 *
	 * @return True if the table has results for both players, false if not.
	 */
	public boolean full() {
		System.out.println("table full queried");
		return (items.get(1) != null && items.get(0) != null);
	}

	public synchronized void clear() {
		while (this.items.get(0) == null && this.items.get(1) == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		for (int i = 0; i < PLAYERCOUNT; i++) {
			items.set(i, null);
		}
		this.notifyAll();
	}

	public RPSType getRPS(int playerNumber) {
		return items.get(playerNumber);
	}

	public synchronized int judge() throws InterruptedException {
		while (!this.full()) {
			this.wait();
		}
		System.out.println("looking for player decisions");
		RPSType p0Rps = items.get(0);
		RPSType p1Rps = items.get(1);
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

}
