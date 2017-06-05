package rps.tests;

import org.junit.Test;
import rps.Player;
import rps.RPSType;
import rps.Table;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PlayerTest {

    @Test
    public void beats() throws Exception {
        Table table = new Table();
        Player player0 = new Player(table, 0);
        Player player1 = new Player(table, 1);

        player0.setRPS(RPSType.PAPER);
        player1.setRPS(RPSType.SCISSOR);
        assertThat(player0.beats(player1), is(-1));
        assertThat(player1.beats(player0), is(1));

        player0.setRPS(RPSType.ROCK);
        assertThat(player0.beats(player1), is(1));
        assertThat(player1.beats(player0), is(-1));

        player0.setRPS(RPSType.SCISSOR);
        assertThat(player0.beats(player1), is(0));
        assertThat(player1.beats(player0), is(0));
    }

}