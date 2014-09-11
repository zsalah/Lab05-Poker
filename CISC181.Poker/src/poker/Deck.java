package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck {
	private ArrayList<Card> cards; // Attribute of a Deck

	public Deck() {
		// Constructs a deck of 52 cards and randomizes their order
		ArrayList<Card> MakingDeck = new ArrayList<Card>();
		for (short i = 0; i <= 3; i++) {
			for (short j = 0; j <= 12; j++) {
				Card ToAdd = new Card((short) i, (short) j, j + 13 * i);
				MakingDeck.add(ToAdd);
			}
		}
		// Shuffles the cards order
		cards = MakingDeck;
		Collections.shuffle(cards, new Random());
	}

	public Card drawFromDeck() {
		// Removes the first card from the deck and return the card
		Card FirstCard = cards.get(0);
		cards.remove(0);
		return FirstCard;
	}

	public int getTotalCards() {
		// Returns the total number of cards still in the deck
		return cards.size();
	}
}