import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    public Direction direction = Direction.DOWN;
    public int quantity = 2;
    public int speed = 1;
    public int lenght = 2;
    public int x = 45;
    public int y = 45;
    public boolean isMoving = false;
    int[][] mapBox = new int[15][15];
    int[][] mapBoom = new int[15][15];

    public boolean isAlive=true;
    public int imgIndex = 0;


    public Player(int speed, int quantity, int length) {
        this.lenght = length;
        this.speed = speed;
        this.quantity = quantity;
    }

    Image[] playerLeft = {
            new ImageIcon(getClass().getResource("/Images/player_left_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_left_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_left_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_left_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_left_5.png")).getImage()
    };

    Image[] playerRight = {
            new ImageIcon(getClass().getResource("/Images/player_right_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_right_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_right_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_right_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_right_5.png")).getImage()
    };
    Image[] playerUp = {
            new ImageIcon(getClass().getResource("/Images/player_up_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_up_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_up_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_up_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_up_5.png")).getImage()
    };

    Image[] playerDown = {
            new ImageIcon(getClass().getResource("/Images/player_down_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_down_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_down_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_down_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_down_5.png")).getImage()
    };
    Image[] imgDead={
            new ImageIcon(getClass().getResource("/Images/player_dead.png")).getImage(),

    };


    public void changeDirection(Direction d) {
        this.direction = d;
        this.isMoving = true;


    }

    public void move(int c) {
        if(isAlive){
        if (c % 2 != 0) {
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
                    } else return;
                }
                if (!walkThrough()) {
                    int k = checkImpactWithObjs(xRaw, yRaw, mapBoom);
                    if (k != 0) {
                        if (0 < k && k <= 25) {
                            x++;
                            return;
                        }
                        if (-25 <= k && k < 0) {
                            x--;
                            return;
                        } else return;
                    }
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
                    } else return;
                }
                if (!walkThrough()) {
                    int k = checkImpactWithObjs(xRaw, yRaw, mapBoom);
                    if (k != 0) {
                        if (0 < k && k <= 25) {
                            x++;
                            return;
                        }
                        if (-25 <= k && k < 0) {
                            x--;
                            return;
                        } else return;
                    }
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
                    } else return;
                }
                if (!walkThrough()) {
                    int k = checkImpactWithObjs(xRaw, yRaw, mapBoom);
                    if (k != 0) {
                        if (0 < k && k <= 25) {
                            y++;
                            return;
                        }
                        if (-25 <= k && k < 0) {
                            y--;
                            return;
                        } else return;
                    }
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
                    } else return;
                }
                if (!walkThrough()) {
                    int k = checkImpactWithObjs(xRaw, yRaw, mapBoom);
                    if (k != 0) {
                        if (0 < k && k <= 25) {
                            y++;
                            return;
                        }
                        if (-25 <= k && k < 0) {
                            y--;
                            return;
                        } else return;
                    }                }
                break;
            }
        }

        x = xRaw;
        y = yRaw;

    }

    }

    public boolean walkThrough() {
        int newx = (int) Math.round((double) x / 45) * 45;
        int newy = (int) Math.round((double) y / 45) * 45;

        Rectangle obj = new Rectangle(newx, newy, 45, 45);
        Rectangle inters = getRect().intersection(obj);
        if (mapBoom[newy / 45][newx / 45] == 1 && inters.getWidth() > 0 && inters.getHeight() > 0) {

            return true;
        }
        return false;
    }

    public Boolean checkcheckImpactVsBot(ArrayList<Bot2> listBot){
        for(int i=0;i<listBot.size();i++){
             return(getRect().intersects(listBot.get(i).getRect()));
        }
        return false;
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
        if(isAlive){
            switch (direction) {
                case LEFT: {
                    if (!isMoving) {
                        g2d.drawImage(playerLeft[0], x, y, 45, 45, null);


                    } else {
                        imgIndex++;
                        g2d.drawImage(playerLeft[imgIndex / 50 % playerLeft.length], x, y, 45, 45, null);
                    }
                    break;
                }
                case RIGHT: {
                    if (!isMoving) {
                        g2d.drawImage(playerRight[0], x, y, 45, 45, null);
                        imgIndex = 1;

                    } else {
                        imgIndex++;
                        g2d.drawImage(playerRight[imgIndex / 50 % playerLeft.length], x, y, 45, 45, null);
                    }
                    break;
                }
                case UP: {
                    if (!isMoving) {
                        g2d.drawImage(playerUp[0], x, y, 45, 45, null);
                        imgIndex = 1;

                    } else {
                        imgIndex++;
                        g2d.drawImage(playerUp[imgIndex / 50 % playerLeft.length], x, y, 45, 45, null);
                    }
                    break;
                }

                case DOWN: {
                    if (!isMoving) {
                        g2d.drawImage(playerDown[0], x, y, 45, 45, null);
                        imgIndex = 1;


                    } else {
                        imgIndex++;
                        g2d.drawImage(playerDown[imgIndex / 50 % playerLeft.length], x, y, 45, 45, null);
                    }
                    break;
                }
            }
            imgIndex++;
            isMoving = false;
        } else g2d.drawImage(imgDead[0],x,y,45,45,null);

    }

    public Rectangle getRect(){
        return new Rectangle(x,y,45,45);
    }


}



