/**
 * Represents a playing card with a suit and a value.
 * @author Praneel Magapu
 */
public class Card implements Comparable < Card > {

    /** Represents the suit Clubs */
    public static final char CLUBS = 'c';

    /** Represents the suit Diamonds */
    public static final char DIAMONDS = 'd';

    /** Represents the suit Hearts */
    public static final char HEARTS = 'h';

    /** Represents the suit Spades */
    public static final char SPADES = 's';

    /** The lowest value a card can have */
    public static final int LOWEST_VALUE = 2;

    /** The highest value a card can have */
    public static final int HIGHEST_VALUE = 14;

    /** The value of Queen card */
    public static final int QUEEN_VALUE = 12;

    /** The suit of the card */
    private char suit = '\0';

    /** The value of the card */
    private int value = 0;

    /** Indicates whether the card has been played */
    private boolean hasBeenPlayed = false;

    /**
     * Constructs a new Card object with the specified suit and value.
     * @param suit The suit of the card
     * @param value The value of the card
     * @throws IllegalArgumentException if the suit or value is invalid
     */
    public Card(char suit, int value) {
        if (suit == CLUBS || suit == DIAMONDS || suit == HEARTS || suit == SPADES) {
            this.suit = suit;
        } else {
            throw new IllegalArgumentException("Invalid suit");
        }

        if (value >= LOWEST_VALUE && value <= HIGHEST_VALUE) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Invalid value");
        }

        this.hasBeenPlayed = false;
    }

    /**
     * Gets the suit of the card.
     * @return The suit of the card
     */
    public char getSuit() {
        return suit;
    }

    /**
     * Gets the value of the card.
     * @return The value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets whether the card has been played.
     * @return True if the card has been played, false otherwise
     */
    public boolean hasBeenPlayed() {
        return hasBeenPlayed;
    }

    /**
     * Sets whether the card has been played.
     * @param played True if the card has been played, false otherwise
     */
    public void setPlayed(boolean played) {
        this.hasBeenPlayed = played;
    }

    /**
     * Checks if the card is a Heart.
     * @return True if the card is a Heart, false otherwise
     */
    public boolean isHeart() {
        return suit == HEARTS;
    }

    /**
     * Checks if the card is the Queen of Spades.
     * @return True if the card is the Queen of Spades, false otherwise
     */
    public boolean isQueenOfSpades() {
        return suit == SPADES && value == 12;
    }

    /**
     * Checks if the card is higher than another card.
     * @param other The other card to compare
     * @return True if this card is higher than the other card, false otherwise
     */
    public boolean isHigherThan(Card other) {
        return suit == other.suit && value > other.value;
    }

    /**
     * Checks if this card is equal to another object.
     * @param o The object to compare
     * @return True if the objects are equal, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card other = (Card) o;
        return suit == other.suit && value == other.value && hasBeenPlayed == other.hasBeenPlayed;
    }

    /**
     * Returns a string representation of the card.
     * @return The string representation of the card
     */
    public String toString() {
        return suit + Integer.toString(value);
    }

    /**
     * Compares this card with another card for order.
     * @param other The other card to compare
     * @return A negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the specified card
     */
    public int compareTo(Card other) {
        if (getSuit() == other.getSuit()) {
            if (getValue() < other.getValue()) {
                return -1;
            } else if (getValue() > other.getValue()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            switch (getSuit()) {
            case HEARTS:
                return 1;
            case SPADES:
                if (other.getSuit() == HEARTS) {
                    return -1;
                } else {
                    return 1;
                }
            case DIAMONDS:
                if (other.getSuit() == HEARTS || other.getSuit() == SPADES) {
                    return -1;
                } else {
                    return 1;
                }
                //CLUBS
            default:
                return -1;
            }
        }
    }
}