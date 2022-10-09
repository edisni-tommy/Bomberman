package UI.InGameGUI;

import Cores.Config;
import Cores.Main;
import UI.Menu.Button;
import UI.Menu.Image;
import UI.Menu.MainMenu;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector2f;
import org.lwjgl.Sys;

public class Defeat extends ScenceGraph {
    private static Image background;
    private static Button home;
    private static Button tryAgain;

    public Defeat() {
        initialize();
    }

    public void initialize() {
        Main.GUI_NODE.detachAllChildren();
        background = new Image(new Vector2f(380, 260), new Vector2f(360, 240), "Textures/Menu/Lobby-Background.png");
        home = new Button(new Vector2f(460, 400), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Home") {
            @Override
            public void Selected() {
                Config.setLevel(InGame.getLevel());
                ScenceGraphController.removeExtension();
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new MainMenu());
            }
        };
        tryAgain = new Button(new Vector2f(460, 300), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Try Again") {
            @Override
            public void Selected() {
                ScenceGraphController.removeExtension();
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(InGame.getLevel()));
                ;
            }
        };
    }

    @Override
    public void display() {
        background.display();
        tryAgain.display();
        home.display();
    }

    @Override
    public void remove() {
        background.remove();
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
