import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Bot2 {
    public Direction direction = Direction.DOWN;
    public int speed = 1;
    public int x = 45;
    public int y = 45;
    public boolean isMoving = false;
    public Direction direct = Direction.RIGHT;
    Queue<Cell> queue = new LinkedList<Cell>();

    ArrayList<Cell> closed = new ArrayList<>();
    ArrayList<Cell> path = new ArrayList<>();


    public int imgIndex = 0;


    public Bot2(int x, int y, Direction direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    Image[] botDown = {
            new ImageIcon(getClass().getResource("/Images/monster_down1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_down2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_down3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_down4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_down5.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_down6.png")).getImage()

    };

    Image[] botLeft = {
            new ImageIcon(getClass().getResource("/Images/monster_left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_left2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_left3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_left4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_left5.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_left6.png")).getImage()

    };
    Image[] botUp = {
            new ImageIcon(getClass().getResource("/Images/monster_up1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_up2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_up3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_up4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_up5.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_up6.png")).getImage()

    };

    Image[] botRight = {
            new ImageIcon(getClass().getResource("/Images/monster_right1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_right2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_right3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_right4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_right5.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/monster_right6.png")).getImage()

    };

    public void findPath(Player player, Cell[][] listCell) {
        queue.clear();
        closed.clear();

        int col = (int) Math.round((double) x / 45);
        int row = (int) Math.round((double) y / 45);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                listCell[i][j].setDefaul();
            }
        }
        queue.add(listCell[row][col]);
        while (!queue.isEmpty()) {
            Cell cell = queue.remove();
            closed.add(cell);
            cell.visited = true;
            if (cell.x / 45 == player.x / 45 && cell.y / 45 == player.y / 45) return;
            addToQueue(cell);

            // queue.remove();
        }


    }

    public void setDirection(Player player, Cell[][] listCell) {

        path.clear();
        findPath(player, listCell);

        Cell parent = closed.get(closed.size() - 1).parent;
        path.add(closed.get(closed.size() - 1));
        while (parent != null) {
            path.add(parent);
            parent = path.get(path.size() - 1).parent;
        }

        Cell next = path.get(path.size() - 2);

        if (x < next.x) {
            changeDirection(Direction.RIGHT);
            return;
        }
        if (x > next.x) {
            changeDirection(Direction.LEFT);
            return;
        }
        if (y < next.y) {
            changeDirection(Direction.DOWN);
            return;
        }
        if (y > next.y) {
            changeDirection(Direction.UP);
            return;
        }
    }

    public void changeDirection(Direction d) {
        this.direction = d;
    }

    public void botMove(int c, int[][] mapBox, int[][] mapBoom) {
        if (c % 10 != 0) {
            return;
        }
        int xRaw = x;
        int yRaw = y;
        switch (direction) {

            case DOWN: {
                yRaw += speed;
                int d = checkImpactWithObjs(xRaw, yRaw, mapBox);
                if (d != 0) {
                    if (0 < d && d <= 25) {
                        x++;
                        return;
                    }
                    if (-25 <= d && d < 0) {
                        x--;
                        return;
                    } else {
                        changeDirection(Direction.randomDirect());
                        return;
                    }
                }

                int k = checkImpactWithObjs(xRaw, yRaw, mapBoom);
                if (k != 0) {

                    changeDirection(Direction.randomDirect());
                    return;


                }

                break;

            }
            case UP: {
                yRaw -= speed;
                int d = checkImpactWithObjs(xRaw, yRaw, mapBox);
                if (d != 0) {
                    if (0 < d && d <= 25) {
                        x++;
                        return;
                    }
                    if (-25 <= d && d < 0) {
                        x--;
                        return;
                    } else {
                        changeDirection(Direction.randomDirect());
                        return;
                    }
                }
                int k = checkImpactWithObjs(xRaw, yRaw, mapBoom);
                if (k != 0) {

                    changeDirection(Direction.randomDirect());
                    return;


                }
                break;
            }
            case RIGHT: {
                xRaw += speed;

                int d = checkImpactWithObjs(xRaw, yRaw, mapBox);

                if (d != 0) {
                    if (0 < d && d <= 25) {
                        y++;
                        return;
                    }
                    if (-25 <= d && d < 0) {
                        y--;
                        return;
                    } else {
                        changeDirection(Direction.randomDirect());
                        return;
                    }
                }
                int k = checkImpactWithObjs(xRaw, yRaw, mapBoom);
                if (k != 0) {

                    changeDirection(Direction.randomDirect());
                    return;

                }

                break;
            }
            case LEFT: {
                xRaw -= speed;
                int d = checkImpactWithObjs(xRaw, yRaw, mapBox);
                if (d != 0) {
                    if (0 < d && d <= 25) {
                        y++;
                        return;
                    }
                    if (-25 <= d && d < 0) {
                        y--;
                        return;
                    } else {
                        changeDirection(Direction.randomDirect());
                        return;
                    }
                }
                int k = checkImpactWithObjs(xRaw, yRaw, mapBoom);
                if (k != 0) {

                    changeDirection(Direction.randomDirect());
                    return;

                }
                break;
            }
        }

        x = xRaw;
        y = yRaw;

    }


    public int checkImpactWithObjs(int x, int y, int[][] map) {
        switch (direction) {
            case RIGHT: {
                int i = x / 45 + 1;
                int j = y / 45;
                Rectangle boxUp = new Rectangle(i * 45, j * 45, 45, 45);
                Rectangle boxDown = new Rectangle(i * 45, (j + 1) * 45, 45, 45);
                Rectangle inters = new Rectangle();
                if (map[j][i] != 0 && map[j + 1][i] != 0) return 30;
                if (map[j][i] != 0) {
                    inters = getRect().intersection(boxUp);
                    return (int) inters.getHeight();

                } else if (map[j + 1][i] != 0) {
                    inters = getRect().intersection(boxDown);
                    return (int) -inters.getHeight();

                }
                break;
            }
            case LEFT: {
                int i = x / 45;
                int j = y / 45;
                Rectangle boxUp = new Rectangle(i * 45, j * 45, 45, 45);
                Rectangle boxDown = new Rectangle(i * 45, (j + 1) * 45, 45, 45);
                Rectangle inters = new Rectangle();
                if (map[j][i] != 0 && map[j + 1][i] != 0) return 30;
                if (map[j][i] != 0) {
                    inters = getRect().intersection(boxUp);
                    return (int) inters.getHeight();
                } else if (map[j + 1][i] != 0) {
                    inters = getRect().intersection(boxDown);
                    return (int) -inters.getHeight();
                }
                break;
            }
            case DOWN: {
                int i = x / 45;
                int j = y / 45 + 1;
                Rectangle boxLeft = new Rectangle(i * 45, j * 45, 45, 45);
                Rectangle boxRight = new Rectangle((i + 1) * 45, j * 45, 45, 45);
                Rectangle inters = new Rectangle();
                if (map[j][i] != 0 && map[j][i + 1] != 0) return 30;
                if (map[j][i] != 0) {
                    inters = getRect().intersection(boxLeft);
                    return (int) inters.getWidth();

                } else if ((map[j][i + 1] != 0)) {
                    inters = getRect().intersection(boxRight);
                    return (int) -inters.getWidth();
                }
                break;
            }
            case UP: {
                int i = x / 45;
                int j = y / 45;
                Rectangle boxLeft = new Rectangle(i * 45, j * 45, 45, 45);
                Rectangle boxRight = new Rectangle((i + 1) * 45, j * 45, 45, 45);
                Rectangle inters = new Rectangle();
                if (map[j][i] != 0) inters = getRect().intersection(boxLeft);
                else if ((map[j][i + 1] != 0)) inters = getRect().intersection(boxRight);
                if (map[j][i] != 0 && map[j][i + 1] != 0) return 30;
                if (map[j][i] != 0) {
                    inters = getRect().intersection(boxLeft);
                    return (int) inters.getWidth();

                } else if ((map[j][i + 1] != 0)) {
                    inters = getRect().intersection(boxRight);
                    return (int) -inters.getWidth();
                }
                break;
            }

        }
        return 0;
    }


    public void addToQueue(Cell cell) {
        if (cell.left != null && !cell.left.visited && cell.left.value == 0) {
            queue.add(cell.left);
            cell.left.visited = true;
            cell.left.parent = cell;
        }
        if (cell.right != null && !cell.right.visited && cell.right.value == 0) {
            queue.add(cell.right);
            cell.right.visited = true;
            cell.right.parent = cell;

        }
        if (cell.up != null && !cell.up.visited && cell.up.value == 0) {
            queue.add(cell.up);
            cell.up.visited = true;
            cell.up.parent = cell;

        }
        if (cell.down != null && !cell.down.visited && cell.down.value == 0) {
            queue.add(cell.down);
            cell.down.visited = true;
            cell.down.parent = cell;

        }
    }

    public void draw(Graphics2D g2d) {
        System.out.println(x);
        System.out.println(y);
        switch (direction) {
            case LEFT: {
                g2d.drawImage(botLeft[imgIndex / 50 % botLeft.length], x, y, 45, 45, null);
                break;
            }
            case RIGHT: {
                g2d.drawImage(botRight[imgIndex / 50 % botRight.length], x, y, 45, 45, null);
                break;
            }
            case UP: {
                g2d.drawImage(botUp[imgIndex / 50 % botUp.length], x, y, 45, 45, null);
                break;
            }

            case DOWN: {
                g2d.drawImage(botDown[imgIndex / 50 % botDown.length], x, y, 45, 45, null);
                break;
            }
        }
        imgIndex++;
        if (imgIndex > 100000) imgIndex = 0;
        isMoving = false;
        g2d.setColor(Color.BLUE);


        for (int i = 0; i < path.size(); i++)
            g2d.drawRect(path.get(i).x, path.get(i).y, 45, 45);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 45, 45);
    }

}



