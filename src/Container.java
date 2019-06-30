import javax.swing.*;
import java.awt.*;


public class Container extends JPanel {
    Audio audio = new Audio();
    private Menu menu;
    private Options options;
    private HighScore highScore;
    private CardLayout card = new CardLayout();
    Boolean isMusic= true;
    Boolean isSound= true;

    public Container(){
        this.setLayout(card);
        menu= new Menu(Container.this);
        this.add(menu, "menu");
        options = new Options(Container.this);
        this.add(options, "options");
        highScore= new HighScore(Container.this);
        this.add(highScore,"highScore");
       showMenu();
    };

    public void showMenu() {
        card.show(Container.this, "menu");
        if(isMusic) audio.getAudio("/Sounds/menu.wav").loop();
        else audio.getAudio("/Sounds/menu.wav").stop();
        if(isSound) audio.getAudio("/Sounds/click.wav").play();


    }

    public void showOptions(){
        card.show(Container.this, "options");
        if(isSound) audio.getAudio("/Sounds/click.wav").play();


    }

    public void showHighScore(){
        card.show(Container.this, "highScore");
        if(isSound) audio.getAudio("/Sounds/click.wav").play();

    }
}
