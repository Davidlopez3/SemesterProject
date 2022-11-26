import application.UIController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {

    public Button start = new Button();

    public main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

        //Title
        Text h1 =  new Text( "BlackJack");
        h1.setFill(Color.RED);
        h1.setX(253);
        h1.setY(170);
        h1.setFont(Font.font("broadway", FontWeight.BOLD, FontPosture.ITALIC, 200));

        // Drop shadow
        Text h2 =  new Text( "BlackJack");
        h2.setFill(Color.YELLOW);
        h2.setX(250);
        h2.setY(170);
        h2.setFont(Font.font("broadway", FontWeight.BOLD, FontPosture.ITALIC, 200));

        //Enter
        Text h3 =  new Text("START");
        h3.setFill(Color.RED);
        h3.setTextAlignment(TextAlignment.CENTER);
        h3.setX(700);
        h3.setY(770);
        h3.setFont(Font.font("broadway", FontWeight.BOLD, FontPosture.ITALIC, 60));

        //Drop Shadow
        Text h4 =  new Text("START");
        h4.setFill(Color.YELLOW);
        h4.setTextAlignment(TextAlignment.CENTER);
        h4.setX(700);
        h4.setY(770);
        h4.setFont(Font.font("broadway", FontWeight.BOLD, FontPosture.ITALIC, 60));

        Group text = new Group(h1, h2, h3, h4);

        //Image
        Image image1 = new Image ("file:CardsFinished/BackCardTwo.png");
        ImageView imageView = new ImageView(image1);
        imageView.setX(500);
        imageView.setY(200);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(800);
        imageView.setFitWidth(320);
        //  one.getChildren().add(image1);
        VBox vbox = new VBox(20,imageView);
        vbox.setAlignment(Pos.CENTER);

        //Button Prompt
        start.setVisible(false);
        start.setManaged(true);
        start.setMinSize(1200, 900);
        start.setMaxSize(1200, 900);
        start.setOnAction(event -> {
            try {
                UIController uiController = new UIController();
                uiController.switchToTable(startHandler(start.isPressed()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        root.getChildren().add(text);
        root.getChildren().add(imageView);


        Scene scene = new Scene( root,1200 , 900, Color.BLACK);
        //CSS

        // of enter then fade out
        //stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("BlackJack Final");
        primaryStage.show();
    }

    public ActionEvent startHandler(boolean start) {
        if (start) {
            return new ActionEvent();
        }
        return null;
    }
}

// all the layers https://edencoding.com/scene-background/
// https://www.reddit.com/r/JavaFX/comments/izrr3y/timeline_with_fade_in_and_fade_out_text_label/

// DAVID
//edits by SAM (11/24/2022 and 11/25/2022)