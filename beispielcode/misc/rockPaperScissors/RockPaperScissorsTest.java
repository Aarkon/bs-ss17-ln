package misc.rockPaperScissors;

import org.junit.Test;

public class RockPaperScissorsTest {
	private Game game = new Game();
	private GameV2 game2 = new GameV2();

	@Test
	public void testGame1000() {
		System.out.println("Game: 1000");
		game.play(1000);
	}

	@Test
	public void testGame21000() {
		System.out.println("GameV2: 1000");
		game2.play(1000);
	}

	@Test
	public void testGame10() {
		System.out.println("Game: 10");
		game.play(10);
	}

	@Test
	public void testGame210() {
		System.out.println("GameV2: 10");
		game.play(10);
	}
}
