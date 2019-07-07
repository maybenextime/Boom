import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public static final int WIDTHF = 905;
    public static final int HEIGHTF = 800;
    public Container container;

    public GUI() {
        this.setSize(WIDTHF, HEIGHTF);
        this.setLayout(new CardLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container = new Container();
        this.add(container);
        this.setVisible(true);
    }


}
