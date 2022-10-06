package Entities;

import Cores.Map;
import Entities.Player.MainPlayer;
import Entities.Player.Player;
import Entities.Player.PlayerList;
import Entities.Terrain.Container;
import Particles.BombExplosion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {

    private float timeExplore = 4.0f;
    private static int flame;
    public Bomb(Vector3f position, int flame) {
        super(position, "Models/Bomb/bomb.obj");
        this.flame = flame;
        BombList.add(this);
    }

    public void onUpdate(float tpf) {
        timeExplore -= tpf;
    }

    public boolean isExplored() {
        return timeExplore <= 0f;
    }

    @Override
    public void remove() {
        explore();
        super.remove();
    }

    private void explore() {
        Vector2f position = getCord();
        int x = (int) position.x;
        int y = (int) position.y;
        Map.setObject(x, y, null);
        for (int i = x; i < Math.min(20, x + flame); ++ i) {
            checkKillPlayer(i, y);
            if (checkExplorsion(i, y)) {
                break;
            }
        }

        for (int i = x; i > Math.max(-1, x - flame); -- i) {
            checkKillPlayer(i, y);
            if (checkExplorsion(i, y)) {
                break;
            }
        }

        for (int i = y; i < Math.min(20, y + flame); ++ i) {
            checkKillPlayer(x, i);
            if (checkExplorsion(x, i)) {
                break;
            }

        }

        for (int i = y; i > Math.max(-1, y - flame); -- i) {
            checkKillPlayer(x, y);
            if (checkExplorsion(x, i)) {
                break;
            }
        }
    }

    private void checkKillPlayer(int x, int y) {
        List<Player> removeList = new ArrayList<>();
        for (Player player: PlayerList.getList()) {
            if ((int) player.getCord().x == x && (int) player.getCord().y == y) {
                removeList.add(player);
            }
        }
        for (Player player: removeList) {
            PlayerList.remove(player);
            if (player instanceof MainPlayer) {
                System.out.println("Player died");
            }
        }
    }

    private boolean checkExplorsion(int x, int y) {
        BombExplosion bombExplosion = new BombExplosion(new Vector3f(x * 2f, 1, y * 2f));
        Entity cur = Map.getObject(x, y);
        if (cur != null) {
            if (cur instanceof Container) {
                Map.setObject(x, y, null);
                cur.remove();
            }
            return true;
        }
        return false;
    }
}