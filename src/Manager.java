import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

class Manager {
    private static int size = 45;

    long timeStart;
    boolean isSound;
    boolean isMusic;
    boolean isWin = false;
    boolean isLose = false;
    private Map map;
    private int round = 1;
    private int score;
    private Audio audio = new Audio();
    Player player = new Player1();
    ArrayList<BoomFire> listBoomFire;
    ArrayList<Boom> listBoom;
    ArrayList<Bot> listBot = new ArrayList<>();
    private Cell[][] listCell;
    private int[][] mapBoom = new int[15][15];
    private ArrayList<Item> listItems = new ArrayList<>();


    Manager(int player) {
        if (player == 1 || player == 0) this.player = new Player1();
        else if (player == 2) this.player = new Player2();
        else if (player == 3) this.player = new Player3();
        creatRound(round);
    }

    private void creatRound(int round) {
        map = new Map("map" + String.valueOf(round));
        player.x = 45;
        player.y = 45;
        init();
    }

    private void init() {
        listBoomFire = new ArrayList<>();
        listBoom = new ArrayList<>();
        listBot = new ArrayList<>();
        listCell = map.listCell;
        player.listCell = listCell;
        creatBot();
    }

    private void creatBot() {
        if (round == 1) {
            listBot.add(new Bot1(225, 135, Direction.DOWN));
            listBot.add(new Bot1(270, 225, Direction.DOWN));
            listBot.add(new Bot1(300, 315, Direction.DOWN));
            listBot.add(new Bot1(225, 405, Direction.DOWN));
            listBot.add(new Bot1(350, 495, Direction.DOWN));
        }
        if (round == 2) {
            listBot.add(new Bot1(225, 135, Direction.DOWN));
            listBot.add(new Bot2(270, 225, Direction.DOWN));
            listBot.add(new Bot1(300, 315, Direction.DOWN));
            listBot.add(new Bot2(225, 405, Direction.DOWN));
            listBot.add(new Bot1(350, 495, Direction.DOWN));
        }
        if (round == 3) {
            listBot.add(new Bot1(585, 45, Direction.DOWN));
            listBot.add(new Bot3(585, 585, Direction.DOWN));
            listBot.add(new Bot2(45, 585, Direction.DOWN));
        }
    }

    void moveBot(int count) {
        for (Bot aListBot : listBot) {
            aListBot.setDirection(player, listCell);
            aListBot.botMove(count, listCell, mapBoom);
        }
    }

    void setBoom(long l) {
        int newX = (int) Math.round((double) player.x / size) * size;
        int newY = (int) Math.round((double) player.y / size) * size;
        for (int i = 0; i < listBoom.size(); i++) {
            if (listBoom.get(i).isExist(newX, newY)) return;
        }
        if (listBoom.size() >= player.quantity) return;
        if (isSound) audio.getAudio("/Sounds/newBomb.wav").play();
        listBoom.add(new Boom(newX, newY, player.lenght, l));
        mapBoom[newY / size][newX / size] = 1;
        player.mapBoom = mapBoom;
    }

    void removeBoom(Boom boom) {
        listBoom.remove(boom);
        listBoomFire.add(new BoomFire(boom.x, boom.y, boom.getLenght(), System.currentTimeMillis()));
        for (int i = 0; i < listBoomFire.size(); i++) {
            listBoomFire.get(i).impactBoomFirevsBox(listCell);
            listBoomFire.get(i).impactBoomFireVsBoom(listBoom);
        }
        mapBoom[boom.y / size][boom.x / size] = 0;
        player.mapBoom = mapBoom;
    }

    void bommFireVsBot() {
        int t = listBot.size();
        for (int i = 0; i < listBoomFire.size(); i++) {
            listBoomFire.get(i).impactVsBot(listBot);
            if (t > listBot.size() && isSound) audio.getAudio("/Sounds/bot_die.wav").play();
        }
    }

    void boomFireVsPlayer() {
        for (BoomFire aListBoomFire : listBoomFire) {
            if (aListBoomFire.checkVsPlayer(player)) {
                playerDeath();
                return;
            }
        }
    }

    void removeBoomFire(BoomFire boomFire) {
        if (isSound) audio.getAudio("/Sounds/bombEx.wav").play();
        boomFire.impactVsItems(listItems);
        for (int j = 0; j < boomFire.listRemove.size(); j++) {
            Rectangle t = boomFire.listRemove.get(j).getRect();
            spawnItems(t);
            listCell[t.y / size][t.x / size].value = 0;
            for (int k = 0; k < boomFire.listRemove.size(); k++) {
                boomFire.listRemove.get(k).value = 0;
            }
        }

        listBoomFire.remove(boomFire);
        player.mapBoom = mapBoom;
    }

