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
        player = new Player(new Vector3f(2f, 1, 2f), "Models/Player/player.gltf");
        ChaseCamera chaseCam = new ChaseCamera(Main.CAM, player.getSpatital(), Main.INPUT_MANAGER);
        chaseCam.setDefaultHorizontalRotation(FastMath.PI);
        chaseCam.setDefaultVerticalRotation(FastMath.PI/3);
        chaseCam.setDefaultDistance(16);
        chaseCam.setMinDistance(10);
        chaseCam.setMaxDistance(20);
        chaseCam.setZoomSensitivity(0.25f);
        chaseCam.cleanupWithInput(Main.INPUT_MANAGER);
        Main.INPUT_MANAGER.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        Main.INPUT_MANAGER.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        Main.INPUT_MANAGER.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        Main.INPUT_MANAGER.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        Main.INPUT_MANAGER.addListener(analogListener, "Forward", "Backward", "Left", "Right");
    }
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
