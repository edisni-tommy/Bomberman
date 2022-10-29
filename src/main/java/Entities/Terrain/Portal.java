package Entities.Terrain;

import Cores.Main;
import Entities.Entity;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import utils.Light;

public class Portal extends Entity {
    private static Spatial spatial;

    public Portal(Vector3f position) {
        spatial = Main.ASSET_MANAGER.loadModel("Models/Portal/scene.gltf");
        spatial.setLocalTranslation(position);
        Light.setSpatialLight(spatial);
        spatial.scale(0.2f);
        spatial.setModelBound(new BoundingBox());
        Main.ROOT_NODE.attachChild(spatial);
    }

    public static void onUpdate(float tpf) {
        Quaternion roll = new Quaternion();
        roll.fromAngleAxis(FastMath.PI * 0.01f, new Vector3f(0,1,0) );
        spatial.rotate(roll);
        spatial.updateModelBound();
    }
}
