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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;
import java.util.Vector;

public class Map {
    private static char[][] map;
    private static Entity[][] entity;

    private static boolean hasPortal;

    public static int containerCount;
    public static int enemyCount;


    public static void initalize(int level) throws FileNotFoundException {
        hasPortal = false;
        containerCount = 0;
        enemyCount =0;
        map = new char[20][20];
        entity = new Entity[20][20];
        for (int i = 0; i < 20; ++ i) {
            for (int j = 0; j < 20; ++ j) {
                Grass grass = new Grass(new Vector3f(i * 2f, 0, j * 2f));
            }
        }
        loadMapFromFile("assets/Level/level" + level + ".txt");
    }

    public static void loadMapFromFile(String path) throws FileNotFoundException {
        File text = new File(path);
        Scanner IN = new Scanner(text);
        for(int i = 0; i < 20; ++ i) {
            String s = IN.nextLine();
            int cnt = 0;
            for (char x: s.toCharArray()) {
                setObject(i, cnt, x);
                ++ cnt;
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
}
