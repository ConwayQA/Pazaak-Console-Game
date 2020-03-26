package io.github.conwayqa.main;

public class Runner {

	public static void main(String[] args) {
//		PlayGame test = new PlayGame();
		//test.testDraw();
		//test.testHandDraw();
//		test.setPlayer1Hand();
//		test.setPlayer2Hand();
//		test.setDeck();
//		test.addPlayer1Table();
//		test.addPlayer2Table();
//
//		test.addPlayer1Table();
//		test.addPlayer2Table();
//
//		test.addPlayer1Table();
//		test.addPlayer2Table();
//		test.printDisplay();
//		clearScreen();
		
		runGame();
		
	}
	
	public static void runGame() {
		PlayGame newGame = new PlayGame();
		newGame.setPlayer1Hand();
		newGame.setPlayer2Hand();
		newGame.setDeck();
		newGame.drawToTable();
		while (newGame.getPlayer1Score() < 3 && newGame.getPlayer2Score() < 3) {
			newGame.printDisplay();
			newGame.progressTurn();
		}
		newGame.endGame();
		newGame.closeScanner();
		
	}
	

}
