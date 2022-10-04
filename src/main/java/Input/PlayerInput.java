package Input;

import Cores.Main;
import Entities.Player.Player;
import Entities.Player.PlayerList;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.*;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

public class PlayerInput {

    private static Player player;

    public static void initalize() {
        player = PlayerList.getMainPlayer();
        Main.INPUT_MANAGER.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        Main.INPUT_MANAGER.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        Main.INPUT_MANAGER.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        Main.INPUT_MANAGER.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        Main.INPUT_MANAGER.addMapping("setBomb", new KeyTrigger(KeyInput.KEY_SPACE));
        Main.INPUT_MANAGER.addListener(actionListener, "setBomb");
        Main.INPUT_MANAGER.addListener(analogListener, "Forward", "Backward", "Left", "Right");
    }

    private static final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String s, boolean keyPressed, float v) {
            if (keyPressed) {
                if (s == "setBomb") {
                    player.setBomb();
                }
            }
        }
    };

    private static final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String s, float b, float v) {
            switch (s) {
                case "Forward":
                    player.moveForward(v);
                    break;
                case "Backward":
                    player.moveBackward(v);
                    break;
                case "Left":
                    player.moveLeft(v);
                    break;
                case "Right":
                    player.moveRight(v);
                    break;
            }
        }

    };

}
