package utils;

import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Light {
    public static void setSpatialLight(Spatial spatial) {
        spatial.addLight(new DirectionalLight(new Vector3f(-0.1f, -0.7f, -1.0f)));
        spatial.addLight(new DirectionalLight(new Vector3f(0.1f, -0.7f, 1.0f)));
        spatial.addLight(new DirectionalLight(new Vector3f(-1.0f, -0.7f, -0.1f)));
        spatial.addLight(new DirectionalLight(new Vector3f(1.0f, -0.7f, 0.1f)));
    }
}
