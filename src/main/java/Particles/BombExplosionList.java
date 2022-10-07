package Particles;

import java.util.ArrayList;
import java.util.List;

public class BombExplosionList {
    private static List<BombExplosion> bombExplosionList = new ArrayList<>();

    public static void add(BombExplosion bombExplosion) {
        bombExplosionList.add(bombExplosion);
    }

    public static void remove(BombExplosion bombExplosion) {
        bombExplosionList.remove(bombExplosion);
        bombExplosion.remove();
    }

    public static void removeAll() {
        bombExplosionList.clear();
    }

    public static void onUpdate(float tpf) {
        List<BombExplosion> removeList = new ArrayList<>();
        for (BombExplosion bombExplosion: bombExplosionList) {
            if(bombExplosion.check()) {
                removeList.add(bombExplosion);
            }
        }
        for (BombExplosion bombExplosion: removeList) {
            remove(bombExplosion);
        }
    }


}
