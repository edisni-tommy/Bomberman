package Entities.Terrain;

import Cores.Main;
import Entities.Entity;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Portal extends Entity {
    public Portal(Vector3f position) {
        super(position, "Models/Tree/tree.obj");
    }
}
