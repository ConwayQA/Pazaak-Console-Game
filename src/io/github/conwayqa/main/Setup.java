package io.github.conwayqa.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Setup {
	
	//Method to create the deck in a form of a stack and shuffle it, ready for drawing
	public Stack<Integer> deckSetup() {
		Stack<Integer> deckCreation = new Stack<Integer>();
		for (int cardValue = 1; cardValue <= 10; cardValue++) {
			for (int cardCount = 1; cardCount <= 4; cardCount++) {
				deckCreation.push(cardValue);
			}
		}
		Collections.shuffle(deckCreation);
		return deckCreation;
	}
	
	//Method to create a randomised player hand for hand drawing
	public List<Integer> handSetup(){
		Stack<Integer> handDeck = new Stack<Integer>();
		for (int cardValue = -6; cardValue <= 6; cardValue++) {
			handDeck.push(cardValue);
		}
		Collections.shuffle(handDeck);
		List<Integer> handCreation = new ArrayList<Integer>();
		for (int handSize = 0; handSize < 4; handSize++) {
			handCreation.add(handDeck.pop());
		}
		return handCreation;
	}

}
