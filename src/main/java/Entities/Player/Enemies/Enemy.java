package Entities.Player.Enemies;

import Cores.Map;
import Entities.BuffItem.BuffItem;
import Entities.Entity;
import Entities.Player.Player;
import Entities.Player.PlayerList;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

import java.util.*;

public class Enemy extends Player {

    private Vector2f target;

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public Enemy(Vector3f position, String path) {
        super(position, path);
        speed = 1.0f;
        Player.countEnemy++;
        bombStatusBar.remove();
    }

    public void remove() {
        super.remove();
        --Player.countEnemy;
    }

    public void onMoving() {

    }

    public void setNormalTarget(int distance) {
        Vector2f position = getCord();
        LinkedList<Vector3f> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[20][20];
        ArrayList<Vector2f> canReach = new ArrayList<>();

        isVisited[(int) position.x][(int) position.y] = true;
        q.addLast(new Vector3f(position.x, position.y, 0));

        while (!q.isEmpty()) {
            Vector3f u = q.removeFirst();
            if (u.z > distance) {
                break;
            }
            for (int i = 0; i < 4; ++i) {
                int nextX = (int) u.x + dx[i];
                int nextY = (int) u.y + dy[i];
                if (!valid(nextX, nextY)) {
                    continue;
                }
                if (!isVisited[nextX][nextY]) {
                    isVisited[nextX][nextY] = true;
                    q.addLast(new Vector3f(nextX, nextY, u.z + 1));
                    canReach.add(new Vector2f(nextX, nextY));
                }
            }
        }
        this.target = canReach.get((int) (Math.random() * canReach.size()));
        System.out.println(this.target);
    }

    public void setChaseTarget() {
        this.target = PlayerList.getMainPlayer().getCord();
    }

    public Vector2f getNextMove() {
        Vector2f position = getCord();
        Vector2f target = getTarget();

        LinkedList<Vector3f> q = new LinkedList<>();
        Vector2f[][] trace = new Vector2f[20][20];

        q.addLast(new Vector3f(position.x, position.y, 0));
        trace[(int) position.x][(int) position.y] = new Vector2f(0, 0);
        boolean found = false;

        while (!q.isEmpty()) {
            Vector3f u = q.removeFirst();
            for (int i = 0; i < 4; ++i) {
                int nextX = (int) u.x + dx[i];
                int nextY = (int) u.y + dy[i];
                if (!valid(nextX, nextY)) {
                    continue;
                }
                if (trace[nextX][nextY] == null) {
                    trace[nextX][nextY] = new Vector2f(u.x, u.y);
                    q.addLast(new Vector3f(nextX, nextY, u.z + 1));
                }
                if (nextX == target.x && nextY == target.y) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (!found) {
            return position;
        }
        Vector2f current = target;
        while (true) {
            if (trace[(int) current.x][(int) current.y].equals(position)) {
                return current;
            }
            current = trace[(int) current.x][(int) current.y];
        }
    }
    public Vector2f getTarget() {
        return this.target;
    }

    public void Move(Vector2f nextMove, float tpf) {
        Vector3f position = getPosition();
        float slopeX = nextMove.x * 2f  - position.x;
        float slopeY = nextMove.y * 2f  - position.z;
        if (slopeX > 0) {
            Vector2f next = new Vector2f(position.x + speed * tpf, position.z);
            if (next.x > nextMove.x * 2f) {
                next.x = nextMove.x * 2f;
            }
            setPosition(new Vector3f(next.x, 1, next.y));

        } else if(slopeX < 0){
            Vector2f next = new Vector2f(position.x - speed * tpf, position.z);
            if (next.x < nextMove.x * 2f) {
                next.x = nextMove.x * 2f;
            }
            setPosition(new Vector3f(next.x, 1, next.y));
        } else if (slopeY > 0) {
            Vector2f next = new Vector2f(position.x, position.z + speed * tpf);
            if (next.y > nextMove.y * 2f) {
                next.y = nextMove.y * 2f;
            }
            setPosition(new Vector3f(next.x, 1, next.y));
        } else {
            Vector2f next = new Vector2f(position.x, position.z - speed * tpf);
            if (next.y < nextMove.y * 2f) {
                next.y = nextMove.y * 2f;
            }
            setPosition(new Vector3f(next.x, 1, next.y));
        }
    }

    private boolean valid(int x, int y) {
        if (!Map.valid(x, y)) {
            return false;
        }
        Entity obj = Map.getObject(x, y);
        return (obj == null || obj instanceof BuffItem);
    }

    private boolean canMoveIn(int x, int y) {
        for (Player player: PlayerList.getList()) {
            if (player instanceof Enemy) {
                if (player.getCord().equals(new Vector2f(x, y))) {
                    return false;
                }
            }
        }
        return valid(x, y);
    }

    public void onUpdate(float tpf) {
        if (getTarget() == null || getTarget().equals(getCord())) {
            setNormalTarget(3);
        }
        if (!canMoveIn((int)target.x, (int)target.y)) {
            setNormalTarget(1);
        }
        Vector2f nextMove = getNextMove();
        Move(nextMove, tpf);
    }
}
