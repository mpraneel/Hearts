import java.util.*;

/**
 * Represents a player in a card game.
 * @author Praneel Magapu
 */
public class Player {

    /** The number of cards in a player's hand. */
    public static final int CARDS_IN_HAND = 13;

    /** The name of the player. */
    private String name;

    /** The overall points scored by the player. */
    private int overallPoints;

    /** The points scored by the player in the current hand. */
    private int handPoints;

    /** The cards in the player's hand. */
    private Card[] hand;

    /** The index indicating the next available position in the hand array. */
    private int next;

    /**
     * Constructs a player with the given name.
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.overallPoints = 0;
        this.handPoints = 0;
        this.hand = new Card[CARDS_IN_HAND];
        this.next = 0;
    }

    /**
     * Gets the name of the player.
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a card to the player's hand.
     * @param card the card to add
     * @throws IllegalStateException if the hand is full
     */
    public void addCard(Card card) {
        if (next >= CARDS_IN_HAND) {
            throw new IllegalStateException("Full hand");
        }
        hand[next++] = card;
        if (next == CARDS_IN_HAND) {
            Arrays.sort(hand);
        }
    }

    /**
     * Gets the points scored by the player in the current hand.
     * @return the points scored in the current hand
     */
    public int getHandPoints() {
        return handPoints;
    }

    /**
     * Gets the overall points scored by the player.
     * @return the overall points scored
     */
    public int getOverallPoints() {
        return overallPoints;
    }

    /**
     * Adds points to the player's hand points and overall points.
     * @param points the points to add
     */
    public void addToHandPoints(int points) {
        handPoints += points;
        overallPoints += points;
    }

    /**
     * Gets the card at the specified index in the player's hand.
     * @param index the index of the card to get
     * @return the card at the specified index
     * @throws IllegalArgumentException if the index is invalid
     */
    public Card getCard(int index) {
        if (index < 0 || index >= CARDS_IN_HAND) {
            throw new IllegalArgumentException("Invalid index");
        } else {
            return hand[index];
        }
    }

    /**
     * Checks if the player has an active card of the specified suit in their hand.
     * @param suit the suit to check for
     * @return true if the player has an active card of the specified suit, false otherwise
     * @throws IllegalStateException if the hand is not full
     */
    public boolean hasActiveCardOfSuit(char suit) {
        if (next < CARDS_IN_HAND) {
            throw new IllegalStateException("Hand not full");
        }
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            if (hand[i].getSuit() == suit && !hand[i].hasBeenPlayed()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player only has hearts in their hand.
     * @return true if the player only has hearts in their hand, false otherwise
     * @throws IllegalStateException if the hand is not full
     */
    public boolean onlyHasHearts() {
        if (next < CARDS_IN_HAND) {
            throw new IllegalStateException("Hand not full");
        }

        for (int i = 0; i < CARDS_IN_HAND; i++) {
            if (hand[i].getSuit() != Card.HEARTS) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the names of all the cards in the player's hand.
     * @return an array of strings containing the names of the cards in the hand
     * @throws IllegalStateException if the hand is not full
     */
    public String[] getCardNames() {
        if (next < CARDS_IN_HAND) {
            throw new IllegalStateException("Hand not full");
        }

        String[] cardNames = new String[CARDS_IN_HAND];
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            cardNames[i] = hand[i].toString();
        }

        return cardNames;
    }

    /**
     * Returns a string representation of the player.
     * @return a string containing the player's name and hand points
     */
    public String toString() {
        return name + ": " + handPoints;
    }

    /**
     * Resets the player's hand by removing all cards.
     */
    public void dumpCards() {
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            hand[i] = null;
        }
        next = 0;
    }

    /**
     * Resets the player's hand points to zero.
     */
    public void resetHandPoints() {
        handPoints = 0;
    }

    /**
     * This method determines the card that a computer player will play in the
     * current round (trick). This method assumes the hand has been sorted and
     * is in order by suits - clubs, diamond, spades, hearts - and the values of
     * the cards in each suit are ordered from lowest to highest value.
     * @param startingCard the card that started the round
     * @param isFirstRound whether or not this is the first round of a hand
     * @param heartsStarted whether or not hearts are in play at this point in the hand
     * @return the card that will be played
     */
    public Card getMove(Card startingCard, boolean isFirstRound, boolean heartsStarted) {

        //If this is the first round (trick) and the computer player has the 2 of Clubs, 
        //they must play it. If the player has the 2 of Clubs, it should be the first 
        //card in their (sorted) hand
        if (isFirstRound && startingCard == null &&
            hand[0].getSuit() == Card.CLUBS && hand[0].getValue() == 2) {
            hand[0].setPlayed(true);
            return hand[0];
        }

        //If an initial card was played, the computer player must follow suit
        //and will play the lowest card in that suit
        if (startingCard != null) {
            char currentSuit = startingCard.getSuit();
            for (int i = 0; i < CARDS_IN_HAND; i++) {
                if (!hand[i].hasBeenPlayed() && hand[i].getSuit() == currentSuit) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
        }

        //If no card with a matching suit was found and it's not the first round(trick)
        //the computer player will play the Queen of Spades if they have it and it
        //hasn't been played yet
        //If not, they will play their highest valued Heart, if they have one and it
        //hasn't been played yet
        if (startingCard != null && !isFirstRound) {
            //Look for the Queen of Spades
            for (int i = 0; i < CARDS_IN_HAND; i++) {
                if (hand[i].getSuit() == Card.SPADES && hand[i].getValue() == Card.QUEEN_VALUE &&
                    !hand[i].hasBeenPlayed()) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
            for (int i = CARDS_IN_HAND - 1; i >= 0; i--) {
                if (hand[i].getSuit() == Card.HEARTS && !hand[i].hasBeenPlayed()) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
        }

        //If no card has been found yet, the first card that hasn't been played
        //in the sorted hand will be played
        for (int i = 0; i < hand.length; i++) {
            if (!hand[i].hasBeenPlayed()) {

                //A club or diamond is always valid
                if (hand[i].getSuit() == Card.CLUBS ||
                    hand[i].getSuit() == Card.DIAMONDS) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }

                //All spades other than the queen are valid
                //The queen of spades can be played if it's not the
                //first round(trick)
                if (hand[i].getSuit() == Card.SPADES) {
                    if (hand[i].getValue() != Card.QUEEN_VALUE) {
                        hand[i].setPlayed(true);
                        return hand[i];
                    } else if (!isFirstRound) {
                        hand[i].setPlayed(true);
                        return hand[i];
                    }
                }

                //A heart is valid if it's not the first round 
                //and either hearts have been played previously or
                //the player only has hearts. NOTE: This case would 
                //occur when the player is playing the initial card
                //in the trick
                if (hand[i].getSuit() == Card.HEARTS && !isFirstRound &&
                    (heartsStarted || onlyHasHearts())) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
        }
        //No card found so far - this could happen in the unlikely situation
        //that the player's hand contained only hearts or
        //the Queen of spades with the rest of the cards being hearts
        //Return the first unplayed card        
        for (int i = 0; i < hand.length; i++) {
            if (!hand[i].hasBeenPlayed()) {
                hand[i].setPlayed(true);
                return hand[i];
            }
        }
        //No unplayed card in hand
        throw new IllegalStateException("No unplayed card in hand");

    }
}