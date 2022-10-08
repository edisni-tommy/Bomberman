package UI.InGameGUI;

import Cores.GameEnvironment;
import Cores.Main;
import Cores.Map;
import Entities.BombList;
import Entities.Player.PlayerList;
import Input.PlayerInput;
import Input.SystemInput;
import Particles.BombExplosionList;
import UI.ScenceGraph;
import UI.ScenceGraphController;

import java.io.FileNotFoundException;

public class InGame extends ScenceGraph {
    private static int level;
    public InGame(int level) {
        InGame.level = level;
        setDisplayed(true);
    }
    @Override
    public void update(float tpf) {
        PlayerList.onUpdate(tpf);
        BombList.onUpdate(tpf);
        BombExplosionList.onUpdate(tpf);
    }

    @Override
    public void display() {
        GameEnvironment.initalize();
        try {
            Map.initalize(level);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
        SystemInput.initialize();
        PlayerInput.initialize();
    }

    @Override
    public void remove() {
        PlayerList.removeAll();
        BombList.removeAll();
        BombExplosionList.removeAll();
        GameEnvironment.remove();
        setDisplayed(false);
    }

    public static int getLevel() {
        return level;
    }
}
