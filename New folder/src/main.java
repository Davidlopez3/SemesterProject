import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Scanner;


public class main extends Application {


    public void secondstage (Stage stage){

        try {

            Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            String css = this.getClass().getResource("application.css").toExternalForm();
           scene.getStylesheets().add(css);
            //scene2.getStylesheets().add(css);
            stage.setScene(scene);


            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {



        Group root = new Group();

        //Title
        Text h1 =  new Text( "BlackJack");
        h1.setFill(Color.RED);
        h1.setX(385);
        h1.setY(170);
        h1.setFont(Font.font("broadway", FontWeight.BOLD, FontPosture.ITALIC, 200));

        // Drop shadow
        Text h2 =  new Text( "BlackJack");
        h2.setFill(Color.YELLOW);
        h2.setX(380);
        h2.setY(170);
        h2.setFont(Font.font("broadway", FontWeight.BOLD, FontPosture.ITALIC, 200));

        //Image
        Image image1 = new Image ("file:Back card three.png");
        ImageView imageView = new ImageView(image1);
        imageView.setX(750);
        imageView.setY(250);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(1200);
        imageView.setFitWidth(400);
      //  one.getChildren().add(image1);
        VBox vbox = new VBox(20,imageView);
        vbox.setAlignment(Pos.CENTER);


        //Enter
        Text h3 =  new Text("press enter to start");
        h3.setFill(Color.WHITE);
        h3.setX(420);
        h3.setY(975);
        h3.setFont(Font.font("broadway", FontWeight.BOLD, FontPosture.ITALIC, 100));

        //Drop Shadow
        Text h4 =  new Text("press enter to start");
        h4.setFill(Color.YELLOW);
        h4.setX(420);
        h4.setY(973);
        h4.setFont(Font.font("broadway", FontWeight.BOLD, FontPosture.ITALIC, 100));


        root.getChildren().add(h1);
        root.getChildren().add(h2);
        root.getChildren().add(h3);
        root.getChildren().add(h4);
        root.getChildren().add(imageView);

        Scene scene = new Scene( root,1900 , 1000, Color.BLACK);
        //CSS

      //  if promptEnterKey
       // System.exit(0);


        // of enter then fade out
        //stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("BlackJack Demo");
        primaryStage.show();
    }


    public void promptEnterKey(){
       // System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}
// all the layers https://edencoding.com/scene-background/
// https://www.reddit.com/r/JavaFX/comments/izrr3y/timeline_with_fade_in_and_fade_out_text_label/