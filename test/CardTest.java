import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Card class
 * 
 * @author Jessica Young Schmidt
 * @author Praneel Magapu
 */
public class CardTest {

    /** three of clubs */
    private Card c3;

    /**
     * Sets up field for testing
     */
    @BeforeEach
    public void setUp() {
        c3 = new Card('c', 3);
    }

    /**
     * Test invalid constructor parameters
     */
    @Test
    public void testInvalidConstructorParameters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Card('a', 33),
            "invalid suit");
        assertEquals("Invalid suit", exception.getMessage(), "invalid suit - exception message");

        exception = assertThrows(IllegalArgumentException.class, () -> new Card('c', 1),
            "invalid value (1)");
        assertEquals("Invalid value", exception.getMessage(),
            "invalid value (1) - exception message");

        // NOTE: You are not required to add additional invalid parameter test. However,
        // you may add tests if you would like. Not all paths through your Card
        // constructors will be tested by the given tests above.
    }

    /**
     * Tests the getSuit method for three of clubs
     */
    @Test
    public void testGetSuitA() {
        assertEquals('c', c3.getSuit(), "Test getSuit for three of clubs");
    }

    /**
     * Tests the getSuit method for five of diamonds
     */
    @Test
    public void testGetSuitB() {
        Card d5 = new Card('d', 5);
        assertEquals('d', d5.getSuit(), "Test getSuit for five of diamonds");
    }

    /**
     * Tests the getValue method for three of clubs
     */
    @Test
    public void testGetValueA() {
        assertEquals(3, c3.getValue(), "Test getValue for three of clubs");
    }

    /**
     * Tests the getValue method for ten of hearts
     */
    @Test
    public void testGetValueB() {
        Card h10 = new Card('h', 10);
        assertEquals(10, h10.getValue(), "Test getValue for ten of hearts");
    }

    /**
     * Tests the hasBeenPlayed method for three of clubs before and after played
     */
    @Test
    public void testHasBeenPlayed() {
        assertFalse(c3.hasBeenPlayed(), "Test hasBeenPlayed for three of clubs before played");
        c3.setPlayed(true);
        assertTrue(c3.hasBeenPlayed(), "Test hasBeenPlayed for three of clubs after played");
    }

    /**
     * Tests the setPlayed method for three of clubs
     */
    @Test
    public void testSetPlayed() {
        c3.setPlayed(true);
        assertTrue(c3.hasBeenPlayed(), "Test setPlayed(true) for three of clubs");
        c3.setPlayed(false);
        assertFalse(c3.hasBeenPlayed(), "Test setPlayed(false) for three of clubs");
    }

    /**
     * Tests the isHeart method for three of clubs
     */
    @Test
    public void testIsHeartA() {
        assertFalse(c3.isHeart(), "Test isHeart for three of clubs");
    }

    /**
     * Tests the isHeart method for eight of hearts
     */
    @Test
    public void testIsHeartB() {
        Card h8 = new Card('h', 8);
        assertTrue(h8.isHeart(), "Test isHeart for eight of hearts");
    }

    /**
     * Tests the toString method for three of clubs
     */
    @Test
    public void testToStringA() {
        assertEquals("c3", c3.toString(), "Test toString for three of clubs");
    }

    /**
     * Tests the toString method for seven of spades
     */
    @Test
    public void testToStringB() {
        Card s7 = new Card('s', 7);
        assertEquals("s7", s7.toString(), "Test toString for seven of spades");
    }

    /**
     * Tests the equals method for three of clubs
     */
    @Test
    public void testEqualsA() {
        assertTrue(c3.equals(c3), "Test equals for three of clubs");
    }

    /**
     * Tests the equals method for three of clubs and three of diamonds
     */
    @Test
    public void testEqualsB() {
        Card d3 = new Card('d', 3);
        assertFalse(c3.equals(d3), "Test equals for three of clubs and three of diamonds");
    }

    /**
     * Tests the equals method for three of clubs and five of clubs
     */
    @Test
    public void testEqualsC() {
        Card c5 = new Card('c', 5);
        assertFalse(c3.equals(c5), "Test equals for three of clubs and five of clubs");
    }

    /**
     * Tests the equals method for three of clubs and three of clubs with different hasBeenPlayed
     */
    @Test
    public void testEqualsD() {
        Card c3Two = new Card('c', 3);
        c3Two.setPlayed(true);
        assertFalse(c3.equals(c3Two), "Test equals for three of clubs and three of clubs with different hasBeenPlayed");
    }

    /**
     * Tests the compareTo method for three of clubs and three of spades
     */
    @Test
    public void testCompareToA() {
        Card s3 = new Card('s', 3);
        assertTrue(c3.compareTo(s3) < 0, "Test compareTo for three of clubs and three of spades");
    }

    /**
     * Tests the compareTo method for three of clubs and two of clubs
     */
    @Test
    public void testCompareToB() {
        Card c2 = new Card('c', 2);
        assertTrue(c3.compareTo(c2) > 0, "Test compareTo for three of clubs and two of clubs");
    }

    /**
     * Tests the compareTo method for two of hearts and three of clubs
     */
    @Test
    public void testCompareToC() {
        Card h2 = new Card('h', 2);
        assertTrue(h2.compareTo(c3) > 0, "Test compareTo for two of hearts and three of clubs");
    }

    /**
     * Tests the compareTo method for three of clubs and four of clubs
     */
    @Test
    public void testCompareToD() {
        Card c4 = new Card('c', 4);
        assertTrue(c3.compareTo(c4) < 0, "Test compareTo for three of clubs and four of clubs");
    }

    /**
     * Tests the compareTo method for three of clubs and three of clubs
     */
    @Test
    public void testCompareToE() {
        Card c3Two = new Card('c', 3);
        assertEquals(0, c3.compareTo(c3Two),
            "Test compareTo for three of clubs and three of clubs");
    }

    /**
     * Tests the isQueenOfSpades method for three of clubs
     */
    @Test
    public void testIsQueenOfSpadesA() {
        assertFalse(c3.isQueenOfSpades(), "Test isQueenOfSpades for three of clubs");
    }

    /**
     * Tests the isQueenOfSpades method for queen of spades
     */
    @Test
    public void testIsQueenOfSpadesB() {
        Card qs = new Card('s', 12);
        assertTrue(qs.isQueenOfSpades(), "Test isQueenOfSpades for queen of spades");
    }

    /**
     * Tests the isQueenOfSpades method for six of spades
     */
    @Test
    public void testIsQueenOfSpadesC() {
        Card s6 = new Card('s', 6);
        assertFalse(s6.isQueenOfSpades(), "Test isQueenOfSpades for six of spades");
    }

    /**
     * Tests the isHigherThan method for four of clubs and three of clubs
     */
    @Test
    public void testIsHigherA() {
        Card c4 = new Card('c', 4);
        assertTrue(c4.isHigherThan(c3), "Test isHigherThan for four of clubs and three of clubs");
    }

    /**
     * Tests the isHigherThan method for three of clubs and six of diamonds
     */
    @Test
    public void testIsHigherB() {
        Card d6 = new Card('d', 6);
        assertFalse(c3.isHigherThan(d6), "Test isHigherThan for three of clubs and six of diamonds");
    }

    /**
     * Tests the isHigherThan method for three of clubs and five of clubs
     */
    @Test
    public void testIsHigherC() {
        Card c5 = new Card('c', 5);
        assertFalse(c3.isHigherThan(c5), "Test isHigherThan for three of clubs and five of clubs");
    }

    /**
     * Tests the class constants CLUBS, DIAMONDS, SPADES, HEARTS, LOWEST_VALUE, HIGHEST_VALUE, QUEEN_VALUE
     */
    @Test
    public void testClassConstants() {
        assertEquals('c', Card.CLUBS, "Test CLUBS constant");
        assertEquals('d', Card.DIAMONDS, "Test DIAMONDS constant");
        assertEquals('s', Card.SPADES, "Test SPADES constant");
        assertEquals('h', Card.HEARTS, "Test HEARTS constant");
        assertEquals(2, Card.LOWEST_VALUE, "Test LOWEST_VALUE constant");
        assertEquals(14, Card.HIGHEST_VALUE, "Test HIGHEST_VALUE constant");
        assertEquals(12, Card.QUEEN_VALUE, "Test QUEEN_VALUE constant");
    }

}