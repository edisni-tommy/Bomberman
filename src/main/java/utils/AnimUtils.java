package utils;

import com.jme3.anim.*;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.SafeArrayList;

public class AnimUtils {

    public static Spatial getAnimRoot(Spatial model) {
        return findAnimRoot(model);
    }

    protected static Spatial findAnimRoot(Spatial s) {
        if (s.getControl(AnimComposer.class) != null) {
            return s;
        }
        if (s instanceof Node) {
            for (Spatial child : ((Node) s).getChildren()) {
                Spatial result = findAnimRoot(child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
