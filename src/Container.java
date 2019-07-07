import javax.swing.*;
import java.awt.*;


public class Container extends JPanel {
    Audio audio = new Audio();
    private Menu menu;
    private Options options;
    private HighScore highScore;
    private PlayGame playGame;
    private CardLayout card = new CardLayout();
    Boolean isMusic = true;
    Boolean isSound = true;

    public Container() {
        this.setLayout(card);
        menu = new Menu(Container.this);
        this.add(menu, "menu");
        options = new Options(Container.this);
        this.add(options, "options");
        highScore = new HighScore(Container.this);
        this.add(highScore, "highScore");
        playGame = new PlayGame(Container.this);
        this.add(playGame, "playGame");

        showMenu();
    }


    public void showMenu() {
        if (isSound) audio.getAudio("/Sounds/click.wav").play();
        menu.requestFocus();
        card.show(Container.this, "menu");
        if (isMusic) audio.getAudio("/Sounds/menu.wav").loop();
        else audio.getAudio("/Sounds/menu.wav").stop();


    }

    public void showOptions() {
        if (isSound) audio.getAudio("/Sounds/click.wav").play();

        card.show(Container.this, "options");
        options.requestFocus();


    }

    public void showHighScore() {
        if (isSound) audio.getAudio("/Sounds/click.wav").play();
        card.show(Container.this, "highScore");
        highScore.requestFocus();

    }

    public void showPLayGame() {
        if (isSound) audio.getAudio("/Sounds/click.wav").play();
        card.show(Container.this, "playGame");
        playGame.requestFocus();

    }
}
