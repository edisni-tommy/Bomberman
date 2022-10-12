package UI.InGameGUI;

import Cores.GameEnvironment;
import Cores.Map;
import Entities.BombList;
import Entities.Player.PlayerList;
import Input.PlayerInput;
import Particles.BombExplosionList;
import UI.PlayerStatus.BuffStatus;
import UI.ScenceGraph;

import java.io.FileNotFoundException;

public class InGame extends ScenceGraph {
    private static int level = 1;

    public InGame(int level) {
        InGame.level = level;
        setDisplayed(true);
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int lastLevel) {
        level = lastLevel;
    }

    public void setDisplayed(boolean displayed) {
        super.setDisplayed(displayed);
        PlayerInput.setActive(displayed);
    }

    @Override
    public void update(float tpf) {
        if (!isDisplayed()) {
            return;
        }
        PlayerList.onUpdate(tpf);
        BombList.onUpdate(tpf);
        BombExplosionList.onUpdate(tpf);
        BuffStatus.onUpdate();
    }

    @Override
    public void display() {
        GameEnvironment.initalize();
        try {
            Map.initalize(level);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
        //SystemInput.initialize();
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

    @Override
    public void scale() {

    }
}
