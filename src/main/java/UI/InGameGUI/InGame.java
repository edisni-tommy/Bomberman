package UI.InGameGUI;

import Cores.Config;
import Cores.GameEnvironment;
import Cores.Main;
import Cores.Map;
import Entities.BombList;
import Entities.Player.PlayerList;
import Input.PlayerInput;
import Particles.BombExplosionList;
import UI.PlayerStatus.BuffStatus;
import UI.ScenceGraph;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;

import java.io.FileNotFoundException;

public class InGame extends ScenceGraph {
    private final static AudioNode music = new AudioNode(Main.ASSET_MANAGER, "Sounds/BGM.wav", AudioData.DataType.Stream);
    private static int level = 1;

    public InGame(int level) {
        InGame.level = level;
        setDisplayed(true);
        music.setVolume(Config.getMusic());
        music.setLooping(true);
        music.setPositional(false);
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int lastLevel) {
        level = lastLevel;
    }

    public void setDisplayed(boolean displayed) {
        super.setDisplayed(displayed);
        PlayerInput.setActive(displayed);
        if (!displayed) {
           music.pause();
        }
    }

    @Override
    public void update(float tpf) {
        if (!isDisplayed()) {
            return;
        }
        PlayerList.onUpdate(tpf);
        BombList.onUpdate(tpf);
        BombExplosionList.onUpdate(tpf);
        BuffStatus.onUpdate();
    }

    @Override
    public void display() {
        GameEnvironment.initalize();
        try {
            Map.initalize(level);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
        //SystemInput.initialize();
        PlayerInput.initialize();
        music.play();
    }

    @Override
    public void remove() {
        PlayerList.removeAll();
        BombList.removeAll();
        BombExplosionList.removeAll();
        GameEnvironment.remove();
        setDisplayed(false);
        music.stop();
    }

    @Override
    public void scale() {

    }
}
