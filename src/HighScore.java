import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class HighScore extends JPanel {
    private Container container;
    private ArrayList<Pair<String, String>> listHighScore;
    private JLabel back;
    private FontAB font = new FontAB(90);
    private FontAB font2 = new FontAB(50);

    HighScore(Container container) {
        this.container = container;
        this.init();
        this.initComp();
        getListHighScore();
    }

    private void init() {
        this.setLayout(null);
    }

    private void initComp() {
        back = new JLabel();
        ImageIcon imgBack1 = new ImageIcon(getClass().getResource("/Images/back1.png"));
        back.setIcon(imgBack1);
        back.setBounds(800, 500, 75, 75);
        back.addMouseListener(mouse);
        this.add(back);
    }

    private void getListHighScore() {
        listHighScore = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream("src/HighScores/HighScores.txt")));
            String line;
            while ((line = file.readLine()) != null) {
                String str[] = line.split(":");
                listHighScore.add(new Pair<>(str[0], str[1]));
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MouseListener mouse = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == back) {
                container.showMenu();
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
            if (label == back) {
                ImageIcon imgBack2 = new ImageIcon(getClass().getResource("/Images/back2.png"));
                back.setIcon(imgBack2);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == back) {
                ImageIcon imgBack1 = new ImageIcon(getClass().getResource("/Images/back1.png"));
                back.setIcon(imgBack1);
            }
        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image imgBackGround  =new ImageIcon(getClass().getResource("/Images/BGHighScore.png")).getImage();
        g.drawImage(imgBackGround, 0, 0, 905, 675, null);
        g.setColor(Color.GREEN);
        g.setFont(font.fontAB);
        g.drawString("HIGH SCORE", 260, 80);
        g.setFont(font2.fontAB);
        int pad = 115;
        for (int i = 0; i < listHighScore.size(); i++) {
            g.drawString(listHighScore.get(i).getKey(), 240, 175 + pad * i);
            g.drawString(listHighScore.get(i).getValue(), 600, 175 + pad * i);
        }

    }
}
