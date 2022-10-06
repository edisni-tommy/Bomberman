package UI.GUI;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class Item {
    private Vector3f position;
    private Vector2f size;

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public void setSizeX(float x) {
        this.size.x = x;
    }

    public void setSizeY(float y) {
        this.size.y = y;
    }
}
