package Particles;

import java.util.ArrayList;
import java.util.List;

public class BombExplosionList {
    private static List<BombExplosion> bombExplosionList = new ArrayList<>();

    public static void add(BombExplosion particle) {
        bombExplosionList.add(particle);
    }

    public static void remove(BombExplosion particle) {
        bombExplosionList.remove(particle);
        particle.remove();
    }

    public static void removeAll() {
        bombExplosionList.clear();
    }

    public static void onUpdate(float tpf) {
        List<BombExplosion> removeList = new ArrayList<>();
        for (BombExplosion particle: bombExplosionList) {
            if(particle.check()) {
                removeList.add(particle);
            }
        }
        for (BombExplosion particle: removeList) {
            remove(particle);
        }
    }


}
