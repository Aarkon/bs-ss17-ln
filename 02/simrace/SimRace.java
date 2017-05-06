import java.util.*;

/**
 * The main class for the thread race in BSP02.
 * Jakob Ledig & Florian Nehmer, 4.5.2017
 */
public class SimRace {
    private static final int CARS = 5;
    static final int LAPS = 3;

    public static void main(String[] args) {
        // Create the given number of cars corresponding to the CARS constant:
        List<Car> cars = new ArrayList<>();
        for (int i = 1; i <= CARS; i++) {
            cars.add(new Car(i));
        }

        // Start threads:
        for (Car car : cars) {
            car.start();
        }

        // Wait for cars to finish
        boolean finished = false;
        while (!finished) {
            finished = true;
            for (Car car : cars) {
                if (car.laps < LAPS) {
                    finished = false;
                }
            }
        }
        report(cars);
    }

    private static void report(List<Car> cars) {
        Collections.sort(cars);
        System.out.println("**** Endstand ****");
        for (int i =  1; i <= cars.size(); i++ ) {
            Car car = cars.get(i - 1);
            System.out.println(i + ". Platz: Wagen " + car.number + ", Zeit: " + car.raceTime);
        }

    }
}
