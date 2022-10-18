package UI.Menu;

import Cores.Config;
import Cores.Main;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.math.Vector2f;

public class MenuSetting extends ScenceGraph {
    private static Image background;
    private static Image wall;
    private static Button fullScreen;
    private static Button music;
    private static Button sound;
    private static Button returnMenu;

    public MenuSetting() {
        initialize();
    }

    public void initialize() {
        background = new Image(new Vector2f(Main.WIDTH, Main.HEIGHT), "Textures/Menu/Lobby-Background.png");
        wall = new Image(new Vector2f(250, 140), new Vector2f(580, 500), "Textures/Settings/mainwindow.png");
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
        String s = (Config.getMusic() > 0) ? "On" : "Off";
        music = new Button(new Vector2f(440, 400), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Music: " + s){
            @Override
            public void Selected() {
                if (Config.getMusic() > 0) {
                    Config.setMusic(0);
                    getText().setText("Music: Off");
                    MainMenu.music.stop();
                } else {
                    Config.setMusic(20);
                    getText().setText("Music: On");
                    MainMenu.music.setVolume(20);
                    MainMenu.music.play();
                }
            }
        };
        s = (Config.getSound() > 0) ? "On" : "Off";
        sound = new Button(new Vector2f(440, 300), new Vector2f(200, 75), "Textures/Menu/button_long.png", "Sound: "+ s){
            @Override
            public void Selected() {
                if (Config.getSound() > 0) {
                    Config.setSound(0);
                    getText().setText("Sound: Off");
                } else {
                    Config.setSound(50);
                    getText().setText("Sound: On");
                }
            }
        };
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
        //wall.display();
        fullScreen.display();
        music.display();
        sound.display();
        returnMenu.display();
        MainMenu.music.play();
    }

    @Override
    public void remove() {
        background.remove();
        //wall.remove();
        fullScreen.remove();
        music.remove();
        sound.remove();
        returnMenu.remove();
        MainMenu.music.pause();
    }

    @Override
    public void scale() {
        background.zoomOut();
        //wall.zoomOut();
        fullScreen.zoomOut();
        music.zoomOut();
        sound.zoomOut();
        returnMenu.zoomOut();
    }

    @Override
    public void update(float tpf) {

    }
}
