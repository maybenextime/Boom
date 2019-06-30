import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Map {
    int[][] listObjs = new int[15][15];
    ArrayList<Integer> listImgName= new ArrayList<>();
    Image[] imgList  ;
    ArrayList<Integer> listUnBreak;
    ArrayList<Integer> listBreak;

    public Map(String map){
        readMap(map);
        getListImgName();
        getListImg();
    }

    private void readMap(String map) {
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream("src/Maps/"+map+".txt")));
            for (int i=0; i<15; i++){
                String line;
                if ((line= file.readLine())== null) break;
                String str[] = line.split(" ");
                for (int j=0;j<15;j++){
                    listObjs[i][j] = Integer.parseInt(str[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getListImgName(){
        for (int i=0;i<15; i++){
            for (int j=0;j<15; j++){
                if (!listImgName.contains(listObjs[i][j])){
                    listImgName.add(listObjs[i][j]);
                }
            }
        }
        Collections.sort(listImgName);
    }

    private void getListImg(){
        imgList= new Image[listImgName.get(listImgName.size()-1)+1];
        for (int i=1; i<listImgName.size();i++){
            int k= listImgName.get(i);
            imgList[listImgName.get(i)] = new ImageIcon(getClass().getResource("/Images/"+ String.valueOf(listImgName.get(i))+".png")).getImage();
        }
    }


    public void drawMap(Graphics g){
        BufferedImage imgBackGround = null;
        try {
            imgBackGround = ImageIO.read(getClass().getResourceAsStream("/Images/map1BG.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(imgBackGround, 20, 20, 610, 610, null);
        for(int i=0;i<15;i++){
            for(int j=0; j<15;j++){
                if(listObjs[i][j]!=0)
                g.drawImage(imgList[listObjs[i][j]],j*45,i*45,45,45,null);
            }
        }
    }
}
