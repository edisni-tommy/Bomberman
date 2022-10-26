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
        for (int i = 0; i < effects.size(); i++) {
            if(effects.get(i).check()) {
                //removeList.add(effects.get(i));
                remove(effects.get(i));
            } else {
                if (effects.get(i) instanceof SpeedBuffEffect) {
                    effects.get(i).show();
                }
            }
        }
        /*for (Effect particle: removeList) {
            remove(particle);
        }*/
    }
}
