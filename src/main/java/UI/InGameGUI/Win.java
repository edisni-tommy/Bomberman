package UI.InGameGUI;

import Cores.Config;
import Cores.Main;
import UI.Menu.Button;
import UI.Menu.Image;
import UI.Menu.MainMenu;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.math.Vector2f;

public class Win extends ScenceGraph {
    private static Image background;
    private static Image win;
    private static Button home;
    private static Button playAgain;
    private static Button nextLevel;

    private final static AudioNode victory = new AudioNode(Main.ASSET_MANAGER, "Sounds/Victory.ogg", AudioData.DataType.Buffer);

    public Win() {
        initialize();
    }

    public void initialize() {
        Main.GUI_NODE.detachAllChildren();
        background = new Image(new Vector2f(380, 260), new Vector2f(360, 240), "Textures/Menu/announcement_background.png");
        win = new Image(new Vector2f(410, 350), new Vector2f(300, 150), "Textures/Menu/win.png");
        home = new Button(new Vector2f(390, 300), new Vector2f(100, 50), "Textures/Menu/button_long.png", "Home") {
            @Override
            public void Selected() {
                Config.setLevel(InGame.getLevel());
                ScenceGraphController.removeExtension();
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new MainMenu());
            }
        };
        playAgain = new Button(new Vector2f(510, 300), new Vector2f(100, 50), "Textures/Menu/button_long.png", "Play Again") {
            @Override
            public void Selected() {
                ScenceGraphController.removeExtension();
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(InGame.getLevel()));
            }
        };
        nextLevel = new Button(new Vector2f(630,300), new Vector2f(100, 50), "Textures/Menu/button_long.png", "Next Level") {
            @Override
            public void Selected() {
                ScenceGraphController.removeExtension();
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(InGame.getLevel() + 1));
            }
        };
        victory.setVolume(Config.getSound());
        victory.setPositional(false);
    }

    @Override
    public void display() {
        victory.play();
        background.display();
        win.display();
        playAgain.display();
        nextLevel.display();
        home.display();
    }

    @Override
    public void remove() {
        background.remove();
        win.remove();
        home.remove();
        nextLevel.remove();
        playAgain.remove();
    }

    @Override
    public void scale() {

    }

    @Override
    public void update(float tpf) {

    }
}
