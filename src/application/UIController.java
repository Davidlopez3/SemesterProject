package application;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    // CLASS INITIALIZATIONS
    Cards card = new Cards();

    // Table.fxml Framework

    @FXML
    private ImageView dealerCard1;

    @FXML
    private ImageView dealerCard2;

    @FXML
    private ImageView dealerCardFront;

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

    public void setDealerPoints() {
        dealerTotalLabel.setText("Dealer: " + dealerTotalValue());
    }

    public void resetDealerPoints() {
        dealerTotalLabel.setText("Dealer: ?");
    }

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

    public void setPlayerPoints() {
        playerTotalLabel.setText("Player: " + playerTotalValue());
    }

    public void resetPlayerPoints() {playerTotalLabel.setText("Player: ");}

    @FXML
    public Label hitStandLabel;

    @FXML
    public Button hitButton;

    @FXML
    public Button standButton;

    @FXML
    private Label resultLabel;

    public void setResultLabel(String result) {resultLabel.setText("Result: " + result);}

    public void resetResultLabel() {resultLabel.setText("Result: ");}

    @FXML
    public Label continueLabel;

    @FXML
    public Button yesButton;

    @FXML
    public Button noButton;

    // GAME CLASS INSERT

    // CARD INITIALIZATIONS
    // pickedCardNum and the amount of cards the player and dealer starts with initialized
    private int pickedCardNum;
    private String pickedStringValue;
    private int playerHas = 0;
    private int dealerHas = 0;
    private final int[] playerCardNum = new int[6];
    private final int[] dealerCardNum = new int[6];
    private final int[] playerCardValue = new int[6];
    private final int[] dealerCardValue = new int[6];
    // total card values for player and dealer
    private int playerTotalValue;
    private int dealerTotalValue;


    // MISC INITIALIZATIONS
    String situation = "";

    // BET INITIALIZATIONS
    private boolean natural;

    public void startGame() {
        yesButton.setOnAction(event -> {
            reset();
            // manage button visibility for yesButton onAction=#start
            yesButton.setVisible(false);
            yesButton.setManaged(false);
            noButton.setVisible(false);
            noButton.setManaged(false);
            continueLabel.setVisible(false);

            dealerDraw();
            playerDraw();
            dealerDraw();
            // sets dealers second card fill in to
            dealerCardFront.setVisible(true);
            dealerCardFront.setImage(card.front);
            playerTurn();
        });
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
        // playerCardStringValue[playerHas] = pickedStringValue;
        playerCardValue[playerHas] = checkCardValue();

        // sets the imageView to the given card passed through pickedCard
        playerCardImage(pickedCard, playerHas);

        // total value
        playerTotalValue = playerTotalValue();
    }

    @FXML
    // hitButton onAction=#playerTurn
    public void playerTurn() {
        playerDraw();
        // setPlayerPoints label to total card value
        setPlayerPoints();
        // Player has busted
        if (playerTotalValue > 21) {
            dealerOpen();
        }
        // Player has a natural (21 on initial draw)
        else if (playerTotalValue == 21) {
            playerNatural();
        }
        // Player has reached the integer limit for card hits; deny out of bounds exception
        else if (playerHas == 7) {
            dealerOpen();
        }
        hitButton.setOnAction(event -> {
            // Player has busted
            if (playerTotalValue > 21) {
                dealerOpen();
            }
            // Player has a natural (21 on initial draw)
            else if (playerTotalValue == 2) {
                playerNatural();
            }

            else {
                // Player can hit another card
                if (playerHas > 1 && playerHas < 7) {
                    // hit and stand state true, player default
                    playerTurn();

                }
                // Player has reached the integer limit for card hits; deny out of bounds exception
                if (playerHas == 7) {
                    dealerOpen();
                }
            }
        });
    }

    public void playerNatural() {
        situation = "playerNatural";
        // boolean natural set to true for dealer methods
        natural = true;

        dealerOpen();
    }

    @FXML
    // standButton onAction=#dealerOpen
    public void dealerOpen() {
        // reveal second card
        dealerCardFront.setVisible(false); // Reveal the second card
        setDealerPoints();

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
        standButton.setOnAction(event -> {
            // reveal second card
            dealerCardFront.setVisible(false);// Reveal the second card
            setDealerPoints();

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
        });
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

        dealerTurn();
    }

    public void checkResult() {
        situation = "checkResult";
        setDealerPoints();

        if (playerTotalValue > 21) {
            // you lost :) text call
            setResultLabel("You lost");
//            loss = true;
            gameFinish();
        }
        else {
            if (natural) {
                if (dealerTotalValue == 21) {
                    // draw text call
                    setResultLabel("Draw");
                    gameFinish();
                }
                else {
                    // you won :( text call
                    setResultLabel("You won");
//                    win = true;
                    gameFinish();
                }
            }
            else {
                if(dealerTotalValue < 22 && dealerTotalValue > playerTotalValue) {
                    // you lost :) text call
                    setResultLabel("You lost");
//                    loss = true;
                    gameFinish();
                }
                else if(dealerTotalValue == playerTotalValue) {
                    // draw text call
                    setResultLabel("Draw");
                    gameFinish();
                }
                else {
                    // you won :( text call
                    setResultLabel("You won");
//                    win = true;
                    gameFinish();
                }
            }
        }
    }

    public void gameFinish() {
        situation = "gameFinish";
        // button visibility and manageability
        yesButton.setVisible(true);
        yesButton.setManaged(true);
        noButton.setVisible(true);
        noButton.setManaged(true);
        continueLabel.setText("Do you want to keep playing?");
        continueLabel.setVisible(true);
        // hit and stand button manageability false
        hitButton.setManaged(false);
        standButton.setManaged(false);
    }

    public void reset() {
        dealerCardImageClear();
        playerCardImageClear();
        resetResultLabel();
        for(int i=1; i < 6; i++) {
            playerCardNum[i]=0;
            playerCardValue[i]=0;
            dealerCardNum[i]=0;
            dealerCardValue[i]=0;
        }
        playerHas=0;
        resetPlayerPoints();
        dealerHas=0;
        resetDealerPoints();
        hitButton.setManaged(true);
        standButton.setManaged(true);
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
}

// DAVID
// SceneBuilder framework
// fxml edits by DAVID (11/25/2022)
// edits by SAM (11/24/2022, 11/25/2022)
