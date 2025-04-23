package hw4.main;

import java.util.Scanner;

import hw4.game.Game;
import hw4.player.Movement;
import hw4.player.test.Player;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		Game game = new Game(3);
		Player player = new Player(game.getGrid().getRows().get(2), game.getGrid().getRows().get(2).getCells().get(2));
		
		
		System.out.println("Welcome to our Maze.");
		game.visualize(player);
		
		//while the game is still going
		while(true) {
				
			Movement move = null;
			System.out.println("Enter a movement (up, down, right, left), or type quit to exit: ");
			String input = scanner.nextLine().trim().toUpperCase(); //converting input to match our components
			
			if (input.equals("QUIT")) {
				System.out.println("Exiting... Goodbye!");
				scanner.close();
				break;
			}
			
			try {
				move = Movement.valueOf(input); //getting the move from user
			} catch (IllegalArgumentException e) {
				System.out.println("Movement was not valid");
				continue;
			}
			
			boolean moved = game.play(move, player);
			if (moved != true) {
				System.out.println("You hit a wall or have an invalid move.");
			}
			
		}
		scanner.close();
		
		
	}

}
