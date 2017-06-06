package misc.rockPaperScissors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends Thread {
	private Referee referee;
	private List<Shape> shapes = new ArrayList<Shape>();

	/**
	 * Starts a Game RockPaperScissors which ends after the handed time in ms.
	 * 
	 * @param time
	 */
	public void play(long time) {
		shapes.add(Shape.ROCK);
		shapes.add(Shape.PAPER);
		shapes.add(Shape.SCISSOR);
		referee = new Referee();
		referee.start();
		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		referee.interrupt();
	}

	private class Player extends Thread {
		private Shape shape = Shape.PAPER;
		private int wins = 0;

		@Override
		public void run() {
			while (!isInterrupted()) {
				synchronized (this) {
					Random rn = new Random();
					shape = shapes.get(rn.nextInt(shapes.size()));
					this.notify();
				}
			}
		}
	}

	private class Referee extends Thread {
		private int draws = 0;
		private int games = 0;
		Player player1 = new Player();
		Player player2 = new Player();

		@Override
		public void run() {
			player1.start();
			player2.start();
			while (!isInterrupted()) {
				synchronized (player1) {
					try {
						player1.wait();
					} catch (InterruptedException e) {
						this.interrupt();
					}
				}
				synchronized (player2) {
					try {
						player2.wait();
					} catch (InterruptedException e) {
						this.interrupt();
					}
					int winner = getWinner(player1.shape, player2.shape);
					if (winner == 0) {
						games++;
						draws++;
					} else {
						if (winner > 0) {
							player1.wins++;
							games++;
						} else {
							player2.wins++;
							games++;
						}
					}
				}

			}
			player1.interrupt();
			player2.interrupt();
			getResults();
		}

		private void getResults() {
			System.out.println("Player 1 won " + player1.wins + ".");
			System.out.println("Player 2 won " + player2.wins + ".");
			System.out.println("And " + referee.draws + " of the " + referee.games + " games were draws.");

		}

		/**
		 * 
		 * @param player1
		 * @param player2
		 * @return Returns 0 if it's a tie 1 if player 1 won and -1 if player 2
		 *         won.
		 * 
		 */
		private int getWinner(Shape player1, Shape player2) {
			if (player1 == player2) {
				return 0;
			} else {
				switch (player1) {
				case ROCK:
					if (player2 == Shape.SCISSOR) {
						return 1;
					} else {
						return -1;
					}
				case SCISSOR:
					if (player2 == Shape.PAPER) {
						return 1;
					} else {
						return -1;
					}
				case PAPER:
					if (player2 == Shape.ROCK) {
						return 1;
					} else {
						return -1;
					}
				default:
					return 0;
				}
			}
		}
	}
}
