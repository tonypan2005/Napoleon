package org.example.game;

import java.util.ArrayList;

public class Bot {

	public Card play(Card[] playerHand, ArrayList<Card> pile) {
		// play most left
		if (pile.isEmpty()) {
			int index = 0;
			for (int u = 0; u < 10; u++) {
				if (playerHand[index] == null) {
					index++;
				} else {
					Card temp = playerHand[index];
					playerHand[index] = null;
					return temp;
				}
			}
		} else {
			// play appropriately
			String suit = pile.get(0).getString();
			int max = 0;
			Boolean flag = false;
			for (int q = 0; q < playerHand.length; q++) {
				if ((playerHand[q] != null) && (playerHand[q].getString().equals(suit))) {
					Card temp = playerHand[q];
					playerHand[q] = null;
					return temp;
				} else {
					q++;
				}
			}
			if (flag == false) {
				int i = 0;
				for (int b = 0; b < 10; b++) {
					if (playerHand[i] == null) {
						i++;
					} else {
						Card temp = playerHand[i];
						playerHand[i] = null;
						return temp;
					}
				}
			}
			System.out.println("smile");
		}
		// end game
		Card c = new Card(0, "diamonds");
		return c;
	}

}
