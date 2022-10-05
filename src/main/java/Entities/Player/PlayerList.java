package Entities.Player;

import Cores.Main;

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
}
