public class Cards extends Deck {
    // initialization of int value
    public int value;
    public String suit;

    // Method to return value of a card in the deck
    public int cardValue(String card) throws NullPointerException{
        try {
            if (card.matches(".Ace"))
                value = 1;
            else if (card.matches(".\\d")) {
                if (card.matches(".2"))
                    value = 2;
                if (card.matches(".3"))
                    value = 3;
                if (card.matches(".4"))
                    value = 4;
                if (card.matches(".5"))
                    value = 5;
                if (card.matches(".6"))
                    value = 6;
                if (card.matches(".7"))
                    value = 7;
                if (card.matches(".8"))
                    value = 8;
                if (card.matches(".9"))
                    value = 9;
                if (card.matches(".10"))
                    value = 10;
            } else if (card.matches(".Jack|.Queen|.King"))
                value = 10;
        }
        catch (NullPointerException e) {
            System.out.println("Card does not exist");
        }

        return value;
    }

    public String cardSuit(String card) {
        if (card.matches("H*")) {
            suit = "Hearts";
        } else if (card.matches("C*")) {
            suit = "Clubs";
        } else if (card.matches("D*")) {
            suit = "Diamonds";
        } else if (card.matches("S*")) {
            suit = "Spades";
        }
        return suit;
    }
}
