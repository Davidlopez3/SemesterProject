public class BlackjackDemo {
    public static void main(String[] args) {
        Deck card = new Deck();
        card.shuffleDeck();

        Cards goober = new Cards();
        System.out.println(goober.cardValue(card.getCard(0)));
    }
}
