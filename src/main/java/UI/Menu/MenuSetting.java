package UI.Menu;

import Cores.Config;
import Cores.Main;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.math.Vector2f;

public class MenuSetting extends ScenceGraph {
    private static Image background;
    private static Button fullScreen;
    private static Button music;
    private static Button sound;
    private static Button returnMenu;

    public MenuSetting() {
        initialize();
    }

    public void initialize() {
        background = new Image(new Vector2f(Main.APP_SETTINGS.getWidth(), Main.APP_SETTINGS.getHeight()), "Textures/Menu/Lobby-Background.png");
        fullScreen = new Button(new Vector2f(440, 500), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Full Screen") {
            @Override
            public void Selected() {
                Config.setFullScreen(!Config.isFullScreen());
                if (Config.isFullScreen()) {
                    Main.toggleToFullscreen();
                } else {
                    Main.APP_SETTINGS.setFullscreen(false);
                    Main.APP_SETTINGS.setHeight(Main.HEIGHT);
                    Main.APP_SETTINGS.setWidth(Main.WIDTH);
                }
                Main.APP.setSettings(Main.APP_SETTINGS);
                ScenceGraphController.scale();
                Main.APP.restart();
            }
        };
        music = new Button(new Vector2f(440, 400), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Music");
        sound = new Button(new Vector2f(440, 300), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Sound");
        returnMenu = new Button(new Vector2f(440, 200), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Back") {
            @Override
            public void Selected() {
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new MainMenu());
            }
        };
    }

    @Override
    public void display() {
        background.display();
        fullScreen.display();
        music.display();
        sound.display();
        returnMenu.display();
    }

    @Override
    public void remove() {
        background.remove();
        fullScreen.remove();
        music.remove();
        sound.remove();
        returnMenu.remove();
    }

    @Override
    public void scale() {
        background.zoomOut();
        fullScreen.zoomOut();
        music.zoomOut();
        sound.zoomOut();
        returnMenu.zoomOut();
    }

    @Override
    public void update(float tpf) {

    }
}
