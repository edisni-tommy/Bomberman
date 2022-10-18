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
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector2f;
import org.lwjgl.Sys;

public class Defeat extends ScenceGraph {
    private static Image background;
    private static Image lose;
    private static Button home;
    private static Button tryAgain;

   private final static AudioNode defeat = new AudioNode(Main.ASSET_MANAGER, "Sounds/Defeat.ogg", AudioData.DataType.Buffer);

    public Defeat() {
        initialize();
    }

    public void initialize() {
        Main.GUI_NODE.detachAllChildren();
        background = new Image(new Vector2f(380, 260), new Vector2f(360, 240), "Textures/Menu/announcement_background.png");
        lose = new Image(new Vector2f(460, 375), new Vector2f(200, 100), "Textures/Menu/lose.png");
        home = new Button(new Vector2f(400, 300), new Vector2f(125, 50), "Textures/Menu/button_long.png", "Home") {
            @Override
            public void Selected() {
                Config.setLevel(InGame.getLevel());
                ScenceGraphController.removeExtension();
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new MainMenu());
            }
        };
        tryAgain = new Button(new Vector2f(600, 300), new Vector2f(125, 50), "Textures/Menu/button_long.png", "Try Again") {
            @Override
            public void Selected() {
                ScenceGraphController.removeExtension();
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(InGame.getLevel()));
            }
        };
        defeat.setVolume(Config.getSound());
        defeat.setPositional(false);
    }

    @Override
    public void display() {
        defeat.play();
        background.display();
        lose.display();
        tryAgain.display();
        home.display();
    }

    @Override
    public void remove() {
        background.remove();
        lose.remove();
        home.remove();
        tryAgain.remove();
    }

    @Override
    public void scale() {

    }

    @Override
    public void update(float tpf) {

    }
}
