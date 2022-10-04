package Entities.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    private static List<Player> playerList = new ArrayList<>();

    public static void add(Player player) {
        playerList.add(player);
    }

    public static void remove(Player player) {
        playerList.remove(player);
    }

    public static Player getPlayer() {
        return playerList.get(0);
    }
}
