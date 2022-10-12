package UI.Menu;

import Cores.Config;
import Cores.Main;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.ui.Picture;

public class Image {
    private boolean canScale = true;
    private boolean is3D = false;
    protected final Picture pic;
    private final Vector2f pos2D = new Vector2f(0, 0);
    private final Vector2f size = new Vector2f();

    public Image(Vector2f pos, Vector2f size, String path) {
        pic = new Picture(path);
        setPos(pos);
        setSize(size);
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
        pic.setImage(Main.ASSET_MANAGER, path, true);
    }

    public void setPos(Vector2f pos) {
        this.pos2D.x = pos.x;
        this.pos2D.y = pos.y;
        pic.setPosition(pos.x, pos.y);
    }

    public void setPos(Vector3f pos) {
        is3D = true;
        pic.setLocalTranslation(pos);
    }

    public void setSize(Vector2f size) {
        this.size.x = size.x;
        this.size.y = size.y;
        pic.setWidth(size.x);
        pic.setHeight(size.y);
    }
    public void zoomIn() {
        canScale = false;
        pic.setWidth(getWidth() * Main.SCALEWIDTH);
        pic.setHeight(getHeight() * Main.SCALEHEIGHT);
        if (!is3D) {
            pic.setPosition(getPosX() * Main.SCALEWIDTH, getPosY() * Main.SCALEHEIGHT);
        }
    }

    public void zoomOut() {
        if (!Config.isFullScreen()) {
            canScale = true;
            pic.setWidth(getWidth());
            pic.setHeight(getHeight());
            pic.setPosition(getPosX(), getPosY());
        }
        if (Config.isFullScreen()) zoomIn();

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

    public Picture getPic() {
        return pic;
    }

    public void display() {
        if (Config.isFullScreen() && canScale){
            zoomIn();
        }
        Main.GUI_NODE.attachChild(pic);
    }

    public void setCanScale(boolean canScale) {
        this.canScale = canScale;
    }

    public void remove() {
        Main.GUI_NODE.detachChild(pic);
    }
}
