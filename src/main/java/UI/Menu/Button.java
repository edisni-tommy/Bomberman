package UI.Menu;

import Cores.Main;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;

public class Button extends Image {
    private Text text;
    private boolean On = true;
    public Button(Vector2f pos, Vector2f size, String path) {
        super(pos, size, path);
        Main.INPUT_MANAGER.addListener(actionListener, "LeftClick");
    }

    public Button(Vector2f pos, Vector2f size, String path, String text) {
        super(pos, size, path);
        this.text = new Text(text, ColorRGBA.Black, pos, size);
        Main.INPUT_MANAGER.addListener(actionListener, "LeftClick");
    }

    @Override
    public void display() {
        super.display();
        text.display();
    }

    @Override
    public void remove() {
        super.remove();
        text.remove();
        setOn(false);
    }

    public void setOn(boolean on) {
        On = on;
    }

    private final ActionListener actionListener = (name, keyPressed, tpf) -> {
        if (On) {
            if (keyPressed && name.equals("LeftClick")) {
                if (isInside(Main.INPUT_MANAGER.getCursorPosition())) {
                    isSelected();
                }
            }
        }
    };

    public void isSelected() {

    }

    public boolean isInside(Vector2f cursor) {
        if (cursor.x < getPosX() || cursor.x > getPosX() + getWidth()) return false;
        return !(cursor.y < getPosY()) && !(cursor.y > getPosY() + getHeight());
    }
}
