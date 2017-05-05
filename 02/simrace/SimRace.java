import java.util.HashSet;
import java.util.Set;

/**
 * The main class for the thread race in BSP02.
 * Jakob Ledig & Florian Nehmer, 4.5.2017
 */
public class SimRace {
    public static final int CARS = 5;
    public static final int LAPS = 3;

    public static void main(String[] args) {
        // Create the given number of cars:
        Set<Car> cars = new HashSet<>();
        for (int i = 1; i <= CARS; i++) {
            cars.add(new Car(i));
        }

        // Start threads:
        for (Car car : cars) {
            System.out.println(car.number);
            car.start();
        }

        // Wait for cars to finish
        boolean finished = false;
        while (!finished) {
            for (Car car : cars) {
                finished = car.laps >= LAPS;
            }
        }
        System.out.println("***Endstand***");
    }
}
