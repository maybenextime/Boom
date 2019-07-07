import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoomFire {

    int time = 500;
    public int lenght = 1;
    public long timeStart;
    public int x;
    public int y;
    public ArrayList<Rectangle> listRemove = new ArrayList<>();
    public ArrayList<Rectangle> listRec = new ArrayList<>();
    public ArrayList<Rectangle> listUpRec = new ArrayList<>();
    public ArrayList<Rectangle> listDownRec = new ArrayList<>();
    public ArrayList<Rectangle> listRightRec = new ArrayList<>();
    public ArrayList<Rectangle> listLeftRec = new ArrayList<>();

    Image[] imgRight = {
            new ImageIcon(getClass().getResource("/Images/bombbang_right_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bombbang_right_2.png")).getImage(),

    };
    Image[] imgLeft = {
            new ImageIcon(getClass().getResource("/Images/bombbang_left_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bombbang_left_2.png")).getImage(),

    };
    Image[] imgDown = {
            new ImageIcon(getClass().getResource("/Images/bombbang_down_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bombbang_down_2.png")).getImage(),

    };
    Image[] imgUp = {
            new ImageIcon(getClass().getResource("/Images/bombbang_up_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bombbang_up_2.png")).getImage(),

    };
    Image imgMID = new ImageIcon(getClass().getResource("/Images/bombbang_mid_2.png")).getImage();


    public BoomFire(int x, int y, int lenght, long timeStart) {
        this.x = x;
        this.y = y;
        this.timeStart = timeStart;
        this.lenght = lenght;
        setAllList();
    }

    public void setListUpRec() {
        for (int i = 1; i <= lenght; i++)
            listUpRec.add(new Rectangle(x, y - 45 * i, 45, 45));
    }

    public void setListDownRec() {
        for (int i = 1; i <= lenght; i++)
            listDownRec.add(new Rectangle(x, y + 45 * i, 45, 45));

    }

    public void setListRightRec() {
        for (int i = 1; i <= lenght; i++)
            listRightRec.add(new Rectangle(x + 45 * i, y, 45, 45));
    }

    public void setListLeftRec() {
        for (int i = 1; i <= lenght; i++)
            listLeftRec.add(new Rectangle(x - 45 * i, y, 45, 45));
    }

    public void setAllList() {
        setListUpRec();
        setListDownRec();
        setListRightRec();
        setListLeftRec();
        listRec.addAll(listDownRec);
        listRec.addAll(listUpRec);
        listRec.addAll(listRightRec);
        listRec.addAll(listLeftRec);
    }


    public void draw(Graphics2D g2d) {
        g2d.drawImage(imgMID, x, y, 45, 45, null);

        for (int i = 0; i < listUpRec.size(); i++) {
            g2d.drawImage(imgUp[0], listUpRec.get(i).x, listUpRec.get(i).y, 45, 45, null);
        }
        if (listUpRec.size() == lenght) {
            g2d.drawImage(imgUp[1], x, y - lenght * 45, 45, 45, null);
        }


        for (int i = 0; i < listDownRec.size(); i++) {
            g2d.drawImage(imgDown[0], listDownRec.get(i).x, listDownRec.get(i).y, 45, 45, null);
        }

        if (listDownRec.size() == lenght) {
            g2d.drawImage(imgDown[1], x, y + lenght * 45, 45, 45, null);
        }

        for (int i = 0; i < listLeftRec.size(); i++) {
            g2d.drawImage(imgLeft[0], listLeftRec.get(i).x, listLeftRec.get(i).y, 45, 45, null);
        }

        if (listLeftRec.size() == lenght) {
            g2d.drawImage(imgLeft[1], x - lenght * 45, y, 45, 45, null);
        }

        for (int i = 0; i < listRightRec.size(); i++) {
            g2d.drawImage(imgRight[0], listRightRec.get(i).x, listRightRec.get(i).y, 45, 45, null);
        }
        if (listRightRec.size() == lenght) {
            g2d.drawImage(imgRight[1], x + lenght * 45, y, 45, 45, null);
        }

    }

    public void impactBoomFireVsBoom(ArrayList<Boom> listBoom) {

        for (int i = 0; i < listBoom.size(); i++) {
            Rectangle boom = new Rectangle(listBoom.get(i).x, listBoom.get(i).y, 45, 45);

            for (int j = 0; j < listRec.size(); j++) {
                Rectangle inter = listRec.get(j).intersection(boom);
                if (inter.getHeight() > 0 && inter.getWidth() > 0) listBoom.get(i).setTimeExplosion(0);
            }
        }
    }

    public void DirectimpactBoomVsBBox(ArrayList<Rectangle> listBBox, ArrayList<Rectangle> directR) {
        for (int i = 0; i < directR.size(); i++) {
            for (int j = 0; j < listBBox.size(); j++) {
                Rectangle inter = directR.get(i).intersection(listBBox.get(j));
                if (inter.getWidth() == 45 && inter.getHeight() == 45) {
                    for (int k = 0; k < lenght - i - 1; k++) {
                        directR.remove(i + 1);
                    }
                    listRemove.add(listBBox.get(j));
                    listBBox.remove(j);
                }
            }
        }
    }

    public void impactBoomFireVsBBox(ArrayList<Rectangle> listBBox) {
        DirectimpactBoomVsBBox(listBBox, listUpRec);
        DirectimpactBoomVsBBox(listBBox, listDownRec);
        DirectimpactBoomVsBBox(listBBox, listLeftRec);
        DirectimpactBoomVsBBox(listBBox, listRightRec);

    }
}







