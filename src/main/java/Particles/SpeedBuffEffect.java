package Particles;

import Cores.Main;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class SpeedBuffEffect extends Effect {
    private static final ParticleEmitter particle = new ParticleEmitter("bombSpark", ParticleMesh.Type.Triangle, 15);

    public SpeedBuffEffect(Vector3f position) {
        super();
        Material material = new Material(Main.ASSET_MANAGER, "Common/MatDefs/Misc/Particle.j3md");
        material.setTexture("Texture", Main.ASSET_MANAGER.loadTexture("Textures/Particles/lightning1.png"));
        particle.setMaterial(material);
        particle.setLocalTranslation(position);
        particle.setImagesX(1);
        particle.setImagesY(1);
        particle.setFacingVelocity(true);
        particle.setStartColor(new ColorRGBA(1.0f, 0.17f, 1.0f, 1f));
        particle.setEndColor(new ColorRGBA(1.0f, 0.17f, 1.0f, 1f));
        particle.setGravity(0, 0, 0);
        particle.setStartSize(1.3f);
        particle.setEndSize(1.3f);
        particle.setLowLife(0.5f);
        particle.setHighLife(0.6f);
        particle.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 1.35f, 0));
        particle.getParticleInfluencer().setVelocityVariation(1.45f);
        DURATION = 600;
    }

    @Override
    public void remove() {
        if (Main.ROOT_NODE.hasChild(particle)) {
            Main.ROOT_NODE.detachChild(particle);
        }
    }

    @Override
    public void show() {
        if (!Main.ROOT_NODE.hasChild(particle)) {
            Main.ROOT_NODE.attachChild(particle);
        }
    }
}
