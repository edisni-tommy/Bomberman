package Entities.Player.Enemies;

import Cores.Main;
import Cores.Map;
import Entities.Player.MainPlayer;
import Entities.Player.Player;
import Entities.Player.PlayerList;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

import java.util.LinkedList;
import java.util.Queue;

public class Enemy extends Player {

    private Vector2f target;

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public Enemy(Vector3f position, String path) {
        super(position, path);
        Player.countEnemy ++;
        bombStatusBar.remove();
    }

    public void remove() {
        super.remove();
        -- Player.countEnemy;
    }

    public void onMoving() {

    }

    public void setNormalTarget(int distance) {
        Vector2f position = getCord();
        Queue<Vector3f> q = new LinkedList<>();
        q.add(new Vector3f(position.x, position.y, 0));
        boolean[][] isVisited = new boolean[20][20];
        isVisited[(int)position.x][(int)position.y] = true;
        while (!q.isEmpty()) {
            Vector3f u = q.peek();
            q.remove();
            if (u.z == distance) {
                this.target = new Vector2f(u.x, u.y);
                return;
            }
            for (int i = 0; i < 4; ++ i) {
                int nextX = (int)u.x + dx[i];
                int nextY = (int)u.y + dy[i];
                if (Map.isBlocked(nextX, nextY)) {
                    continue;
                }
                if (!isVisited[nextX][nextY]) {
                    isVisited[nextX][nextY] = true;
                    q.add(new Vector3f(nextX, nextY, u.z + 1));
                }
            }
        }
    }

    public void setChaseTarget() {
        this.target = PlayerList.getMainPlayer().getCord();
    }

    public Vector2f getNextMove() {
        Vector2f position = getCord();
        Queue<Vector3f> q = new LinkedList<>();
        q.add(new Vector3f(position.x, position.y, 0));
        boolean[][] isVisited = new boolean[20][20];
        Vector2f[][] trace = new Vector2f[20][20];
        isVisited[(int)position.x][(int)position.y] = true;
        while (!q.isEmpty()) {
            Vector3f u = q.peek();
            q.remove();
            for (int i = 0; i < 4; ++ i) {
                int nextX = (int)u.x + dx[i];
                int nextY = (int)u.y + dy[i];
                if (Map.isBlocked(nextX, nextY)) {
                    continue;
                }
                if (nextX == (int)this.target.x && nextY == (int)this.target.y)  {
                    break;
                }
                if (!isVisited[nextX][nextY]) {
                    isVisited[nextX][nextY] = true;
                    trace[nextX][nextY] = new Vector2f(u.x, u.y);
                    q.add(new Vector3f(nextX, nextY, u.z + 1));
                }
            }
        }
        Vector2f current = this.target;
        while (true) {
            if (trace[(int)current.x][(int)current.y].equals(position)) {
                return current;
            }
            current = trace[(int)current.x][(int)current.y];
        }
    }
}
