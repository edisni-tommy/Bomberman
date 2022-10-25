package Entities.Player.Enemies;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

public class NormalBot extends Enemy {

    private final float DEFAULT_ULTIMATE_COOLDOWN = 15f;
    private float ultimateCooldown = DEFAULT_ULTIMATE_COOLDOWN;
    private float ultimaiteActive = 0f;

    public NormalBot(Vector3f position) {
        super(position, "Models/Monster/spider.obj");
        this.direction = FastMath.PI;
    }

    public void onUpdate(float tpf) {
        ultimateCooldown -= tpf;
        ultimaiteActive -= tpf;
        if (ultimateCooldown  <= 0) {
            ultimaiteActive = 5f;
            ultimateCooldown = DEFAULT_ULTIMATE_COOLDOWN;
        }
        if (ultimaiteActive > 0f) {
            speed = DEFAULT_SPEED;
            if (!isMoving()) {
                setChaseTarget();
                if (getNextMove().equals(getCoord())) {
                    setTarget(getCoord());
                }
            }
        } else {
            speed = DEFAULT_SPEED / 2;
        }

        super.onUpdate(tpf);
    }
}
