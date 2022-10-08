package Entities.Player.Enemies;

import Cores.Map;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class EasyBot extends Enemy {

    public EasyBot(Vector3f position) {
        super(position, "Models/Monster/turtle.obj");
    }

    public void onUpdate(float tpf) {
        super.onUpdate(tpf);
    }

}
