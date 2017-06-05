package rps;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple concurrent countdown that takes time in milliseconds as a parameter.
 * The remaining time can be retrieved by the getter on the field.
 */
public class Countdown {
    private int time;

    public Countdown(int time) {
        this.time = time;
    }

    public void go() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                //System.out.println(time);
                time--;
                if (time < 0) timer.cancel();
            }
        }, 0, 1);
    }

    public int getTime() {
        return time;
    }
}
