import javax.swing.*;
import java.util.Random;

public class Game {
    // CARD INITIALIZATIONS
    // pickedCardNum and the amount of cards the player and dealer starts with initialized
    private int pickedCardNum;
    private String pickedStringValue;
    private int playerHas = 0;
    private int dealerHas = 0;
    // values for total hand set to 8 since the highest logistic possibility is
    // four aces, four 2s, and three 3s which is 1/10 billion if we're using one deck. highly unlikely circumstance.
    private int[] playerCardNum = new int[8];
    private int[] dealerCardNum = new int[8];
    private int[] playerCardValue = new int[8];
    private int[] dealerCardValue = new int[8];
    private String[] playerCardStringValue = new String[8];
    // total card values for player and dealer
    private int playerTotalValue;
    private int dealerTotalValue;


    // MISC INITIALIZATIONS
    ImageIcon dealerSecondCard;
    ImageIcon playerThirdCard;
    // placeholder for certain situations
    String situation = "";

    // BET INITIALIZATIONS
    private double playerBet;
    private boolean win;
    private boolean natural;
    private boolean doubleDown;
    private boolean splittingPairs;

    public static void main(String[] args) {

    }

    public void startGame() {
        dealerDraw();
        playerDraw();
        dealerDraw();
        // set second card to front
        playerTurn();
    }

    public void dealerDraw() {
        // increments the amount of cards the dealer has
        dealerHas++;

        // pickedCard imageIcon set to randomCard value
        ImageIcon pickedCard = pickRandomCard();

        // passes in the picked card into the first value the dealer has
        dealerCardNum[dealerHas] = pickedCardNum;
        dealerCardValue[dealerHas] = checkCardValue();

        //total value
        dealerTotalValue = dealerTotalValue();
    }

    public void playerDraw() {
        // increments the amount of cards the player has
        playerHas++;

        // pickedCard imageIcon set to randomCard value
        ImageIcon pickedCard = pickRandomCard();

        // passes in the picked card into the first value the player has
        playerCardNum[playerHas] = pickedCardNum;
        playerCardStringValue[playerHas] = pickedStringValue;
        playerCardValue[playerHas] = checkCardValue();

        // total value
        playerTotalValue = playerTotalValue();
    }

    public void playerTurn() {
        situation = "playerTurn";

        // Hit another card
        playerDraw();
        if (playerHas == 2 && checkSplittingPairs()) {
            // do you want to split pairs
        }
        // Player has busted
        if (playerTotalValue > 21) {
            dealerOpen();
        }
        // Player has a natural (21 on initial draw)
        else if (playerTotalValue == 21 && playerHas == 2) {
            playerNatural();
            // boolean natural set to true for dealer methods
            natural = true;
        }
        else if (playerHas == 2 && playerTotalValue == 9 || playerTotalValue == 10 || playerTotalValue == 11) {
            // do you want to double down
        }

        else {
            // Player can hit another card
            if (playerHas > 1 && playerHas < 7) {
                // do you want to hit again
                // button prompt
            }
            // Player has reached the integer limit for card hits
            if (playerHas == 7) {
                dealerOpen();
            }
        }
    }

    public void playerNatural() {
        playerBet = playerBet * 1.5;
        situation = "playerNatural";

        // buttons appear
    }

    public void playerDoubleDown() {
        playerBet = playerBet * 2.0;
        doubleDown = true;
        situation = "playerDoubleDown";

        // buttons appear
    }

    public void playerSplittingPairs() {
        splittingPairs = true;
        situation = "playerSplittingPairs";

        // buttons appear
    }

    public void dealerOpen() {
        // reveal second card

        // if the value is a natural
        if (natural) {
            checkResult();
        }

        // if the dealerTotalValue is greater than 17 and the player is less than 21
        else if (dealerTotalValue < 17 && playerTotalValue <= 21) {
            dealerTurnContinue();
        }

        else {
            checkResult();
        }
    }

    public void dealerTurn() {
        // dealer must hit
        if (dealerTotalValue < 17) {
            // call dealerDraw and check again
            dealerDraw();

            // post-dealerDraw call
            // if dealerHas 7 cards or has a totalValue above 17
            if (dealerHas == 7 || dealerTotalValue >= 17) {
                checkResult();
            }

            // continue the dealers turn
            else {
                dealerTurnContinue();
            }
        }

        // any other circumstance
        else {
            checkResult();
        }
    }

    public void dealerTurnContinue() {
        situation = "dealerTurnContinue";

        // buttons appear
    }

    public void checkResult() {
        situation = "checkResult";

        if (playerTotalValue > 21) {
            // you lost :) text call
            playerBetSettlement();
        }
        else {
            if (natural) {
                if (dealerTotalValue == 21) {
                    // draw text call
                    playerBetSettlement();
                }
                else {
                    // you won :( text call
                    win = true;
                    playerBetSettlement();
                }
            }
            else {
                if(dealerTotalValue < 22 && dealerTotalValue > playerTotalValue) {
                    // you lost :) text call
                    playerBetSettlement();
                }
                else if(dealerTotalValue == playerTotalValue) {
                    // draw text call
                    playerBetSettlement();
                }
                else {
                    // you won :( text call
                    win = true;
                    playerBetSettlement();
                }
            }
        }
    }

