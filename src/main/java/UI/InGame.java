package UI;

import Cores.GameEnvironment;
import Cores.Map;
import Entities.BombList;
import Input.PlayerInput;
import Input.SystemInput;
import Particles.BombExplosionList;
import UI.ScenceGraph;

import java.io.FileNotFoundException;

public class InGame extends ScenceGraph {
    @Override
    public void update(float tpf) {
        BombList.onUpdate(tpf);
        BombExplosionList.onUpdate(tpf);
    }

    @Override
    public void display() {
        GameEnvironment.initalize();
        try {
            Map.initalize();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
       // SystemInput.initalize();
        PlayerInput.initalize();
    }

    @Override
    public void remove() {
        setDisplayed(false);
    }
}
