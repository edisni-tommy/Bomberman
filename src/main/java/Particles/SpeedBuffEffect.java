package Particles;

import Cores.Main;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class SpeedBuffEffect extends Effect {

    public SpeedBuffEffect(Vector3f position) {
        super();
        particle = new ParticleEmitter("bombSpark", ParticleMesh.Type.Triangle, 15);
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
        particle.setStartSize(1f);
        particle.setEndSize(1f);
        particle.setLowLife(0.5f);
        particle.setHighLife(0.6f);
        particle.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 1.35f, 0));
        particle.getParticleInfluencer().setVelocityVariation(1.45f);
        DURATION = 500;
        Main.ROOT_NODE.attachChild(particle);
    }
}
