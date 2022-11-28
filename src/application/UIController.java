package application;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class UIController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("main.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToTable(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("Tables.fxml")));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Table.fxml Framework

    @FXML
    private Label betLabel;

    @FXML
    private TextArea betTextArea;

    @FXML
    private ImageView dealerCard1;

    @FXML
    private ImageView dealerCard2;

    @FXML
    private ImageView dealerCard3;

    @FXML
    private ImageView dealerCard4;

    @FXML
    private ImageView dealerCard5;

    @FXML
    private ImageView dealerCard6;

    public void dealerCardImage(Image card, int num) {
        switch (num) {
            case 1:
                dealerCard1.setImage(card);
                break;
            case 2:
                dealerCard2.setImage(card);
                break;
            case 3:
                dealerCard3.setImage(card);
                break;
            case 4:
                dealerCard4.setImage(card);
                break;
            case 5:
                dealerCard5.setImage(card);
                break;
            case 6:
                dealerCard6.setImage(card);
                break;
        }
    }

    public void dealerCardImageClear() {
        dealerCard1.setImage(null);
        dealerCard2.setImage(null);
        dealerCard3.setImage(null);
        dealerCard4.setImage(null);
        dealerCard5.setImage(null);
        dealerCard6.setImage(null);
    }

    @FXML
    private Label dealerTotalLabel;

    public void setDealerPoints(int points) {
        dealerTotalLabel.setText("Dealer: " + points);
    }

    public void resetDealerPoints() {
        dealerTotalLabel.setText("Dealer: ?");
    }

    @FXML
    public Label insuranceLabel;

    @FXML
    public ToggleButton insuranceNoButton;

    @FXML
    public ToggleButton insuranceYesButton;

    @FXML
    private ImageView playerCard1;

    @FXML
    private ImageView playerCard2;

    @FXML
    private ImageView playerCard3;

    @FXML
    private ImageView playerCard4;

    @FXML
    private ImageView playerCard5;

    @FXML
    private ImageView playerCard6;

    @FXML
    public void playerCardImage(Image card, int num) {
        switch (num) {
            case 1:
                playerCard1.setImage(card);
                break;
            case 2:
                playerCard2.setImage(card);
                break;
            case 3:
                playerCard3.setImage(card);
                break;
            case 4:
                playerCard4.setImage(card);
                break;
            case 5:
                playerCard5.setImage(card);
                break;
            case 6:
                playerCard6.setImage(card);
                break;
        }
    }

    @FXML
    public void playerCardImageClear() {
        playerCard1.setImage(null);
        playerCard2.setImage(null);
        playerCard3.setImage(null);
        playerCard4.setImage(null);
        playerCard5.setImage(null);
        playerCard6.setImage(null);
    }

    @FXML
    private Label playerTotalLabel;

    public void setPlayerPoints(int points) {
        playerTotalLabel.setText("Player: " + points);
    }

    @FXML
    public Label hitStandLabel;

    @FXML
    public Button hitButton;

    @FXML
    public Button standButton;

    @FXML
    private Label resultLabel;

    public void setResultLabel(String result) {
        resultLabel.setText("Result: " + result);
    }

    @FXML
    public Label fundsLabel;

    @FXML
    public Label continueLabel;

    @FXML
    public Button yesButton;

    @FXML
    public Button noButton;

    // GAME CLASS SCRAP INSERT

    // CLASS INITIALIZATIONS
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
    String situation = "";
    // EVENT BOOLEANS
    boolean restartState;

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

    public void start() {
        yesButton.setOnAction(event -> {
            if (restartState) {
                // if restartState is default true the game starts
                this.reset();
                restartState = false;
            }
            if (!restartState) {
                // if restartState is false the game restarts
                this.startGame();
                restartState = true;
            }
        });
    }

    public void startGame() {
        // manage button visibility for yesButton onAction=#start
        yesButton.setVisible(false);
        yesButton.setManaged(false);
        noButton.setVisible(false);
        noButton.setManaged(false);
        continueLabel.setVisible(false);
        insuranceYesButton.setOnAction(event -> {
            if (insuranceYesButton.isSelected()) {
                insurance = true;
            }
        });

        dealerDraw();
        playerDraw();
        dealerDraw();
        // sets dealers second card to the front side png
        dealerCardImage(card.front,2);
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
        dealerCardImage(pickedCard, dealerHas); // Display the card

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
        playerCardImage(pickedCard, playerHas);

        // total value
        playerTotalValue = playerTotalValue();
    }

    @FXML
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
            playerCardImage(card.front, 3);
            dealerOpen();
        }

        // Calls playerDoubleDown() method; won't be called again
