package rps;

/**
 * Administrates a game of Rock Paper Scissor by taking the role of the consumer in a monitor controlled
 * producer-consumer scenario.
 */
public class Referee extends Thread {
    private Table table;
    private final Player player0;
    private final Player player1;

    public Referee(Table table, Player player0, Player player1) {
        this.table = table;
        this.player0 = player0;
        this.player1 = player1;
    }

    synchronized Player judge() throws InterruptedException {
        while (!table.full()) {
            this.wait();
        }
        RPSType fromPlayer0 = table.items.get(0);
        RPSType fromPlayer1 = table.items.get(1);
        this.notifyAll();
        switch (fromPlayer0.beats(fromPlayer1)) {
            case 1:
                return player0;
            case -1:
                return player1;
            default:
                return null;
        }
    }
}
