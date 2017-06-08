package rps.locksconditions;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import rps.synchronizedthreads.RPSType;
import rps.synchronizedthreads.Table;

public class PlayerLC extends Thread {

	private ReentrantLock lock;
	private List<Thread> schedule;
	private Table table;
	private int number;

	public PlayerLC(Table table, int number, ReentrantLock lock, List<Thread> schedule) {
		this.table = table;
		this.number = number;
		this.lock = lock;
		this.schedule = schedule;
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			if (schedule.get(0) == this) {
				lock.lock();
				// reschedule all tokens but the just used one:
				schedule.add(schedule.remove(0));
				RPSType rps = RPSType.randomRockPaperScissor();
				System.out.println("player" + number + " plays " + rps.toString());
				table.add(rps, this.number);
				lock.unlock();
			}
		}
	}
}