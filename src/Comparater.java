import java.util.Comparator;

public class Comparater implements Comparator<Cell> {
    int xPlayer;
    int yPlayer;
    @Override
    public int compare(Cell c1, Cell c2){
        if (Math.abs( (c1.x-xPlayer)/45) +Math.abs((c1.y-yPlayer)/45) +c1.cost > Math.abs( (c2.x - xPlayer)/45) + Math.abs((c2.y - yPlayer)/45) + c2.cost)
        return 1;
        else if (Math.abs( (c1.x-xPlayer)/45) +Math.abs((c1.y-yPlayer)/45) +c1.cost < Math.abs( (c2.x - xPlayer)/45) + Math.abs((c2.y - yPlayer)/45) + c2.cost)
            return -1;
        else return 0;

    }
}
