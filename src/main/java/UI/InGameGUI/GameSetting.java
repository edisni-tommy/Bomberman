package UI.InGameGUI;

import Cores.Main;
import Input.PlayerInput;
import UI.Menu.Button;
import UI.Menu.Image;
import UI.Menu.MainMenu;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.math.Vector2f;

public class GameSetting extends ScenceGraph {
    private static Image background;
    private static Button home;
    private static Button resume;
    private static Button restart;

    public GameSetting() {
        initialize();
        display();
    }

    public void initialize() {
        Main.GUI_NODE.detachAllChildren();
        background = new Image(new Vector2f(310, 150), new Vector2f(480,350), "Textures/Menu/Lobby-Background.png");
        home = new Button(new Vector2f(460, 400), new Vector2f(210, 80), "Textures/Menu/button_long.png", "BACK TO MENU"){
            @Override
            public void isSelected() {
                ScenceGraphController.remove();
                ScenceGraphController.removeExtension();
                ScenceGraphController.setScence(new MainMenu());
            }
        };
        resume = new Button(new Vector2f(460, 300), new Vector2f(210, 80), "Textures/Menu/button_long.png", "RESUME GAME"){
            @Override
            public void isSelected() {
                ScenceGraphController.removeExtension();
                ScenceGraphController.setPause();
            }
        };
        restart = new Button(new Vector2f(460, 200), new Vector2f(210, 80), "Textures/Menu/button_long.png", "RESTART GAME") {
            @Override
            public void isSelected() {
                ScenceGraphController.removeExtension();
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(InGame.getLevel()));
            }
        };
    }

    @Override
    public void remove() {
        background.remove();
        resume.remove();
        home.remove();
        restart.remove();
    }

    @Override
    public void display() {
        background.display();
        resume.display();
        home.display();
        restart.display();
    }

    @Override
    public void update(float tpf) {

    }
}
