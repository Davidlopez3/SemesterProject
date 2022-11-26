package application;

import javafx.scene.image.Image;

public class Cards {

    Image front;
    Image[] spades = new Image[14];
    Image[] hearts = new Image[14];
    Image[] clubs = new Image[14];
    Image[] diamonds = new Image[14];

    public Cards() {

        // FRONT
        front = new Image("file:CardsFinished/BackCardTwo.png");

        // SPADES
        for(int num=1; num<14; num++) {
            spades[num] = new Image ("file:CardsFinished/Spades/S" + num + ".png");
        }

        // HEARTS
        for(int num=1; num<14; num++) {
            hearts[num]  = new Image("file:CardsFinished/Hearts/H" + num + ".png");

        }

        // CLUBS
        for(int num=1; num<14; num++) {
            clubs[num]  = new Image("file:CardsFinished/Clubs/C" + num + ".png");

        }

        // DIAMONDS
        for(int num=1; num<14; num++) {
            diamonds[num]  = new Image("file:CardsFinished/Diamonds/D" + num + ".png");
        }
    }
}

// code based on https://www.ryisnow.online/2021/05/java-sample-code-black-jack-game.html
// alterations to change from Swing to JavaFX format

// SAM