    public void playerBetSettlement() {
        if (win) {
            playerBet = playerBet * 2;
        }
        else {
            playerBet = playerBet - playerBet;
        }
    }

    public void gameFinish() {
        situation = "gameFinish";

        // buttons appear
    }

    public void reset() {

        for(int i=1; i < 8; i++ ){
            // player and dealer card labels == false
        }
        for(int i=1; i < 6; i++) {
            playerCardNum[i]=0;
            playerCardValue[i]=0;
            dealerCardNum[i]=0;
            dealerCardValue[i]=0;
        }
        playerHas=0;
        dealerHas=0;

        // removeButtons
        startGame();
    }

    public int playerTotalValue() {

        playerTotalValue = playerCardValue[1] + playerCardValue[2] + playerCardValue[3] + playerCardValue[4] + playerCardValue[5];

        if(playerTotalValue > 21) {
            adjustPlayerAceValue();
        }
        playerTotalValue = playerCardValue[1] + playerCardValue[2] + playerCardValue[3] + playerCardValue[4] + playerCardValue[5];
        return playerTotalValue;
    }
    public int dealerTotalValue() {

        dealerTotalValue = dealerCardValue[1] + dealerCardValue[2] + dealerCardValue[3] + dealerCardValue[4] + dealerCardValue[5];

        if(dealerTotalValue > 21) {
            adjustDealerAceValue();
        }
        dealerTotalValue = dealerCardValue[1] + dealerCardValue[2] + dealerCardValue[3] + dealerCardValue[4] + dealerCardValue[5];
        return dealerTotalValue;
    }
    public void adjustPlayerAceValue() {

        for(int i=1; i<6; i++) {
            if(playerCardNum[i]==1) {
                playerCardValue[i]=1;
                playerTotalValue = playerCardValue[1] + playerCardValue[2] + playerCardValue[3] + playerCardValue[4] + playerCardValue[5];
                if(playerTotalValue < 21) {
                    break;
                }
            }
        }
    }
    public void adjustDealerAceValue() {

        for(int i=1; i<6; i++) {
            if(dealerCardNum[i]==1) {
                dealerCardValue[i]=1;
                dealerTotalValue = dealerCardValue[1] + dealerCardValue[2] + dealerCardValue[3] + dealerCardValue[4] + dealerCardValue[5];
                if(dealerTotalValue < 21) {
                    break;
                }
            }
        }
    }

    public ImageIcon pickRandomCard() {

        ImageIcon pickedCard = null;
        pickedStringValue = "";
        Random random = new Random();
        Cards cards = new Cards();

        pickedCardNum = random.nextInt(13)+1;
        int pickedFace = random.nextInt(4)+1;

        switch(pickedCardNum) {
            case 1:
                pickedStringValue = "1";
                break;
            case 2:
                pickedStringValue = "2";
                break;
            case 3:
                pickedStringValue = "3";
                break;
            case 4:
                pickedStringValue = "4";
                break;
            case 5:
                pickedStringValue = "5";
                break;
            case 6:
                pickedStringValue = "6";
                break;
            case 7:
                pickedStringValue = "7";
                break;
            case 8:
                pickedStringValue = "8";
                break;
            case 9:
                pickedStringValue = "9";
                break;
            case 10:
                pickedStringValue = "10";
                break;
            case 11:
                pickedStringValue = "11";
                break;
            case 12:
                pickedStringValue = "12";
                break;
            case 13:
                pickedStringValue = "13";
                break;
        }

        switch(pickedFace) {
            case 1:
                pickedCard = cards.spades[pickedCardNum];
            break;
            case 2:
                pickedCard = cards.hearts[pickedCardNum];
            break;
            case 3:
                pickedCard = cards.clubs[pickedCardNum];
            break;
            case 4:
                pickedCard = cards.diamonds[pickedCardNum];
            break;
        }
        return pickedCard;
    }

    public int checkCardValue() {
        int cardValue = pickedCardNum;

        if(pickedCardNum==1) {
            cardValue=11;
        }
        if(pickedCardNum>10) {
            cardValue=10;
        }

        return cardValue;
    }

    private boolean checkSplittingPairs() {
        String concatCard = "";
        for (int i = 1; i <= playerHas; i++) {
            concatCard = "+" + playerCardStringValue[i];
        }
        if (concatCard.matches("1{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("2{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("3{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("4{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("5{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("6{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("7{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("8{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("9{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("10{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("11{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("12{2}")) {
            return splittingPairs = true;
        }
        else if (concatCard.matches("13{2}")) {
            return splittingPairs = true;
        }
        else {
            return splittingPairs = false;
        }
    }
}
