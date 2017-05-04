import java.util.HashSet;
import java.util.Set;

/**
 * The main class for the thread race in BSP02.
 * Jakob Ledig & Florian Nehmer, 3.5.2017
 */
public class SimRace {
    private final int CARS = 5;
    private final int LAPS = 3;
    private Set<Car> cars;

    public static void main(String[] args) {
        SimRace race = new SimRace();
        for (int lap = 1; lap <= race.LAPS; lap++) {
            newLap();
        }
    }

    private static void newLap() {

    }

    private SimRace() {
        this.cars = new HashSet<>();
        for (int i = 1; i <= CARS; i++) {
            cars.add(new Car(i));
        }
    }
}
