import java.util.Random;

/**
 * Class for the cars represented by threads that shall race each other. Jakob
 * Ledig & Florian Nehmer, 4.5.2017
 */
public class Car extends Thread implements Comparable<Car> {
	int number;
	int raceTime = 0;
	int laps = 0;
	private Random random = new Random();

	Car(int number) {
		this.number = number;
	}

	@Override
	public synchronized void run() {
		long lapTime = 0;
		// The cars only go the given number of laps and instantly stop if an
		// accident has happened:
		while (!isInterrupted() && this.laps < SimRace.LAPS) {
			lapTime = nextLap();
			try {
				sleep(lapTime);
			} catch (InterruptedException e) {
				this.interrupt();
			}
		}
		System.out.println("Wagen " + this.number + ": " + raceTime + ", Runde: " + this.laps);
	}
	
	private long nextLap() {
		laps++;
		long lapTime = random.nextInt(100);
		raceTime += lapTime;
		return lapTime;
	}

	/**
	 * A little comfort for sorting the cars in the list representing the race
	 * outcome: This method will compare two cars concerning their total race
	 * time, thus making any collection holding cars sortable by their race
	 * time.
	 *
	 * @param o
	 *            The other object (preferably of type car) to compare it's race
	 *            time to.
	 * @return The difference between the car's and the other's race time.
	 */
	@Override
	public int compareTo(Car other) {
		return this.raceTime - other.raceTime;
	}
}
