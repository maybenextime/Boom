import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Options extends JPanel {
    private Container container;
    private JLabel player1;
    private JLabel player2;
    private JLabel player3;
    int player;

    private JLabel back;
    private JLabel soundInGame;
    private JLabel musicInGame;
    private Boolean isMusic = false;
    private Boolean isSound = true;
    private FontAB font = new FontAB(20);
    private Boolean enter1 = false;
    private Boolean enter2 = false;
    private Boolean enter3 = false;
    private Boolean getPlayer1 = false;
    private Boolean getPlayer2 = false;
    private Boolean getPlayer3 = false;

    private Image check = new ImageIcon(getClass().getResource("/Images/check.png")).getImage();


    Options(Container container) {
        this.container = container;
        this.init();
        this.initComp();
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


        ImageIcon checkBox2 = new ImageIcon(getClass().getResource("/Images/checkbox.png"));
        musicInGame = new JLabel();
        musicInGame.setIcon(checkBox2);
        musicInGame.setFont(font.fontAB);
        musicInGame.setText("Music In Game");
        musicInGame.setBounds(620, 200, 200, 70);
        musicInGame.addMouseListener(mouse);
        this.add(musicInGame);
        ImageIcon checkBox = new ImageIcon(getClass().getResource("/Images/checkbox2.png"));

        soundInGame = new JLabel();
        soundInGame.setIcon(checkBox);
        soundInGame.setFont(font.fontAB);
        soundInGame.setText("Sound In Game");
        soundInGame.setBounds(620, 300, 200, 70);
        soundInGame.addMouseListener(mouse);
        this.add(soundInGame);


        player1 = new JLabel();
        player2 = new JLabel();
        player3 = new JLabel();

        player1.setBounds(150, 50, 300, 120);
        player1.addMouseListener(mouse);

        player2.setBounds(150, 200, 300, 120);
        player2.addMouseListener(mouse);

        player3.setBounds(150, 350, 300, 120);
        player3.addMouseListener(mouse);

        this.add(player1);
        this.add(player2);
        this.add(player3);
    }

    private void drawInfoPlayer(Graphics g, int cont, int speed, int quantity, int length) {
        g.setFont(font.fontAB);
        g.drawString("Speed     :" + String.valueOf(speed), 300, cont);
        g.drawString("Quantity  :" + String.valueOf(quantity), 300, cont + 35);
        g.drawString("Lenght    :" + String.valueOf(length), 300, cont + 70);
    }

    private void drawF(Graphics g, int numb) {
        g.setColor(Color.GREEN);
        g.drawRect(140, 70 + 150 * numb, 300, 120);
        g.setColor(Color.BLACK);
    }

    private void drawCheckPlayer(Graphics g, int numb) {
        g.drawImage(check, 80, 100 + 150 * numb, null);
    }

    private MouseListener mouse = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == back) {
                Options.this.container.isMusic = isMusic;
                Options.this.container.isSound = isSound;
                container.showMenu();
            }
            if (label == player1) {
                player = 1;
                getPlayer1 = true;
                getPlayer2 = false;
                getPlayer3 = false;
            }
            if (label == player2) {
                player = 2;
                getPlayer1 = false;
                getPlayer2 = true;
                getPlayer3 = false;
            }
            if (label == player3) {
                player = 3;
                getPlayer1 = false;
                getPlayer2 = false;
                getPlayer3 = true;
            }
            if (label == musicInGame) {
                if (isMusic) {
                    ImageIcon checkBox2 = new ImageIcon(getClass().getResource("/Images/checkbox.png"));
                    musicInGame.setIcon(checkBox2);
                    isMusic = false;
                    Options.this.container.audio.getAudio("/Sounds/menu.wav").stop();
                } else {
                    ImageIcon checkBox = new ImageIcon(getClass().getResource("/Images/checkbox2.png"));
                    musicInGame.setIcon(checkBox);
                    isMusic = true;
                }
            }

            if (label == soundInGame) {
                if (isSound) {
                    ImageIcon checkBox2 = new ImageIcon(getClass().getResource("/Images/checkbox.png"));
                    soundInGame.setIcon(checkBox2);
                    isSound = false;
                } else {
                    ImageIcon checkBox = new ImageIcon(getClass().getResource("/Images/checkbox2.png"));
                    soundInGame.setIcon(checkBox);
                    isSound = true;
                }
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
            if (label == player1) {
                enter1 = true;
                repaint();
            }
            if (label == player2) {
                enter2 = true;
                repaint();
            }
            if (label == player3) {
                enter3 = true;
                repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == back) {
                ImageIcon imgBack1 = new ImageIcon(getClass().getResource("/Images/back1.png"));
                back.setIcon(imgBack1);
            }
            if (label == player1) {
                enter1 = false;
                repaint();
            }
            if (label == player2) {
                enter2 = false;
                repaint();
            }
            if (label == player3) {
                enter3 = false;
                repaint();
            }
        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image imgBackGround = new ImageIcon(getClass().getResource("/Images/BGOptions.png")).getImage();
        g.drawImage(imgBackGround, 0, 0, 905, 675, null);
        drawInfoPlayer(g, 100, 2, 1, 1);
        drawInfoPlayer(g, 250, 1, 1, 2);
        drawInfoPlayer(g, 400, 1, 2, 1);
        g.fillRect(590, 0, 10, 675);
        if (enter1) drawF(g, 0);
        if (enter2) drawF(g, 1);
        if (enter3) drawF(g, 2);
        if (getPlayer1) drawCheckPlayer(g, 0);
        if (getPlayer2) drawCheckPlayer(g, 1);
        if (getPlayer3) drawCheckPlayer(g, 2);
        Image imgPlayer1 = new ImageIcon(getClass().getResource("/Images/player_down_1.png")).getImage();
        Image effect1 = new ImageIcon(getClass().getResource("/Images/effect7.png")).getImage();

        Image imgPlayer2 = new ImageIcon(getClass().getResource("/Images/player_down_1.png")).getImage();
        Image effect2 = new ImageIcon(getClass().getResource("/Images/effect26.png")).getImage();

        Image imgPlayer3 = new ImageIcon(getClass().getResource("/Images/player_down_1.png")).getImage();
        Image effect3 = new ImageIcon(getClass().getResource("/Images/effect16.png")).getImage();

        g.drawImage(effect1, 132, 72, null);
        g.drawImage(imgPlayer1, 150, 85, null);

        g.drawImage(effect2, 137, 222, null);
        g.drawImage(imgPlayer2, 150, 235, null);

        g.drawImage(effect3, 132, 372, null);
        g.drawImage(imgPlayer3, 150, 385, null);


    }

}
