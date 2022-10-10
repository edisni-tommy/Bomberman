package UI.Menu;

import Cores.Config;
import Cores.Main;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.ui.Picture;

public class Image {
    protected final Picture pic;
    private final Vector2f pos2D = new Vector2f(0, 0);
    private final Vector2f size = new Vector2f();

    public Image(Vector2f pos, Vector2f size, String path) {
        pic = new Picture(path);
        setPos(pos);
        setSize(size);
        if (Config.isFullScreen()) {
            setSize(new Vector2f(getWidth() * Main.SCALEWIDTH, getHeight() * Main.SCALEHEIGHT));
            setPos(new Vector2f(getPosX()* Main.SCALEWIDTH, getPosY() * Main.SCALEHEIGHT));
        }
        pic.setImage(Main.ASSET_MANAGER, path, true);
    }

    public Image(Vector3f position, Vector2f size, String path) {
        pic = new Picture(path);
        pic.setImage(Main.ASSET_MANAGER, path, true);
        setPos(position);
        setSize(size);
    }
    public Image(Vector2f size, String path) {
        pic = new Picture(path);
        setSize(size);
        if (Config.isFullScreen()) {
            setSize(new Vector2f(getWidth() * Main.SCALEWIDTH, getHeight() * Main.SCALEHEIGHT));
        }
        pic.setImage(Main.ASSET_MANAGER, path, true);
    }

    public void setPos(Vector2f pos) {
        this.pos2D.x = pos.x;
        this.pos2D.y = pos.y;
        pic.setPosition(pos.x, pos.y);
    }

    public void setPos(Vector3f pos) {
        pic.setLocalTranslation(pos);
    }

    public void setSize(Vector2f size) {
        this.size.x = size.x;
        this.size.y = size.y;
        pic.setWidth(size.x);
        pic.setHeight(size.y);
    }
    public void scale() {
        if (Config.isFullScreen()) {
            setSize(new Vector2f(getWidth() * Main.SCALEWIDTH, getHeight() * Main.SCALEHEIGHT));
            setPos(new Vector2f(getPosX()* Main.SCALEWIDTH, getPosY() * Main.SCALEHEIGHT));
        } else {
            setSize(new Vector2f(getWidth() / Main.SCALEWIDTH, getHeight() / Main.SCALEHEIGHT));
            setPos(new Vector2f(getPosX() / Main.SCALEWIDTH, getPosY() / Main.SCALEHEIGHT));
        }
    }
    public float getPosX() {
        return pos2D.x;
    }

    public float getPosY() {
        return pos2D.y;
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
