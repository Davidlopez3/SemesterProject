import javax.swing.ImageIcon;
import java.util.Objects;

public class Cards {

    ImageIcon front = new ImageIcon();
    ImageIcon[] spades = new ImageIcon[14];
    ImageIcon[] hearts = new ImageIcon[14];
    ImageIcon[] clubs = new ImageIcon[14];
    ImageIcon[] diamonds = new ImageIcon[14];

    public Cards() {

        front = new ImageIcon(Objects.requireNonNull
                (getClass().getClassLoader().getResource("front.png")));

        // SPADES
        for(int num=1; num<14; num++) {
            spades[num]  = new ImageIcon(Objects.requireNonNull
                    (getClass().getClassLoader().getResource(num + "S.png")));
        }

        // HEARTS
        for(int num=1; num<14; num++) {
            hearts[num]  = new ImageIcon(Objects.requireNonNull
                    (getClass().getClassLoader().getResource(num + "H.png")));

        }

        // CLUBS
        for(int num=1; num<14; num++) {
            clubs[num]  = new ImageIcon(Objects.requireNonNull
                    (getClass().getClassLoader().getResource(num + "C.png")));

        }

        // DIAMONDS
        for(int num=1; num<14; num++) {
            diamonds[num]  = new ImageIcon(Objects.requireNonNull
                    (getClass().getClassLoader().getResource(num + "D.png")));
        }
    }
}