package application;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class UIController {

    private Stage stage;
    private Scene scene;
    public Parent root;

    public UIController() {
    }


    public void switchToScene1() throws IOException {
        root = FXMLLoader.load(getClass().getResource("application/Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Scene2.fxml Skeleton

    @FXML // fx:id="ambientLight"
    private AmbientLight ambientLight; // Value injected by FXMLLoader

    @FXML // fx:id="dealerCard1"
    public ImageView dealerCard1; // Value injected by FXMLLoader

    @FXML // fx:id="dealerCard2"
    public ImageView dealerCard2; // Value injected by FXMLLoader

    @FXML // fx:id="dealerCard3"
    public ImageView dealerCard3; // Value injected by FXMLLoader

    @FXML // fx:id="dealerCard4"
    public ImageView dealerCard4; // Value injected by FXMLLoader

    @FXML // fx:id="dealerCard5"
    public ImageView dealerCard5; // Value injected by FXMLLoader

    @FXML // fx:id="dealerCard6"
    public ImageView dealerCard6; // Value injected by FXMLLoader

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

    @FXML // fx:id="dealerPoints"
    public Label dealerPoints; // Value injected by FXMLLoader

    public void setDealerPoints(int points) {
        dealerPoints.setText("Dealer: " + points);
    }

    @FXML // fx:id="dealerSide"
    public HBox dealerSide; // Value injected by FXMLLoader

    @FXML // fx:id="exit"
    public Button exit; // Value injected by FXMLLoader

    @FXML // fx:id="hit"
    public Button hit; // Value injected by FXMLLoader

    @FXML // fx:id="stand"
    public Button stand; // Value injected by FXMLLoader

    public void buttonLoad() {

    }

    @FXML
    public void hitHandler(ActionEvent event) {

    }

    @FXML
    public void standHandler(ActionEvent event) {

    }

    @FXML
    public void exitHandler(ActionEvent event) {

    }

    @FXML // fx:id="playerCard1"
    public ImageView playerCard1; // Value injected by FXMLLoader

    @FXML // fx:id="playerCard2"
    public ImageView playerCard2; // Value injected by FXMLLoader

    @FXML // fx:id="playerCard3"
    public ImageView playerCard3; // Value injected by FXMLLoader

    @FXML // fx:id="playerCard4"
    public ImageView playerCard4; // Value injected by FXMLLoader

    @FXML // fx:id="playerCard5"
    public ImageView playerCard5; // Value injected by FXMLLoader

    @FXML // fx:id="playerCard6"
    public ImageView playerCard6; // Value injected by FXMLLoader

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

    @FXML // fx:id="playerPoints"
    public Label playerPoints; // Value injected by FXMLLoader

    public void setPlayerPoints(int points) {
        dealerPoints.setText("Player: " + points);
    }

    @FXML // fx:id="playerSide"
    public HBox playerSide; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private Pane table; // Value injected by FXMLLoader

    @FXML // fx:id="tableOverlay"
    private Rectangle tableOverlay; // Value injected by FXMLLoader

}
// DAVID
// SceneBuilder framework
// edits by SAM (11/24/2022)
