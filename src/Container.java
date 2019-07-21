import javax.swing.*;
import java.awt.*;


class Container extends JPanel {
    Audio audio = new Audio();
    private Menu menu;
    Options options;
    private HighScore highScore;
    private PlayGame playGame;
    private CardLayout card = new CardLayout();
    Boolean isMusic = false;
    Boolean isSound = true;

    Container() {
        this.setLayout(card);
        menu = new Menu(Container.this);
        this.add(menu, "menu");
        options = new Options(Container.this);
        this.add(options, "options");
        highScore = new HighScore(Container.this);
        this.add(highScore, "highScore");
        playGame = new PlayGame();
        this.add(playGame, "playGame");

        showMenu();
    }


    void showMenu() {
        if (isSound) audio.getAudio("/Sounds/click.wav").play();
        menu.requestFocus();
        card.show(Container.this, "menu");
        if (isMusic) audio.getAudio("/Sounds/menu.wav").loop();
        else audio.getAudio("/Sounds/menu.wav").stop();

    }

    void showOptions() {
        if (isSound) audio.getAudio("/Sounds/click.wav").play();
        card.show(Container.this, "options");
        options.requestFocus();


    }

    void showHighScore() {
        Container.this.remove(highScore);
        highScore= new HighScore(Container.this);
        this.add(highScore,"highScore");
        if (isSound) audio.getAudio("/Sounds/click.wav").play();
        card.show(Container.this, "highScore");
        highScore.requestFocus();

    }

    void showPLayGame() {
        Container.this.remove(playGame);
        playGame = new PlayGame(Container.this);
        playGame.manager.timeStart=System.currentTimeMillis();
        this.add(playGame, "playGame");
        playGame.manager.isMusic=isMusic;
        playGame.manager.isSound=isSound;
        if (isSound) audio.getAudio("/Sounds/click.wav").play();
        card.show(Container.this, "playGame");
        playGame.requestFocus();

    }
}
