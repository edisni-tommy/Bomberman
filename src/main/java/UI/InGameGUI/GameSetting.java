package UI.InGameGUI;

import Cores.Config;
import Cores.Main;
import Entities.Player.MainPlayer;
import UI.Menu.Button;
import UI.Menu.Image;
import UI.Menu.MainMenu;
import UI.Menu.Text;
import UI.PlayerStatus.BuffStatus;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import com.jme3.font.BitmapFont;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;

public class GameSetting extends ScenceGraph {
    private static Image layout1;
    private static Text music;
    private static Text sound;
    private static Button home;
    private static Button restart;
    private static Button musicBut;
    private static Button soundBut;
    private static Button quit;

    public GameSetting() {
        initialize();
        display();
    }

    public void initialize() {
        Main.GUI_NODE.detachAllChildren();
        layout1 = new Image(new Vector2f(250, 110), new Vector2f(580, 500), "Textures/Settings/layout.png");
        String offPath = "Textures/Settings/Off.png";
        String onPath = "Textures/Settings/On.png";
        String state = (Config.getMusic() > 0) ? onPath : offPath;
        musicBut = new Button(new Vector2f(585, 500), new Vector2f(100, 50), state) {
            @Override
            public void Selected() {
                if (Config.getMusic() > 0) {
                    Config.setMusic(0);
                    setPic(offPath);
                    InGame.music.pause();
                } else {
                    Config.setMusic(20);
                    setPic(onPath);
                    InGame.music.setVolume(20);
                    InGame.music.play();
                }
                if (Config.isFullScreen()) super.zoomOut();
            }
        };
        state = (Config.getSound() > 0) ? onPath : offPath;
        soundBut = new Button(new Vector2f(585, 400), new Vector2f(100, 50), state) {
            @Override
            public void Selected() {
                if (Config.getSound() > 0) {
                    Config.setSound(0);
                    MainPlayer.BuffItem.setVolume(0);
                    setPic(offPath);
                } else {
                    Config.setSound(1);
                    MainPlayer.BuffItem.setVolume(1);
                    setPic(onPath);
                }
                if (Config.isFullScreen()) super.zoomOut();
            }
        };
        quit = new Button(new Vector2f(770, 555), new Vector2f(50, 50), "Textures/Settings/X.png") {
            @Override
            public void Selected() {
                ScenceGraphController.removeExtension();
                ScenceGraphController.setPause(false);
            }
        };
        home = new Button(new Vector2f(365, 200), new Vector2f(350, 50), "Textures/Menu/transparent.png", "Return To Menu") {
            @Override
            public void Selected() {
                Config.setLevel(InGame.getLevel());
                ScenceGraphController.remove();
                ScenceGraphController.removeExtension();
                ScenceGraphController.setScence(new MainMenu());
                BuffStatus.zoomOut();
            }
        };
        restart = new Button(new Vector2f(415, 300), new Vector2f(250, 50), "Textures/Menu/transparent.png", "Restart Game") {
            @Override
            public void Selected() {
                ScenceGraphController.removeExtension();
                ScenceGraphController.remove();
                ScenceGraphController.setScence(new InGame(InGame.getLevel()));
            }
        };
        music = new Text("Music", ColorRGBA.White, new Vector2f(400, 490), new Vector2f(200, 50));
        sound = new Text("Sound", ColorRGBA.White, new Vector2f(400, 390), new Vector2f(200, 50));
        home.getText().setColor(ColorRGBA.White);
        restart.getText().setColor(ColorRGBA.White);
        if (Config.isFullScreen()) {
            sound.setSize(76);
            music.setSize(76);
            home.getText().setSize(76);
            restart.getText().setSize(76);
        } else {
            sound.setSize(36);
            music.setSize(36);
            home.getText().setSize(36);
            restart.getText().setSize(36);
        }
        sound.setAlignment(BitmapFont.Align.Left);
        music.setAlignment(BitmapFont.Align.Left);
    }

    @Override
    public void display() {
        layout1.display();
        home.display();
        restart.display();
        music.display();
        musicBut.display();
        sound.display();
        soundBut.display();
        quit.display();
    }

    @Override
    public void remove() {
        layout1.remove();
        home.remove();
        restart.remove();
        music.remove();
        musicBut.remove();
        sound.remove();
        soundBut.remove();
        quit.remove();
    }

    @Override
    public void scale() {
        layout1.zoomOut();
        home.zoomOut();
        restart.zoomOut();
        music.scale();
        musicBut.zoomOut();
        sound.scale();
        soundBut.zoomOut();
        quit.zoomOut();

    }

    @Override
    public void update(float tpf) {

    }
}
