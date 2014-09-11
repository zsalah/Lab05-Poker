package poker;

import java.util.ArrayList;


public class Hand {
	private ArrayList<Card> cards;
	private int HandStrength;
	private int HighPairStrength;
	private int LowPairStrength;
	private int Kicker;
	private boolean Flush;
	private boolean Straight;
	private boolean Ace;

	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		cards = Import;
	}

	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes
		ArrayList<Card> Sorted = new ArrayList<Card>();
				
		for (int x = 12; x >= 0; x--) {
			for (int i = 0; i <= 4; i++) {
				if (cards.get(i).getRank() == x) {
					Sorted.add(cards.get(i));
				}
			}
		}
		cards = Sorted;

		// Ace Evaluation
		if (cards.get(4).getRank() == (short) 0) {
			Ace = true;
		}

		// Flush Evaluation
		if (cards.get(0).getSuit() == cards.get(1).getSuit()
				&& cards.get(0).getSuit() == cards.get(2).getSuit()
				&& cards.get(0).getSuit() == cards.get(3).getSuit()
				&& cards.get(0).getSuit() == cards.get(4).getSuit()) {
			Flush = true;
		} else {
			Flush = false;
		}

		// Straight Evaluation
		if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (cards.get(0).getRank() == (short) 12
					&& cards.get(1).getRank() == (short) 11
					&& cards.get(2).getRank() == (short) 10
					&& cards.get(3).getRank() == (short) 9) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (cards.get(0).getRank() == (short) 4
					&& cards.get(1).getRank() == (short) 3
					&& cards.get(2).getRank() == (short) 2
					&& cards.get(3).getRank() == (short) 1) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
		} else if (cards.get(0).getRank() == cards.get(1).getRank() + 1
				&& cards.get(0).getRank() == cards.get(2).getRank() + 2
				&& cards.get(0).getRank() == cards.get(3).getRank() + 3
				&& cards.get(0).getRank() == cards.get(4).getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// Evaluates the hand type
			// Rolyal Flush
		if (Straight == true && Flush == true && cards.get(0).getRank() == 12
				&& Ace) {
			HandStrength = 10;
			// Straight Flush
		} else if (Straight == true && Flush == true) {
			HandStrength = 9;
			Kicker = cards.get(0).getRank();
			// Four of a Kind
		} else if (cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(0).getRank() == cards.get(2).getRank()
				&& cards.get(0).getRank() == cards.get(3).getRank()) {
			HandStrength = 8;
			HighPairStrength = cards.get(0).getRank();
			Kicker = cards.get(4).getRank();
		} else if (cards.get(4).getRank() == cards.get(1).getRank()
				&& cards.get(4).getRank() == cards.get(2).getRank()
				&& cards.get(4).getRank() == cards.get(3).getRank()) {
			HandStrength = 8;
			HighPairStrength = cards.get(4).getRank();
			Kicker = cards.get(0).getRank();
			// Full House
		} else if (cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(0).getRank() == cards.get(2).getRank()
				&& cards.get(3).getRank() == cards.get(4).getRank()) {
			HandStrength = 7;
			HighPairStrength = cards.get(0).getRank();
			LowPairStrength = cards.get(4).getRank();
		} else if (cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(2).getRank() == cards.get(4).getRank()
				&& cards.get(3).getRank() == cards.get(4).getRank()) {
			HandStrength = 7;
			HighPairStrength = cards.get(0).getRank();
			LowPairStrength = cards.get(4).getRank();
			// Flush
		} else if (Flush) {
			HandStrength = 6;
			Kicker = cards.get(0).getRank();
			// Straight 
		} else if (Straight) {
			HandStrength = 5;
			Kicker = cards.get(0).getRank();
			// Three of a Kind
		} else if (cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(0).getRank() == cards.get(2).getRank()) {
			HandStrength = 4;
			HighPairStrength = cards.get(0).getRank();
			Kicker = cards.get(3).getRank();
		} else if (cards.get(1).getRank() == cards.get(2).getRank()
				&& cards.get(1).getRank() == cards.get(3).getRank()) {
			HandStrength = 4;
			HighPairStrength = cards.get(1).getRank();
			Kicker = cards.get(0).getRank();
		} else if (cards.get(2).getRank() == cards.get(3).getRank()
				&& cards.get(2).getRank() == cards.get(4).getRank()) {
			HandStrength = 4;
			HighPairStrength = cards.get(2).getRank();
			Kicker = cards.get(0).getRank();
			// Two Pair
		} else if (cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(2).getRank() == cards.get(3).getRank()){
			HandStrength = 3;
			HighPairStrength = cards.get(0).getRank();
			LowPairStrength = cards.get(2).getRank();
			Kicker = cards.get(4).getRank();
		} else if (cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(3).getRank() == cards.get(4).getRank()){
			HandStrength = 3;
			HighPairStrength = cards.get(0).getRank();
			LowPairStrength = cards.get(3).getRank();
			Kicker = cards.get(2).getRank();
		} else if (cards.get(1).getRank() == cards.get(2).getRank()
				&& cards.get(3).getRank() == cards.get(4).getRank()){
			HandStrength = 3;
			HighPairStrength = cards.get(1).getRank();
			LowPairStrength = cards.get(3).getRank();
			Kicker = cards.get(0).getRank();
			// Pair
		} else if (cards.get(0).getRank() == cards.get(1).getRank()){
			HandStrength = 2;
			HighPairStrength = cards.get(0).getRank();
			Kicker = cards.get(2).getRank();
		}  else if (cards.get(1).getRank() == cards.get(2).getRank()){
			HandStrength = 2;
			HighPairStrength = cards.get(1).getRank();
			Kicker = cards.get(0).getRank();
		}  else if (cards.get(2).getRank() == cards.get(3).getRank()){
			HandStrength = 2;
			HighPairStrength = cards.get(2).getRank();
			Kicker = cards.get(0).getRank();
		}  else if (cards.get(3).getRank() == cards.get(4).getRank()){
			HandStrength = 2;
			HighPairStrength = cards.get(3).getRank();
			Kicker = cards.get(0).getRank();
			// High Card
		} else {
			HandStrength = 1;
			Kicker = cards.get(0).getRank();
		}
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}

	public int getHandStrength() {
		return HandStrength;
	}

	public int getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HighPairStrength;
	}

	public int getLowPairStrength() {
		return LowPairStrength;
	}
	
	public boolean getAce() {
		return Ace;
	}
}