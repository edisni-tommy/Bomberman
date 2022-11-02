package UI.Menu;

import Cores.Config;
import Cores.Main;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.font.BitmapFont;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;

public class MenuSetting extends ScenceGraph {
    private static Image background;
    private static Image layout1;
    private static Text setting;
    private static Text fullScreen;
    private static Text music;
    private static Text sound;
    private static Button fullScreenBut;
    private static Button musicBut;
    private static Button soundBut;
    private static Button quit;

    public MenuSetting() {
        initialize();
    }

    public void initialize() {
        background = new Image(new Vector2f(Main.WIDTH, Main.HEIGHT), "Textures/Menu/Lobby-Background.png");
        layout1 = new Image(new Vector2f(250, 110), new Vector2f(580, 500), "Textures/Settings/layout.png");
        String offPath = "Textures/Settings/Off.png";
        String onPath = "Textures/Settings/On.png";
        String state;
        state = (Config.isFullScreen()) ? onPath : offPath;
        fullScreenBut = new Button(new Vector2f(630, 440), new Vector2f(100, 50), state) {
            @Override
            public void Selected() {
                Config.setFullScreen(!Config.isFullScreen());
                if (Config.isFullScreen()) {
                    fullScreenBut.setPic(onPath);
                    Main.toggleToFullscreen();
                } else {
                    fullScreenBut.setPic(offPath);
                    Main.APP_SETTINGS.setFullscreen(false);
                    Main.APP_SETTINGS.setHeight(Main.HEIGHT);
                    Main.APP_SETTINGS.setWidth(Main.WIDTH);
                }
                Main.APP.setSettings(Main.APP_SETTINGS);
                ScenceGraphController.scale();
                Main.APP.restart();
            }
        };
        state = (Config.getMusic() > 0) ? onPath : offPath;
        musicBut = new Button(new Vector2f(630, 340), new Vector2f(100, 50), state) {
            @Override
            public void Selected() {
                if (Config.getMusic() > 0) {
                    Config.setMusic(0);
                    setPic(offPath);
                    MainMenu.music.stop();
                    if (Config.isFullScreen()) super.zoomOut();
                } else {
                    Config.setMusic(20);
                    setPic(onPath);
                    MainMenu.music.setVolume(20);
                    MainMenu.music.play();
                    if (Config.isFullScreen()) super.zoomOut();
                }
            }
        };
        state = (Config.getSound() > 0) ? onPath : offPath;
        soundBut = new Button(new Vector2f(630, 240), new Vector2f(100, 50), state) {
            @Override
            public void Selected() {
                if (Config.getSound() > 0) {
                    Config.setSound(0);
                    setPic(offPath);
                } else {
                    Config.setSound(1);
                    setPic(onPath);
                }
            }
        };
        quit = new Button(new Vector2f(770, 555), new Vector2f(50, 50), "Textures/Settings/X.png") {
            @Override
            public void Selected() {
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new MainMenu());
            }
        };
        setting = new Text("Setting", ColorRGBA.White, new Vector2f(450, 520), new Vector2f(200, 50));
        fullScreen = new Text("Full Screen", ColorRGBA.White, new Vector2f(350, 430), new Vector2f(200, 50));
        music = new Text("Music", ColorRGBA.White, new Vector2f(350, 330), new Vector2f(200, 50));
        sound = new Text("Sound", ColorRGBA.White, new Vector2f(350, 230), new Vector2f(200, 50));
        if (Config.isFullScreen()) {
            setting.setSize(86);
            fullScreen.setSize(76);
            sound.setSize(76);
            music.setSize(76);
        } else {
            setting.setSize(46);
            fullScreen.setSize(36);
            sound.setSize(36);
            music.setSize(36);
        }
        fullScreen.setAlignment(BitmapFont.Align.Left);
        sound.setAlignment(BitmapFont.Align.Left);
        music.setAlignment(BitmapFont.Align.Left);
    }

    @Override
    public void display() {
        background.display();
        layout1.display();
        setting.display();
        fullScreen.display();
        fullScreenBut.display();
        music.display();
        musicBut.display();
        sound.display();
        soundBut.display();
        quit.display();
        MainMenu.music.play();
    }

    @Override
    public void remove() {
        background.remove();
        layout1.remove();
        setting.remove();
        fullScreen.remove();
        fullScreenBut.remove();
        music.remove();
        musicBut.remove();
        sound.remove();
        soundBut.remove();
        quit.remove();
        MainMenu.music.pause();
    }

    @Override
    public void scale() {
        background.zoomOut();
        layout1.zoomOut();
        setting.scale();
        fullScreen.scale();
        fullScreenBut.zoomOut();
        music.scale();
        musicBut.zoomOut();
        soundBut.zoomOut();
        sound.scale();
        quit.zoomOut();
    }

    @Override
    public void update(float tpf) {

    }
}
