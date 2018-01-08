package org.example.game;

import java.util.*;

public class Game {
	
	//sorts a player's hand
	public static Card[] sortHand(Card[] hand) {
		int[] temp = new int[10];
		for (int k = 0; k < hand.length; k++) {
			int offset = 0;
			switch (hand[k].getString()) {
			case "clubs":
				offset = 0;
				temp[k] = hand[k].getValue() + offset;
				break;
			case "diamonds":
				offset = 13;
				temp[k] = hand[k].getValue() + offset;
				break;
			case "hearts":
				offset = 26;
				temp[k] = hand[k].getValue() + offset;
				break;
			case "spades":
				offset = 39;
				temp[k] = hand[k].getValue() + offset;
				break;
			}
		}
		Arrays.sort(temp);
		for (int i = 0; i < temp.length; i++) {
			int count = 0;
			int remainder = 0;
			count = temp[i] / 13;
			remainder = temp[i] % 13;
			if(remainder == 0)
				remainder = 13;
			switch (count) {
			case 0:
				hand[i].setValue(remainder);
				hand[i].setString("clubs");
				break;
			case 1:
				hand[i].setValue(remainder);
				hand[i].setString("diamonds");
				break;
			case 2:
				hand[i].setValue(remainder);
				hand[i].setString("hearts");
				break;
			case 3:
				hand[i].setValue(remainder);
				hand[i].setString("spades");
				break;
			}
		}
		return hand;
	}

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
		Card[] player1 = new Card[10];
		Card[] player2 = new Card[10];
		Card[] player3 = new Card[10];
		Card[] player4 = new Card[10];
		Card[] player5 = new Card[10];
		Card[] baggage = new Card[10];

		// deal
		int index = 0;
		while (deck.size() >= 3) {
			player1[index] = deck.remove(0);
			player2[index] = deck.remove(0);
			player3[index] = deck.remove(0);
			player4[index] = deck.remove(0);
			player5[index] = deck.remove(0);
			index++;
		}
		baggage[0] = deck.remove(0);
		baggage[1] = deck.remove(0);

		sortHand(player1);
		sortHand(player2);
		sortHand(player3);
		sortHand(player4);
		sortHand(player5);
		for (int v = 0; v < 10; v++) {
			System.out.printf("%s", player1[v].toString());
			System.out.print("\t");
			System.out.printf("%s", player2[v].toString());
			System.out.print("\t");
			System.out.printf("%s", player3[v].toString());
			System.out.print("\t");
			System.out.printf("%s", player4[v].toString());
			System.out.print("\t");
			System.out.printf("%s", player5[v].toString());
			System.out.println();
		}
		
		

		// game logic
	}
}
