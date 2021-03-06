package rps.locksconditions;

import java.util.ArrayList;
import java.util.List;

import rps.synchronizedthreads.RPSType;

/**
 * The Buffer in the monitor controlled producer-consumer scenario.
 */
public class Table {
	// Stores the choice of a player at the index of its number. E.G. player 0's
	// choice will be found at index 0.
	List<RPSType> items;
	final int PLAYERCOUNT = 2;

	public Table() {
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
	 */
	public void add(RPSType rps, int playerNumber) {
		// System.out.println("got " + rps.toString() + " from player " + playerNumber);
		items.set(playerNumber, rps);
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

	public void clear() {
		for (int i = 0; i < PLAYERCOUNT; i++) {
			items.set(0, null);
		}
	}

	public RPSType getRPS(int playerNumber) {
		return items.get(playerNumber);
	}
}
