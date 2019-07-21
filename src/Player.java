import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Player {
    private static int size = 45;

    private Direction direction = Direction.DOWN;
    int heart = 3;
    int quantity;
    int lenght;
    int speed;
    public int x = 45;
    public int y = 45;
    private boolean isMoving = false;
    Cell[][] listCell = new Cell[15][15];
    int[][] mapBoom = new int[15][15];
    long timeDeath;

    boolean isAlive = true;
    private int imgIndex = 0;


    Player(int speed, int quantity, int length) {
        this.lenght = length;
        this.speed = speed;
        this.quantity = quantity;
    }

    private Image[] playerLeft = {
            new ImageIcon(getClass().getResource("/Images/player_left_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_left_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_left_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_left_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_left_5.png")).getImage()
    };

    private Image[] playerRight = {
            new ImageIcon(getClass().getResource("/Images/player_right_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_right_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_right_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_right_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_right_5.png")).getImage()
    };
    private Image[] playerUp = {
            new ImageIcon(getClass().getResource("/Images/player_up_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_up_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_up_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_up_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_up_5.png")).getImage()
    };

    private Image[] playerDown = {
            new ImageIcon(getClass().getResource("/Images/player_down_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_down_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_down_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_down_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/player_down_5.png")).getImage()
    };
    private Image[] imgDead = {
            new ImageIcon(getClass().getResource("/Images/player_dead.png")).getImage(),
    };
    private Image[] effect;

    void setEffect(Image[] effect) {
        this.effect = effect;
    }


    void changeDirection(Direction d) {
        this.direction = d;
        this.isMoving = true;
    }

    void move(int c) {
        if (isAlive) {
            if (c % 5 != 0) {
                return;
            }
            int xRaw = x;
            int yRaw = y;
            switch (direction) {

                case DOWN: {
                    int d = checkImpactWithObjs(xRaw, yRaw, listCell);
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
                    yRaw += speed;


                    break;

                }
                case UP: {
                    yRaw -= speed;
                    int d = checkImpactWithObjs(xRaw, yRaw, listCell);
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

                    int d = checkImpactWithObjs(xRaw, yRaw, listCell);

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
                    xRaw += speed;

                    break;
                }
                case LEFT: {
                    xRaw -= speed;
                    int d = checkImpactWithObjs(xRaw, yRaw, listCell);
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
            }

            x = xRaw;
            y = yRaw;

        }
    }

    private boolean walkThrough() {
        int newx = (int) Math.round((double) x / size) * size;
        int newy = (int) Math.round((double) y / size) * size;

        Rectangle obj = new Rectangle(newx, newy, size, size);
        Rectangle inters = getRect().intersection(obj);
        return mapBoom[newy / size][newx / size] == 1 && inters.getWidth() > 0 && inters.getHeight() > 0;
    }

    Boolean checkImpactVsBot(ArrayList<Bot> listBot) {
        for (int i = 0; i < listBot.size(); i++) {
            if (getRect().intersects(listBot.get(i).getRect())) return true;
        }
        return false;
    }

    private int checkImpactWithObjs(int x, int y, Cell[][] listCell) {
        switch (direction) {
            case RIGHT: {
                int i = x / size + 1;
                int j = y / size;
                Rectangle boxUp = new Rectangle(i * size, j * size, size, size);
                Rectangle boxDown = new Rectangle(i * size, (j + 1) * size, size, size);
                Rectangle inters = new Rectangle();
                if (listCell[j][i].value != 0 && listCell[j + 1][i].value != 0) return 30;
                if (listCell[j][i].value != 0) {
                    inters = getRect().intersection(boxUp);
                    return (int) inters.getHeight();

                } else if (listCell[j + 1][i].value != 0) {
                    inters = getRect().intersection(boxDown);
                    return (int) -inters.getHeight();

                }
                break;
            }
            case LEFT: {
                int i = x / size;
                int j = y / size;
                Rectangle boxUp = new Rectangle(i * size, j * size, size, size);
                Rectangle boxDown = new Rectangle(i * size, (j + 1) * size, size, size);
                Rectangle inters = new Rectangle();
                if (listCell[j][i].value != 0 && listCell[j + 1][i].value != 0) return 30;
                if (listCell[j][i].value != 0) {
                    inters = getRect().intersection(boxUp);
                    return (int) inters.getHeight();
                } else if (listCell[j + 1][i].value != 0) {
                    inters = getRect().intersection(boxDown);
                    return (int) -inters.getHeight();
                }
                break;
            }
            case DOWN: {
                int i = x / size;
                int j = y / size + 1;
                Rectangle boxLeft = new Rectangle(i * size, j * size, size, size);
                Rectangle boxRight = new Rectangle((i + 1) * size, j * size, size, size);
                Rectangle inters = new Rectangle();
                if (listCell[j][i].value != 0 && listCell[j][i + 1].value != 0) return 30;
                if (listCell[j][i].value != 0) {
                    inters = getRect().intersection(boxLeft);
                    return (int) inters.getWidth();

                } else if ((listCell[j][i + 1].value != 0)) {
                    inters = getRect().intersection(boxRight);
                    return (int) -inters.getWidth();
                }
                break;
            }
            case UP: {
                int i = x / size;
                int j = y / size;
                Rectangle boxLeft = new Rectangle(i * size, j * size, size, size);
                Rectangle boxRight = new Rectangle((i + 1) * size, j * size, size, size);
                Rectangle inters = new Rectangle();
                if (listCell[j][i].value != 0) inters = getRect().intersection(boxLeft);
                else if ((listCell[j][i + 1].value != 0)) inters = getRect().intersection(boxRight);
                if (listCell[j][i].value != 0 && listCell[j][i + 1].value != 0) return 30;
                if (listCell[j][i].value != 0) {
                    inters = getRect().intersection(boxLeft);
                    return (int) inters.getWidth();

                } else if ((listCell[j][i + 1].value != 0)) {
                    inters = getRect().intersection(boxRight);
                    return (int) -inters.getWidth();
                }
                break;
            }

        }
        return 0;
    }

    private int checkImpactWithObjs(int x, int y, int[][] map) {
        switch (direction) {
            case RIGHT: {
                int i = x / size + 1;
                int j = y / size;
                Rectangle boxUp = new Rectangle(i * size, j * size, size, size);
                Rectangle boxDown = new Rectangle(i * size, (j + 1) * size, size, size);
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
                int i = x / size;
                int j = y / size;
                Rectangle boxUp = new Rectangle(i * size, j * size, size, size);
                Rectangle boxDown = new Rectangle(i * size, (j + 1) * size, size, size);
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
                int i = x / size;
                int j = y / size + 1;
                Rectangle boxLeft = new Rectangle(i * size, j * size, size, size);
                Rectangle boxRight = new Rectangle((i + 1) * size, j * size, size, size);
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
                int i = x / size;
                int j = y / size;
                Rectangle boxLeft = new Rectangle(i * size, j * size, size, size);
                Rectangle boxRight = new Rectangle((i + 1) * size, j * size, size, size);
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


    void draw(Graphics2D g2d) {
        if (isAlive) {
            g2d.drawImage(effect[imgIndex / 20 % effect.length], x - 5, y - 5, size + 10, size + 10, null);
            imgIndex++;

            switch (direction) {
                case LEFT: {
                    if (!isMoving) {
                        g2d.drawImage(playerLeft[0], x, y, size, size, null);


                    } else {
                        g2d.drawImage(playerLeft[imgIndex / 50 % playerLeft.length], x, y, size, size, null);
                    }
                    break;
                }
                case RIGHT: {
                    if (!isMoving) {
                        g2d.drawImage(playerRight[0], x, y, size, size, null);

                    } else {
                        g2d.drawImage(playerRight[imgIndex / 50 % playerLeft.length], x, y, size, size, null);
                    }
                    break;
                }
                case UP: {
                    if (!isMoving) {
                        g2d.drawImage(playerUp[0], x, y, size, size, null);


                    } else {
                        g2d.drawImage(playerUp[imgIndex / 50 % playerLeft.length], x, y, size, size, null);
                    }
                    break;
                }

                case DOWN: {
                    if (!isMoving) {
                        g2d.drawImage(playerDown[0], x, y, size, size, null);


                    } else {
                        g2d.drawImage(playerDown[imgIndex / 50 % playerLeft.length], x, y, size, size, null);
                    }
                    break;
                }
            }
            if (imgIndex > 10000) imgIndex = 0;
            isMoving = false;
        } else g2d.drawImage(imgDead[0], x, y, size, size, null);

    }

    void setQuantity(int quantity) {
        if (quantity < 6)
            this.quantity = quantity;
    }

    void setLenght(int lenght) {
        if (lenght < 6)
            this.lenght = lenght;
    }

    void setSpeed(int speed) {
        if (speed < 4)
            this.speed = speed;
    }

    Rectangle getRect() {
        return new Rectangle(x, y, size, size);
    }


}



