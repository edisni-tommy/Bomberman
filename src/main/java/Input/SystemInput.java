package Input;

import Cores.Main;
import Entities.Player.Player;
import com.jme3.input.ChaseCamera;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.ui.Picture;
import com.jme3.util.SkyFactory;

public class SystemInput {
    public static void initalize() {
        Main.INPUT_MANAGER.addMapping("LeftClick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        Main.INPUT_MANAGER.addMapping("RightClick", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        Main.INPUT_MANAGER.addListener(actionListener, "LeftClick", "RightClick");
    }
    private static final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String s, boolean b, float v) {
            if(b) {
                switch (s) {
                    case "LeftClick":

                }
            }
        }

    };
}
