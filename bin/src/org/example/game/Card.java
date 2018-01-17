package org.example.game;

//alphabetical - "clubs, diamonds, hearts, spades" (+0, +13, +26, +39)
public class Card {
	private int value;
	private String text;

	public Card(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String getString() {
		return text;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setString(String text) {
		this.text = text;
	}

	public String toString() {
		switch (value) {
		case 0:
			return "null";
		case 2:
			return "two of " + text;
		case 3:
			return "three of " + text;
		case 4:
			return "four of " + text;
		case 5:
			return "five of " + text;
		case 6:
			return "six of " + text;
		case 7:
			return "seven of " + text;
		case 8:
			return "eight of " + text;
		case 9:
			return "nine of " + text;
		case 10:
			return "ten of " + text;
		case 11:
			return "jack of " + text;
		case 12:
			return "queen of " + text;
		case 13:
			return "king of " + text;
		case 14:
			return "ace of " + text;
		}
		return "toString() error";
	}
}
