package Entities.Player;

import Cores.Map;
import Entities.Entity;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import utils.Light;

import java.awt.desktop.SystemEventListener;


public class Player extends Entity {

    protected final float DEFAULT_SPEED = 3.0f;
    protected float speed = DEFAULT_SPEED;

    private final float checkSize = 0.5f;

    public Player(Vector3f position, String path) {
        super(position, path);
        Light.setSpatialLight(spatial);
        spatial.setModelBound(new BoundingBox());
        spatial.updateModelBound();
        PlayerList.add(this);
    }

    public Spatial getSpatital() {
        return spatial;
    }
    public void moveForward(float tpf) {
        Rotate(0);
        Vector3f currentPosition = this.getPosition();
        float currentX = currentPosition.getX();
        float currentZ = currentPosition.getZ();
        if (invalid(currentX + speed * tpf + checkSize))  {
            return;
        }
        Vector2f leftX = getPositionCord(currentX + speed * tpf + checkSize, currentZ + checkSize);
        Vector2f rightX = getPositionCord(currentX + speed * tpf + checkSize, currentZ - checkSize);
        Vector2f nextX = getPositionCord(currentX + speed * tpf + checkSize, currentZ);
        if (Map.isBlocked((int)leftX.x, (int)leftX.y) || Map.isBlocked((int)rightX.x, (int)rightX.y) || Map.isBlocked((int)nextX.x, (int)nextX.y)) {
            return;
        } else {
            this.setPosition(new Vector3f(currentX + tpf * speed, currentPosition.y, currentPosition.z));
        }
    }

    public void moveBackward(float tpf) {
        Rotate(FastMath.PI);
        Vector3f currentPosition = this.getPosition();
        float currentX = currentPosition.getX();
        float currentZ = currentPosition.getZ();
        if (invalid(currentX - speed * tpf + checkSize))  {
            return;
        }
        Vector2f leftX = getPositionCord(currentX - speed * tpf - checkSize, currentZ + checkSize);
        Vector2f rightX = getPositionCord(currentX - speed * tpf - checkSize, currentZ - checkSize);
        Vector2f nextX = getPositionCord(currentX - speed * tpf - checkSize, currentZ);
        if (Map.isBlocked((int)leftX.x, (int)leftX.y) || Map.isBlocked((int)rightX.x, (int)rightX.y) || Map.isBlocked((int)nextX.x, (int)nextX.y)) {
            return;
        } else {
           this.setPosition(new Vector3f(currentX - tpf * speed, currentPosition.y, currentPosition.z));
        }
    }

    public void moveLeft(float tpf) {
        Rotate(FastMath.PI / 2);
        Vector3f currentPosition = this.getPosition();
        float currentX = currentPosition.getX();
        float currentZ = currentPosition.getZ();
        if (invalid(currentZ - speed * tpf + checkSize))  {
            return;
        }
        Vector2f leftX = getPositionCord(currentX + checkSize, currentZ - speed * tpf - checkSize);
        Vector2f rightX = getPositionCord(currentX - checkSize, currentZ - speed * tpf - checkSize);
        Vector2f nextX = getPositionCord(currentX, currentZ  - speed * tpf - checkSize);
        if (Map.isBlocked((int)leftX.x, (int)leftX.y) || Map.isBlocked((int)rightX.x, (int)rightX.y) || Map.isBlocked((int)nextX.x, (int)nextX.y)) {
            return;
        } else {
            this.setPosition(new Vector3f(currentX, currentPosition.y, currentZ - tpf * speed));
        }
    }

    public void moveRight(float tpf) {
        Rotate(-FastMath.PI / 2);
        Vector3f currentPosition = this.getPosition();
        float currentX = currentPosition.getX();
        float currentZ = currentPosition.getZ();
        if (invalid(currentZ + speed * tpf + checkSize))  {
            return;
        }
        Vector2f leftX = getPositionCord(currentX + checkSize, currentZ + speed * tpf + checkSize);
        Vector2f rightX = getPositionCord(currentX - checkSize, currentZ + speed * tpf + checkSize);
        Vector2f nextX = getPositionCord(currentX, currentZ  + speed * tpf + checkSize);
        if (Map.isBlocked((int)leftX.x, (int)leftX.y) || Map.isBlocked((int)rightX.x, (int)rightX.y) || Map.isBlocked((int)nextX.x, (int)nextX.y)) {
            return;
        } else {
            this.setPosition(new Vector3f(currentX, currentPosition.y, currentZ + tpf * speed));
        }
    }

    public void Rotate(float value) {
        Quaternion rot = spatial.getLocalRotation();
        rot.slerp(new Quaternion().fromAngleAxis(value + FastMath.HALF_PI, Vector3f.UNIT_Y), 0.25f);
        spatial.setLocalRotation(rot);
    }

    private boolean invalid(float x) {
        return x < 0f || x > 39.0f;
    }

}
