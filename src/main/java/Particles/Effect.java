package Particles;

import Cores.Main;
import com.jme3.effect.ParticleEmitter;

public class Effect {
    protected ParticleEmitter particle;
    protected long DURATION;
    private final long startTime;

    public Effect() {
        startTime = System.currentTimeMillis();
        EffectList.add(this);
    }


    public void remove() {
        Main.ROOT_NODE.detachChild(particle);
        particle.killAllParticles();
    }

    public void show() {

    }

    public boolean check() {
        //System.out.println(System.currentTimeMillis() + " " + DURATION + startTime);
        return System.currentTimeMillis() >= DURATION + startTime;
    }
}
