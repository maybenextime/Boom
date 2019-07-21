import javax.swing.*;
import java.awt.*;

class Player1 extends Player {
    private Image[] effect = {
            new ImageIcon(getClass().getResource("/Images/effect1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect5.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect6.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect7.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect8.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect9.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect10.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect11.png")).getImage()
    };


    Player1() {
        super(2, 1, 1);
        setEffect(this.effect);
    }
}
