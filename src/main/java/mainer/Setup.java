package mainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class Setup {
	
	//Method to create the deck in a form of a stack and shuffle it, ready for drawing
	@SuppressWarnings("unchecked")
	public Deque<Integer> deckSetup() {
		List<Integer> deckCreation = new ArrayList<>();
		for (int cardValue = 1; cardValue <= 10; cardValue++) {
			for (int cardCount = 1; cardCount <= 4; cardCount++) {
				deckCreation.add(cardValue);
			}
		}
		Collections.shuffle(deckCreation);
		Deque<Integer> deckMade = (Deque<Integer>) deckCreation;
		return deckMade;
	}
	
	//Method to create a randomised player hand for hand drawing
	public List<Integer> handSetup(){
		Stack<Integer> handDeck = new Stack<>();
		for (int cardValue = -6; cardValue <= 6; cardValue++) {
			handDeck.push(cardValue);
		}
		Collections.shuffle(handDeck);
		List<Integer> handCreation = new ArrayList<>();
		for (int handSize = 0; handSize < 4; handSize++) {
			handCreation.add(handDeck.pop());
		}
		return handCreation;
	}

}
