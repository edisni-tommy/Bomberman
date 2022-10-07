package Entities.BuffItem;

import Cores.Main;
import Entities.Entity;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.ui.Picture;

public abstract class BuffItem extends Entity {
    private ParticleEmitter item;

    public BuffItem(Vector3f position, String path) {
        super(position, path);
        BuffItemList.add(this);
        Quaternion rot = spatial.getLocalRotation();
        rot.slerp(new Quaternion().fromAngleAxis(FastMath.HALF_PI, Vector3f.UNIT_Y), 0.5f);
        spatial.setLocalRotation(rot);
    }

    public void remove() {
        BuffItemList.remove(this);
    }
}
