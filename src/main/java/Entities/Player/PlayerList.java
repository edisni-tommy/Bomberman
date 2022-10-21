package Entities.Player;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerList {
    private static List<Player> playerList = new ArrayList<>();
    private static Set<Player> removeList = new HashSet<>();

    public static void add(Player player) {
        playerList.add(player);
    }

    public static void remove(Player player) {
        removeList.add(player);
    }

    private static void Remove() {
        for (Player player: removeList) {
            playerList.remove(player);
            player.remove();
        }
        removeList.clear();
    }

    public static void removeAll() {
        playerList.clear();
    }

    public static List<Player> getList() {
        return playerList;
    }

    public static Player getMainPlayer() {
        for (Player player: playerList) {
            if (player instanceof MainPlayer) {
                return player;
            }
        }
        return null;
    }

    public static void onUpdate(float tpf) {
        for (Player player: playerList) {
            player.onUpdate(tpf);
        }
        Remove();
    }
}
