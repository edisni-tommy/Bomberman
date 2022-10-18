package UI.Menu;

import Cores.Config;
import Cores.Main;
import UI.InGameGUI.InGame;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.math.Vector2f;

public class MainMenu extends ScenceGraph {
    private static Image background;
    private static Image logo;
    private static Button play;
    private static Button continuegame;
    private static Button setting;
    private static Button exit;
    public final static AudioNode music = new AudioNode(Main.ASSET_MANAGER, "Sounds/Lobby.wav", AudioData.DataType.Stream);

    public MainMenu() {
        initialize();
    }

    public void initialize() {
        background = new Image(new Vector2f(Main.WIDTH, Main.HEIGHT), "Textures/Menu/Lobby-Background.png");
        logo = new Image(new Vector2f(350, 520), new Vector2f(400, 150), "Textures/Menu/logo.png");
        play = new Button(new Vector2f(440, 450), new Vector2f(200, 75), "Textures/Menu/button_long.png", "New game") {
            @Override
            public void Selected() {
                setDisplayed(false);
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(1));
            }
        };
        continuegame = new Button(new Vector2f(440, 350), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Continue") {
            @Override
            public void Selected() {
                setDisplayed(false);
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(Config.getLevel()));
            }
        };
        setting = new Button(new Vector2f(440, 250), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Setting") {
            @Override
            public void Selected() {
                setDisplayed(false);
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new MenuSetting());
            }
        };
        exit = new Button(new Vector2f(440, 150), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Exit") {
            @Override
            public void Selected() {
                Main.APP.stop();
                Config.saveConfig();
            }
        };
        music.setVolume(Config.getMusic());
        music.setLooping(true);
        music.setPositional(false);
    }

    @Override
    public void display() {
        background.display();
        logo.display();
        play.display();
        continuegame.display();
        setting.display();
        exit.display();
        music.play();
    }

    @Override
    public void remove() {
        background.remove();
        logo.remove();
        play.remove();
        continuegame.remove();
        setting.remove();
        exit.remove();
        music.pause();
    }

    @Override
    public void scale() {

    }

    @Override
    public void update(float tpf) {

    }
}
