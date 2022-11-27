package application;

import javafx.scene.image.Image;
import java.util.Random;

public class Game {
    // CLASS INITIALIZATIONS
    UIController ui = new UIController();
    Cards card = new Cards();
    Bets bet = new Bets();

    // CARD INITIALIZATIONS
    // pickedCardNum and the amount of cards the player and dealer starts with initialized
    private int pickedCardNum;
    private String pickedStringValue;
    private int playerHas = 0;
    private int dealerHas = 0;
    private int[] playerCardNum = new int[6];
    private int[] dealerCardNum = new int[6];
    private int[] playerCardValue = new int[6];
    private int[] dealerCardValue = new int[6];
    // private String[] playerCardStringValue = new String[8];
    // total card values for player and dealer
    private int playerTotalValue;
    private int dealerTotalValue;


    // MISC INITIALIZATIONS
    Image dealerSecondCard;
    Image playerThirdCard;
    // placeholder for situations handled by ActionHandler class
    String situation = "";

    // BET INITIALIZATIONS
    // Values holding the originalPlayerBet and the currentPlayerBet
    public double totalPool;
    protected boolean win;
    protected boolean loss;
    protected boolean natural;
    protected boolean insurance;
    // doubleDown booleans to handle the method call
    protected boolean doubleDown;
    private boolean noDoubleDown;

    public void startGame() {
        ui.continueLabel.setVisible(false);
        ui.yesButton.setVisible(false);
        ui.yesButton.setManaged(false);
        ui.noButton.setVisible(false);
        ui.noButton.setManaged(false);
        ui.insuranceYesButton.setSelected(false);
        ui.insuranceYesButton.setOnAction(event -> {
            if (ui.insuranceYesButton.isSelected()) {
                insurance = true;
                ui.insuranceLabel.setVisible(false);
                ui.insuranceYesButton.setManaged(false);
                ui.insuranceYesButton.setVisible(false);
                ui.insuranceNoButton.setManaged(false);
                ui.insuranceNoButton.setVisible(false);
            }
        });
        ui.insuranceNoButton.setSelected(false);
        ui.insuranceNoButton.setOnAction(event -> {
            if (ui.insuranceNoButton.isSelected()) {
                ui.insuranceLabel.setVisible(false);
                ui.insuranceYesButton.setManaged(false);
                ui.insuranceYesButton.setVisible(false);
                ui.insuranceNoButton.setManaged(false);
                ui.insuranceNoButton.setVisible(false);
            }
        });
        dealerDraw();
        playerDraw();
        dealerDraw();
        // sets dealers second card to the front side png
        ui.dealerCardImage(card.front,2);
        playerTurn();
    }
    public void dealerDraw() {
        // increments the amount of cards the dealer has
        dealerHas++;

        // pickedCard imageIcon set to randomCard value
        Image pickedCard = pickRandomCard();

        // passes in the picked card into the first value the dealer has
        dealerCardNum[dealerHas] = pickedCardNum;
        dealerCardValue[dealerHas] = checkCardValue();

        // sets the imageView to the given card passed through pickedCard
        ui.dealerCardImage(pickedCard, dealerHas); // Display the card

        //total value
        dealerTotalValue = dealerTotalValue();
    }

    public void playerDraw() {
        // increments the amount of cards the player has
        playerHas++;

        // pickedCard imageIcon set to randomCard value
        Image pickedCard = pickRandomCard();

        // passes in the picked card into the first value the player has
        playerCardNum[playerHas] = pickedCardNum;
//        playerCardStringValue[playerHas] = pickedStringValue;
        playerCardValue[playerHas] = checkCardValue();

        // sets the imageView to the given card passed through pickedCard
        ui.playerCardImage(pickedCard, playerHas);

        // total value
        playerTotalValue = playerTotalValue();
    }

    public void playerTurn() {
        situation = "playerTurn";

        if (!noDoubleDown) {
            playerDraw();
        }

//        if (playerHas == 2 && checkSplittingPairs()) {
//            // do you want to split pairs
//        }

        // Player has busted
        if (playerTotalValue > 21) {
            dealerOpen();
        }
        // Player has a natural (21 on initial draw)
        else if (playerTotalValue == 21 && playerHas == 2) {
            playerNatural();
        }

        // If doubleDown is active we draw the third card faceDown
        else if (doubleDown) {
            ui.playerCardImage(card.front, 3);
            dealerOpen();
        }

        // Calls playerDoubleDown() method; won't be called again
        else if (playerHas == 2 && playerTotalValue == 9 || playerTotalValue == 10 || playerTotalValue == 11
                && !noDoubleDown) {
            playerDoubleDown();
        }

        else {
            // Player can hit another card
            if (playerHas > 1 && playerHas < 7) {
                // do you want to hit again
                // button prompt
                ui.hitStandLabel.setText("Do you want to hit another card?");
                ui.hitButton.setVisible(true);
                ui.hitButton.setOnAction(event -> {
                    if (ui.hitButton.isPressed()) {
                        playerTurn();
                        ui.hitButton.setVisible(false);
                        ui.hitButton.setManaged(false);
                    }
                });
                ui.standButton.setVisible(true);
                ui.standButton.setOnAction(event -> {
                    if (ui.standButton.isPressed()) {
                        dealerOpen();
                        ui.standButton.setVisible(false);
                        ui.standButton.setManaged(false);
                    }
                });
            }
            // Player has reached the integer limit for card hits; deny out of bounds exception
            if (playerHas == 7) {
                dealerOpen();
            }
        }
    }

