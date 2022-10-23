package Cores;

import Entities.BombList;
import Entities.Entity;
import Entities.Player.Enemies.EasyBot;
import Entities.Player.Enemies.NormalBot;
import Entities.Player.MainPlayer;
import Entities.Player.Player;
import Entities.Player.PlayerList;
import Entities.Terrain.Container;
import Entities.Terrain.Grass;
import Entities.Terrain.Rock;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;
import java.util.Vector;

public class Map {
    private static char[][] map;
    private static Entity[][] entity;

    private static boolean hasPortal;

    public static int containerCount;
    public static int enemyCount;


    public static void initalize(int level) throws IOException {
        hasPortal = false;
        containerCount = 0;
        enemyCount = 0;
        map = new char[20][20];
        entity = new Entity[20][20];
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                Grass grass = new Grass(new Vector3f(i * 2f, 0, j * 2f));
            }
        }
        if (level > 30) generateMap(level);
        loadMapFromFile("assets/Level/level" + level + ".txt");
    }

    public static void loadMapFromFile(String path) throws FileNotFoundException {
        File text = new File(path);
        Scanner IN = new Scanner(text);
        IN.nextLine();
        for (int i = 0; i < 20; ++i) {
            String s = IN.nextLine();
            int cnt = 0;
            for (char x : s.toCharArray()) {
                setObject(i, cnt, x);
                ++cnt;
            }
        }
        IN.close();
    }

    private static void setObject(int i, int j, char x) {
        map[i][j] = x;
        switch (x) {
            case '*':
                entity[i][j] = new Container(new Vector3f(i * 2f, 1, j * 2f));
                break;
            case '#':
                entity[i][j] = new Rock(new Vector3f(i * 2f, 2f, j * 2f));
                break;
            case 'x':
                entity[i][j] = new Container(new Vector3f(i * 2f, 1, j * 2f));
                break;
            case 'p':
                Player player = new MainPlayer(new Vector3f(i * 2f, 1, j * 2f));
                break;
            case '1':
                Player easyBot = new EasyBot(new Vector3f(i * 2f, 1, j * 2f));
                break;
            case '2':
                Player normalBot = new NormalBot(new Vector3f(i * 2f, 1, j * 2f));
                break;
        }
    }

    public static int getContainerCount() {
        return containerCount;
    }


    public static void remove(int x, int y) {
        entity[x][y].remove();
        entity[x][y] = null;
    }

    public static boolean isBlocked(int i, int j) {
        Entity current = getObject(i, j);
        return (current != null && current.isBlocked());
    }

    public static Entity getObject(int x, int y) {
        return entity[x][y];
    }

    public static void setObject(int x, int y, Entity obj) {
        entity[x][y] = obj;
    }

    public static void setHasPortal(boolean newHasPortal) {
        hasPortal = newHasPortal;
    }

    public static boolean isHasPortal() {
        return hasPortal;
    }

    public static boolean valid(int x, int y) {
        return 0 <= x && 0 <= y && 20 > x && 20 > y;
    }

    public static boolean canReach(int x, int y) {
        if (valid(x, y + 1) && map[x][y + 1] != '#') return true;
        if (valid(x, y - 1) && map[x][y - 1] != '#') return true;
        if (valid(x - 1, y) && map[x - 1][y] != '#') return true;
        if (valid(x + 1, y) && map[x + 1][y] != '#') return true;
        return false;
    }

    public static boolean canMoveVertical(int x, int y) {
        boolean res = false;
        if (valid(x, y + 1)) {
            if (map[x][y + 1] == '1' || map[x][y + 1] == '2') return false;
            else if (map[x][y + 1] == ' ') res = true;
        }
        if (valid(x, y - 1)) {
            if (map[x][y - 1] == '1' || map[x][y - 1] == '2') return false;
            else if (map[x][y - 1] == ' ') res = true;
        }
        return res;
    }

    public static boolean canMoveHorizontal(int x, int y) {
        boolean res = false;
        if (valid(x + 1, y)) {
            if (map[x + 1][y] == '1' || map[x + 1][y] == '2') return false;
            else if (map[x + 1][y] == ' ') res = true;
        }
        if (valid(x - 1, y)) {
            if (map[x - 1][y] == '1' || map[x - 1][y] == '2') return false;
            else if (map[x - 1][y] == ' ') res = true;
        }
        return res;
    }

    public static void generateMap(int level) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("assets/Level/level" + level + ".txt"));
        bw.write(level + " 20 20\n");
        StringBuilder line;
        int numContainer = 100 + Math.min(level, 50);
        int numBlock = Math.min(level, 30) + 40;
        int numEnemy = Math.min(level/2 + 3, 30);
        int numEasyBot = (level % 2 == 0) ? (numEnemy * 2) / 3 : numEnemy / 2;
        int numNormalBot = numEnemy - numEasyBot;
        map = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = ' ';
            }
        }
        while (numBlock > 0 ) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ' && canReach(row, col)) {
                map[row][col] = '#';
                numBlock--;
            }
        }

        while (numContainer > 0) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ' && canReach(row, col)) {
                map[row][col] = '*';
                numContainer--;
            }
        }
        while (numNormalBot > 0) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ' && canReach(row, col)) {
                map[row][col] = '2';
                numNormalBot--;
            }
        }
        while (numEasyBot > 0) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ' && canReach(row, col)) {
                map[row][col] = '1';
                numEasyBot--;
            }
        }

        while (true) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ' && canMoveVertical(row, col) && canMoveHorizontal(row, col)) {
                map[row][col] = 'p';
                break;
            }
        }

        for (int i = 0; i < 20; i++) {
            line = new StringBuilder();
            for (int j = 0; j < 20; j++) {
                line.append(map[i][j]);
            }
            bw.write(line + "\n");
        }
        bw.close();
    }
}
