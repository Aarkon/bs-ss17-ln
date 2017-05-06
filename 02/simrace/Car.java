import java.util.Random;

/**
 * Class for the cars represented by threads that shall race each other.
 * Jakob Ledig & Florian Nehmer, 4.5.2017
 */
public class Car extends Thread implements Comparable {
    int number;
    int raceTime = 0;
    int laps = 0;
    private Random random = new Random();

    Car(int number) {
        this.number = number;
    }

    @Override
    public synchronized void run() {
        while (laps < SimRace.LAPS) {
            long lapTime = nextLap();
            try {
                sleep(lapTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long nextLap() {
        laps++;
        long lapTime = random.nextInt(100);
        System.out.println("Car " + this.number + ": " + lapTime);
        raceTime += lapTime;
        return lapTime;
    }

    @Override
    public int compareTo(Object o) {
        Car other = (Car) o;
        return this.raceTime - other.raceTime;
    }
}
