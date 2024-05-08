import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the Player class.
 * 
 * @author Jessica Young Schmidt
 * @author Praneel Magapu
 */
public class PlayerTest {

    /** Test player */
    private Player testPlayer;

    /** Three of clubs */
    private Card c3;

    /**
     * Set up fields for tests
     */
    @BeforeEach
    public void setUp() {
        testPlayer = new Player("Human");
        c3 = new Card('c', 3);
    }

    /**
     * Test the constructor of the Player class.
     */
    @Test
    public void testConstructor() {
        // Test constructor: getName
        assertEquals("Human", testPlayer.getName(), "Test constructor: getName");
        // Test constructor: getHandPoints
        assertEquals(0, testPlayer.getHandPoints(), "Test constructor: getHandPoints");
        // Test constructor: getOverallPoints
        assertEquals(0, testPlayer.getOverallPoints(), "Test constructor: getOverallPoints");
        // Test that all cards in hand are initially null
        for (int i = 0; i < 13; i++) {
            assertNull(testPlayer.getCard(i), "Test that card " + i + " is null");
        }
    }

    /**
     * Test the addCard method of the Player class with one card.
     */
    @Test
    public void testAddCardA() {
        // Test that there are no cards in hand initially
        assertNull(testPlayer.getCard(0), "Test that first card of hand is null");

        // Add a card to the hand
        testPlayer.addCard(c3);
        assertEquals(new Card('c', 3), testPlayer.getCard(0), "Test that card is added as first card");
        // Test that the rest of the cards are null
        for (int i = 1; i < 13; i++) {
            assertNull(testPlayer.getCard(i), "Test that card " + i + " is null");
        }
    }

    /**
     * Test the addCard method of the Player class with multiple cards.
     */
    @Test
    public void testAddCardB() {
        // Test adding multiple cards
        Card c1 = new Card('h', 7);
        Card c2 = new Card('d', 8);

        testPlayer.addCard(c1);
        testPlayer.addCard(c2);

        assertEquals(c1, testPlayer.getCard(0), "Test that the first card is correct");
        assertEquals(c2, testPlayer.getCard(1), "Test that the second card is correct");

        // Test that the rest of the cards are null
        for (int i = 2; i < 13; i++) {
            assertNull(testPlayer.getCard(i), "Test that card " + i + " is null");
        }
    }