    public void playerNatural() {
        situation = "playerNatural";
        // boolean natural set to true for dealer methods
        natural = true;

        ui.hitStandLabel.setText("You have a natural.");
        // buttons appear
        ui.standButton.setText("Continue");
        ui.standButton.setVisible(true);
        ui.standButton.setOnAction(event -> {
            if (ui.standButton.isPressed()) {
                dealerOpen();
                ui.standButton.setVisible(false);
                ui.standButton.setManaged(false);
                ui.standButton.setText("Stand");
            }
        });
    }

    public void playerDoubleDown() {
        situation = "playerDoubleDown";

        ui.hitStandLabel.setText("You have two " + playerCardNum[playerHas] + "'s." +
                " Would you like to double down?");
        // buttons appear, ActionHandler call
        ui.standButton.setText("Continue");
        ui.standButton.setVisible(true);
        ui.standButton.setOnAction(event -> {
            if (ui.standButton.isPressed()) {
                noDoubleDown = true;
                playerTurn();
                ui.standButton.setVisible(false);
                ui.standButton.setManaged(false);
                ui.standButton.setText("Stand");
            }
        });
        ui.hitButton.setText("Double");
        ui.hitButton.setVisible(true);
        ui.hitButton.setOnAction(event -> {
            if (ui.hitButton.isPressed()) {
                doubleDown = true;
                playerTurn();
                ui.hitButton.setVisible(false);
                ui.hitButton.setManaged(false);
                ui.hitButton.setText("Stand");
            }
        });
    }

    public void dealerOpen() {
        // reveal second card
        ui.dealerCardImage(dealerSecondCard, 2);// Reveal the second card
        ui.setDealerPoints(dealerTotalValue);
        if (doubleDown) {
            ui.playerCardImage(playerThirdCard, 3);// Reveal the second card
        }

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
        ui.hitStandLabel.setText("The dealer is going to hit another card.");
        ui.standButton.setText("Continue");
        ui.standButton.setVisible(true);
        ui.standButton.setOnAction(event -> {
            if (ui.standButton.isPressed()) {
                dealerTurn();
                ui.standButton.setVisible(false);
                ui.standButton.setManaged(false);
                ui.hitStandLabel.setVisible(false);
                ui.standButton.setText("Stand");
            }
        });
    }

    public void checkResult() {
        situation = "checkResult";

        if (playerTotalValue > 21) {
            // you lost :) text call
            ui.setResultLabel("You lost");
            loss = true;
            playerBetSettlement();
        }
        else {
            if (natural) {
                if (dealerTotalValue == 21) {
                    // draw text call
                    ui.setResultLabel("Draw");
                    playerBetSettlement();
                }
                else {
                    // you won :( text call
                    ui.setResultLabel("You won");
                    win = true;
                    playerBetSettlement();
                }
            }
            else {
                if(dealerTotalValue < 22 && dealerTotalValue > playerTotalValue) {
                    // you lost :) text call
                    ui.setResultLabel("You lost");
                    loss = true;
                    playerBetSettlement();
                }
                else if(dealerTotalValue == playerTotalValue) {
                    // draw text call
                    ui.setResultLabel("Draw");
                    playerBetSettlement();
                }
                else {
                    // you won :( text call
                    ui.setResultLabel("You won");
                    win = true;
                    playerBetSettlement();
                }
            }
        }
    }

    public void playerBetSettlement() {
        totalPool += bet.settlement(win, loss, natural, doubleDown, insurance);
        gameFinish();
    }

    public void gameFinish() {
        situation = "gameFinish";

        // buttons appear
        ui.continueLabel.setVisible(true);
        ui.yesButton.setVisible(true);
        ui.yesButton.setOnAction(event -> {
            if (ui.yesButton.isPressed()) {
                reset();
                ui.yesButton.setVisible(false);
                ui.yesButton.setManaged(false);
                ui.yesButton.setVisible(false);
            }
        });

        ui.noButton.setVisible(true);
        ui.noButton.setOnAction(event -> {
            if (ui.noButton.isPressed()) {
                ui.noButton.setVisible(false);
                ui.noButton.setManaged(false);
                ui.continueLabel.setVisible(false);
                totalPool = 0;
                System.out.println("System terminated : Continue Label : noButton ActionEvent");
                System.exit(0);
            }
        });
    }

    public void reset() {
        ui.dealerCardImageClear();
        ui.playerCardImageClear();
        for(int i=1; i < 6; i++) {
            playerCardNum[i]=0;
            playerCardValue[i]=0;
            dealerCardNum[i]=0;
            dealerCardValue[i]=0;
        }
        playerHas=0;
        ui.setPlayerPoints(playerHas);
        dealerHas=0;
        ui.resetDealerPoints();

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

    public Image pickRandomCard() {

        Image pickedCard = null;
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


//    private boolean checkSplittingPairs() {
//        String concatCard = "";
//        for (int i = 1; i <= playerHas; i++) {
//            concatCard = "+" + playerCardStringValue[i];
//        }
//        if (concatCard.matches("1{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("2{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("3{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("4{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("5{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("6{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("7{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("8{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("9{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("10{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("11{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("12{2}")) {
//            return splittingPairs = true;
//        }
//        else if (concatCard.matches("13{2}")) {
//            return splittingPairs = true;
//        }
//        else {
//            return splittingPairs = false;
//        }
//    }
}
// code based on https://www.ryisnow.online/2021/05/java-sample-code-black-jack-game.html
// converted from Swing to JavaFX
// SAM
// edits by Sam (11/25/2022)
