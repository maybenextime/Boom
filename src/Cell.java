
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

    public Cell(int x, int y, int value) {

        this.x = x;
        this.y = y;
        this.value = value;

    }

    public void setDefaul() {
        this.visited = false;
        this.parent = null;
    }


}
