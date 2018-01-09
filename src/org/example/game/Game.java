package org.example.game;

import java.util.*;

public class Game {

	public static int finalCardValue(Card cardOriginal, Card cardToPlay) {
		int value = 0;
		Boolean pass = false;

		if (cardOriginal.getString().equals(cardToPlay.getString()))
			pass = true;
		switch (cardToPlay.getString()) {
		case "clubs":
			int off_s = 0;
			value = cardToPlay.getValue() + off_s;
			break;
		case "diamonds":
			off_s = 13;
			value = cardToPlay.getValue() + off_s;
			break;
		case "hearts":
			off_s = 26;
			value = cardToPlay.getValue() + off_s;
			break;
		case "spades":
			off_s = 39;
			value = cardToPlay.getValue() + off_s;
			break;
		}
		if (pass)
			return value;
		else
			return -1;
	}

	// sorts a player's hand
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
			count = (temp[i] - 2) / 13;
			remainder = temp[i] % 13;
			if (remainder == 0)
				remainder = 13;
			if (remainder == 1)
				remainder = 14;
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
			for (int y = 2; y <= 14; y++) {
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
		Card[] player1H = new Card[10];
		Card[] player2H = new Card[10];
		Card[] player3H = new Card[10];
		Card[] player4H = new Card[10];
		Card[] player5H = new Card[10];
		Card[] baggage = new Card[10];

		// deal
		int index = 0;
		while (deck.size() >= 3) {
			player1H[index] = deck.remove(0);
			player2H[index] = deck.remove(0);
			player3H[index] = deck.remove(0);
			player4H[index] = deck.remove(0);
			player5H[index] = deck.remove(0);
			index++;
		}
		baggage[0] = deck.remove(0);
		baggage[1] = deck.remove(0);

		sortHand(player1H);
		sortHand(player2H);
		sortHand(player3H);
		sortHand(player4H);
		sortHand(player5H);

		// print
		for (int v = 0; v < 10; v++) {
			System.out.printf("%-20s", player1H[v].toString());
			System.out.print("\t");
			System.out.printf("%-20s", player2H[v].toString());
			System.out.print("\t");
			System.out.printf("%-20s", player3H[v].toString());
			System.out.print("\t");
			System.out.printf("%-20s", player4H[v].toString());
			System.out.print("\t");
			System.out.printf("%-20s", player5H[v].toString());
			System.out.println();
		}

		// game logic
		Bot p1 = new Bot(1);
		Bot p2 = new Bot(2);
		Bot p3 = new Bot(3);
		Bot p4 = new Bot(4);
		Bot p5 = new Bot(5);
		ArrayList<Card> pile = new ArrayList<Card>();

		int max = 0;
		// final int TRUMP = 100;
		// int bid = 0;
		int playOrder = 1;
		for (int y = 0; y < 10; y++) {
			switch (playOrder) {
			case 1:
				pile.add(p1.play(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 1;
				}
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 2;
				}
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 3;
				}
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 4;
				}
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 5;
				}
				break;
			case 2:
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 2;
				}
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 3;
				}
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 4;
				}
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 5;
				}
				pile.add(p1.play(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 1;
				}
				break;
			case 3:
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 3;
				}
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 4;
				}
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 5;
				}
				pile.add(p1.play(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 1;
				}
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 2;
				}
				break;
			case 4:
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 4;
				}
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 5;
				}
				pile.add(p1.play(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 1;
				}
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 2;
				}
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 3;
				}
				break;
			case 5:
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 5;
				}
				pile.add(p1.play(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 1;
				}
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 2;
				}
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 3;
				}
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1))) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1));
					playOrder = 4;
				}
				break;
			}
			System.out.println(pile.get(0));
			System.out.println(pile.get(1));
			System.out.println(pile.get(2));
			System.out.println(pile.get(3));
			System.out.println(pile.get(4));
			pile.clear();
			max = 0;

			// Card c = new Card(5, "diamonds");
			// System.out.println(finalCardValue(c));
		}
	}
}