    /**
     * Test the getCard method of the Player class with an invalid index.
     */
    @Test
    public void testGetCardException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> testPlayer.getCard(-1), "Invalid index");
        assertEquals("Invalid index", exception.getMessage(), "Invalid index - exception message");
    }

    /**
     * Test the hasActiveCardOfSuit method of the Player class with the hand not being full.
     */
    @Test
    public void testHasActiveCardOfSuitException() {
        Exception exception = assertThrows(IllegalStateException.class,
            () -> testPlayer.hasActiveCardOfSuit('c'), "Hand not full");
        assertEquals("Hand not full", exception.getMessage(), "Hand not full - exception message");
    }

    /**
     * Test the onlyHasHearts method of the Player class with the hand not being full.
     */
    @Test
    public void testOnlyHasHeartsException() {
        Exception exception = assertThrows(IllegalStateException.class,
            () -> testPlayer.onlyHasHearts(), "Hand not full");
        assertEquals("Hand not full", exception.getMessage(), "Hand not full - exception message");
    }

    /**
     * Test the getCardNames method of the Player class with the hand not being full.
     */
    @Test
    public void testGetCardNamesException() {
        Exception exception = assertThrows(IllegalStateException.class,
            () -> testPlayer.getCardNames(), "Hand not full");
        assertEquals("Hand not full", exception.getMessage(), "Hand not full - exception message");
    }

    /**
     * Test the hasActiveCardOfSuit method of the Player class when all cards of a suit have been played.
     */
    @Test
    public void testHasActiveCardOfSuitA() {
        // Construct a hand with all cards of a suit already played
        testPlayer.addCard(c3);
        for (int i = 2; i < 14; i++) {
            testPlayer.addCard(new Card(Card.HEARTS, i));
        }
        // Mark the first card as played
        Card card = testPlayer.getCard(0);
        card.setPlayed(true);
        assertFalse(testPlayer.hasActiveCardOfSuit('c'));
    }

    /**
     * Test the hasActiveCardOfSuit method of the Player class with a suit that has active cards.
     */
    @Test
    public void testHasActiveCardOfSuitB() {
        // Construct a hand with active cards of the same suit
        testPlayer.addCard(new Card('h', 6));
        testPlayer.addCard(new Card('c', 4));
        testPlayer.addCard(new Card('d', 9));
        testPlayer.addCard(new Card('h', 5));
        for (int i = 6; i <= 14; i++) {
            testPlayer.addCard(new Card(Card.CLUBS, i));
        }

        // Mark specific cards as played
        testPlayer.getCard(1).setPlayed(true);
        testPlayer.getCard(3).setPlayed(true);

        assertTrue(testPlayer.hasActiveCardOfSuit('h'));
    }

    /**
     * Test the hasActiveCardOfSuit method of the Player class with a suit that has no active cards.
     */
    @Test
    public void testHasActiveCardOfSuitC() {
        // Construct a hand with no active cards of the same suit
        testPlayer.addCard(new Card('c', 6));
        testPlayer.addCard(new Card('d', 8));
        testPlayer.addCard(new Card('s', 4));
        for (int i = 5; i <= 14; i++) {
            testPlayer.addCard(new Card(Card.SPADES, i));
        }

        assertFalse(testPlayer.hasActiveCardOfSuit('h'));
    }

    /**
     * Test the onlyHasHearts method of the Player class with a hand consisting only of hearts.
     */
    @Test
    public void testOnlyHasHeartsA() {
        // Construct a hand with only hearts
        for (int i = 2; i <= 14; i++) {
            testPlayer.addCard(new Card(Card.HEARTS, i));
        }
        assertTrue(testPlayer.onlyHasHearts(), "player has only hearts");
    }

    /**
     * Test the onlyHasHearts method of the Player class with a hand containing no hearts.
     */
    @Test
    public void testOnlyHasHeartsB() {
        // Construct a hand with no hearts
        for (int i = 2; i <= 14; i++) {
            testPlayer.addCard(new Card(Card.CLUBS, i));
        }
        assertFalse(testPlayer.onlyHasHearts(), "player does not have only hearts");
    }

    /**
     * Test the onlyHasHearts method of the Player class with a hand containing both hearts and non-hearts.
     */
    @Test
    public void testOnlyHasHeartsC() {
        // Construct a hand with both hearts and non-hearts not played
        testPlayer.addCard(new Card('s', 5));
        testPlayer.addCard(new Card('c', 3));
        testPlayer.addCard(new Card('d', 10));
        testPlayer.addCard(new Card('h', 2));
        for (int i = 6; i <= 14; i++) {
            testPlayer.addCard(new Card(Card.SPADES, i));
        }

        assertFalse(testPlayer.onlyHasHearts(), "player does not have only hearts");
    }

    /**
     * Test the getCardNames method of the Player class with all cards being of the same suit.
     */
    @Test
    public void testGetCardNames() {
        // Construct a hand with all cards being of the same suit
        String[] names = new String[13];
        for (int i = 2; i <= 14; i++) {
            testPlayer.addCard(new Card(Card.CLUBS, i));
            names[i - 2] = "" + Card.CLUBS + i;
        }
        assertArrayEquals(names, testPlayer.getCardNames(), "test getCardNames() for all clubs");
    }

    /**
     * Test the addToHandPoints method of the Player class.
     */
    @Test
    public void testAddToHandPoints() {
        // Add points to hand and overall points
        testPlayer.addToHandPoints(5);
        testPlayer.addToHandPoints(10);

        assertEquals(15, testPlayer.getHandPoints(), "Test that getHandPoints() returns the correct value");
        assertEquals(15, testPlayer.getOverallPoints(), "Test that getOverallPoints() returns the correct value");
    }

    /**
     * Test the resetHandPoints method of the Player class.
     */
    @Test
    public void testResetHandPoints() {
        // Add points to hand, then reset
        testPlayer.addToHandPoints(5);
        assertEquals(5, testPlayer.getHandPoints(), "Test that getHandPoints() returns the correct value before reset");
        testPlayer.resetHandPoints();
        assertEquals(0, testPlayer.getHandPoints(), "Test that getHandPoints returns 0 after reset");
    }

    /**
     * Test the dumpCards method of the Player class.
     */
    @Test
    public void testDumpCards() {
        // Add a card, dump it, then check if it's null
        testPlayer.addCard(c3);
        assertEquals(new Card('c', 3), testPlayer.getCard(0), "test dump Cards(): cards [0] not null before dump");
        testPlayer.dumpCards();
        assertEquals(null, testPlayer.getCard(0), "test dumpCards: cards[0] = null after dump");
        // Add a card after dumping, check if next was reset to 0
        testPlayer.addCard(c3);
        assertEquals(new Card('c', 3), testPlayer.getCard(0), "test dump Cards(): next was reset to 0");
    }

    /**
     * Test the toString method of the Player class with zero points.
     */
    @Test
    public void testToString() {
        assertEquals("Human: 0", testPlayer.toString(), "Test toString");
    }

    /**
     * Test the toString method of the Player class with non-zero points.
     */
    @Test
    public void testToStringNonZeroPoints() {
        // Add points and test toString
        testPlayer.addToHandPoints(5);
        assertEquals("Human: 5", testPlayer.toString(), "Test toString for non-zero points");
    }

    /**
     * Test the getMove method of the Player class.
     */
    @Test
    public void testGetMove() {
        // Construct a hand with various cards
        Card c2 = new Card('c', 2);
        Card c12 = new Card('c', 12);
        Card d4 = new Card('d', 4);
        Card d5 = new Card('d', 5);
        Card d7 = new Card('d', 7);
        Card d12 = new Card('d', 12);
        Card s5 = new Card('s', 5);
        Card s11 = new Card('s', 11);
        Card s12 = new Card('s', 12);
        Card h2 = new Card('h', 2);
        Card h3 = new Card('h', 3);
        Card h5 = new Card('h', 5);

        testPlayer.addCard(c3);
        testPlayer.addCard(h5);
        testPlayer.addCard(d7);
        testPlayer.addCard(s12);
        testPlayer.addCard(c12);
        testPlayer.addCard(c2);
        testPlayer.addCard(d4);
        testPlayer.addCard(h2);
        testPlayer.addCard(s5);
        testPlayer.addCard(s11);
        testPlayer.addCard(d5);
        testPlayer.addCard(h3);
        testPlayer.addCard(d12);

        // Test first round (trick) and the computer player has the 2 of Clubs
        Card c2Copy = new Card('c', 2);
        c2Copy.setPlayed(true);
        assertEquals(c2Copy, testPlayer.getMove(null, true, false), "Has 2 of clubs");
        Card c3copy = new Card('c', 3);
        c3copy.setPlayed(true);
        assertEquals(c3copy, testPlayer.getMove(new Card('c', 4), true, false), "Lowest clubs");
    }
}