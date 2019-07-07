import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;


public class PlayGame extends JPanel implements Runnable {
    public static boolean IS_RUNNING = true;
    private BitSet key = new BitSet();
    Container container;
    Manager manager = new Manager();
    int c = 0;
    int count = 0;

    public PlayGame(Container container) {
        this.container = container;
        setBackground(Color.WHITE);
        setLayout(null);
        setFocusable(true);
        addKeyListener(new Hander());
        Thread thread = new Thread(this);
        thread.start();

    }

    private class Hander implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            key.set(e.getKeyCode());


        }

        @Override
        public void keyReleased(KeyEvent e) {
            key.clear(e.getKeyCode());
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Image infoBg = new ImageIcon(getClass().getResource("/Images/inGameInfo.png")).getImage();

        g.drawImage(infoBg, 675, 0, 235, 675, null);
        manager.draw(g2d);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                g.drawRect(i * 45, j * 45, 45, 45);
            }
        }

    }


    @Override
    public void run() {
        while (IS_RUNNING) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (key.get(KeyEvent.VK_LEFT)) {
                manager.player.changeDirection(Direction.LEFT);
                manager.movePlayer(count);

            } else if (key.get(KeyEvent.VK_RIGHT)) {
                manager.player.changeDirection(Direction.RIGHT);
                manager.movePlayer(count);


            } else if (key.get(KeyEvent.VK_DOWN)) {
                manager.player.changeDirection(Direction.DOWN);
                manager.movePlayer(count);

            } else if (key.get(KeyEvent.VK_UP)) {
                manager.player.changeDirection(Direction.UP);
                manager.movePlayer(count);
            }
            if (key.get(KeyEvent.VK_SPACE)) {
                manager.setBoom(System.currentTimeMillis());
            }
            for (int i = 0; i < manager.listBoom.size(); i++) {
                if (System.currentTimeMillis() - manager.listBoom.get(i).timeStart > manager.listBoom.get(i).timeExplosion)
                    manager.removeBoom(manager.listBoom.get(i));
            }

            for (int i = 0; i < manager.listBoomFire.size(); i++) {
                if (System.currentTimeMillis() - manager.listBoomFire.get(i).timeStart > manager.listBoomFire.get(i).time)
                    manager.removeBoomFire(manager.listBoomFire.get(i));
            }
            repaint();
            count++;
            if (count == 1000000) {
                count = 0;
            }

        }
    }
}

