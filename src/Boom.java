import javax.swing.*;
import java.awt.*;

public class Boom {
    int timeExplosion = 3000;
    public int lenght = 2;

    public long timeStart;
    public int x;
    public int y;


    public int imgIndex = 0;


    public Boom(int x, int y, int lenght, long timeStart) {
        this.x = x;
        this.y = y;
        this.timeStart = timeStart;
        this.lenght = lenght;
    }

    Image[] imgBoom = {
            new ImageIcon(getClass().getResource("/Images/boom1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom5.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom6.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom7.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom8.png")).getImage(),
    };


    public boolean isExist(int newx, int newy) {
        Rectangle rec1 = new Rectangle(x, y, 45, 45);
        Rectangle rec2 = new Rectangle(newx, newy, 45, 45);
        return rec1.intersects(rec2);
    }

    public void draw(Graphics2D g2d) {
        imgIndex++;
        g2d.drawImage(imgBoom[imgIndex / 15 % imgBoom.length], x, y, 45, 45, null);

    }
    public void setTimeExplosion(int t){
        this.timeExplosion=t;
    }

}