//        else if (playerHas == 2 && playerTotalValue == 9 || playerTotalValue == 10 || playerTotalValue == 11
//                && !noDoubleDown) {
//            playerDoubleDown();
//        }

        else {
            // Player can hit another card
            if (playerHas > 1 && playerHas < 7) {
                // do you want to hit again
                // button prompt
                hitStandLabel.setText("Do you want to hit another card?");
                hitButton.setOnAction(event -> {
                    playerTurn();
                });
                standButton.setOnAction(event -> {
                    dealerOpen();
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

        hitStandLabel.setText("You have a natural.");
        // buttons appear
        standButton.setText("Continue");
        standButton.setOnAction(event -> {
            dealerOpen();
            standButton.setText("Stand");
        });
    }

//    public void playerDoubleDown() {
//        situation = "playerDoubleDown";
//
//        hitStandLabel.setText("You have two " + playerCardNum[playerHas] + "'s." +
//                " Would you like to double down?");
//        // buttons appear, ActionHandler call
//        standButton.setText("Continue");
//        standButton.setOnAction(event -> {
//            noDoubleDown = true;
//            playerTurn();
//            standButton.setText("Stand");
//        });
//        hitButton.setText("Double");
//        hitButton.setOnAction(event -> {
//            doubleDown = true;
//            playerTurn();
//            hitButton.setText("Stand");
//        });
//    }

    public void dealerOpen() {
        // reveal second card
        dealerCardImage(dealerSecondCard, 2);// Reveal the second card
        setDealerPoints(dealerTotalValue);
        if (doubleDown) {
            playerCardImage(playerThirdCard, 3);// Reveal the second card
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
        hitStandLabel.setText("Dealers Turn");
        standButton.setText("Continue");
        standButton.setOnAction(event -> {
            dealerTurn();
            standButton.setText("Stand");
        });
    }

    public void checkResult() {
        situation = "checkResult";

        if (playerTotalValue > 21) {
            // you lost :) text call
            setResultLabel("You lost");
            loss = true;
            playerBetSettlement();
        }
        else {
            if (natural) {
                if (dealerTotalValue == 21) {
                    // draw text call
                    setResultLabel("Draw");
                    playerBetSettlement();
                }
                else {
                    // you won :( text call
                    setResultLabel("You won");
                    win = true;
                    playerBetSettlement();
                }
            }
            else {
                if(dealerTotalValue < 22 && dealerTotalValue > playerTotalValue) {
                    // you lost :) text call
                    setResultLabel("You lost");
                    loss = true;
                    playerBetSettlement();
                }
                else if(dealerTotalValue == playerTotalValue) {
                    // draw text call
                    setResultLabel("Draw");
                    playerBetSettlement();
                }
                else {
                    // you won :( text call
                    setResultLabel("You won");
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
        // button visibility and manageability
        yesButton.setVisible(true);
        yesButton.setManaged(true);
        noButton.setVisible(true);
        noButton.setManaged(true);
        continueLabel.setVisible(true);
        // yesButton to reset()
        // noButton to switchToTitle()
    }

    public void reset() {
        dealerCardImageClear();
        playerCardImageClear();
        for(int i=1; i < 6; i++) {
            playerCardNum[i]=0;
            playerCardValue[i]=0;
            dealerCardNum[i]=0;
            dealerCardValue[i]=0;
        }
        playerHas=0;
        setPlayerPoints(playerHas);
        dealerHas=0;
        resetDealerPoints();
        // yesButton to startGame()
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



// DAVID
// SceneBuilder framework
// fxml edits by DAVID (11/25/2022)
// edits by SAM (11/24/2022, 11/25/2022)
