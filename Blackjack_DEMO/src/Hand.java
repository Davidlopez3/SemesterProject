public class Hand{
    private Deck hand = new Deck();
    private int numberOfCards = 0;

    public int calculeTotal() {
        int total = 0;
        boolean isAceFlag = false;

        for(int i = 0; i < numberOfCards; i++) {
            int value = hand.cardValue(hand.getCard(0));
            if (value > 10) {
                value = 10;
            }
            else if (value == 1) {
                isAceFlag = true;
            }
            total += value;
        }

        if (isAceFlag && total + 10 <= 21) {
            total += 10;
        }

        return total;
    }

    public int hit(Deck card) {
        hand.getCard(0);
        hand.removeCard();
        numberOfCards++;
        return numberOfCards;
    }


}