    private void spawnItems(Rectangle rect) {
        Integer random = new Random().nextInt(14);
        if (random == 1) {
            Integer type = new Random().nextInt(3) + 1;
            listItems.add(new Item(rect.x, rect.y, type));
        }
    }

    void spawnPlayer() {
        player.x = 45;
        player.y = 45;
        player.isAlive = true;
    }

    void itemVsPlayer() {
        for (int i = 0; i < listItems.size(); i++) {
            if (player.getRect().intersects(listItems.get(i).getRect())) {
                if (isSound) audio.getAudio("/Sounds/item.wav").play();

                if (listItems.get(i).type == 1) player.setQuantity(player.quantity + 1);
                else if (listItems.get(i).type == 2) player.setLenght(player.lenght + 1);
                else if (listItems.get(i).type == 3) player.setSpeed(player.speed + 1);
                listItems.remove(i);
            }
        }
    }

    void playerVsBot() {
        if (player.checkImpactVsBot(listBot)) {
            playerDeath();
        }
    }

    private void playerDeath() {
        player.isAlive = false;
        player.heart--;
        if (player.heart == 0) {
            isLose = true;
            if (isMusic) audio.getAudio("/Sounds/player_die.wav");
            return;
        }
        if (isSound) audio.getAudio("/Sounds/player_die.wav").play();

        player.timeDeath = System.currentTimeMillis();
    }

    void endRound() {
        if (player.heart != 0 && listBot.size() == 0) {
            if (round == 3) {
                if (!isWin) winGame();
            } else {
                round++;
                creatRound(round);
                init();
            }
        }
    }

    private void winGame() {
        isWin = true;
        score();
        if (isMusic) audio.getAudio("/Sounds/win.wav").play();
        String name = JOptionPane.showInputDialog("Your score is:" + this.score + "\n" + "Enter Name");
        setListHighScore(name, score);
    }

    private void drawInfo(Graphics2D g2d) {
        Image infoBg = new ImageIcon(getClass().getResource("/Images/inGameInfo.png")).getImage();
        g2d.drawImage(infoBg, 675, 0, 235, 675, null);
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("1", Font.BOLD, 20));
        g2d.drawString("HEART", 755, 100);
        Image heart = new ImageIcon(getClass().getResource("/Images/heart_1.png")).getImage();
        if (player.heart == 3) {
            g2d.drawImage(heart, 750, 120, null);
            g2d.drawImage(heart, 775, 120, null);
            g2d.drawImage(heart, 800, 120, null);
        }
        if (player.heart == 2) {
            g2d.drawImage(heart, 750, 120, null);
            g2d.drawImage(heart, 775, 120, null);
        }
        if (player.heart == 1) {
            g2d.drawImage(heart, 750, 120, null);
        }
    }

    void draw(Graphics2D g2d) {

        map.drawMap(g2d);
        for (int i = 0; i < listItems.size(); i++) {
            listItems.get(i).draw(g2d);
        }
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
        drawInfo(g2d);
        if (isWin) {
            g2d.setFont(new Font("1", Font.BOLD, 70));
            g2d.setColor(Color.RED);
            g2d.drawString("You Win !!!", 200, 250);
        }
        if (isLose) {
            g2d.setFont(new Font("1", Font.BOLD, 70));
            g2d.setColor(Color.RED);
            g2d.drawString("You Lose !!!", 200, 250);
        }
    }

    void movePlayer(int c) {
        player.move(c);

    }

    private void score() {
        long timeEnd = System.currentTimeMillis();
        score = (int) ((10 * 60000 - timeEnd + timeStart) / 6000);
        if (score < 0) score = 0;
    }

    private void setListHighScore(String name, Integer score) {
        ArrayList<Pair<String, Integer>> listHighScore = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream("src/HighScores/HighScores.txt")));
            String line;
            while ((line = file.readLine()) != null) {
                String str[] = line.split(":");
                listHighScore.add(new Pair<>(str[0], Integer.parseInt(str[1])));
            }
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        listHighScore.add(new Pair<>(name, score));
        Collections.sort(listHighScore, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
                if (o1.getValue() > o2.getValue()) return -1;
                else if (o1.getValue() == o2.getValue()) return 0;
                else return 1;
            }
        });

        if (listHighScore.size() > 5) listHighScore.remove(listHighScore.size() - 1);

        BufferedWriter file = null;
        try {
            file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/HighScores/HighScores.txt")));
            for (int i = 0; i < listHighScore.size(); i++) {
                file.write(listHighScore.get(i).getKey() + ":" + listHighScore.get(i).getValue() + "\n");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
