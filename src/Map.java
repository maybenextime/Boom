import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Map {
    int[][] mapObjs = new int[15][15];
    ArrayList<Integer> listImgName = new ArrayList<>();
    Image[] imgList;
    Cell[][] listCell = new Cell[15][15];

    public Map(String map) {
        readMap(map);
        getListImgName();
        getListImg();
        setCellMap();
    }

    private void setCellMap() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i > 0) listCell[i][j].up = listCell[i - 1][j];
                if (i < 14) listCell[i][j].down = listCell[i + 1][j];
                if (j > 0) listCell[i][j].left = listCell[i][j - 1];
                if (j < 14) listCell[i][j].right = listCell[i][j + 1];
            }
        }
    }

    private void readMap(String map) {
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream("src/Maps/" + map + ".txt")));
            for (int i = 0; i < 15; i++) {
                String line;
                if ((line = file.readLine()) == null) break;
                String str[] = line.split(" ");
                for (int j = 0; j < 15; j++) {
                    mapObjs[i][j] = Integer.parseInt(str[j]);
                    listCell[i][j] = new Cell(j * 45, i * 45, Integer.parseInt(str[j]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getListImgName() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (!listImgName.contains(mapObjs[i][j])) {
                    listImgName.add(mapObjs[i][j]);
                }
            }
        }
        Collections.sort(listImgName);
    }

    private void getListImg() {
        imgList = new Image[listImgName.get(listImgName.size() - 1) + 1];
        for (int i = 1; i < listImgName.size(); i++) {
            imgList[listImgName.get(i)] = new ImageIcon(getClass().getResource("/Images/" + String.valueOf(listImgName.get(i)) + ".png")).getImage();
        }
    }


    public void drawMap(Graphics2D g2d) {
        Image imgBackGround = new ImageIcon(getClass().getResource("/Images/map1BG.png")).getImage();
        g2d.drawImage(imgBackGround, 45, 45, 585, 585, null);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (mapObjs[i][j] != 0)
                    g2d.drawImage(imgList[mapObjs[i][j]], j * 45, i * 45, 45, 45, null);
            }
        }
    }
}
