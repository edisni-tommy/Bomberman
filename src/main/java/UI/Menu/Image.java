package UI.Menu;

import Cores.Main;
import com.jme3.math.Vector2f;
import com.jme3.ui.Picture;

public class Image {
    protected final Picture pic;
    private final Vector2f pos = new Vector2f(0, 0);
    private final Vector2f size;

    public Image(Vector2f pos, Vector2f size, String path) {
        this.size = size;
        pic = new Picture(path);
        setPos(pos);
        pic.setWidth(size.x);
        pic.setHeight(size.y);
        pic.setImage(Main.ASSET_MANAGER, path, true);
    }

    public Image(Vector2f size, String path) {
        this.size = size;
        pic = new Picture(path);
        pic.setHeight(size.y);
        pic.setWidth(size.x);
        pic.setImage(Main.ASSET_MANAGER, path, true);
    }

    public void setPos(Vector2f pos) {
        this.pos.x = pos.x;
        this.pos.y = pos.y;
        pic.setPosition(pos.x, pos.y);
    }
    public float getPosX() {
        return pos.x;
    }

    public float getPosY() {
        return pos.y;
    }

    public float getWidth() {
        return size.x;
    }

    public float getHeight() {
        return size.y;
    }

    public void display() {
        Main.GUI_NODE.attachChild(pic);
    }

    public void remove() {
        Main.GUI_NODE.detachChild(pic);
    }
}
