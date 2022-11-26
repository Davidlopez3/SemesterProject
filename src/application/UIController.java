package application;
import java.io.IOException;
import java.util.Objects;

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

    public void switchToTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("application.Tables.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
        switch(num) {
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
        switch(num) {
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


}


// DAVID
// SceneBuilder framework
// fxml edits by DAVID (11/25/2022)
// edits by SAM (11/24/2022, 11/25/2022)
