package Particles;

import Cores.Main;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class BombExplosion {

    private ParticleEmitter particle;
    private final long DURATION = 1000;
    private final long startTime;
    public BombExplosion(Vector3f position) {
        super();
        particle = new ParticleEmitter("bombSpark", ParticleMesh.Type.Triangle, 15);
        Material material = new Material(Main.ASSET_MANAGER, "Common/MatDefs/Misc/Particle.j3md");
        material.setTexture("Texture", Main.ASSET_MANAGER.loadTexture("Textures/Particles/bomb_explode.png"));
        particle.setMaterial(material);
        particle.setLocalTranslation(position);
        particle.setImagesX(1);
        particle.setImagesY(2);
        particle.setStartColor(new ColorRGBA(1f, 0.1f, 0.1f, 1f));
        particle.setEndColor(new ColorRGBA(0f, 0f, 0f, 1f));
        particle.setGravity(0, 0, 0);
        particle.setStartSize(0.8f);
        particle.setEndSize(0.0f);
        particle.setLowLife(1.2f);
        particle.setHighLife(1.5f);
        particle.setRotateSpeed(2);
        particle.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 1, 0));
        particle.getParticleInfluencer().setVelocityVariation(0.5f);
        particle.emitAllParticles();
        Main.ROOT_NODE.attachChild(particle);
        startTime = System.currentTimeMillis();
        BombExplosionList.add(this);
    }

    public void remove() {
        Main.ROOT_NODE.detachChild(particle);
        particle.killAllParticles();
    }

    public boolean check() {
        //System.out.println(System.currentTimeMillis() + " " + DURATION + startTime);
        return System.currentTimeMillis() >= DURATION + startTime;
    }
}
