package Entities.Player;

import Cores.Map;
import Entities.Bomb;
import Entities.Entity;
import UI.PlayerStatus.BombStatusBar;
import UI.PlayerStatus.ShieldStatus;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import utils.Light;


public class Player extends Entity {

    private final float checkSize = 0.5f;

    private final float DEFAUT_BUFF_DURATION = 10.0f;
    protected final float DEFAULT_SPEED = 3.0f;
    protected float speed = DEFAULT_SPEED;
    private float speedBuffDuration = 0f;

    protected final float DEFAULT_COOLDOWN_BOMB = 4.0f;
    protected float currentCooldownBomb = 0f;

    private final int DEFAULT_FLAME = 3;
    private int flame = DEFAULT_FLAME;
    private float flameBuffDuration = 0f;

    private boolean isShield = false;
    private float shieldBuffDuration = 0f;

    private float bombExtendDuration = 0f;
    private final int DEFFAULT_BOMB_CARRY = 3;
    protected int bombLeft = DEFFAULT_BOMB_CARRY;
    protected int bombMax = DEFFAULT_BOMB_CARRY;

    protected BombStatusBar bombStatusBar;
    protected ShieldStatus shieldStatus;


    public Player(Vector3f position, String path) {
        super(position, path);
        PlayerList.add(this);
        Light.setSpatialLight(spatial);
        spatial.setModelBound(new BoundingBox());
        spatial.updateModelBound();
        bombStatusBar = new BombStatusBar(spatial, bombMax, bombLeft);
        shieldStatus = new ShieldStatus(spatial, false);
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
        Vector2f leftX = getPositionCoord(currentX + speed * tpf + checkSize, currentZ + checkSize);
        Vector2f rightX = getPositionCoord(currentX + speed * tpf + checkSize, currentZ - checkSize);
        Vector2f nextX = getPositionCoord(currentX + speed * tpf + checkSize, currentZ);
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
        Vector2f leftX = getPositionCoord(currentX - speed * tpf - checkSize, currentZ + checkSize);
        Vector2f rightX = getPositionCoord(currentX - speed * tpf - checkSize, currentZ - checkSize);
        Vector2f nextX = getPositionCoord(currentX - speed * tpf - checkSize, currentZ);
        if (Map.isBlocked((int)leftX.x, (int)leftX.y) || Map.isBlocked((int)rightX.x, (int)rightX.y) || Map.isBlocked((int)nextX.x, (int)nextX.y)) {
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
        Vector2f leftX = getPositionCoord(currentX + checkSize, currentZ - speed * tpf - checkSize);
        Vector2f rightX = getPositionCoord(currentX - checkSize, currentZ - speed * tpf - checkSize);
        Vector2f nextX = getPositionCoord(currentX, currentZ  - speed * tpf - checkSize);
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
        Vector2f leftX = getPositionCoord(currentX + checkSize, currentZ + speed * tpf + checkSize);
        Vector2f rightX = getPositionCoord(currentX - checkSize, currentZ + speed * tpf + checkSize);
        Vector2f nextX = getPositionCoord(currentX, currentZ  + speed * tpf + checkSize);
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

        Vector2f position = getCoord();
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
        onCooldownBomb(tpf);
        onSpeedEffect(tpf);
        onFlameEffect(tpf);
        onShieldEffect(tpf);
        onBombExtend(tpf);
        bombStatusBar.onUpdate(bombLeft, bombMax, currentCooldownBomb);
        shieldStatus.onUpdate(isShield);
    }

    public void onCooldownBomb(float tpf) {
        currentCooldownBomb -= tpf;
        if (bombLeft == bombMax) {
            currentCooldownBomb = DEFAULT_COOLDOWN_BOMB;
            return;
        }
        if (currentCooldownBomb <= 0) {
            ++ bombLeft;
            currentCooldownBomb = DEFAULT_COOLDOWN_BOMB;
        }
    }

    public void onSpeedEffect(float tpf) {
        this.speedBuffDuration -= tpf;
        this.speed = DEFAULT_SPEED * 1.5f;
        if (this.speedBuffDuration <= 0) {
            this.speed = DEFAULT_SPEED;
        }
    }
    public void onFlameEffect(float tpf) {
        this.flameBuffDuration -= tpf;
        this.flame = DEFAULT_FLAME + 1;
        if (this.flameBuffDuration <= 0) {
            this.flame = DEFAULT_FLAME;
        }
    }

    public void onBombExtend(float tpf) {
        this.bombExtendDuration -= tpf;
        if(this.bombExtendDuration <= 0) {
            this.bombMax = DEFFAULT_BOMB_CARRY;
        }
        this.bombLeft = Math.min(this.bombMax, this.bombLeft);
    }

    public void onShieldEffect(float tpf) {
        this.shieldBuffDuration -= tpf;
        this.isShield = true;
        if (this.shieldBuffDuration <= 0) {
            this.isShield = false;
        }
    }


    public void setSpeedBuff() {
        this.speedBuffDuration = DEFAUT_BUFF_DURATION;
    }

    public void setFlameBuff() {
        this.flameBuffDuration = DEFAUT_BUFF_DURATION;
    }

    public void setBombExtend() {
        this.bombExtendDuration = DEFAUT_BUFF_DURATION;
        this.bombMax = DEFFAULT_BOMB_CARRY + 1;
        this.bombLeft = Math.min(this.bombMax, this.bombLeft + 1);
    }

    public void setShieldBuff() {
        this.shieldBuffDuration = DEFAUT_BUFF_DURATION;
    }

    public boolean isShield() {
        return this.isShield;
    }

    public void removeShield() {
        this.shieldBuffDuration = 0f;
        this.isShield = false;
    }

    public boolean isSpeedBuff() {
        return this.speedBuffDuration > 0;
    }

    public void remove() {
        super.remove();
    }
}
