import javax.swing.*;
import java.awt.*;


public class Bot1 extends Bot {
    private Direction direction;
    private static int speed = 1;
    public int x ;
    public int y ;

    Bot1(int x, int y, Direction direct) {
        super(x, y,1, direct);
        this.x=x;
        this.y=y;
        this.direction=direct;
        Image[] botDown = {
                new ImageIcon(getClass().getResource("/Images/boss_ donw_1.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss_down2.png")).getImage(),

        };
        Image[] botLeft = {
                new ImageIcon(getClass().getResource("/Images/boss_left_1.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss_left2.png")).getImage(),


        };
        Image[] botRight = {
                new ImageIcon(getClass().getResource("/Images/boss_right_1.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss_right2.png")).getImage(),


        };
        Image[] botUp = {
                new ImageIcon(getClass().getResource("/Images/boss_ up_1.png")).getImage(),
                new ImageIcon(getClass().getResource("/Images/boss_up2.png")).getImage(),


        };
        super.setImg(botUp, botDown, botLeft, botRight);
    }

    @Override
    public void botMove(int c, Cell[][] mapBox, int[][] mapBoom) {
        if (c % 10 != 0) {
            return;
        }
        int xRaw = x;
        int yRaw = y;
        direction=super.direction;
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
        super.setCoord(this.x,this.y);
    }

}





