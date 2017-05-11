import java.util.List;
import java.util.Random;

/**
 * Jakob Ledig & Florian Nehmer, 4.5.2017
 */
public class Accident extends Thread {
	static boolean happened = false;
	private double chance;
	private int interval;
	private Random random = new Random();
	List<Car> cars;

	Accident(float chance, int interval, List<Car> cars) {
		this.cars = cars;
		this.chance = chance; // 0: Accident never happens, 1: happens instantly
		this.interval = interval;
	}

	Accident(List<Car> cars) {
		this.cars = cars;
		this.chance = 0.5;
		interval = 100;
	}

	@Override
	public synchronized void run() {
		boolean finished = false;
		do {
			happened = Math.random() < chance;
			try {
				sleep(random.nextInt(interval) * 2);
			} catch (InterruptedException e) {
				// e.printStackTrace()
				this.interrupt();;
			}
			if (happened) {
				System.out.println("Ein Crash ist geschehen!");
				for (Car car : cars) {
					car.interrupt();
				}
				break;
			}
			for (Car car : cars) {
				finished = car.laps >= SimRace.LAPS;
			}
		} while (!finished && !isInterrupted());
	}

}
