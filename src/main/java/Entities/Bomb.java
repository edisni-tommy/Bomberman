package Entities;

import com.jme3.math.Vector3f;

public class Bomb extends Entity {

    private float timeExplore = 4.0f;
    private static int flame = 3;
    public Bomb(Vector3f position) {
        super(position, "Models/Bomb/bomb.obj");
        BombList.add(this);
    }

    public void onUpdate(float tpf) {
        timeExplore -= tpf;
    }

    public boolean isExplored() {
        return timeExplore <= 0f;
    }
}
