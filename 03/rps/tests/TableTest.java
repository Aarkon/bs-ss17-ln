package rps.tests;

import org.junit.Before;
import org.junit.Test;
import rps.RPSType;
import rps.Table;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TableTest {
    Table table0;

    @Before
    public void setUp() {
        table0 = new Table();
    }

    @Test
    public void CRUD() throws Exception {
        table0.add(RPSType.PAPER, 0);
        assertThat(table0.getRPS(0), is(RPSType.PAPER));
    }

    @Test
    public void full() throws Exception {
        table0.add(RPSType.PAPER, 0);
        table0.add(RPSType.ROCK, 1);
        assertThat(table0.full(), is(true));
    }

    @Test
    public void clear() throws Exception {
        table0.add(RPSType.PAPER, 0);
        table0.add(RPSType.ROCK, 1);
        table0.clear();
        assertThat(table0.full(), is(false));
    }

}