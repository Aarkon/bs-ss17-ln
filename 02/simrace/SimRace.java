import java.util.*;

/**
 * The main class for the thread race in BSP02. Jakob Ledig & Florian Nehmer,
 * 4.5.2017
 */
public class SimRace {
	private static final int CARS = 5;
	static final int LAPS = 3;

	public static void main(String[] args) throws InterruptedException {
		// Create the given number of cars corresponding to the CARS constant:
		List<Car> cars = new ArrayList<>();
		for (int i = 1; i <= CARS; i++) {
			cars.add(new Car(i));
		}
		// Parameterise the creation to make the accident happening more or less
		// likely:
		Accident accident = new Accident(cars);

		// Start threads:
		for (Car car : cars) {
			car.start();
		}
		accident.start();

		// Wait for cars to finish
		for (Car car : cars) {
			car.join();
		}
		accident.interrupt();
		if (!Accident.happened) {
			report(cars);
		}
	}

	static void report(List<Car> cars) {
		Collections.sort(cars);
		System.out.println("**** Endstand ****");
		for (int i = 1; i <= cars.size(); i++) {
			Car car = cars.get(i - 1);
			System.out.println(i + ". Platz: Wagen " + car.number + ", Zeit: " + car.raceTime);
		}

	}
}
