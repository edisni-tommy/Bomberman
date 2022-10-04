package Entities;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import Cores.Main;

import java.util.Vector;

public abstract class Entity {
    public Spatial spatial;
    private boolean isblocked;
    private static final float BLOCK_SIZE = 2f;

    public Entity(Vector3f position, String path) {
        spatial = Main.ASSET_MANAGER.loadModel(path);
        spatial.setLocalTranslation(position);
        Main.ROOT_NODE.attachChild(spatial);
    }

    public Entity() {

    }

    public Vector3f getPosition() {
        return this.spatial.getLocalTranslation();
    }

    public void setPosition(Vector3f position) {
        this.spatial.setLocalTranslation(position);
    }

    public Vector2f getCord() {
        Vector3f current = this.getPosition();
        int cordX = (int) ((current.x + Entity.BLOCK_SIZE / 2) / Entity.BLOCK_SIZE);
        int cordZ = (int) ((current.z + Entity.BLOCK_SIZE / 2) / Entity.BLOCK_SIZE);
        return new Vector2f(cordX, cordZ);
    }

    public Vector2f getPositionCord(float x, float z) {
        int cordX = (int) ((x + Entity.BLOCK_SIZE / 2) / Entity.BLOCK_SIZE);
        int cordZ = (int) ((z + Entity.BLOCK_SIZE / 2) / Entity.BLOCK_SIZE);
        return new Vector2f(cordX, cordZ);
    }

    public Vector2f getCenter(float x, float y) {
        return new Vector2f(x * BLOCK_SIZE, y * BLOCK_SIZE);
    }

    public void setBlocked(boolean blocked) {
        this.isblocked = blocked;
    }

    public boolean isBlocked() {
        return this.isblocked;
    }

    public void remove() {
        Main.ROOT_NODE.detachChild(this.spatial);
    }

}
