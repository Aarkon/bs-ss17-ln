package rps.tests;

import org.junit.Before;
import org.junit.Test;
import rps.Countdown;

import java.util.concurrent.TimeUnit;

public class CountdownTest {
    Countdown countdown0;
    Countdown countdown1;

    @Before
    public void setUp() throws Exception {
        countdown0 = new Countdown(1000);
        countdown1 = new Countdown(2000);
    }

    @Test
    public void go() throws Exception {
        countdown0.go();
        assert (countdown0.getTime() > 0);
        TimeUnit.SECONDS.sleep(1);
        assert (countdown0.getTime() <= 0);

        countdown1.go();
        assert (countdown1.getTime() > 0);
        TimeUnit.SECONDS.sleep(1);
        assert (countdown1.getTime() > 0);
        TimeUnit.SECONDS.sleep(1);
        assert (countdown1.getTime() <= 0);

    }

}