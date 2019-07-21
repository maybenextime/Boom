import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.BitSet;


public class PlayGame extends JPanel implements Runnable {
    private static boolean IS_RUNNING = true;
    private BitSet key = new BitSet();
    private JLabel back;
    private Container container;
    Manager manager;
    private int count = 0;

    PlayGame(){
        setBackground(Color.WHITE);
        setLayout(null);
        setFocusable(true);
    }

    PlayGame(Container container) {
        initComp();
        manager = new Manager(container.options.player);
        this.container = container;
        setBackground(Color.WHITE);
        setLayout(null);
        setFocusable(true);
        addKeyListener(new Hander());
        Thread thread = new Thread(this);
        thread.start();

    }

    private void initComp() {
        back = new JLabel();
        ImageIcon imgBack1 = new ImageIcon(getClass().getResource("/Images/back1.png"));
        back.setIcon(imgBack1);
        back.setBounds(800, 500, 75, 75);
        back.addMouseListener(mouse);
        this.add(back);
    }

    private MouseListener mouse = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == back) {
                container.showMenu();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == back) {
                ImageIcon imgBack2 = new ImageIcon(getClass().getResource("/Images/back2.png"));
                back.setIcon(imgBack2);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (label == back) {
                ImageIcon imgBack1 = new ImageIcon(getClass().getResource("/Images/back1.png"));
                back.setIcon(imgBack1);
            }
        }
    };

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
        manager.draw(g2d);

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

            for (int i = 0; i < manager.listBoom.size(); i++) {
                if (System.currentTimeMillis() - manager.listBoom.get(i).timeStart > manager.listBoom.get(i).getTimeExplosion())
                    manager.removeBoom(manager.listBoom.get(i));
            }

            for (int i = 0; i < manager.listBoomFire.size(); i++) {
                if (System.currentTimeMillis() - manager.listBoomFire.get(i).getTimeStart() > manager.listBoomFire.get(i).getTime())
                    manager.removeBoomFire(manager.listBoomFire.get(i));
            }
            if(!manager.isLose&&!manager.isWin) {
                if (manager.player.isAlive) {
                    if (key.get(KeyEvent.VK_SPACE)) {
                        manager.setBoom(System.currentTimeMillis());
                    }
                    manager.itemVsPlayer();
                    manager.playerVsBot();
                    manager.moveBot(count);
                    manager.bommFireVsBot();
                    manager.boomFireVsPlayer();
                    manager.endRound();
                } else {
                    if (System.currentTimeMillis() - manager.player.timeDeath > 2000) manager.spawnPlayer();
                }
            }
            repaint();
            count++;
            if (count == 10000) {
                count = 0;
            }

        }
    }
}

