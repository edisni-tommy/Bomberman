package Entities.Terrain;

import Cores.Map;
import Entities.Entity;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.Vector3f;
import utils.Light;

public class Container extends Entity {

    private static int count = 0;
    public Container(Vector3f position) {
        super(position, "Models/Container/container.obj");
        //Light.setSpatialLight(spatial);
        spatial.setModelBound(new BoundingBox());
        spatial.updateModelBound();
        setBlocked(true);
        ++ Map.containerCount;
    }

    @Override
    public void remove() {
        super.remove();
        -- Map.containerCount;
    }

}
