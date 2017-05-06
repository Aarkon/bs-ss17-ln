import java.util.Random;

/**
 * Jakob Ledig & Florian Nehmer, 4.5.2017
 */
public class Accident extends Thread {
    static boolean happened = false;
    private double chance;
    private int interval;
    private Random random = new Random();

    Accident(float chance, int interval) {
        this.chance = chance; // 0: Accident never happens, 1: happens instantly
        this.interval = interval;
    }

    Accident() {
        this.chance = 0.5;
        interval = 100;
    }

    @Override
    public synchronized void run() {
        while (!happened) {
            happened = Math.random() < chance;
            try {
                sleep(random.nextInt(interval) * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
