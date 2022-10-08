package Input;

import Cores.Main;

import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;


public class SystemInput {
    public static void initialize() {
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
                        break;
                }
            }
        }

    };
}
