package Particles;

import Cores.Main;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class BombExplosion {
    private ParticleEmitter explosion;
    private final long DURATION = 1000;
    private final long startTime;
    public BombExplosion(Vector3f position) {
        explosion = new ParticleEmitter("explosion effect", ParticleMesh.Type.Triangle, 15);
        Material mat_red = new Material(Main.ASSET_MANAGER,
                "Common/MatDefs/Misc/Particle.j3md");
        mat_red.setTexture("Texture", Main.ASSET_MANAGER.loadTexture(
                "Textures/Particles/bomb_explode.png"));
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
        startTime = System.currentTimeMillis();
        Main.ROOT_NODE.attachChild(explosion);
        BombExplosionList.add(this);
    }

    public void remove() {
        Main.ROOT_NODE.detachChild(explosion);
        explosion.killAllParticles();
    }

    public boolean check() {
        //System.out.println(System.currentTimeMillis() + " " + DURATION + startTime);
        return System.currentTimeMillis() >= DURATION + startTime;
    }
}
