import javax.swing.*;
import java.awt.*;

class Player2 extends Player {
    Image[] effect = {
            new ImageIcon(getClass().getResource("/Images/effect22.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect23.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect24.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect25.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect26.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect27.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect28.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/effect29.png")).getImage(),
    };

    Player2() {
        super(1, 1, 2);
        setEffect(this.effect);
    }
}