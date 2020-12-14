package test;

import java.util.Scanner;

import game.PlayUno;

/**
 * Test without FX user interface. Check if simulation with computer opponent 
 * and human player (i.e. console entry) runs OK. 
 * 
 */
public class ShowOtherVersions {

	public static void main(String[] args) {
		PlayUno playuno = new PlayUno();
		System.out.println("If you want to play against 3 Computer: enter 1 \nIf you want to simulate the game with 3 computer: enter 2.");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		if (input == 1) {
			playuno.gameStarter(true);
		} else if (input == 2){
			playuno.gameStarter(false);
		}
//		playuno.gameStarter(false);
		playuno.playUno();
				
	}

}
