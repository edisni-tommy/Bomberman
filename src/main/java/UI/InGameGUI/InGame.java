package UI.InGameGUI;

import Cores.Config;
import Cores.GameEnvironment;
import Cores.Main;
import Cores.Map;
import Entities.BombList;
import Entities.Player.PlayerList;
import Input.PlayerInput;
import Particles.BombExplosionList;
import UI.Menu.Text;
import UI.PlayerStatus.BuffStatus;
import UI.ScenceGraph;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioSource;
import com.jme3.font.BitmapFont;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InGame extends ScenceGraph {
    private final static AudioNode music = new AudioNode(Main.ASSET_MANAGER, "Sounds/BGM.wav", AudioData.DataType.Stream);
    private static int level = 1;

    private Text curLevel = new Text("Level " + level, ColorRGBA.Black, new Vector2f(20, Main.HEIGHT - 60), new Vector2f(200 , 75));
    private Text remainEnemy = new Text("Enemies: 0", ColorRGBA.Black, new Vector2f(20, Main.HEIGHT - 100), new Vector2f(200, 75));

    public InGame(int level) {
        InGame.level = level;
        setDisplayed(true);
        music.setVolume(Config.getMusic());
        music.setLooping(true);
        music.setPositional(false);
        curLevel.setText("Level: " + level);
        curLevel.setAlignment(BitmapFont.Align.Left);
        remainEnemy.setAlignment(BitmapFont.Align.Left);
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
        } else {
            if (music.getStatus().equals(AudioSource.Status.Paused)) {
                music.play();
            }
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
        remainEnemy.setText("Enemies: " + Map.enemyCount);
    }

    @Override
    public void display() {
        GameEnvironment.initalize();
        try {
            Map.initalize(level);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //SystemInput.initialize();
        PlayerInput.initialize();
        remainEnemy.display();
        curLevel.display();
        music.play();
    }

    @Override
    public void remove() {
        PlayerList.removeAll();
        BombList.removeAll();
        BombExplosionList.removeAll();
        GameEnvironment.remove();
        setDisplayed(false);
        curLevel.remove();
        remainEnemy.remove();
        music.stop();
    }

    @Override
    public void scale() {

    }
}
