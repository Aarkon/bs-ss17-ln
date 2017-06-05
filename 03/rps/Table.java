package rps;

import java.util.ArrayList;
import java.util.List;

/**
 * The Buffer in the monitor controlled producer-consumer scenario.
 */
public class Table {
    // Stores the choice of a player at the index of its number. E.G. player 0's choice will be found at index 0.
    List<RPSType> items;
    final int PLAYERCOUNT = 2;

    public Table() {
        // Considered not to be a magic number because Rock Paper Scissor with more than two players is unheard of.
        items = new ArrayList<>(PLAYERCOUNT);
        // initialize the list so it actually has the fields we are later accessing:
        for (int i = 0; i < PLAYERCOUNT; i++) {
            items.add(null);
        }
    }

    /**
     * Stores whatever the move of the player is.
     *
     * @param rps The Rock Paper Scissor-Representation
     */
    public void add(RPSType rps, int playerNumber) {
        items.set(playerNumber, rps);
    }

    /**
     * Use this to find out if both players have made their moves.
     *
     * @return True if the table has results for both players, false if not.
     */
    public boolean full() {
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
