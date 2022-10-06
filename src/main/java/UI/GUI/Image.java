package UI.GUI;

import Cores.Main;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.ui.Picture;

public class Image extends Item {
    private Picture item;

    public Image(Vector3f position, Vector2f size, String path) {
        item = new Picture(path);
        item.setImage(Main.ASSET_MANAGER, path, true);
        setPosition(position);
        setSize(size);
    }

    public Image(Vector2f size, String path) {
        item = new Picture(path);
        item.setImage(Main.ASSET_MANAGER, path, true);
        setSize(size);
    }

    public Picture getItem() {
        return this.item;
    }

    public void setSize(Vector2f size) {
        item.setHeight(size.y);
        item.setWidth(size.x);
    }

    public void setPosition(Vector3f position) {
        item.setLocalTranslation(position);
    }

    public void show() {
        Main.GUI_NODE.attachChild(item);
    }

    public void remove() {
        Main.GUI_NODE.detachChild(item);
    }


}
