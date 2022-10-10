package Entities.BuffItem;

import Cores.Main;
import Entities.Entity;
import Particles.BuffItemEffect;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.ui.Picture;

public abstract class BuffItem extends Entity {
    private BuffItemEffect item;
    private final Spatial shadow;

    public BuffItem(Vector3f position, String path) {
        super(position, path);
        BuffItemList.add(this);
        Quaternion rot = spatial.getLocalRotation();
        rot.slerp(new Quaternion().fromAngleAxis(FastMath.PI, Vector3f.UNIT_Y), 0.5f);
        spatial.setLocalRotation(rot);

        Material mat_shad = new Material(Main.ASSET_MANAGER, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_shad.setTexture("ColorMap", Main.ASSET_MANAGER.loadTexture("Textures/Buffs/shadow.png"));
        mat_shad.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        shadow = new Geometry("Box", new Box(0.3f, 0f, 0.3f));
        shadow.setMaterial(mat_shad);
        shadow.setQueueBucket(RenderQueue.Bucket.Transparent);
        shadow.setLocalTranslation(position.x, position.y - 0.4f, position.z);
        item = new BuffItemEffect(this.spatial);
        Main.ROOT_NODE.attachChild(shadow);
    }

    public void remove() {
        super.remove();
        item.remove();
        Main.ROOT_NODE.detachChild(shadow);
        BuffItemList.remove(this);
    }
}
