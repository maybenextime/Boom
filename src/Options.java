import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Options extends JPanel {
    Container container;
    JLabel player1;
    JLabel player2;
    JLabel player3;
    JLabel back;
    JLabel soundInGame;
    JLabel musicInGame;
    Boolean isMusic = true;
    Boolean isSound = true;
    FontAB font= new FontAB(20);
    Boolean enter1=false;
    Boolean enter2=false;
    Boolean enter3=false;


    public Options(Container container){
        this.container=container;
        this.init();
        this.initComp();
    }

    private void init(){
        this.setLayout(null);
    }

    private void initComp(){
        back= new JLabel();
        ImageIcon imgBack1= new ImageIcon(getClass().getResource("/Images/back1.png"));
        back.setIcon(imgBack1);
        back.setBounds(800, 500, 75, 75);
        back.addMouseListener(mouse);
        this.add(back);


        ImageIcon checkBox = new ImageIcon(getClass().getResource("/Images/checkbox2.png"));
        musicInGame= new JLabel();
        musicInGame.setIcon(checkBox);
        musicInGame.setFont(font.fontAB);
        musicInGame.setText("Music In Game");
        musicInGame.setBounds(620, 200,200,70);
        musicInGame.addMouseListener(mouse);
        this.add(musicInGame);

        soundInGame= new JLabel();
        soundInGame.setIcon(checkBox);
        soundInGame.setFont(font.fontAB);
        soundInGame.setText("Sound In Game");
        soundInGame.setBounds(620, 300,200,70);
        soundInGame.addMouseListener(mouse);
        this.add(soundInGame);


        player1= new JLabel();
        player2= new JLabel();
        player3= new JLabel();

        ImageIcon imgPlayer1= new ImageIcon(getClass().getResource("/Images/player_down_1.png"));
        ImageIcon imgPlayer2= new ImageIcon(getClass().getResource("/Images/player_down_1.png"));
        ImageIcon imgPlayer3= new ImageIcon(getClass().getResource("/Images/player_down_1.png"));

        player1.setIcon(imgPlayer1);
        player1.setBounds(150,50,300,120);
        player1.addMouseListener(mouse);

        player2.setIcon(imgPlayer2);
        player2.setBounds(150,200,300,120);
        player2.addMouseListener(mouse);

        player3.setIcon(imgPlayer3);
        player3.setBounds(150,350,300,120);
        player3.addMouseListener(mouse);

        this.add(player1);
        this.add(player2);
        this.add(player3);

    }

    public void drawPlayer(Graphics g, int cont, int speed, int quantity, int length ){
            g.setFont(font.fontAB);
            g.drawString("Speed     :"+ String.valueOf(speed),300, cont);
            g.drawString("Quantity  :"+ String.valueOf(quantity),300, cont+35);
            g.drawString("Lenght    :"+ String.valueOf(length),300, cont+70);
    }

    public void drawF(Graphics g,int numb){
        g.setColor(Color.GREEN);
        g.drawRect(140,70+150*numb,300,120);
        g.setColor(Color.BLACK);
    }

    private MouseListener mouse = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == back) {
                Options.this.container.isMusic= isMusic;
                Options.this.container.isSound= isSound;
                container.showMenu();
            }
            if (label == player1){

            }
            if (label == musicInGame){
                if (isMusic){
                    ImageIcon checkBox2 = new ImageIcon(getClass().getResource("/Images/checkbox.png"));
                    musicInGame.setIcon(checkBox2);
                    isMusic=false;
                    Options.this.container.audio.getAudio("/Sounds/menu.wav").stop();
                } else {
                    ImageIcon checkBox = new ImageIcon(getClass().getResource("/Images/checkbox2.png"));
                    musicInGame.setIcon(checkBox);
                    isMusic= true;
                }
            }

            if (label == soundInGame){
                if (isSound){
                    ImageIcon checkBox2 = new ImageIcon(getClass().getResource("/Images/checkbox.png"));
                    soundInGame.setIcon(checkBox2);
                    isSound=false;
                } else {
                    ImageIcon checkBox = new ImageIcon(getClass().getResource("/Images/checkbox2.png"));
                    soundInGame.setIcon(checkBox);
                    isSound= true;
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
            JLabel label = (JLabel) e.getSource() ;
            if (label == back) {
                ImageIcon imgBack2 = new ImageIcon(getClass().getResource("/Images/back2.png"));
                back.setIcon(imgBack2);
            }
            if (label == player1) {
                enter1=true;
                repaint();
            }
            if (label == player2) {
                enter2=true;
                repaint();
            }
            if (label == player3) {
                enter3=true;
                repaint();
            }
            }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getSource() ;
            if (label == back) {
                ImageIcon imgBack1 = new ImageIcon(getClass().getResource("/Images/back1.png"));
                back.setIcon(imgBack1);
            }
            if (label == player1) {
                enter1=false;
                repaint();
            }
            if (label == player2) {
                enter2=false;
                repaint();
            }
            if (label == player3) {
                enter3=false;
                repaint();
            }
        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage imgBackGround = null;
        try {
            imgBackGround = ImageIO.read(getClass().getResourceAsStream("/Images/BGOptions.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(imgBackGround, 0, 0, 905, 610, null);

        drawPlayer(g,100,  2, 2 , 2 );
        drawPlayer(g,250,  2, 2 , 2 );
        drawPlayer(g,400,  2, 2 , 2 );
        g.fillRect(590, 0,10,610);
        if(enter1) drawF(g,0);
        if(enter2) drawF(g,1);
        if(enter3) drawF(g,2);

    }

}
