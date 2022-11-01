package Entities.Player.Enemies;

import Cores.Map;
import Entities.BuffItem.BuffItem;
import Entities.Entity;
import Entities.Player.MainPlayer;
import Entities.Player.Player;
import Entities.Player.PlayerList;
import UI.InGameGUI.Defeat;
import UI.InGameGUI.InGame;
import UI.ScenceGraphController;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

import java.nio.file.PathMatcher;
import java.util.*;

public class Enemy extends Player {

    private Vector2f target;

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private boolean moving;
    private Vector2f nextMove;
    private Vector2f lastPosition;

    protected float direction = 0;

    public Enemy(Vector3f position, String path) {
        super(position, path);
        this.speed = 1.0f;
        Map.enemyCount++;
        bombStatusBar.remove();
    }

    public void remove() {
        super.remove();
        --Map.enemyCount;
    }

    public void onMoving() {

    }

    public void setNormalTarget(int distance) {
        Vector2f position = getCoord();
        LinkedList<Vector3f> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[20][20];
        ArrayList<Vector2f> canReach = new ArrayList<>();

        isVisited[(int) position.x][(int) position.y] = true;
        q.addLast(new Vector3f(position.x, position.y, 0));

        while (!q.isEmpty()) {
            Vector3f u = q.removeFirst();
            canReach.add(new Vector2f(u.x, u.y));
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
                }
            }
        }
        this.target = canReach.get((int) (Math.random() * canReach.size()));
    }

    public void setChaseTarget() {
        this.target = PlayerList.getMainPlayer().getCoord();
    }

    public Vector2f getNextMove() {
        Vector2f position = getCoord();
        Vector2f target = getTarget();

        if (position.equals(target)) {
            return position;
        }

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
        setMoving(true);
        Vector3f position = getPosition();
        float slopeX = nextMove.x * 2f - position.x;
        float slopeY = nextMove.y * 2f - position.z;
        Vector2f next;
        if (slopeX > 0) {
            Rotate(-FastMath.PI/2 + direction);
            next = new Vector2f(Math.min(position.x + speed * tpf, nextMove.x * 2f), position.z);
        } else if (slopeX < 0) {
            Rotate(FastMath.PI/2 + direction);
            next = new Vector2f(Math.max(position.x - speed * tpf, nextMove.x * 2f), position.z);
        } else if (slopeY > 0) {
            Rotate(FastMath.PI + direction);
            next = new Vector2f(position.x, Math.min(position.z + speed * tpf, nextMove.y * 2f));
        } else {
            Rotate(0 + direction);
            next = new Vector2f(position.x, Math.max(position.z - speed * tpf, nextMove.y * 2f));
        }
        setPosition(new Vector3f(next.x, 1, next.y));
        if (getPosition().equals(new Vector3f(nextMove.x * 2f, 1, nextMove.y * 2f))) {
            setMoving(false);
        }
    }

    private boolean valid(int x, int y) {
        if (!Map.valid(x, y)) {
            return false;
        }
        return !Map.isBlocked(x, y);
    }

    private boolean canMoveIn(int x, int y) {
//        for (Player player : PlayerList.getList()) {
//            if (player instanceof Enemy) {
//                if(player.equals(this)) {
//                    continue;
//                }
//                if (player.getCoord().equals(new Vector2f(x, y))) {
//                    return false;
//                }
//            }
//        }
        return valid(x, y);
    }

    public void onUpdate(float tpf) {
        onMoving(tpf);
        checkKillPlayer();
    }

    public void onMoving(float tpf) {
        if (isMoving()) {
            if (!canMoveIn((int)this.nextMove.x, (int)this.nextMove.y)) {
                this.nextMove = this.lastPosition;
                this.target = this.lastPosition;
            }
            Move(this.nextMove, tpf);
            return;
        }
        if (getTarget() == null || getTarget().equals(getCoord())) {
            setNormalTarget(3);
        }
        if (!canMoveIn((int) target.x, (int) target.y)) {
            setNormalTarget(1);
        }
        this.nextMove = getNextMove();
        this.lastPosition = getCoord();
        Move(this.nextMove, tpf);
    }

    public void checkKillPlayer() {
        MainPlayer mainPlayer = (MainPlayer) PlayerList.getMainPlayer();
        if (mainPlayer == null) {
            return;
        }
        Vector3f MainPlayerPosition = mainPlayer.getPosition();
        Vector3f position = getPosition();
        if(distance(position, MainPlayerPosition) <= 1f) {
            PlayerList.remove(mainPlayer);
            ScenceGraphController.setExtension(new Defeat());
        }
    }

    public void setTarget(Vector2f target) {
        this.target = target;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return this.moving;
    }

    private double distance(Vector3f a, Vector3f b) {
        return Math.hypot(a.x - b.x, a.z - b.z);
    }
}
