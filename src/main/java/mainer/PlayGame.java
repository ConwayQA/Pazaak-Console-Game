package mainer;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class PlayGame {
	String lineBreak = "=====================================================================";
	
	private Scanner checkInput = new Scanner(System.in);
	
	//Declare and initialise all game variables
	private int player1Score = 0;
	private int player2Score = 0;
	private int player1TableTotal = 0;
	private int player2TableTotal = 0;
	private List<Integer> player1Hand = new ArrayList<>();
	private List<Integer>  player2Hand = new ArrayList<>();
	private List<Integer> player1Table = new ArrayList<>();
	private List<Integer> player2Table = new ArrayList<>();
	private Deque<Integer> gameDeck;
	private boolean player1Stand = false;
	private boolean player2Stand = false;
	private boolean player1Turn = true;
	
	//getter for player1Score
	public int getPlayer1Score() {
		return player1Score;
	}
	
	//getter for player2Score
	public int getPlayer2Score() {
		return player2Score;
	}
	
	//creates a new deck for the game
	public void setDeck(){
		Setup deckFetcher = new Setup();
		gameDeck = deckFetcher.deckSetup();
	}
	
	//creates a new hand for player 1 the game
	public void setPlayer1Hand(){
		Setup handFetcher = new Setup();
		player1Hand = handFetcher.handSetup();
	}
	
	//creates a new hand for player 2 the game
	public void setPlayer2Hand(){
		Setup handFetcher = new Setup();
		player2Hand = handFetcher.handSetup();
	}
	
	//Draw card and add it to player's Table depending on whos turn it is
	public void drawToTable() {
		if (player1Turn) {
			int cardDraw = gameDeck.pop();
			player1TableTotal += cardDraw;
			player1Table.add(cardDraw);
		} else {
			int cardDraw = gameDeck.pop();
			player2TableTotal += cardDraw;
			player2Table.add(cardDraw);
		}
		
	}
	
	//plays a card from the hand of player 1
	public void playPlayer1Table(int cardNum){
		player1TableTotal += player1Hand.get(cardNum);
		player1Table.add(player1Hand.get(cardNum));
		player1Hand.remove(cardNum);
	}
	
	//plays a card from the hand of player 2
	public void playPlayer2Table(int cardNum){
		player2TableTotal += player2Hand.get(cardNum);
		player2Table.add(player2Hand.get(cardNum));
		player2Hand.remove(cardNum);
	}
		
	//Method for choice to end turn or stand after playing card
		public void endOrStand() {
			clearScreen();
			System.out.println(lineBreak);
			System.out.println("Player 1 =-  Score: " + player1Score + "   |||   Hand: " + listIntToString(player1Hand));
			System.out.println("Player 2 =-  Score: " + player2Score + "   |||   Hand: " + listIntToString(player2Hand));
			System.out.println(lineBreak);
			System.out.println("Player 1's Board: ");
			System.out.println("|                     " + listIntToString(player1Table));
			System.out.println("Total: " + player1TableTotal);
			System.out.println(lineBreak);
			System.out.println("Player 2's Board: ");
			System.out.println("|                     " + listIntToString(player2Table));
			System.out.println("Total: " + player2TableTotal);
			System.out.println(lineBreak);
			if (player1Turn) {
				System.out.println("Player 1! Choose an option:");
			} else {
				System.out.println("Player 2! Choose an option:");
			}
			System.out.println("[1] End your turn!          [2] Stand!");
			int playerInput = checkInput.nextInt();
			checkInput.nextLine();
			while (playerInput <= 0 || playerInput > 2) {
				System.out.println("Please choose a valid number from above: (1, 2, 3, 4, 5, 6)");
				playerInput = checkInput.nextInt();
				checkInput.nextLine();
			}
			switch (playerInput) {
			case 1:
				if (player1Turn) {
					player1Turn = false;
				} else {
					player1Turn = true;
				}
				break;
			case 2:
				if (player1Turn) {
					player1Stand = true;
					player1Turn = false;
				} else {
					player2Stand = true;
					player1Turn = true;
				}
				break;
			}
		}
		
	//run the steps required for a turn to progress
	public void progressTurn() {
		int playerInput = checkInput.nextInt();
		checkInput.nextLine();
		boolean valid = false;
		while ((playerInput <= 0 || playerInput > 6) && valid == false) {
			System.out.println("Please choose a valid number from above: (1, 2, 3, 4, 5, 6)");
			playerInput = checkInput.nextInt();
			checkInput.nextLine();
			if (player1Turn) {
				if (playerInput < player1Hand.size()) {
					valid = true;
				}
			} else {
				if (playerInput < player2Hand.size()) {
					valid = true;
				}
			}
		}
		switch (playerInput) {
		case 1:
		case 2:
		case 3:
		case 4:
			if (player1Turn) {
				playPlayer1Table(playerInput - 1);
			} else {
				playPlayer2Table(playerInput - 1);
			}
			endOrStand();
			break;
		case 5:
			if (player1Turn) {
				player1Turn = false;
			} else {
				player1Turn = true;
			}
			break;
		case 6:
			if (player1Turn) {
				player1Stand = true;
				player1Turn = false;
			} else {
				player2Stand = true;
				player1Turn = true;
			}
			break;
		}
		if (player1TableTotal > 20 || player1Stand == true) {
			player1Stand = true;
			player1Turn = false;
		}
		if (player2TableTotal > 20 || player2Stand == true) {
			player2Stand = true;
			player1Turn = true;
		}
		clearScreen();
		if (player1Stand == true && player2Stand == true) {
			endSet();
		} else {
			drawToTable();
		}

	}
	
	//displays the current game progress
	public void printDisplay() {
		System.out.println(lineBreak);
		System.out.println("Player 1 =-  Score: " + player1Score + "   |||   Hand: " + listIntToString(player1Hand));
		System.out.println("Player 2 =-  Score: " + player2Score + "   |||   Hand: " + listIntToString(player2Hand));
		System.out.println(lineBreak);
		System.out.println("Player 1's Board: ");
		System.out.println("|                     " + listIntToString(player1Table));
		System.out.println("Total: " + player1TableTotal);
		System.out.println(lineBreak);
		System.out.println("Player 2's Board: ");
		System.out.println("|                     " + listIntToString(player2Table));
		System.out.println("Total: " + player2TableTotal);
		System.out.println(lineBreak);
		if (player1Turn) {
			System.out.println("Player 1! Choose an option:");
		} else {
			System.out.println("Player 2! Choose an option:");
		}
		System.out.println("[1] Play card 1!          [5] End your turn!");
		System.out.println("[2] Play card 2!          [6] Stand!");
		System.out.println("[3] Play card 3!");
		System.out.println("[4] Play card 4!");
	}
	
	//Converts an array into a String for output
	public String listIntToString(List<Integer> tempList) {
		String listString = "";
		for (Integer i : tempList) {
			listString += (Integer.toString(i) + " ");
		}
		return listString;
	}
	
	//runs end of set code, see who won that set, if anyone and adjust their scores to match.
	public void endSet() {
		System.out.println(lineBreak);
		System.out.println("Player 1: " + player1TableTotal);
		System.out.println("Player 2: " + player2TableTotal);
		System.out.println(lineBreak);
		
		if (player1TableTotal > player2TableTotal && player1TableTotal <= 20) {
			System.out.println("Player 1 wins the set! With a hand of: " + player1TableTotal);
			player1Score++;
		} else if (player1TableTotal > player2TableTotal && player2TableTotal <= 20) {
			System.out.println("Player 2 wins the set! With a hand of: " + player2TableTotal);
			player2Score++;
		} else if (player2TableTotal > player1TableTotal && player2TableTotal <= 20) {
			System.out.println("Player 2 wins the set! With a hand of: " + player2TableTotal);
			player2Score++;
		} else if (player2TableTotal > player1TableTotal && player1TableTotal <= 20) {
			System.out.println("Player 1 wins the set! With a hand of: " + player1TableTotal);
			player1Score++;
		} else if (player1TableTotal > 20 && player2TableTotal > 20) {
			System.out.println("Set ends in a tie! Both players went bust!");
		} else if (player1TableTotal == player2TableTotal) {
			System.out.println("Set ends in a tie! with both players hands totalling: " + player1TableTotal);
		}
		System.out.println(lineBreak);
		
		System.out.println("");
		System.out.println("Press Enter to continue to next set!..");
		checkInput.nextLine();
		
		player1Table.clear();
		player2Table.clear();
		player1TableTotal = 0;
		player2TableTotal = 0;
		player1Stand = false;
		player2Stand = false;
		player1Turn = true;
		drawToTable();
	}
	
	//Prints end of game results
	public void endGame() {
		clearScreen();
		System.out.println(lineBreak);
		System.out.println("Player 1's Score: " + player1Score);
		System.out.println("Player 2's Score: " + player2Score);
		System.out.println(lineBreak);
		if (player1Score >= 3) {
			System.out.println("Player 1 wins!");
		} else {
			System.out.println("Player 2 wins!");
		}
		System.out.println("");
		System.out.println("Press Enter to end game...");
		checkInput.nextLine();
	}
	
	//Prints 30 blank lines to clear the console screen
	public void clearScreen() {
		for(int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
	
	public void closeScanner() {
		checkInput.close();
	}

}
