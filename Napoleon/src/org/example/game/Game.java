package org.example.game;

import java.util.*;

public class Game {

	public static void main(String[] args) {

		// set-up deck
		ArrayList<Card> deck = new ArrayList<Card>();
		for (int x = 0; x < 4; x++) {
			for (int y = 1; y <= 13; y++) {
				switch (x) {
				case 0:
					deck.add(new Card(y, "clubs"));
					break;
				case 1:
					deck.add(new Card(y, "diamonds"));
					break;
				case 2:
					deck.add(new Card(y, "hearts"));
					break;
				case 3:
					deck.add(new Card(y, "spades"));
					break;
				}
			}
		}
		// shuffles
		Collections.shuffle(deck);

		// set-up player hands
		ArrayList<Card> player1 = new ArrayList<Card>();
		ArrayList<Card> player2 = new ArrayList<Card>();
		ArrayList<Card> player3 = new ArrayList<Card>();
		ArrayList<Card> player4 = new ArrayList<Card>();
		ArrayList<Card> player5 = new ArrayList<Card>();
		ArrayList<Card> baggage = new ArrayList<Card>();

		// deal
		int index = 0;
		while (deck.size() >= 3) {
			player1.add(deck.remove(0));
			player2.add(deck.remove(0));
			player3.add(deck.remove(0));
			player4.add(deck.remove(0));
			player5.add(deck.remove(0));
			index++;

		}
		baggage.add(deck.remove(0));
		baggage.add(deck.remove(0));
		
		//hand print
		for (int x = 0; x < player1.size(); x++) {
			System.out.println(player1.get(x));
		}
		
		//game logic
	}
}
