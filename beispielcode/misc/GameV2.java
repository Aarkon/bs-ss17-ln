package misc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class GameV2 extends Thread {
	private List<Thread> waitList = new ArrayList<Thread>();
	Player player1 = new Player();
	Player player2 = new Player();
	Referee referee = new Referee();
	private ReentrantLock lock = new ReentrantLock();
	private List<Shape> shapes = new ArrayList<Shape>();

	public static void main(String[] args) throws IOException {
		GameV2 game = new GameV2();
		game.play(100);
	}

	public void play(long time) {
		shapes.add(Shape.ROCK);
		shapes.add(Shape.PAPER);
		shapes.add(Shape.SCISSOR);
		waitList.add(player1);
		waitList.add(player2);
		waitList.add(referee);
		player1.start();
		player2.start();
		referee.start();
		try {
			sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player1.interrupt();
		player2.interrupt();
		referee.interrupt();
	}

	private class Referee extends Thread {
		private int draw = 0;
		private int games = 0;

		@Override
		public void run() {
			while (!isInterrupted()) {
				if (waitList.get(0) == this) {
					lock.lock();
					waitList.add(waitList.remove(0));
					int winner = getWinner(player1.shape, player2.shape);
					if (winner == 0) {
						games++;
						draw++;
					} else {
						if (winner > 0) {
							player1.win++;
							games++;
						} else {
							player2.win++;
							games++;
						}
					}
					lock.unlock();
				}
			}
			getResults();
		}

		private void getResults() {
			System.out.println("Player 1 won " + player1.win + ".");
			System.out.println("Player 2 won " + player2.win + ".");
			System.out.println("And " + referee.draw + " of the " + referee.games + " games were draws.");

		}

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

	private class Player extends Thread {
		private Shape shape;
		private int win = 0;

		@Override
		public void run() {
			while (!isInterrupted()) {
				if (waitList.get(0) == this) {
					lock.lock();
					waitList.add(waitList.remove(0));
					Random rn = new Random();
					shape = shapes.get(rn.nextInt(shapes.size()));
					lock.unlock();
				}
			}
		}
	}

}
