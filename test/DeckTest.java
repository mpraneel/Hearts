import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Deck class
 * @author Jessica Young Schmidt
 * @author Praneel Magapu
 */
public class DeckTest {

    /** Deck for testing */
    private Deck deck;

    /**
     * Sets up fields for testing
     */
    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    /**
     * Test nextCard for the first card
     */
    @Test
    public void testNextCardA() {
        assertEquals(new Card('c', 2), deck.nextCard(), "Test first card");
    }

    @Test
    public void testNextCardB() {
        deck.nextCard(); // First call
        deck.nextCard(); // Second call
        assertEquals(new Card('c', 4), deck.nextCard(), "Test third card");
    }

    /**
     * Tests that exception is thrown for next card when no cards are left
     */
    @Test
    public void testNextCardException() {
        for (int i = 0; i < 52; i++) {
            deck.nextCard();
        }

        Exception exception = assertThrows(IllegalStateException.class, () -> deck.nextCard(),
            "no cards left");
        assertEquals("No more cards", exception.getMessage(), "No more cards - exception message");
    }

    @Test
    public void testGetNextA() {
        assertEquals(0, deck.getNext(), "test initial value of next");
    }

    @Test
    public void testGetNextB() {
        deck.nextCard(); // First call
        deck.nextCard(); // Second call
        assertEquals(2, deck.getNext(), "test value of next after two calls to nextCard()");
    }

    @Test
    public void testGetCards() {
        Card[] cards = deck.getCards();
        int index = 0;
        for (int j = 2; j <= 14; j++) {
            assertEquals(cards[index++], new Card(Card.CLUBS, j));
        }
        for (int j = 2; j <= 14; j++) {
            assertEquals(cards[index++], new Card(Card.DIAMONDS, j));
        }
        for (int j = 2; j <= 14; j++) {
            assertEquals(cards[index++], new Card(Card.SPADES, j));
        }
        for (int j = 2; j <= 14; j++) {
            assertEquals(cards[index++], new Card(Card.HEARTS, j));
        }

    }

    /**
     * Test toString
     */
    @Test
    public void testToString() {
        String expected = "card 0: c2\n" + "card 1: c3\n" + "card 2: c4\n" + "card 3: c5\n" +
            "card 4: c6\n" + "card 5: c7\n" + "card 6: c8\n" + "card 7: c9\n" +
            "card 8: c10\n" + "card 9: c11\n" + "card 10: c12\n" + "card 11: c13\n" +
            "card 12: c14\n" + "card 13: d2\n" + "card 14: d3\n" + "card 15: d4\n" +
            "card 16: d5\n" + "card 17: d6\n" + "card 18: d7\n" + "card 19: d8\n" +
            "card 20: d9\n" + "card 21: d10\n" + "card 22: d11\n" + "card 23: d12\n" +
            "card 24: d13\n" + "card 25: d14\n" + "card 26: s2\n" + "card 27: s3\n" +
            "card 28: s4\n" + "card 29: s5\n" + "card 30: s6\n" + "card 31: s7\n" +
            "card 32: s8\n" + "card 33: s9\n" + "card 34: s10\n" + "card 35: s11\n" +
            "card 36: s12\n" + "card 37: s13\n" + "card 38: s14\n" + "card 39: h2\n" +
            "card 40: h3\n" + "card 41: h4\n" + "card 42: h5\n" + "card 43: h6\n" +
            "card 44: h7\n" + "card 45: h8\n" + "card 46: h9\n" + "card 47: h10\n" +
            "card 48: h11\n" + "card 49: h12\n" + "card 50: h13\n" + "card 51: h14\n";
        assertEquals(expected, deck.toString(), "Test toString after constructing deck");
    }

    /**
     * Test initialize method
     */
    @Test
    public void testInitialize() {
        Card nextCard = deck.nextCard();
        nextCard.setPlayed(true);
        assertEquals(1, deck.getNext(), "dealt 1 card");
        assertTrue(nextCard.hasBeenPlayed(), "first card of deck played");
        deck.initialize();
        assertEquals(0, deck.getNext(), "initialize resets next to 0");
        assertFalse(nextCard.hasBeenPlayed(), "initialize resets hasBeenPlayed to false");
    }

    /**
     * Test that shuffle results in different deck
     */
    @Test
    public void testShuffle() {
        Deck deck2 = new Deck();
        deck2.shuffle();
        assertFalse(deck.equals(deck2), "Test that shuffle results in different deck");
    }

    /**
     * Test equals with equal decks
     */
    @Test
    public void testEqualsA() {
        Deck deck2 = new Deck();
        assertTrue(deck.equals(deck2), "Test equal decks");
    }

    @Test
    public void testEqualsB() {
        Deck deck2 = new Deck();
        deck2.nextCard(); // Call nextCard on deck2
        assertFalse(deck.equals(deck2), "Test equal decks with different next values");
    }

    @Test
    public void testEqualsC() {
        Deck deck2 = new Deck();
        deck2.shuffle(); // Shuffle deck2
        assertFalse(deck.equals(deck2), "Test equal decks with different card orders");
    }

    /**
     * Tests values for public constant
     */
    @Test
    public void testClassConstants() {
        assertEquals(52, Deck.CARDS_IN_DECK, "Test CARDS_IN_DECK constant");
    }
}