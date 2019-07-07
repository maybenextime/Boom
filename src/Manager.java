import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Manager {

    Map map;
    Player player = new Player(1, 2, 2);

    ArrayList<BoomFire> listBoomFire = new ArrayList<>();
    ArrayList<Boom> listBoom = new ArrayList<>();
    ArrayList<Rectangle> breakBox = new ArrayList<>();
    int[][] mapBoom = new int[15][15];
    int[][] mapBox = new int[15][15];


    public Manager() {
        map = new Map("map1");
        init();

    }

    public void init() {
        mapBox = map.mapObjs;
        player.mapBox = mapBox;
        makeListBBox();
        Rectangle rec = new Rectangle(45, 0, 45, 45);
        System.out.println(rec.intersection(breakBox.get(1)));
    }

    public void makeListBBox() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (mapBox[i][j] < 7 && mapBox[i][j] > 0) {
                    breakBox.add(new Rectangle(j * 45, i * 45, 45, 45));
                }
            }
        }

    }


    public void setBoom(long l) {
        int newX = (int) Math.round((double) player.x / 45) * 45;
        int newY = (int) Math.round((double) player.y / 45) * 45;
        for (int i = 0; i < listBoom.size(); i++) {
            if (listBoom.get(i).isExist(newX, newY)) return;
        }
        if (listBoom.size() >= player.quantity) return;
        listBoom.add(new Boom(newX, newY, player.lenght, l));
        mapBoom[newY / 45][newX / 45] = 1;
        player.mapBoom = mapBoom;

    }

    public void removeBoom(Boom boom) {
        listBoom.remove(boom);
        listBoomFire.add(new BoomFire(boom.x, boom.y, boom.lenght, System.currentTimeMillis()));
        for (int i = 0; i < listBoomFire.size(); i++) {
            listBoomFire.get(i).impactBoomFireVsBoom(listBoom);
            listBoomFire.get(i).impactBoomFireVsBBox(breakBox);
            for (int j = 0; j < listBoomFire.get(i).listRemove.size(); j++) {
                Rectangle t = listBoomFire.get(i).listRemove.get(j);
                mapBox[t.y / 45][t.x / 45] = 0;
            }
        }
        mapBoom[boom.y / 45][boom.x / 45] = 0;
        player.mapBoom = mapBoom;
    }

    public void removeBoomFire(BoomFire boom) {
        listBoomFire.remove(boom);
        player.mapBoom = mapBoom;
    }


    public void draw(Graphics2D g2d) {
        map.drawMap(g2d);
        player.draw(g2d);
        for (int i = 0; i < listBoom.size(); i++) {
            listBoom.get(i).draw(g2d);
        }
        for (int i = 0; i < listBoomFire.size(); i++) {
            listBoomFire.get(i).draw(g2d);
        }

    }


    public void movePlayer(int c) {
        player.move(c);

    }


}
