package UI;

import Cores.Main;

public class ScenceGraphController {
    private static ScenceGraph scence;
    private static ScenceGraph extension;

    public static void setScence(ScenceGraph cur) {
        scence = cur;
        scence.display();
    }

    public static void setExtension(ScenceGraph cur) {
        setPause(true);
        extension = cur;
        extension.display();
    }
    public static void remove() {
        if (scence != null) scence.remove();
        Main.ROOT_NODE.detachAllChildren();
        Main.GUI_NODE.detachAllChildren();
    }

    public static void scale() {
        if (scence != null) scence.scale();
        if (extension != null) extension.scale();
    }
    public static void removeExtension() {
        if (extension != null) extension.remove();
    }
    public static ScenceGraph getScence() {
        return scence;
    }

    public static void update(float tpf) {
        if (scence != null && scence.isDisplayed()) {
            scence.update(tpf);
        }
    }

    public static void setPause(boolean pause) {
        scence.setDisplayed(!pause);
    }
}
