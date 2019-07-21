import javax.swing.*;
import java.awt.*;

class GUI extends JFrame {
    private static final int WIDTHF = 905;
    private static final int HEIGHTF = 705;

    GUI() {
        this.setSize(WIDTHF, HEIGHTF);
        this.setLayout(new CardLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Container container = new Container();
        this.add(container);
        this.setVisible(true);
    }


}
