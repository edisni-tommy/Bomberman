package Entities;

import Entities.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class BombList {
    static List<Bomb> bombList = new ArrayList<>();

    public static void add(Bomb bomb) {
        bombList.add(bomb);
    }

    public static void remove(Bomb bomb) {
        bombList.remove(bomb);
        bomb.remove();
    }

    public static void removeAll() {
        List<Bomb> removeList = new ArrayList<>(bombList);
        for (Bomb bomb: removeList) {
            remove(bomb);
        }
    }

    public static void onUpdate(float tpf) {
        List<Bomb> removeList = new ArrayList<>();
        for (Bomb bomb: bombList) {
            bomb.onUpdate(tpf);
            if (bomb.isExplored()) {
                removeList.add(bomb);
            }
        }
        for (Bomb bomb: removeList) {
            remove(bomb);
        }
    }
}
