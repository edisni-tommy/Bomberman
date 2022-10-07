package Entities.Player;

import Cores.Map;
import Entities.Bomb;
import Entities.Entity;
import UI.PlayerStatus.BombStatusBar;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import utils.Light;


public class Player extends Entity {

    private final float checkSize = 0.5f;

    protected final float DEFAULT_SPEED = 3.0f;
    protected float speed = DEFAULT_SPEED;
    protected boolean isShield = false;
    protected final int DEFAULT_BOMB = 3;
    protected int bombLeft = DEFAULT_BOMB;
    protected int bombMax = DEFAULT_BOMB;
    protected float hasShield = 0f;
    protected int DEFAULT_FLAME = 3;
    protected int flame = 3;

    protected final float DEFAULT_COOLDOWN_BOMB = 4.0f;
    protected float currentCooldownBomb = 0f;

    protected BombStatusBar bombStatusBar;

    public Player(Vector3f position, String path) {
        super(position, path);
        PlayerList.add(this);
        Light.setSpatialLight(spatial);
        spatial.setModelBound(new BoundingBox());
        spatial.updateModelBound();
        bombStatusBar = new BombStatusBar(spatial, bombMax, bombLeft);
    }

    public Spatial getSpatital() {
        return super.spatial;
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

    public void setBomb() {
        if (bombLeft == 0) {
            return;
        }

        Vector2f position = getCord();
        int x = (int)position.x;
        int y = (int)position.y;
        if (Map.getObject(x, y) == null) {
            bombLeft -= 1;
            Map.setObject(x, y, new Bomb(new Vector3f(x * 2f, 1, y * 2f), flame));
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

    public void onUpdate(float tpf) {
        currentCooldownBomb -= tpf;
        if (bombLeft == bombMax) {
            currentCooldownBomb = DEFAULT_COOLDOWN_BOMB;
            return;
        }
        if (currentCooldownBomb <= 0) {
            ++ bombLeft;
            currentCooldownBomb = DEFAULT_COOLDOWN_BOMB;
        }
        bombStatusBar.onUpdate(bombLeft, bombMax, currentCooldownBomb);
    }

    public void remove() {
        super.remove();
    }
}
