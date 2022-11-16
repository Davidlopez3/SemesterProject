import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
    // initialization of deck
    public ArrayList<String> deck;
    public String card;

    // no-args constructor
    public Deck() {
        // ArrayList class was used in this instance for the ability to variably change the size of an array
        // Initialized a ArrayList<String> for each card suit
        // Starting each String with the first letter of the suit for later point values
        final ArrayList<String> HEARTS = new ArrayList<>(Arrays.asList
                ("HAce", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJack", "HQueen", "HKing"));

        final ArrayList<String> CLUBS = new ArrayList<>(Arrays.asList
                ("CAce", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJack", "CQueen", "CKing"));

        final ArrayList<String> DIAMONDS = new ArrayList<>(Arrays.asList
                ("DAce", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJack", "DQueen", "DKing"));

        final ArrayList<String> SPADES = new ArrayList<>(Arrays.asList
                ("SAce", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJack", "SQueen", "SKing"));


        // Arraylist deck is initialized as the combination of each suit's ArrayList

        deck = new ArrayList<>(HEARTS);
        deck.addAll(CLUBS);
        deck.addAll(DIAMONDS);
        deck.addAll(SPADES);

        /* This was done for easier readability as opposed to writing in all the deck at once
         *  it makes it to where we can easily see which number goes with each suit
         *  then those suits are added into the deck after they're initialized */
    }

    // Shuffler method for shuffling the deck of cards
    public void shuffleDeck() {
        Collections.shuffle(deck);
        System.out.println(deck);
    }

    // Getter to return the String value of deck at an index
    // Note that the deck value starts at zero because it's an array
    public String getCard (int index) throws IndexOutOfBoundsException {
        // try-catch block for a more graceful exit code in case of IndexOutOfBoundsException
        try {
            card = deck.get(index);
            System.out.println("Card " + (index + 1) + " is " + card);
            return card;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Selected card number is out of bounds");
        }
        return card;
    }

}
