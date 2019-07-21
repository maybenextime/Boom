
import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

class Audio {
    private HashMap<String, AudioClip> audioMap;

    Audio() {
        audioMap = new HashMap<>();
        addAudio("/Sounds/menu.wav");
        addAudio("/Sounds/click.wav");
        addAudio("/Sounds/bombEx.wav");
        addAudio("/Sounds/newBomb.wav");
        addAudio("/Sounds/bot_die.wav");
        addAudio("/Sounds/item.wav");
        addAudio("/Sounds/lose.mid");
        addAudio("/Sounds/win.wav");
        addAudio("/Sounds/player_die.wav");
    }

    private void addAudio(String name) {
        AudioClip auClip = Applet.newAudioClip(Audio.class.getResource(name));
        audioMap.put(name, auClip);
    }

    AudioClip getAudio(String name) {
        return audioMap.get(name);
    }
}
