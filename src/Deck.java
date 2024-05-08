import java.util.*;

/**
 * Represents a deck of playing cards.
 */
public class Deck {

    /** The number of cards in a standard deck. */
    public static final int CARDS_IN_DECK = 52;

    /** The array containing the cards in the deck. */
    private Card[] cards;

    /** The index indicating the next card to be dealt from the deck. */
    private int next;

    /**
     * Constructs a new deck of cards, initializing it with all 52 cards in a standard deck.
     */
    public Deck() {
        cards = new Card[CARDS_IN_DECK];
        int index = 0;
        char suit = Card.CLUBS;
        for (int i = 0; i < 4; i++) {
            for (int value = Card.LOWEST_VALUE; value <= Card.HIGHEST_VALUE; value++) {
                cards[index] = new Card(suit, value);
                index++;
            }

            suit = getNextSuit(suit);
        }
        next = 0;
    }

    /**
     * Returns character of next suit
     * @param currentSuit the current suit of the card
     * @return
     */
    private char getNextSuit(char currentSuit) {
        switch (currentSuit) {
        case Card.CLUBS:
            return Card.DIAMONDS;
        case Card.DIAMONDS:
            return Card.SPADES;
        case Card.SPADES:
            return Card.HEARTS;
        case Card.HEARTS:
            return Card.CLUBS;
        default:
            throw new IllegalArgumentException("Invalid suit");
        }
    }

    /**
     * Returns the next available index in the deck.
     * @return the next available index in the deck
     */
    public int getNext() {
        return next;
    }

    /**
     * Returns the array of cards in the deck.
     * @return the array of cards in the deck
     */
    public Card[] getCards() {
        return cards;
    }

    /**
     * Shuffles the cards in the deck.
     */
    public void shuffle() {
        Random rand = new Random();
        for (int i = CARDS_IN_DECK - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    /**
     * Initializes the deck by marking all cards as not played and resetting the next card index.
     */
    public void initialize() {
        for (Card card: cards) {
            card.setPlayed(false);
        }
        next = 0;
    }

    /**
     * Draws the next card from the deck.
     * @return the next card from the deck
     * @throws IllegalStateException if there are no more cards in the deck
     */
    public Card nextCard() {
        if (next >= CARDS_IN_DECK) {
            throw new IllegalStateException("No more cards");
        }
        return cards[next++];
    }

    /**
     * Checks if this deck is equal to another object.
     * @param ob the object to compare to
     * @return true if the object is a Deck with the same cards and next index, false otherwise
     */
    public boolean equals(Object ob) {
        if (this == ob) {
            return true;
        }
        if (!(ob instanceof Deck)) {
            return false;
        }
        Deck other = (Deck) ob;
        return (next == other.next && java.util.Arrays.equals(cards, other.cards));
    }

    /**
     * Returns a string representation of the deck, listing all the cards.
     * @return a string containing the details of each card in the deck
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < cards.length; i++) {
            result += "card " + i + ": " + cards[i].getSuit() + cards[i].getValue() + "\n";
        }
        return result;
    }

}