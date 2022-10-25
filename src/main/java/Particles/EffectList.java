package Particles;

import java.util.ArrayList;
import java.util.List;

public class EffectList {
    private static List<Effect> effects = new ArrayList<>();

    public static void add(Effect effect) {
        effects.add(effect);
    }

    public static void remove(Effect effect) {
        effects.remove(effect);
        effect.remove();
    }


    public static void removeAll() {
        effects.clear();
    }

    public static void onUpdate(float tpf) {
        List<Effect> removeList = new ArrayList<>();
        for (Effect particle: effects) {
            if(particle.check()) {
                removeList.add(particle);
            }
        }
        for (Effect particle: removeList) {
            remove(particle);
        }
    }
}
