import java.awt.*;

class FontAB {
    Font fontAB;

    FontAB(int size) {
        this.fontAB = new Font("Tahoma", Font.BOLD, size);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, HighScore.class.getResourceAsStream("/fonts/ApexBrush.otf"));
            font = font.deriveFont(Font.BOLD, size);
            this.fontAB = font;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
