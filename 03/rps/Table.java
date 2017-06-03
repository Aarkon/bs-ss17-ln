package rps;

import java.util.ArrayList;
import java.util.List;

/**
 * The Buffer in the monitor controlled producer-consumer scenario.
 */
public class Table {
    // Stores the choice of a player at the index of its number. E.G. player 0's choice will be found at index 0.
    List<RPSType> items;

    public Table() {
        // Considered not to be a magic number because Rock Paper Scissor with more than two players is unheard of.
        items = new ArrayList<>(2);
    }

    /**
     * Stores whatever the move of the player is.
     *
     * @param rps The Rock Paper Scissor-Representation
     */
    void add(RPSType rps, int playerNumber) {
        items.set(playerNumber, rps);
    }

    /**
     * Use this to find out if both players have made their moves.
     *
     * @return True if the table has results for both players, false if not.
     */
    boolean full() {
        return (items.get(items.size() - 1) != null);
    }
}
