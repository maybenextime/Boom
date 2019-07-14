import javax.swing.*;
import java.awt.*;


public class Bot {
    public Direction direction = Direction.DOWN;
    public int speed = 1;
    public int x = 45;
    public int y = 45;
    public boolean isMoving = false;
    public Direction direct=Direction.RIGHT;




    public int imgIndex = 0;


    public Bot(int x, int y, Direction direct) {
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

    public void changeDirection(Direction d) {
        this.direction = d;
    }

    public void botMove(int c, int[][] mapBox, int[][]mapBoom) {
        if (c % 20 != 0) {
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
                    } else{
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
                    } else
                    {
                        changeDirection(Direction.randomDirect());
                        return;
                    }
                }
                    int k = checkImpactWithObjs(xRaw, yRaw, mapBoom);
                    if (k != 0){
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




    public void draw(Graphics2D g2d) {
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
        if (imgIndex>100000) imgIndex=0;
        isMoving = false;
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,45,45);
    }

}



