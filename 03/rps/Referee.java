package rps;

import java.util.HashMap;
import java.util.Map;

/**
 * Administrates a game of Rock Paper Scissor by taking the role of the consumer in a monitor controlled
 * producer-consumer scenario.
 */
public class Referee extends Thread {
    private Map<String, Integer> results;
    private Table table;
    private Player player0;
    private Player player1;

    Referee(Table table, Player player0, Player player1) {
        this.table = table;
        this.player0 = player0;
        this.player1 = player1;

        results = new HashMap<>();
    }

    synchronized Player judge() throws InterruptedException {
        while (!table.full()) {
            this.wait();
        }
        RPSType fromPlayer0 = table.items.get(0);
        RPSType fromPlayer1 = table.items.get(1);
        this.notifyAll();
        if (player0.beats(player1) == 1) {
            return player0;
        } else if (player0.beats(player1) == -1) {
            return player1;
        } else {
            return null;
        }
    }

    public void startSession() throws InterruptedException {
        player0.play();
        player1.play();
        while (!interrupted()) {

        }

    }
}
