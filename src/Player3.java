import javax.swing.*;
import java.awt.*;

class Player3 extends Player {
    private Image[] effect = {
            new ImageIcon(getClass().getResource("/Images/effect12.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect13.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect14.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect15.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect16.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect17.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect18.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect19.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect20.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect21.png")).getImage()
    };

    Player3() {
        super(1, 2, 1);
        setEffect(this.effect);
    }
}