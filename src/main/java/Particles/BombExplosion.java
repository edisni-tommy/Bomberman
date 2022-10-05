package Particles;

import Cores.Main;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class BombExplosion {
    public BombExplosion(Vector3f position) {
        ParticleEmitter explosion = new ParticleEmitter("explosion effect", ParticleMesh.Type.Triangle, 15);
        Material mat_red = new Material(Main.ASSET_MANAGER,
                "Common/MatDefs/Misc/Particle.j3md");
        mat_red.setTexture("Texture", Main.ASSET_MANAGER.loadTexture(
                "Textures/Particles/bomb_spark.png"));
        explosion.setMaterial(mat_red);
        explosion.setLocalTranslation(position);
        explosion.getLocalTranslation().addLocal(0, 1.35f, 0.4f);
        explosion.setStartColor(new ColorRGBA(1f, 1f, 0.1f, 1f));
        explosion.setEndColor(new ColorRGBA(1f, 0.1f, 0.1f, 1f));
        explosion.setStartSize(0.5f);
        explosion.setEndSize(0.2f);
        explosion.setLowLife(0.3f);
        explosion.setHighLife(0.4f);
        explosion.emitAllParticles();
        Main.ROOT_NODE.attachChild(explosion);
    }
}
