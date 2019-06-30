
import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

public class Audio {
    private HashMap<String, AudioClip> audioMap;

    public Audio() {
        audioMap = new HashMap<>();
        addAudio("/Sounds/menu.wav");
        addAudio("/Sounds/click.wav");
    }

    public void addAudio(String name) {
        AudioClip auClip = Applet.newAudioClip(Audio.class.getResource(name));
        audioMap.put(name, auClip);
    }

    public AudioClip getAudio(String name) {
        return audioMap.get(name);
    }
}
