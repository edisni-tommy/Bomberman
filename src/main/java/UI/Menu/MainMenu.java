package UI.Menu;

import Cores.Config;
import Cores.Main;
import UI.InGameGUI.InGame;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.math.Vector2f;

public class MainMenu extends ScenceGraph {
    private static Image background;
    private static Button play;
    private static Button continuegame;
    private static Button setting;
    private static Button exit;

    public MainMenu() {
        initialize();
    }

    public void initialize() {
        background = new Image(new Vector2f(Main.APP_SETTINGS.getWidth(), Main.APP_SETTINGS.getHeight()), "Textures/Menu/Lobby-Background.png");
        play = new Button(new Vector2f(440, 500), new Vector2f(200, 75), "Textures/Menu/button_long.png", "New game") {
            @Override
            public void Selected() {
                setDisplayed(false);
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(1));
            }
        };
        continuegame = new Button(new Vector2f(440, 400), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Continue") {
            @Override
            public void Selected() {
                setDisplayed(false);
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(Config.getLevel()));
            }
        };
        setting = new Button(new Vector2f(440, 300), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Setting") {
            @Override
            public void Selected() {
                setDisplayed(false);
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new MenuSetting());
            }
        };
        exit = new Button(new Vector2f(440, 200), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Exit") {
            @Override
            public void Selected() {
                Main.APP.stop();
                Config.saveConfig();
            }
        };
    }

    @Override
    public void display() {
        background.display();
        play.display();
        continuegame.display();
        setting.display();
        exit.display();
    }

    @Override
    public void remove() {
        background.remove();
        play.remove();
        continuegame.remove();
        setting.remove();
        exit.remove();
    }

    @Override
    public void scale() {

    }

    @Override
    public void update(float tpf) {

    }
}
