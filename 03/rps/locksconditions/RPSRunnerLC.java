package rps.locksconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class RPSRunnerLC extends Thread {
	private static List<Thread> schedule = new ArrayList<>();
	private static ReentrantLock lock = new ReentrantLock();

	private final static int TIME = 10;
	static Table table = new Table();
	static PlayerLC player0 = new PlayerLC(table, 0, lock, schedule);
	static PlayerLC player1 = new PlayerLC(table, 1, lock, schedule);
	static RefereeLC referee = new RefereeLC(table, player0, player1, lock, schedule);

	public static void main(String[] args) {
		schedule.add(player0);
		schedule.add(player1);
		schedule.add(referee);
		player0.start();
		player1.start();
		referee.start();

		try {
			sleep(TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		player0.interrupt();
		player1.interrupt();
		referee.interrupt();
		evaluate();
	}

	private static void evaluate() {
		System.out.println("Evaluation!");
		referee.getResults().forEach((string, number) -> {
			System.out.println(string + number);
		});
	}
}
