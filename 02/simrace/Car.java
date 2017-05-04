import java.util.Random;

/**
 * Class for the cars represented by threads that shall race each other.
 * Jakob Ledig & Florian Nehmer, 3.5.2017
 */
public class Car implements Runnable {
    private int number;
    private int lapTime;
    private int raceTime = 0;
    private Random random;

    public Car(int number) {
        this.number = number;
    }

    /**
     * Rolls and returns a value between 0 and 100 as a lap time.
     * @return lapTime
     */
    int newLap() {
        lapTime = random.nextInt(100);
        raceTime += lapTime;
        return lapTime;
    }
    @Override
    public void run() {

    }

    public int getNumber() {
        return number;
    }

    public int getLapTime() {
        return lapTime;
    }

    public int getRaceTime() {
        return raceTime;
    }
}
