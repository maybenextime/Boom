import javax.swing.*;
import java.awt.*;

class Item {
    private static int size=45;

    private static int itemBomb=1;
    private static int itemBombSize=2;
    private static int itemBoot=3;
    int x;
    int y;
    int type;
    Item(int x, int y, int type){
        this.type=type;
        this.x=x;
        this.y=y;
    }

    Rectangle getRect(){
        return new Rectangle(x,y,size,size);
    }
    void draw(Graphics2D g2d){
        if (this.type == itemBomb)
            g2d.drawImage(new ImageIcon(getClass().getResource("/Images/item_bomb.png")).getImage(),x,y,size,size,null);
        if (this.type == itemBombSize)
            g2d.drawImage(new ImageIcon(getClass().getResource("/Images/item_bombsize.png")).getImage(),x,y,size,size,null);
        if (this.type == itemBoot)
            g2d.drawImage(new ImageIcon(getClass().getResource("/Images/item_boot.png")).getImage(),x,y,size,size,null);
    }

}
