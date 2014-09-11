package poker;

public class Card {
	private short CardSuit; // Attribute of a Card
	private short CardRank; // Attribute of a Card
	private int CardNumber; // Attribute of a Card
	private static String[] Suits = { "Spades", "Hearts", "Diamonds", "Clubs" };
	private static String[] Ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "Jack", "Queen", "King" };

	public Card(short suit, short rank, int number) {
		// Constructor for a card. Card takes a number for suit, a number for
		// rank and a number for number
		CardSuit = suit; // Set the card's suit parameter to the suit
		CardRank = rank; // Set the card's rank parameter to the rank
		CardNumber = number; // Set the card's number (1-52)
	}

	public short getRank() {
		// Method returns the index of the card's rank (Ace = 0)
		return CardRank;
	}

	public short getSuit() {
		// Method returns the index of the card's suit (Spades = 0)
		return CardSuit;
	}

	public int getNumber() {
		// Method returns the card's number
		return CardNumber;
	}

}