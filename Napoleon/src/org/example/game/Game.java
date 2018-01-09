package org.example.game;

import java.util.*;

public class Game {

	public static Card playCard(Card[] playerHand, ArrayList<Card> pile) {
		Scanner scan_player = new Scanner(System.in);
		int index = 0;
		while (true) {
			System.out.println("Please choose a valid card (0-9):");
			index = scan_player.nextInt();
			if (pile.isEmpty()) {
				if (playerHand[index] != null) {
					Card temp = playerHand[index];
					playerHand[index] = null;
					return temp;
				} else
					System.out.println("Invalid selection");

			} else {
				// play appropriately
				String suit = pile.get(0).getString();
				Boolean flag = false;

				if ((playerHand[index] != null) && (playerHand[index].getString().equals(suit))) {
					Card temp = playerHand[index];
					playerHand[index] = null;
					return temp;
				}
				if (flag == false) {

					if (playerHand[index] != null) {
						Card temp = playerHand[index];
						playerHand[index] = null;
						return temp;
					} else {
						System.out.println("Invalid selection");
					}
				}
			}
		}
	}

	public static int finalCardValue(Card cardOriginal, Card cardToPlay, String suit) {
		int value = 0;
		Boolean pass = false;
		if (cardToPlay.getString().equals(suit)) {
			value = cardToPlay.getValue() + 200;
			return value;
		}
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

		// game logic
		Scanner scan = new Scanner(System.in);
		Scanner sc_string = new Scanner(System.in);
		Boolean start = true;
		int bid = 0;
		System.out.println("Welcome to Napoleon!\n");
		System.out.println("Player 1, here is your hand:\n");

		// print hand
		for (int v = 0; v < 10; v++) {
			System.out.println(v + ") " + player1H[v].toString());
		}
		System.out.println("");

		System.out.println("Player 1, please place your bets (8-16, 8 is default):");
		while (start) {
			try {
				bid = scan.nextInt();
				if (bid < 8 || bid > 16) {
					System.out.println("Player 1, please only bet from 8 to 16!");
				} else
					start = false;
			} catch (java.util.InputMismatchException e) {
				break;
			}
		}
		if (bid == 0)
			bid = 8;
		System.out.println("");
		System.out.println("Your bid is now: " + bid + ".");
		System.out.println("Bot 2 passed.");
		System.out.println("Bot 3 passed.");
		System.out.println("Bot 4 passed.");
		System.out.println("Bot 5 passed.\n");
		System.out.println("Player 1, you are the highest bidder.\n");
		System.out.println("Please call a trump suit (clubs, diamonds, hearts, spades):");
		String s = sc_string.nextLine();
		System.out.println("");
		System.out.println("Your extra cards are the following:\n");
		System.out.println("10) " + baggage[0].toString());
		System.out.println("11) " + baggage[1].toString());
		System.out.println("");
		System.out.println("Do you want to swap out any cards with the baggage (Y/N)? ");
		String swap = sc_string.nextLine();
		if (swap.equals("Y") || swap.equals("y")) {
			System.out.println("Do you want slot 10? (Y/N)");
			String ten = sc_string.nextLine();
			System.out.println("Do you want slot 11? (Y/N)");
			String eleven = sc_string.nextLine();
			int count = 0;
			int count1 = 0;
			if (ten.equals("Y") || ten.equals("y")) {
				count++;
			}
			if (eleven.equals("Y") || eleven.equals("y")) {
				count1++;
			}
			if (count > 0) {
				System.out.println("Which slot to remove (0-9)?");
				int delete = scan.nextInt();
				Card temp = player1H[delete];
				player1H[delete] = baggage[0];
				baggage[0] = temp;
			}
			if (count1 > 0) {
				System.out.println("Which slot to remove (0-9)?");
				int delete = scan.nextInt();
				Card temp1 = player1H[delete];
				player1H[delete] = baggage[1];
				baggage[1] = temp1;
			}
		}
		sortHand(player1H);
		System.out.println("Your new hand: ");

		Bot p2 = new Bot();
		Bot p3 = new Bot();
		Bot p4 = new Bot();
		Bot p5 = new Bot();
		ArrayList<Card> pile = new ArrayList<Card>();

		int max = 0;
		int playOrder = 1;
		for (int y = 0; y < 10; y++) {
			switch (playOrder) {
			case 1:
				for (int v = 0; v < 10; v++) {
					if (player1H[v] == null)
						System.out.println(v + ") null");
					else
						System.out.println(v + ") " + player1H[v].toString());
				}
				pile.add(playCard(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 1;
				}
				System.out.println("You played: " + pile.get(0));
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(1));
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(2));
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				System.out.println("bot 3 played: " + pile.get(3));
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(4));
				break;
			case 2:
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(0));
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(1));
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				System.out.println("bot 3 played: " + pile.get(2));
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(3));
				System.out.println("");
				for (int v = 0; v < 10; v++) {
					if (player1H[v] == null)
						System.out.println(v + ") null");
					else
						System.out.println(v + ") " + player1H[v].toString());
				}
				pile.add(playCard(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 1;
				}
				System.out.println("You played: " + pile.get(4));
				break;
			case 3:
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(0));
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				System.out.println("bot 3 played: " + pile.get(1));
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(2));
				System.out.println("");
				for (int v = 0; v < 10; v++) {
					if (player1H[v] == null)
						System.out.println(v + ") null");
					else
						System.out.println(v + ") " + player1H[v].toString());
				}
				pile.add(playCard(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 1;
				}
				System.out.println("You played: " + pile.get(3));
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(4));
				break;
			case 4:
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				System.out.println("bot 3 played: " + pile.get(0));
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(1));
				System.out.println("");
				for (int v = 0; v < 10; v++) {
					if (player1H[v] == null)
						System.out.println(v + ") null");
					else
						System.out.println(v + ") " + player1H[v].toString());
				}
				pile.add(playCard(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 1;
				}
				System.out.println("You played: " + pile.get(2));
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(3));
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(4));
				break;
			case 5:
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(0));
				System.out.println("");
				for (int v = 0; v < 10; v++) {
					if (player1H[v] == null)
						System.out.println(v + ") null");
					else
						System.out.println(v + ") " + player1H[v].toString());
				}
				pile.add(playCard(player1H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 1;
				}
				System.out.println("You played: " + pile.get(1));
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(2));
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(3));
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				System.out.println("bot 3 played: " + pile.get(4));
				break;
			}
			if (playOrder != 1)
				System.out.println("BOT " + (playOrder - 1) + " TAKES THE POT!");
			else
				System.out.println("PLAYER " + playOrder + " TAKES THE POT!");
			System.out.println("----------------------------");
			pile.clear();
			max = 0;
		}
		scan.close();
	}
}
