import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoomFire {
    private static int size = 45;
    private static int time = 300;

    private long timeStart;
    private int lenght;
    public int x;
    public int y;

    ArrayList<Cell> listRemove = new ArrayList<>();
    private ArrayList<Rectangle> listRec = new ArrayList<>();
    private ArrayList<Rectangle> listUpRec = new ArrayList<>();
    private ArrayList<Rectangle> listDownRec = new ArrayList<>();
    private ArrayList<Rectangle> listRightRec = new ArrayList<>();
    private ArrayList<Rectangle> listLeftRec = new ArrayList<>();

    private Image[] imgRight = {
            new ImageIcon(getClass().getResource("/Images/bombbang_right_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bombbang_right_2.png")).getImage(),

    };
    private Image[] imgLeft = {
            new ImageIcon(getClass().getResource("/Images/bombbang_left_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bombbang_left_2.png")).getImage(),

    };
    private Image[] imgDown = {
            new ImageIcon(getClass().getResource("/Images/bombbang_down_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bombbang_down_2.png")).getImage(),

    };
    private Image[] imgUp = {
            new ImageIcon(getClass().getResource("/Images/bombbang_up_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bombbang_up_2.png")).getImage(),

    };
    private Image imgMID = new ImageIcon(getClass().getResource("/Images/bombbang_mid_2.png")).getImage();


    BoomFire(int x, int y, int lenght, long timeStart) {
        this.x = x;
        this.y = y;
        this.timeStart = timeStart;
        this.lenght = lenght;
        setAllDirectList();
        setListRect();
    }

    private void setListUpRec() {
        for (int i = 1; i <= lenght; i++)
            listUpRec.add(new Rectangle(x, y - size * i, size, size));
    }

    private void setListDownRec() {
        for (int i = 1; i <= lenght; i++)
            listDownRec.add(new Rectangle(x, y + size * i, size, size));

    }

    private void setListRightRec() {
        for (int i = 1; i <= lenght; i++)
            listRightRec.add(new Rectangle(x + size * i, y, size, size));
    }

    private void setListLeftRec() {
        for (int i = 1; i <= lenght; i++)
            listLeftRec.add(new Rectangle(x - size * i, y, size, size));
    }

    private void setAllDirectList() {
        setListUpRec();
        setListDownRec();
        setListRightRec();
        setListLeftRec();
    }

    private void setListRect() {
        listRec.clear();
        listRec.add(new Rectangle(x, y, size, size));
        listRec.addAll(listDownRec);
        listRec.addAll(listUpRec);
        listRec.addAll(listRightRec);
        listRec.addAll(listLeftRec);
    }

    void impactBoomFireVsBoom(ArrayList<Boom> listBoom) {
        for (Boom aListBoom : listBoom) {
            Rectangle boom = new Rectangle(aListBoom.x, aListBoom.y, size, size);
            for (Rectangle aListRec : listRec) {
                if (aListRec.intersects(boom)) aListBoom.setTimeExplosion(0);
            }
        }
    }

    private void impactDirectBoomFirevsBox(Cell[][] listCell, ArrayList<Rectangle> directRect) {
        for (int i = 0; i < directRect.size(); i++) {
            int row = directRect.get(i).y / size;
            int col = directRect.get(i).x / size;
            if (listCell[row][col].value != 0) {
                if (listCell[row][col].value < 15) {
                    for (int k = i + 1; k < lenght; k++) {
                        if (i + 1 == directRect.size()) return;
                        directRect.remove(i + 1);
                    }
                    setListRect();
                    listRemove.add(listCell[row][col]);
                } else if (listCell[row][col].value > 15) {
                    for (int k = i; k < lenght; k++) {
                        directRect.remove(i);
                    }
                    setListRect();
                }
                break;
            }
        }
    }

    void impactBoomFirevsBox(Cell[][] listCell) {
        impactDirectBoomFirevsBox(listCell, listUpRec);
        impactDirectBoomFirevsBox(listCell, listDownRec);
        impactDirectBoomFirevsBox(listCell, listLeftRec);
        impactDirectBoomFirevsBox(listCell, listRightRec);

    }

    Boolean checkVsPlayer(Player player) {
        for (Rectangle aListRec : listRec) {
            Rectangle inter = player.getRect().intersection(aListRec);
            int min;
            min = Math.min(inter.width, inter.height);
            if (min > 20) return true;
        }
        return false;
    }

    void impactVsBot(ArrayList<Bot> listbot) {
        for (int i = 0; i < listbot.size(); i++) {
            for (Rectangle aListRec : listRec) {
                if (listbot.get(i).getRect().intersects(aListRec)) {
                    listbot.remove(i);
                    break;
                }
            }
        }
    }

    void impactVsItems(ArrayList<Item> listItem) {
        for (int i = 0; i < listItem.size(); i++) {
            for (Rectangle aListRec : listRec) {
                if (listItem.get(i).getRect().intersects(aListRec)) {
                    listItem.remove(i);
                    break;
                }
            }
        }
    }

    void draw(Graphics2D g2d) {
        g2d.drawImage(imgMID, x, y, size, size, null);

        for (int i=0;i<listUpRec.size();i++) {
            g2d.drawImage(imgUp[0], listUpRec.get(i).x, listUpRec.get(i).y, size, size, null);
        }
        if (listUpRec.size() == lenght) {
            g2d.drawImage(imgUp[1], x, y - lenght * size, size, size, null);
        }


        for (int i=0;i<listDownRec.size();i++) {
            g2d.drawImage(imgDown[0], listDownRec.get(i).x, listDownRec.get(i).y, size, size, null);
        }

        if (listDownRec.size() == lenght) {
            g2d.drawImage(imgDown[1], x, y + lenght * size, size, size, null);
        }

        for (int i=0;i<listLeftRec.size();i++) {
            g2d.drawImage(imgLeft[0], listLeftRec.get(i).x, listLeftRec.get(i).y, size, size, null);
        }

        if (listLeftRec.size() == lenght) {
            g2d.drawImage(imgLeft[1], x - lenght * size, y, size, size, null);
        }

        for (int i=0;i<listRightRec.size();i++) {
            g2d.drawImage(imgRight[0], listRightRec.get(i).x, listRightRec.get(i).y, size, size, null);
        }
        if (listRightRec.size() == lenght) {
            g2d.drawImage(imgRight[1], x + lenght * size, y, size, size, null);
        }
    }

    public long getTimeStart() {
        return timeStart;
    }

    public int getTime() {
        return time;
    }


}








