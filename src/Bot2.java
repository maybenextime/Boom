import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Bot2 extends Bot {
    private static int size = 45;
    private Direction direction;
    private int speed = 1;
    public int x;
    public int y;

    private ArrayList<Cell> openList = new ArrayList<>();
    private ArrayList<Cell> closed = new ArrayList<>();
    private ArrayList<Cell> path = new ArrayList<>();


    Bot2(int x, int y, Direction direct) {
        super(x, y,1, direct);
        this.x = x;
        this.y = y;
        this.direction = direct;
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
        super.setImg(botUp, botDown, botLeft, botRight);
    }

    private boolean findPath(Player player, Cell[][] listCell) {
        openList.clear();
        closed.clear();

        int col = (int) Math.round((double) x / size);
        int row = (int) Math.round((double) y / size);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                listCell[i][j].setDefaul();
            }
        }
        openList.add(listCell[row][col]);
        while (!openList.isEmpty()) {
            Cell cell = openList.remove(0);
            closed.add(cell);
            cell.visited = true;
            if (cell.x / size == player.x / size && cell.y / size == player.y / size) return true;
            addToOpenList(player.mapBoom, cell);
        }
        return false;
    }

    @Override
    public void setDirection(Player player, Cell[][] listCell) {
        path.clear();
        if (findPath(player, listCell)) {
            ;
            Cell parent = closed.get(closed.size() - 1).parent;
            path.add(closed.get(closed.size() - 1));
            while (parent != null) {
                path.add(parent);
                parent = path.get(path.size() - 1).parent;
            }
            if (path.size() < 2) return;
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
    }

    @Override
    public void botMove(int c, Cell[][] mapBox, int[][] mapBoom) {
        if (c % 10 != 0) {
            return;
        }
        this.direction = super.direction;
        int xRaw = x;
        int yRaw = y;
        switch (direction) {
            case DOWN: {
                int d = checkImpactWithObjs(xRaw, yRaw, mapBox);
                if (d != 0) {
                    if (0 < d && d <= 25) {
                        x++;
                        super.setX(this.x);
                        return;
                    }
                    if (-25 <= d && d < 0) {
                        x--;
                        super.setX(this.x);
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
                yRaw += speed;

                break;

            }
            case UP: {
                yRaw -= speed;
                int d = checkImpactWithObjs(xRaw, yRaw, mapBox);
                if (d != 0) {
                    if (0 < d && d <= 25) {
                        x++;
                        super.setX(this.x);
                        return;
                    }
                    if (-25 <= d && d < 0) {
                        x--;
                        super.setX(this.x);
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

                int d  = checkImpactWithObjs(xRaw, yRaw, mapBox);

                if (d != 0) {
                    if (0 < d && d <= 25) {
                        y++;
                        super.setY(this.y);
                        return;
                    }
                    if (-25 <= d && d < 0) {
                        y--;
                        super.setY(this.y);
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
                xRaw += speed;
                break;
            }
            case LEFT: {
                xRaw -= speed;
                int d = checkImpactWithObjs(xRaw, yRaw, mapBox);
                if (d != 0) {
                    if (0 < d && d <= 25) {
                        y++;
                        super.setY(this.y);
                        return;
                    }
                    if (-25 <= d && d < 0) {
                        y--;
                        super.setY(this.y);
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
        super.setCoord(this.x, this.y);

    }

    private void addToOpenList(int[][] mapBoom, Cell cell) {
        if (cell.left != null && !cell.left.visited && cell.left.value == 0 && mapBoom[cell.y / size][cell.x / size] == 0) {
            cell.left.visited = true;
            cell.left.parent = cell;
            openList.add(cell.left);
        }
        if (cell.right != null && !cell.right.visited && cell.right.value == 0 && mapBoom[cell.y / size][cell.x / size] == 0) {
            cell.right.visited = true;
            cell.right.parent = cell;
            openList.add(cell.right);
        }
        if (cell.up != null && !cell.up.visited && cell.up.value == 0 && mapBoom[cell.y / size][cell.x / size] == 0) {
            cell.up.visited = true;
            cell.up.parent = cell;
            openList.add(cell.up);

        }
        if (cell.down != null && !cell.down.visited && cell.down.value == 0 && mapBoom[cell.y / size][cell.x / size] == 0) {
            cell.down.visited = true;
            cell.down.parent = cell;
            openList.add(cell.down);
        }
    }



}



