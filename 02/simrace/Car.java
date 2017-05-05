import java.util.Random;

/**
 * Class for the cars represented by threads that shall race each other.
 * Jakob Ledig & Florian Nehmer, 4.5.2017
 */
public class Car extends Thread {
    int number;
    int raceTime = 0;
    int laps = 0;
    private Random random = new Random();

    public Car(int number) {
        this.number = number;
    }

    @Override
    public synchronized void start() {
        System.out.println(this.number);
        long lapTime = nextLap();
        try {
            sleep(lapTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // super.start();
    }

    private long nextLap() {
        laps++;
        long lapTime = random.nextInt(100);
        System.out.println("Car " + this.number + ": " + lapTime);
        raceTime += lapTime;
        return lapTime;
    }
}
