package UI.Menu;

import Cores.Main;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.math.Vector2f;

public class MainMenu extends ScenceGraph {
    private static Image background;
    private static Button play;
    private static Button exit;

    public MainMenu() {
        initialize();
    }
    @Override
    public void display() {
        background.display();
        play.display();
        exit.display();
    }

    @Override
    public void remove() {
        background.remove();
        play.remove();
        exit.remove();
    }

    @Override
    public void update(float tpf) {

    }

    public void initialize() {
        background = new Image(new Vector2f(Main.WIDTH, Main.HEIGHT), "Textures/Menu/Lobby-Background.png");
        play = new Button(new Vector2f(440,400), new Vector2f(200, 75), "Textures/Menu/button_long.png", "PLAY"){
            @Override
            public void isSelected() {
                setDisplayed(false);
                ScenceGraphController.setScence(new InGame());
            }
        };
        exit = new Button(new Vector2f(440,300), new Vector2f(200, 75), "Textures/Menu/button_long.png", "EXIT") {
            @Override
            public void isSelected() {
                Main.APP.stop();
            }
        };
    }
}
