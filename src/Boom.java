import javax.swing.*;
import java.awt.*;

public class Boom {
    private static int size = 45;
    private int timeExplosion = 3000;
    private int lenght;
    long timeStart;

    public int x;
    public int y;
    private int imgIndex = 0;


    Boom(int x, int y, int lenght, long timeStart) {
        this.x = x;
        this.y = y;
        this.timeStart = timeStart;
        this.lenght = lenght;
    }

    private Image[] imgBoom = {
            new ImageIcon(getClass().getResource("/Images/boom1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom5.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom6.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom7.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/boom8.png")).getImage(),
    };

    boolean isExist(int newx, int newy) {
        Rectangle rec1 = new Rectangle(x, y, size, size);
        Rectangle rec2 = new Rectangle(newx, newy, size, size);
        return rec1.intersects(rec2);
    }

    void draw(Graphics2D g2d) {
        imgIndex++;
        g2d.drawImage(imgBoom[imgIndex / 12 % imgBoom.length], x, y, size, size, null);

    }

    int getTimeExplosion() {
        return timeExplosion;
    }

    void setTimeExplosion(int t) {
        this.timeExplosion = t;
    }

    int getLenght() {
        return lenght;
    }


}

