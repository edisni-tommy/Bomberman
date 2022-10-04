package Entities.Terrain;

import Entities.Entity;
import com.jme3.math.Vector3f;

public class Grass extends Entity {
    public Grass(Vector3f position) {
        super(position, "Models/Grass/grass.obj");
    }
}
