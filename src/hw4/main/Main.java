package hw4.main;

import hw4.game.Game;
import hw4.player.test.Player;

public class Main {

	public static void main(String[] args) {

		Game game = new Game(3);
		Player player = new Player(game.getGrid().getRows().get(2), game.getGrid().getRows().get(2).getCells().get(2));
		
		game.visualize(player);
		
	}

}
