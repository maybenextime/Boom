import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu extends JPanel {
    JLabel lPlay = new JLabel();
    JLabel lOptions = new JLabel();
    JLabel lHighScore = new JLabel();
    JLabel lExit = new JLabel();
    Container container;

    public Menu(Container container) {
        this.container = container;
        this.init();
        this.initComp();
    }

    private void init() {
        this.setLayout(null);
    }

    private void initComp() {

        ImageIcon imgPlay = new ImageIcon(getClass().getResource("/Images/play1.png"));
        ImageIcon imgOptions = new ImageIcon(getClass().getResource("/Images/options1.png"));
        ImageIcon imgHighScore = new ImageIcon(getClass().getResource("/Images/highScore1.png"));
        ImageIcon imgExit = new ImageIcon(getClass().getResource("/Images/exit1.png"));

        lPlay.setIcon(imgPlay);
        lPlay.setBounds(350, 140, 250, 55);
        lPlay.addMouseListener(mouse);

        lOptions.setIcon(imgOptions);
        lOptions.setBounds(350, 220, 250, 55);
        lOptions.addMouseListener(mouse);

        lHighScore.setIcon(imgHighScore);
        lHighScore.setBounds(350, 300, 250, 55);
        lHighScore.addMouseListener(mouse);

        lExit.setIcon(imgExit);
        lExit.setBounds(350, 380, 250, 55);
        lExit.addMouseListener(mouse);

        this.add(lPlay);
        this.add(lOptions);
        this.add(lHighScore);
        this.add(lExit);
    }

    private MouseListener mouse = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == lPlay) {
                Menu.this.container.showPLayGame();

            }

            if (label == lOptions) {
                Menu.this.container.showOptions();
            }


            if (label == lHighScore) {
                Menu.this.container.showHighScore();
            }

            if (label == lExit) {
                System.exit(0);
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {

            JLabel label = (JLabel) e.getSource();
            if (label == lPlay) {
                ImageIcon imgPlay2 = new ImageIcon(getClass().getResource("/Images/play2.png"));
                lPlay.setIcon(imgPlay2);
            }
            if (label == lOptions) {
                ImageIcon imgOptions2 = new ImageIcon(getClass().getResource("/Images/options2.png"));
                lOptions.setIcon(imgOptions2);
            }
            if (label == lHighScore) {
                ImageIcon imgHighScore2 = new ImageIcon(getClass().getResource("/Images/highScore2.png"));
                lHighScore.setIcon(imgHighScore2);
            }
            if (label == lExit) {
                ImageIcon imgExit2 = new ImageIcon(getClass().getResource("/Images/exit2.png"));
                lExit.setIcon(imgExit2);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == lPlay) {
                ImageIcon imgPlay1 = new ImageIcon(getClass().getResource("Images/play1.png"));
                lPlay.setIcon(imgPlay1);
            }
            if (label == lOptions) {
                ImageIcon imgOptions1 = new ImageIcon(getClass().getResource("Images/options1.png"));
                lOptions.setIcon(imgOptions1);
            }
            if (label == lHighScore) {
                ImageIcon imgHighScore1 = new ImageIcon(getClass().getResource("Images/highScore1.png"));
                lHighScore.setIcon(imgHighScore1);
            }
            if (label == lExit) {
                ImageIcon imgExit1 = new ImageIcon(getClass().getResource("Images/exit1.png"));
                lExit.setIcon(imgExit1);
            }
        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage imgBackGround = null;
        try {
            imgBackGround = ImageIO.read(getClass().getResourceAsStream("/Images/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(imgBackGround, 0, 0, 905, 675, null);
    }


}