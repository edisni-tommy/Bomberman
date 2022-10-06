package UI;

import Cores.Main;

public class ScenceGraphController {
    private static ScenceGraph scence;

    public static void setScence(ScenceGraph cur) {
        if (scence != null) scence.remove();
        Main.ROOT_NODE.detachAllChildren();
        Main.GUI_NODE.detachAllChildren();
        scence = cur;
        scence.display();
    }

    public static ScenceGraph getScence() {
        return scence;
    }

    public static void update(float tpf) {
        if (scence != null && scence. isDisplayed()) {
            scence.update(tpf);
        }
    }

    public static void setPause(boolean pause) {
        scence.setDisplayed(pause);
    }
}
