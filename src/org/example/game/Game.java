package org.example.game;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Game extends Application {

//	private Text message = new Text();
//	private SimpleBooleanProperty isPlayable = new SimpleBooleanProperty(false);
//	private HBox p1 = new HBox(20);
//	private HBox p2 = new HBox(20);
//	private HBox p3 = new HBox(20);
//	private HBox p4 = new HBox(20);
//	private HBox p5 = new HBox(20);

	static Scanner scan_player = new Scanner(System.in);

	public static Card playCard(Card[] playerHand, ArrayList<Card> pile) {

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
						if (playerHand[index].getString().equals(suit)) {
							Card temp = playerHand[index];
							playerHand[index] = null;
							return temp;
						} else {
							Boolean fl = true;
							for (int r = 0; r < 10; r++) {
								if (playerHand[r] == null) {
									r++;
								} else {
									if (playerHand[r].getString().equals(suit)) {
										fl = false;
									}
								}
							}
							if (fl) {
								Card temp = playerHand[index];
								playerHand[index] = null;
								return temp;
							} else
								System.out.println("Invalid selection");
						}
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

	private Parent createContent() {
		Image card1 = new Image("org/example/game/two of clubs.png");
		ImageView temp1 = new ImageView(card1);
		temp1.setFitHeight(75);
		temp1.setFitWidth(75);
		temp1.setPreserveRatio(true);

		Image card2 = new Image("org/example/game/two of diamonds.png");
		ImageView temp2 = new ImageView(card2);
		temp2.setFitHeight(75);
		temp2.setFitWidth(75);
		temp2.setPreserveRatio(true);

		Image card3 = new Image("org/example/game/two of hearts.png");
		ImageView temp3 = new ImageView(card3);
		temp3.setFitHeight(75);
		temp3.setFitWidth(75);
		temp3.setPreserveRatio(true);

		Image card4 = new Image("org/example/game/two of spades.png");
		ImageView temp4 = new ImageView(card4);
		temp4.setFitHeight(75);
		temp4.setFitWidth(75);
		temp4.setPreserveRatio(true);

		Image card5 = new Image("org/example/game/three of clubs.png");
		ImageView temp5 = new ImageView(card5);
		temp5.setFitHeight(75);
		temp5.setFitWidth(75);
		temp5.setPreserveRatio(true);

		Image card6 = new Image("org/example/game/three of diamonds.png");
		ImageView temp6 = new ImageView(card6);
		temp6.setFitHeight(75);
		temp6.setFitWidth(75);
		temp6.setPreserveRatio(true);

		Image card7 = new Image("org/example/game/three of hearts.png");
		ImageView temp7 = new ImageView(card7);
		temp7.setFitHeight(75);
		temp7.setFitWidth(75);
		temp7.setPreserveRatio(true);

		Image card8 = new Image("org/example/game/three of spades.png");
		ImageView temp8 = new ImageView(card8);
		temp8.setFitHeight(75);
		temp8.setFitWidth(75);
		temp8.setPreserveRatio(true);

		Image card9 = new Image("org/example/game/four of clubs.png");
		ImageView temp9 = new ImageView(card9);
		temp9.setFitHeight(75);
		temp9.setFitWidth(75);
		temp9.setPreserveRatio(true);

		Image card10 = new Image("org/example/game/four of diamonds.png");
		ImageView temp10 = new ImageView(card10);
		temp10.setFitHeight(75);
		temp10.setFitWidth(75);
		temp10.setPreserveRatio(true);

		Image card11 = new Image("org/example/game/four of hearts.png");
		ImageView temp11 = new ImageView(card11);
		temp11.setFitHeight(75);
		temp11.setFitWidth(75);
		temp11.setPreserveRatio(true);

		Image card12 = new Image("org/example/game/four of spades.png");
		ImageView temp12 = new ImageView(card12);
		temp12.setFitHeight(75);
		temp12.setFitWidth(75);
		temp12.setPreserveRatio(true);

		Image card13 = new Image("org/example/game/five of clubs.png");
		ImageView temp13 = new ImageView(card13);
		temp13.setFitHeight(75);
		temp13.setFitWidth(75);
		temp13.setPreserveRatio(true);

		Image card14 = new Image("org/example/game/five of diamonds.png");
		ImageView temp14 = new ImageView(card14);
		temp14.setFitHeight(75);
		temp14.setFitWidth(75);
		temp14.setPreserveRatio(true);

		Image card15 = new Image("org/example/game/five of hearts.png");
		ImageView temp15 = new ImageView(card15);
		temp15.setFitHeight(75);
		temp15.setFitWidth(75);
		temp15.setPreserveRatio(true);

		Image card16 = new Image("org/example/game/five of spades.png");
		ImageView temp16 = new ImageView(card16);
		temp16.setFitHeight(75);
		temp16.setFitWidth(75);
		temp16.setPreserveRatio(true);

		Image card17 = new Image("org/example/game/six of clubs.png");
		ImageView temp17 = new ImageView(card17);
		temp17.setFitHeight(75);
		temp17.setFitWidth(75);
		temp17.setPreserveRatio(true);

		Image card18 = new Image("org/example/game/six of diamonds.png");
		ImageView temp18 = new ImageView(card18);
		temp18.setFitHeight(75);
		temp18.setFitWidth(75);
		temp18.setPreserveRatio(true);

		Image card19 = new Image("org/example/game/six of hearts.png");
		ImageView temp19 = new ImageView(card19);
		temp19.setFitHeight(75);
		temp19.setFitWidth(75);
		temp19.setPreserveRatio(true);

		Image card20 = new Image("org/example/game/six of spades.png");
		ImageView temp20 = new ImageView(card20);
		temp20.setFitHeight(75);
		temp20.setFitWidth(75);
		temp20.setPreserveRatio(true);

		Image card21 = new Image("org/example/game/seven of clubs.png");
		ImageView temp21 = new ImageView(card21);
		temp21.setFitHeight(75);
		temp21.setFitWidth(75);
		temp21.setPreserveRatio(true);

		Image card22 = new Image("org/example/game/seven of diamonds.png");
		ImageView temp22 = new ImageView(card22);
		temp22.setFitHeight(75);
		temp22.setFitWidth(75);
		temp22.setPreserveRatio(true);

		Image card23 = new Image("org/example/game/seven of hearts.png");
		ImageView temp23 = new ImageView(card23);
		temp23.setFitHeight(75);
		temp23.setFitWidth(75);
		temp23.setPreserveRatio(true);

		Image card24 = new Image("org/example/game/seven of spades.png");
		ImageView temp24 = new ImageView(card24);
		temp24.setFitHeight(75);
		temp24.setFitWidth(75);
		temp24.setPreserveRatio(true);

		Image card25 = new Image("org/example/game/eight of clubs.png");
		ImageView temp25 = new ImageView(card25);
		temp25.setFitHeight(75);
		temp25.setFitWidth(75);
		temp25.setPreserveRatio(true);

		Image card26 = new Image("org/example/game/eight of diamonds.png");
		ImageView temp26 = new ImageView(card26);
		temp26.setFitHeight(75);
		temp26.setFitWidth(75);
		temp26.setPreserveRatio(true);

		Image card27 = new Image("org/example/game/eight of hearts.png");
		ImageView temp27 = new ImageView(card27);
		temp27.setFitHeight(75);
		temp27.setFitWidth(75);
		temp27.setPreserveRatio(true);

		Image card28 = new Image("org/example/game/eight of spades.png");
		ImageView temp28 = new ImageView(card28);
		temp28.setFitHeight(75);
		temp28.setFitWidth(75);
		temp28.setPreserveRatio(true);

		Image card29 = new Image("org/example/game/nine of clubs.png");
		ImageView temp29 = new ImageView(card29);
		temp29.setFitHeight(75);
		temp29.setFitWidth(75);
		temp29.setPreserveRatio(true);

		Image card30 = new Image("org/example/game/nine of diamonds.png");
		ImageView temp30 = new ImageView(card30);
		temp30.setFitHeight(75);
		temp30.setFitWidth(75);
		temp30.setPreserveRatio(true);

		Image card31 = new Image("org/example/game/nine of hearts.png");
		ImageView temp31 = new ImageView(card31);
		temp31.setFitHeight(75);
		temp31.setFitWidth(75);
		temp31.setPreserveRatio(true);

		Image card32 = new Image("org/example/game/nine of spades.png");
		ImageView temp32 = new ImageView(card32);
		temp32.setFitHeight(75);
		temp32.setFitWidth(75);
		temp32.setPreserveRatio(true);

		Image card33 = new Image("org/example/game/ten of clubs.png");
		ImageView temp33 = new ImageView(card33);
		temp33.setFitHeight(75);
		temp33.setFitWidth(75);
		temp33.setPreserveRatio(true);

		Image card34 = new Image("org/example/game/ten of diamonds.png");
		ImageView temp34 = new ImageView(card34);
		temp34.setFitHeight(75);
		temp34.setFitWidth(75);
		temp34.setPreserveRatio(true);

		Image card35 = new Image("org/example/game/ten of hearts.png");
		ImageView temp35 = new ImageView(card35);
		temp35.setFitHeight(75);
		temp35.setFitWidth(75);
		temp35.setPreserveRatio(true);

		Image card36 = new Image("org/example/game/ten of spades.png");
		ImageView temp36 = new ImageView(card36);
		temp36.setFitHeight(75);
		temp36.setFitWidth(75);
		temp36.setPreserveRatio(true);

		Image card37 = new Image("org/example/game/jack of clubs.png");
		ImageView temp37 = new ImageView(card37);
		temp37.setFitHeight(75);
		temp37.setFitWidth(75);
		temp37.setPreserveRatio(true);

		Image card38 = new Image("org/example/game/jack of diamonds.png");
		ImageView temp38 = new ImageView(card38);
		temp38.setFitHeight(75);
		temp38.setFitWidth(75);
		temp38.setPreserveRatio(true);

		Image card39 = new Image("org/example/game/jack of hearts.png");
		ImageView temp39 = new ImageView(card39);
		temp39.setFitHeight(75);
		temp39.setFitWidth(75);
		temp39.setPreserveRatio(true);

		Image card40 = new Image("org/example/game/jack of spades.png");
		ImageView temp40 = new ImageView(card40);
		temp40.setFitHeight(75);
		temp40.setFitWidth(75);
		temp40.setPreserveRatio(true);

		Image card41 = new Image("org/example/game/queen of clubs.png");
		ImageView temp41 = new ImageView(card41);
		temp41.setFitHeight(75);
		temp41.setFitWidth(75);
		temp41.setPreserveRatio(true);

		Image card42 = new Image("org/example/game/queen of diamonds.png");
		ImageView temp42 = new ImageView(card42);
		temp42.setFitHeight(75);
		temp42.setFitWidth(75);
		temp42.setPreserveRatio(true);

		Image card43 = new Image("org/example/game/queen of hearts.png");
		ImageView temp43 = new ImageView(card43);
		temp43.setFitHeight(75);
		temp43.setFitWidth(75);
		temp43.setPreserveRatio(true);

		Image card44 = new Image("org/example/game/queen of spades.png");
		ImageView temp44 = new ImageView(card44);
		temp44.setFitHeight(75);
		temp44.setFitWidth(75);
		temp44.setPreserveRatio(true);

		Image card45 = new Image("org/example/game/king of clubs.png");
		ImageView temp45 = new ImageView(card45);
		temp45.setFitHeight(75);
		temp45.setFitWidth(75);
		temp45.setPreserveRatio(true);

		Image card46 = new Image("org/example/game/king of diamonds.png");
		ImageView temp46 = new ImageView(card46);
		temp46.setFitHeight(75);
		temp46.setFitWidth(75);
		temp46.setPreserveRatio(true);

		Image card47 = new Image("org/example/game/king of hearts.png");
		ImageView temp47 = new ImageView(card47);
		temp47.setFitHeight(75);
		temp47.setFitWidth(75);
		temp47.setPreserveRatio(true);

		Image card48 = new Image("org/example/game/king of spades.png");
		ImageView temp48 = new ImageView(card48);
		temp48.setFitHeight(75);
		temp48.setFitWidth(75);
		temp48.setPreserveRatio(true);

		Image card49 = new Image("org/example/game/ace of clubs.png");
		ImageView temp49 = new ImageView(card49);
		temp49.setFitHeight(75);
		temp49.setFitWidth(75);
		temp49.setPreserveRatio(true);

		Image card50 = new Image("org/example/game/ace of diamonds.png");
		ImageView temp50 = new ImageView(card50);
		temp50.setFitHeight(75);
		temp50.setFitWidth(75);
		temp50.setPreserveRatio(true);

		Image card51 = new Image("org/example/game/ace of hearts.png");
		ImageView temp51 = new ImageView(card51);
		temp51.setFitHeight(75);
		temp51.setFitWidth(75);
		temp51.setPreserveRatio(true);

		Image card52 = new Image("org/example/game/ace of spades.png");
		ImageView temp52 = new ImageView(card52);
		temp52.setFitHeight(75);
		temp52.setFitWidth(75);
		temp52.setPreserveRatio(true);

		Pane root = new Pane();
		root.setStyle("-fx-background-color: black;");
		root.setPrefSize(1000, 700);
		HBox rootLayout = new HBox();
		rootLayout.setPadding(new Insets(5, 5, 5, 5));

		Rectangle board = new Rectangle(987, 665);
		board.setArcWidth(50);
		board.setArcHeight(50);
		board.setFill(Color.GREEN);

		StackPane sPane = new StackPane();

		HBox hBoxTop = new HBox();
		HBox hBoxBottom = new HBox();
		HBox hBoxLeft = new HBox();
		HBox hBoxRight = new HBox();
		Text centerText = new Text("center");
		Text fillText = new Text("                             ");

		hBoxTop.getChildren().addAll(temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13);
		hBoxTop.setSpacing(-15);
		hBoxTop.setAlignment(Pos.TOP_CENTER);

		hBoxLeft.getChildren().addAll(temp17, temp18, temp19, temp20, temp21, temp22, temp23, temp24, temp25, temp26);
		hBoxLeft.setRotate(90);
		hBoxLeft.setSpacing(-15);
		hBoxLeft.setAlignment(Pos.BOTTOM_LEFT);
		hBoxLeft.setPadding(new Insets(0, 0, 100, 0));

		hBoxRight.getChildren().addAll(temp30, temp31, temp32, temp33, temp34, temp35, temp36, temp37, temp38, temp39);
		hBoxRight.setRotate(-90);
		hBoxRight.setSpacing(-15);
		hBoxRight.setAlignment(Pos.BOTTOM_LEFT);
		hBoxRight.setPadding(new Insets(0, 0, 100, 0));

		hBoxBottom.getChildren().addAll(temp40, temp41, temp42, temp43, temp44, temp45, temp46, temp47, temp48, temp49,
				temp50, fillText, temp51, temp52, temp27, temp28, temp29, temp14, temp15, temp16, temp1, temp2, temp3);
		hBoxBottom.setSpacing(-15);
		hBoxBottom.setAlignment(Pos.BOTTOM_CENTER);

		BorderPane b = new BorderPane(centerText, hBoxTop, hBoxRight, hBoxBottom, hBoxLeft);

		sPane.getChildren().addAll(board, b);
		rootLayout.getChildren().addAll(sPane);
		root.getChildren().addAll(rootLayout);
		return root;
	}

	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene(createContent()));
		mainStage.setWidth(1000);
		mainStage.setHeight(700);
		mainStage.setResizable(false);
		mainStage.setTitle("Napoleon");
		mainStage.show();
	}

	public static void main(String[] args) {
		launch(args);

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
		for (int o = 0; o < 10; o++) {
			System.out.println(o + ") " + player1H[o].toString());
		}
		System.out.println("Who do you want as your secretary (2-12, 13(King), 14(Ace):");
		int secretary = scan.nextInt();

		Bot p2 = new Bot();
		Bot p3 = new Bot();
		Bot p4 = new Bot();
		Bot p5 = new Bot();
		ArrayList<Card> pile = new ArrayList<Card>();

		int max = 0;
		int playOrder = 1;
		int sec_team = 0;
		int point1 = 0;
		int point2 = 0;
		int point3 = 0;
		int point4 = 0;
		int point5 = 0;
		int tempCount = 0;
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
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is player1 himself/herself.");
					sec_team = 1;
				}
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(1));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 1.");
					sec_team = 2;
				}
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(2));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 2.");
					sec_team = 3;
				}
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				System.out.println("bot 3 played: " + pile.get(3));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 3.");
					sec_team = 4;
				}
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(4));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 4.");
					sec_team = 5;
				}
				break;
			case 2:
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(0));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 1.");
					sec_team = 2;
				}
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(1));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 2.");
					sec_team = 3;
				}
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				System.out.println("bot 3 played: " + pile.get(2));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 3.");
					sec_team = 4;
				}
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(3));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 4.");
					sec_team = 5;
				}
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
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is player1 himself/herself.");
					sec_team = 1;
				}
				break;
			case 3:
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(0));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 2.");
					sec_team = 3;
				}
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				System.out.println("bot 3 played: " + pile.get(1));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 3.");
					sec_team = 4;
				}
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(2));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 4.");
					sec_team = 5;
				}
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
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is player1 himself/herself.");
					sec_team = 1;
				}
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(4));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 1.");
					sec_team = 2;
				}
				break;
			case 4:
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 3.");
					sec_team = 4;
				}
				System.out.println("bot 3 played: " + pile.get(0));
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(1));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 4.");
					sec_team = 5;
				}
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
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is player1 himself/herself.");
					sec_team = 1;
				}
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(3));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 1.");
					sec_team = 2;
				}
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(4));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 2.");
					sec_team = 3;
				}
				break;
			case 5:
				pile.add(p5.play(player5H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 5;
				}
				System.out.println("bot 4 played: " + pile.get(0));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 4.");
					sec_team = 5;
				}
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
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is player1 himself/herself.");
					sec_team = 1;
				}
				pile.add(p2.play(player2H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 2;
				}
				System.out.println("bot 1 played: " + pile.get(2));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 1.");
					sec_team = 2;
				}
				pile.add(p3.play(player3H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 3;
				}
				System.out.println("bot 2 played: " + pile.get(3));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 2.");
					sec_team = 3;
				}
				pile.add(p4.play(player4H, pile));
				if (max < finalCardValue(pile.get(0), pile.get(pile.size() - 1), s)) {
					max = finalCardValue(pile.get(0), pile.get(pile.size() - 1), s);
					playOrder = 4;
				}
				System.out.println("bot 3 played: " + pile.get(4));
				if (pile.get(pile.size() - 1).getValue() == secretary
						&& pile.get(pile.size() - 1).getString().equals(s)) {
					System.out.println("Secretary revealed! It is bot 3.");
					sec_team = 4;
				}
				break;
			}
			if (playOrder != 1)
				System.out.println("BOT " + (playOrder - 1) + " TAKES THE POT!");
			else
				System.out.println("PLAYER " + playOrder + " TAKES THE POT!");
			System.out.println("----------------------------");
			for (int m = 0; m < 5; m++) {
				if (pile.get(m).getValue() == 11 || pile.get(m).getValue() == 12 || pile.get(m).getValue() == 13
						|| pile.get(m).getValue() == 14)
					tempCount++;
			}
			switch (playOrder) {
			case 1:
				point1 += tempCount;
				break;
			case 2:
				point2 += tempCount;
				break;
			case 3:
				point3 += tempCount;
				break;
			case 4:
				point4 += tempCount;
				break;
			case 5:
				point5 += tempCount;
				break;
			}
			tempCount = 0;
			pile.clear();
			max = 0;
		}
		switch (sec_team) {
		case 1:
			break;
		case 2:
			point1 += point2;
			break;
		case 3:
			point1 += point3;
			break;
		case 4:
			point1 += point4;
			break;
		case 5:
			point1 += point5;
			break;
		}
		System.out.println("Score: " + point1);
		if (point1 >= bid)
			System.out.println("The player and his secretary wins!");
		else
			System.out.println("The player and his secretary loses!");
		scan.close();
		sc_string.close();
		scan_player.close();
	}
}
