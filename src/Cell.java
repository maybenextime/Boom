import java.awt.*;

public class Cell {
    int x;
    int y;
    int value;

    int cost;
    Cell left;
    Cell right;
    Cell up;
    Cell down;
    Boolean visited = false;
    Cell parent;
    Boolean checkA;


    public Cell(int x, int y, int value) {

        this.x = x;
        this.y = y;
        this.value = value;

    }

    void setDefaul() {
        this.visited = false;
        this.parent = null;
    }

    void setDefaultA() {
        if (this.value != 0) this.cost = 2000;
        else this.cost = 1;
        this.checkA = false;
        this.parent = null;

    }

    void setBoomCost() {
        this.cost = 10000;
    }

    void setBoomCost2(int i) {
        if (i == 1) this.cost = 4000;
        else cost = 1500;
    }



    Rectangle getRect() {
        return new Rectangle(this.x, this.y, 45, 45);
    }


}
