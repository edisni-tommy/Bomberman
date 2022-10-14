package Input;

import Cores.Main;
import Entities.Player.MainPlayer;
import Entities.Player.PlayerList;
import UI.InGameGUI.GameSetting;
import UI.ScenceGraphController;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.*;


public class PlayerInput {

    private static MainPlayer player;
    private static boolean active;

    public static void initialize() {
        player = (MainPlayer)PlayerList.getMainPlayer();
        Main.INPUT_MANAGER.deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT);
        Main.INPUT_MANAGER.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W), new KeyTrigger(KeyInput.KEY_UP));
        Main.INPUT_MANAGER.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        Main.INPUT_MANAGER.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        Main.INPUT_MANAGER.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        Main.INPUT_MANAGER.addMapping("setBomb", new KeyTrigger(KeyInput.KEY_SPACE));
        Main.INPUT_MANAGER.addMapping("setting", new KeyTrigger(KeyInput.KEY_ESCAPE));
        Main.INPUT_MANAGER.addListener(actionListener, "Forward", "Backward", "Left", "Right", "setBomb", "setting");
        Main.INPUT_MANAGER.addListener(analogListener, "Forward", "Backward", "Left", "Right");
    }

    private static final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String s, boolean keyPressed, float v) {
            if (!PlayerInput.isActive()) {
                return;
            }
            if (keyPressed) {
                if (s.equals("setBomb")) {
                    player.setBomb();
                }
                if (s.equals("setting")) {
                    ScenceGraphController.setExtension(new GameSetting());
                }
            }
        }
    };

    private static final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String s, float b, float v) {
            if (!PlayerInput.isActive()) {
                return;
            }
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

    public static void setActive(boolean newActive) {
        active = newActive;
    }

    public static boolean isActive() {
        return active;
    }

}
