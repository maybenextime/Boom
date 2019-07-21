import java.awt.*;


public abstract class Bot {
    private static int size = 45;
    Direction direction;
    public int heart;
    public int x;
    public int y;
    public boolean isBot3=false;
    public boolean isUndeath=false;
    public long timeCount;
    private int imgIndex = 0;


    Bot(int x, int y, int heart, Direction direct) {
        this.x = x;
        this.y = y;
        this.direction = direct;
        this.heart=heart;
    }

    private Image[] botDown;
    private Image[] botLeft;
    private Image[] botUp;
    private Image[] botRight;

    private void setImgUp(Image[] botUp) {
        this.botUp = botUp;
    }

    private void setImgDown(Image[] botDown) {
        this.botDown = botDown;
    }

    private void setImgLeft(Image[] botLeft) {
        this.botLeft = botLeft;
    }

    private void setImgRight(Image[] botRight) {
        this.botRight = botRight;
    }

    void setImg(Image[] botUp, Image[] botDown, Image[] botLeft, Image[] botRight) {
        setImgUp(botUp);
        setImgDown(botDown);
        setImgLeft(botLeft);
        setImgRight(botRight);

    }

    int checkImpactWithObjs(int x, int y, Cell[][] listCell) {
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

    int checkImpactWithObjs(int x, int y, int[][] mapBoom) {
        switch (direction) {
            case RIGHT: {
                int i = x / size + 1;
                int j = y / size;
                Rectangle boxUp = new Rectangle(i * size, j * size, size, size);
                Rectangle boxDown = new Rectangle(i * size, (j + 1) * size, size, size);
                Rectangle inters = new Rectangle();
                if (mapBoom[j][i] != 0 && mapBoom[j + 1][i] != 0) return 30;
                if (mapBoom[j][i] != 0) {
                    inters = getRect().intersection(boxUp);
                    return (int) inters.getHeight();

                } else if (mapBoom[j + 1][i] != 0) {
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
                if (mapBoom[j][i] != 0 && mapBoom[j + 1][i] != 0) return 30;
                if (mapBoom[j][i] != 0) {
                    inters = getRect().intersection(boxUp);
                    return (int) inters.getHeight();
                } else if (mapBoom[j + 1][i] != 0) {
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
                if (mapBoom[j][i] != 0 && mapBoom[j][i + 1] != 0) return 30;
                if (mapBoom[j][i] != 0) {
                    inters = getRect().intersection(boxLeft);
                    return (int) inters.getWidth();

                } else if ((mapBoom[j][i + 1] != 0)) {
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
                if (mapBoom[j][i] != 0) inters = getRect().intersection(boxLeft);
                else if ((mapBoom[j][i + 1] != 0)) inters = getRect().intersection(boxRight);
                if (mapBoom[j][i] != 0 && mapBoom[j][i + 1] != 0) return 30;
                if (mapBoom[j][i] != 0) {
                    inters = getRect().intersection(boxLeft);
                    return (int) inters.getWidth();

                } else if ((mapBoom[j][i + 1] != 0)) {
                    inters = getRect().intersection(boxRight);
                    return (int) -inters.getWidth();
                }
                break;
            }

        }
        return 0;
    }


    void changeDirection(Direction d) {
        this.direction = d;
    }

    public void setDirection(Player player, Cell[][] listCell) {}

    public void botMove(int c, Cell[][] listCell, int[][] mapBoom) {}

    void draw(Graphics2D g2d) {
        switch (direction) {
            case LEFT: {
                g2d.drawImage(botLeft[imgIndex / 50 % botLeft.length], x, y, size, size, null);
                break;
            }
            case RIGHT: {
                g2d.drawImage(botRight[imgIndex / 50 % botRight.length], x, y, size, size, null);
                break;
            }
            case UP: {
                g2d.drawImage(botUp[imgIndex / 50 % botUp.length], x, y, size, size, null);
                break;
            }
            case DOWN: {
                g2d.drawImage(botDown[imgIndex / 50 % botDown.length], x, y, size, size, null);
                break;
            }
        }
        imgIndex++;
        if (imgIndex > 1000) imgIndex = 0;
    }

    Rectangle getRect() {
        return new Rectangle(x, y, size, size);
    }

    void setCoord(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
