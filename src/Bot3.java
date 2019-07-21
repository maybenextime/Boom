import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class Bot3 extends Bot {
    private static int size = 45;
    private static int speed = 1;
    public boolean isMove= true;
    private Direction direction;
    public int x;
    public int y;


    private ArrayList<Cell> openList = new ArrayList<>();
    private ArrayList<Cell> closed = new ArrayList<>();
    private ArrayList<Cell> path = new ArrayList<>();


    Bot3(int x, int y, Direction direct) {
        super(x, y, direct);
        this.x = x;
        this.y = y;
        this.direction = direct;
        Image[] botDown = {
                new ImageIcon(getClass().getResource("/Images/boss3_down_01.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_down_02.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_down_03.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_down_04.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_down_05.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_down_06.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_down_07.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_down_08.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_down_09.png")).getImage(),

        };
        Image[] botLeft = {
                new ImageIcon(getClass().getResource("/Images/boss3_left_01.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_left_02.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_left_03.png")).getImage(),

        };
        Image[] botUp = {
                new ImageIcon(getClass().getResource("/Images/boss3_up_01.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_up_02.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_up_03.png")).getImage(),


        };
        Image[] botRight = {
                new ImageIcon(getClass().getResource("/Images/boss3_right_01.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_right_02.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss3_right_03.png")).getImage(),


        };
        super.setImg(botUp, botDown, botLeft, botRight);
    }

    private void findPath(Player player, Cell[][] listCell) {
        openList.clear();
        closed.clear();

        int col = (int) Math.round((double) x / size);
        int row = (int) Math.round((double) y / size);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                listCell[i][j].setDefaultA();
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (player.mapBoom[i][j] == 1) {
                    listCell[i][j].setBoomCost();
                    for (int k = 1; k < player.lenght + 1; k++) {
                        if (i + k < 14) listCell[i + k][j].setBoomCost2(k);
                        if (i - k > 0) listCell[i - k][j].setBoomCost2(k);
                        if (j + k < 14) listCell[i][j + k].setBoomCost2(k);
                        if (j - k > 0) listCell[i][j - k].setBoomCost2(k);
                    }
                }
            }
        }

        openList.add(listCell[row][col]);
        openList.get(0).cost = 0;
        openList.get(0).checkA = true;
        Comparater compare = new Comparater();
        compare.xPlayer = player.x;
        compare.yPlayer = player.y;

        while (!openList.isEmpty()) {
            Cell cell = openList.get(0);
            if (cell.x / size == player.x / size && cell.y / size == player.y / size) {
                closed.add(cell);
                return;
            } else {
                if (closed.contains(cell)) {
                    openList.remove(0);
                } else {
                    closed.add(cell);
                    openList.remove(0);
                    addToOpenList(cell);
                    Collections.sort(openList, compare);
                }
            }
        }
    }

    @Override
    public void setDirection(Player player, Cell[][] listCell) {
        path.clear();
        findPath(player, listCell);
        isMove=true;
        if (!openList.isEmpty()) {
            Cell parent = closed.get(closed.size() - 1).parent;
            path.add(closed.get(closed.size() - 1));
            while (parent != null) {
                path.add(parent);
                parent = path.get(path.size() - 1).parent;
            }
            if(path.size()<2 )return;
            Cell next = path.get(path.size() - 2);
            if(next.cost>1000 && path.get(path.size()-1).cost ==1 && (x%45==0)  &&( y%45==0)) {
                isMove=false;
                return;
            }
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
        if (isMove) {
            if (c % 10 != 0) {
                return;
            }
            int xRaw = x;
            int yRaw = y;
            direction = super.direction;
            switch (direction) {
                case DOWN: {
                    yRaw += speed;
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

                    int d = checkImpactWithObjs(xRaw, yRaw, mapBox);

                    if (d != 0) {
                        if (0 < d && d <= 25) {
                            y++;
                            super.setY(y);
                            return;
                        }
                        if (-25 <= d && d < 0) {
                            y--;
                            super.setY(y);
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
                            super.setY(y);
                            return;
                        }
                        if (-25 <= d && d < 0) {
                            y--;
                            super.setY(y);
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
    }


    private void addToOpenList(Cell cell) {
        if (cell.left != null && !cell.left.checkA) {
            openList.add(cell.left);
            cell.left.checkA = true;
            cell.left.parent = cell;
            cell.left.cost += cell.left.parent.cost;
        }
        if (cell.right != null && !cell.right.checkA) {
            openList.add(cell.right);
            cell.right.checkA = true;
            cell.right.parent = cell;
            cell.right.cost += cell.right.parent.cost;


        }
        if (cell.up != null && !cell.up.checkA) {
            openList.add(cell.up);
            cell.up.checkA = true;
            cell.up.parent = cell;
            cell.up.cost += cell.up.parent.cost;


        }
        if (cell.down != null && !cell.down.checkA) {
            openList.add(cell.down);
            cell.down.checkA = true;
            cell.down.parent = cell;
            cell.down.cost += cell.down.parent.cost;


        }
    }

}



