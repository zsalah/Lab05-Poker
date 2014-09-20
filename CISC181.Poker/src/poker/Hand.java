package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand {
	private ArrayList<Card> cards;

	private int HandStrength;
	private int HiHand;
	private int LoHand;
	private int Kicker;
	private boolean bScored = false;

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
	
	public Hand(ArrayList<Card> SeededHand) {
		cards = SeededHand;
	}	

	public ArrayList<Card> getCards() {
		return cards;
	}

	public int getHandStrength() {
		return HandStrength;
	}

	public int getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		ArrayList<Card> Sorted;

		// Sort the cards!
		Collections.sort(cards, Card.CardRank);

		// Ace Evaluation
		if (cards.get(4).getRank() == eRank.ACE) {
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
			if (cards.get(0).getRank() == eRank.TEN
					&& cards.get(1).getRank() == eRank.JACK
					&& cards.get(2).getRank() == eRank.QUEEN
					&& cards.get(3).getRank() == eRank.KING) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (cards.get(0).getRank() == eRank.TWO
					&& cards.get(1).getRank() == eRank.THREE
					&& cards.get(2).getRank() == eRank.FOUR
					&& cards.get(3).getRank() == eRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
		} else if (cards.get(0).getRank().getRank() == cards.get(1).getRank()
				.getRank() + 1
				&& cards.get(0).getRank().getRank() == cards.get(2).getRank()
						.getRank() + 2
				&& cards.get(0).getRank().getRank() == cards.get(3).getRank()
						.getRank() + 3
				&& cards.get(0).getRank().getRank() == cards.get(4).getRank()
						.getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// Evaluates the hand type
		if (Straight == true && Flush == true
				&& cards.get(4).getRank() == eRank.ACE && Ace) {
			ScoreHand(eHandStrength.RoyalFlush, 0, 0, 0);
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			ScoreHand(eHandStrength.StraightFlush, cards.get(0).getRank().getRank(), 0, 0);
		}
		// Four of a Kind

		else if (cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(0).getRank() == cards.get(2).getRank()
				&& cards.get(0).getRank() == cards.get(3).getRank()) {
			ScoreHand(eHandStrength.FourOfAKind, cards.get(0).getRank().getRank(), 0, cards.get(4)
					.getRank().getRank());
		}

		else if (cards.get(4).getRank() == cards.get(1).getRank()
				&& cards.get(4).getRank() == cards.get(2).getRank()
				&& cards.get(4).getRank() == cards.get(3).getRank()) {
			ScoreHand(eHandStrength.FourOfAKind, cards.get(4).getRank().getRank(), 0, cards.get(0)
					.getRank().getRank());
		}

		// Full House
		else if (cards.get(0).getRank() == cards.get(2).getRank()
				&& cards.get(3).getRank() == cards.get(4).getRank()) {
			ScoreHand(eHandStrength.FullHouse, cards.get(0).getRank().getRank(), cards.get(3)
					.getRank().getRank(), 0);
		}

		else if (cards.get(2).getRank() == cards.get(4).getRank()
				&& cards.get(0).getRank() == cards.get(1).getRank()) {
			ScoreHand(eHandStrength.FullHouse, cards.get(2).getRank().getRank(), cards.get(0)
					.getRank().getRank(), 0);
		}

		// Flush
		else if (Flush) {
			ScoreHand(eHandStrength.Flush, cards.get(0).getRank().getRank(), 0, 0);
		}

		// Straight
		else if (Straight) {
			ScoreHand(eHandStrength.Straight, cards.get(0).getRank().getRank(), 0, 0);
		}

		// Three of a Kind
		else if (cards.get(0).getRank() == cards.get(2).getRank()) {
			ScoreHand(eHandStrength.ThreeOfAKind, cards.get(0).getRank().getRank(), 0, cards.get(3)
					.getRank().getRank());
		}

		else if (cards.get(1).getRank() == cards.get(3).getRank()) {
			ScoreHand(eHandStrength.ThreeOfAKind, cards.get(1).getRank().getRank(), 0, cards.get(4)
					.getRank().getRank());
		} else if (cards.get(2).getRank() == cards.get(4).getRank()) {
			ScoreHand(eHandStrength.ThreeOfAKind, cards.get(2).getRank().getRank(), 0, cards.get(0)
					.getRank().getRank());
		}

		// Two Pair
		else if (cards.get(0).getRank() == cards.get(1).getRank()
				&& (cards.get(2).getRank() == cards.get(3).getRank())) {
			ScoreHand(eHandStrength.TwoPair, cards.get(0).getRank().getRank(), cards.get(2)
					.getRank().getRank(), cards.get(4).getRank().getRank());
		} else if (cards.get(0).getRank() == cards.get(1).getRank()
				&& (cards.get(3).getRank() == cards.get(4).getRank())) {
			ScoreHand(eHandStrength.TwoPair, cards.get(0).getRank().getRank(), cards.get(3)
					.getRank().getRank(), cards.get(2).getRank().getRank());
		} else if (cards.get(1).getRank() == cards.get(2).getRank()
				&& (cards.get(3).getRank() == cards.get(4).getRank())) {
			ScoreHand(eHandStrength.TwoPair, cards.get(1).getRank().getRank(), cards.get(3)
					.getRank().getRank(), cards.get(0).getRank().getRank());
		}

		// Pair
		else if (cards.get(0).getRank() == cards.get(1).getRank()) {
			ScoreHand(eHandStrength.Pair, cards.get(0).getRank().getRank(), 0, cards.get(2)
					.getRank().getRank());
		} else if (cards.get(1).getRank() == cards.get(2).getRank()) {
			ScoreHand(eHandStrength.Pair, cards.get(1).getRank().getRank(), 0, cards.get(0)
					.getRank().getRank());
		} else if (cards.get(2).getRank() == cards.get(3).getRank()) {
			ScoreHand(eHandStrength.Pair, cards.get(2).getRank().getRank(), 0, cards.get(0)
					.getRank().getRank());
		} else if (cards.get(3).getRank() == cards.get(4).getRank()) {
			ScoreHand(eHandStrength.Pair, cards.get(3).getRank().getRank(), 0, cards.get(0)
					.getRank().getRank());
		}

		else {
			ScoreHand(eHandStrength.HighCard, cards.get(0).getRank().getRank(), 0, cards.get(1)
					.getRank().getRank());
		}
	}

	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, int Kicker) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kicker = Kicker;
		this.bScored = true;

	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.HandStrength - h1.HandStrength;

			if (result != 0) {
				return result;
			}
			
			result = h2.HiHand - h1.HiHand;
			if (result != 0) {
				return result;
			}
			

			result = h2.LoHand = h1.LoHand;
			if (result != 0) {
				return result;
			}

			result = h2.Kicker = h1.Kicker;
			if (result != 0) {
				return result;
			}

			return 0;
		}
	};
}
