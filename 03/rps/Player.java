package rps;

/**
 * Plays Rock Paper Scissor by taking the role of the producer in a monitor
 * controlled producer-consumer scenario.
 */
public class Player extends Thread {
	private RPSType rps;
	private Table table;
	private int number;

	/**
	 * Player representation.
	 *
	 * @param table
	 * @param number
	 *            When creating players, be sure only to use 0 and 1.
	 */
	public Player(Table table, int number) {
		this.table = table;
		this.number = number;
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			System.out.println("player main loop");
			rps = RPSType.randomRockPaperScissor();
			synchronized (table) {
				table.add(rps, number);
				table.notify();
			}
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Performs a comparison between another instance and determines the winner.
	 *
	 * @param other
	 * @return 1 if you win, -1 if the other wins, or 0 for a draw
	 */
	public int beats(Player other) {
		switch (this.rps) {
		case ROCK:
			if (other.getRPS() == RPSType.ROCK) {
				return 0;
			} else if (other.getRPS() == RPSType.PAPER) {
				return -1;
			} else {
				return 1;
			}
		case PAPER:
			if (other.getRPS() == RPSType.ROCK) {
				return 1;
			} else if (other.getRPS() == RPSType.PAPER) {
				return 0;
			} else {
				return -1;
			}
		case SCISSOR:
			if (other.getRPS() == RPSType.ROCK) {
				return -1;
			} else if (other.getRPS() == RPSType.PAPER) {
				return 1;
			} else {
				return 0;
			}
		default:
			return 0;
		}
	}

	public void setRPS(RPSType rps) {
		this.rps = rps;
	}

	public int getNumber() {
		return number;
	}

	public RPSType getRPS() {
		return rps;
	}
}
