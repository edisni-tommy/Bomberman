package Cores;

import Entities.Entity;
import Entities.Player.Player;
import Entities.Terrain.Container;
import Entities.Terrain.Rock;
import com.jme3.math.Vector3f;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    private static char[][] map = new char[20][20];
    private static Entity[][] entity = new Entity[20][20];

    public static void initalize() throws FileNotFoundException {
        for (int i = 0; i < 20; ++ i) {
            for (int j = 0; j < 20; ++ j) {
                Player grass = new Player(new Vector3f(i * 2f, 0, j * 2f), "Models/Grass/grass.obj");
            }
        }
        loadMapFromFile("assets/Level/level1.txt");
    }

    public static void loadMapFromFile(String path) throws FileNotFoundException {
        System.out.println(path);
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
    }

    private static void setObject(int i, int j, char x) {
        map[i][j] = x;
        switch (x) {
            case '*':
                entity[i][j] = new Container(new Vector3f(i * 2f, 1, j * 2f));
            case '#':
                entity[i][j] = new Rock(new Vector3f(i * 2f, 2f, j * 2f));

        }
    }

    public static boolean isBlocked(int i, int j) {
        return (map[i][j] == '*' || map[i][j] == '#');
    }
}
