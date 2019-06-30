import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayGame extends JPanel {
    Container container;
    Manager manager ;

    public PlayGame(Container container){
        this.container= container;
        this.manager= new Manager();
        init();
    }

    private void init(){
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        BufferedImage infoBg = null;
        try {
            infoBg = ImageIO.read(getClass().getResourceAsStream("/Images/inGameInfo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(infoBg, 675, 0, null);
        manager.map.drawMap(g);
    }

}
