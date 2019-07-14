import java.awt.*;
import java.util.ArrayList;

public class Manager {

    Map map;
    Player player = new Player(1, 2, 4);

    ArrayList<BoomFire> listBoomFire = new ArrayList<>();
    ArrayList<Boom> listBoom = new ArrayList<>();
    ArrayList<Rectangle> breakBox = new ArrayList<>();
    ArrayList<Bot2> listBot = new ArrayList<>();
    Cell[][] listCell = new Cell[15][15];

    ArrayList<Rectangle> unbreakBox = new ArrayList<>();
    int[][] mapBoom = new int[15][15];
    int[][] mapBox = new int[15][15];


    public Manager() {
        map = new Map("map1");
        init();
        listBot.add(new Bot2(225, 225, Direction.DOWN));

    }

    public void init() {
        mapBox = map.mapObjs;
        player.mapBox = mapBox;
        this.listCell = map.listCell;
        makeListBBox();
        makeListUBox();
    }

    public void makeListUBox() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (mapBox[i][j] > 6) {
                    unbreakBox.add(new Rectangle(j * 45, i * 45, 45, 45));
                }
            }
        }
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

    public void moveBot(int count) {
        for (int i = 0; i < listBot.size(); i++) {
            listBot.get(i).setDirection(player, listCell);
            listBot.get(i).botMove(count, mapBox, mapBoom);


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
            listBoomFire.get(i).impactBoomFireVsBBox(breakBox);

            listBoomFire.get(i).impactBoomFireVsUBox(unbreakBox);
            listBoomFire.get(i).impactBoomFireVsBoom(listBoom);
        }

        mapBoom[boom.y / 45][boom.x / 45] = 0;
        player.mapBoom = mapBoom;
    }

    public void removeBoomFire(BoomFire boom) {
        for (int i = 0; i < listBoomFire.size(); i++) {
            for (int j = 0; j < listBoomFire.get(i).listRemove.size(); j++) {
                Rectangle t = listBoomFire.get(i).listRemove.get(j);
                mapBox[t.y / 45][t.x / 45] = 0;
                breakBox.removeAll(listBoomFire.get(i).listRemove);
                for (int k = 0; k < listBoomFire.get(i).listRemove.size(); k++) {
                    listCell[listBoomFire.get(i).listRemove.get(k).y / 45][listBoomFire.get(i).listRemove.get(k).x / 45].value = 0;
                }
            }

        }


        listBoomFire.remove(boom);
        player.mapBoom = mapBoom;
    }

    public void bommFireVsBot() {
        for (int i = 0; i < listBoomFire.size(); i++) {
            listBoomFire.get(i).checkVsBot(listBot);
        }
    }

    public void boomFireVsPlayer() {
        for (int i = 0; i < listBoomFire.size(); i++) {
            if (listBoomFire.get(i).checkVsPlayer(player)) {
                player.isAlive = false;
                return;
            }

        }
    }

    public void draw(Graphics2D g2d) {

        map.drawMap(g2d);
        player.draw(g2d);
        for (int i = 0; i < listBot.size(); i++) {
            listBot.get(i).draw(g2d);
        }
        for (int i = 0; i < listBoom.size(); i++) {
            listBoom.get(i).draw(g2d);
        }
        for (int i = 0; i < listBoomFire.size(); i++) {
            listBoomFire.get(i).draw(g2d);
        }
    }

    public void playerAlive() {
        if (player.checkcheckImpactVsBot(listBot)) {
            player.isAlive = false;
            return;
        }

    }


    public void movePlayer(int c) {
        player.move(c);

    }


}
