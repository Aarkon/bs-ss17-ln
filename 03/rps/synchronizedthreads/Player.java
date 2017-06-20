package rps.synchronizedthreads;

/**
 * Plays Rock Paper Scissor by taking the role of the producer in a monitor
 * controlled producer-consumer scenario.
 */
public class Player extends Thread {
	private RPSType rps;
	private Table table;
	private int number;
	private Referee referee;

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
		System.out.println("player" + number + " starts playing");
		while (!isInterrupted()) {
			rps = RPSType.randomRockPaperScissor();
			table.add(rps, number);
			System.out.println("player" + number + " plays " + rps.toString());
			referee.playRound();
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

	public void setReferee(Referee referee) {
		this.referee = referee;
	}
}
