package Cores;

import com.jme3.math.Vector2f;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateMap {
    private final char[][] map;
    private Vector2f playerPos;

    public GenerateMap(int level) throws IOException {
        long start = System.currentTimeMillis();
        BufferedWriter bw = new BufferedWriter(new FileWriter("assets/Level/level" + level + ".txt"));
        bw.write(level + " 20 20\n");
        StringBuilder line;
        int numContainer = 100 + Math.min(level, 50);
        int numBlock = Math.min(level, 30) + 40;
        int numEnemy = Math.min(level / 2 + 3, 20);
        int numEasyBot = (level % 2 == 0) ? (numEnemy * 2) / 3 : numEnemy / 2;
        int numNormalBot = numEnemy - numEasyBot;
        if (numEasyBot == numNormalBot && level % 2 == 0) {
            ++numEasyBot;
            --numNormalBot;
        }
        map = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = ' ';
            }
        }
        while (true) {
            int temp = numBlock;
            while (temp > 0) {
                int row = (int) Math.floor(Math.random() * 20);
                int col = (int) Math.floor(Math.random() * 20);
                if (map[row][col] == ' ') {
                    map[row][col] = '#';
                    temp--;
                }
            }
            if (checkConnected()) {
                break;
            } else {
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        map[i][j] = ' ';
                    }
                }
            }
        }

        while (numContainer > 0) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ') {
                map[row][col] = '*';
                numContainer--;
            }
        }

        while (true) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ' && canMoveVertical(row, col) && canMoveHorizontal(row, col)) {
                map[row][col] = 'p';
                playerPos = new Vector2f(row, col);
                break;
            }
            if (System.currentTimeMillis() - start > 10) {
                new GenerateMap(level);
                return;
            }
        }

        while (numEasyBot > 0) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ' && safeDistance(row, col)) {
                map[row][col] = '1';
                numEasyBot--;
            }
            if (System.currentTimeMillis() - start > 10) {
                new GenerateMap(level);
                return;
            }
        }

        while (numNormalBot > 0) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ' && safeDistance(row, col)) {
                map[row][col] = '2';
                numNormalBot--;
            }
            if (System.currentTimeMillis() - start > 10) {
                new GenerateMap(level);
                return;
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

    public boolean safeDistance(int row, int col) {
        return (Math.abs(row - playerPos.x) + Math.abs(col - playerPos.y)) > 2;
    }

    public boolean canMoveVertical(int x, int y) {
        if (valid(x, y + 1) && map[x][y + 1] == ' ') {
            return true;
        }
        return valid(x, y - 1) && map[x][y - 1] == ' ';
    }

    public boolean canMoveHorizontal(int x, int y) {
        if (valid(x + 1, y) && map[x + 1][y] == ' ') {
            return true;
        }
        return valid(x - 1, y) && map[x - 1][y] == ' ';
    }

    public boolean checkConnected() {
        Vector2f first;
        while (true) {
            int row = (int) Math.floor(Math.random() * 20);
            int col = (int) Math.floor(Math.random() * 20);
            if (map[row][col] == ' ') {
                first = new Vector2f(row, col);
                break;
            }
        }
        boolean[][] visited = new boolean[20][20];
        DFS((int) first.x, (int) first.y, visited);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == ' ' && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void DFS(int row, int col, boolean[][] visited) {
        visited[row][col] = true;
        List<Vector2f> neighbor = new ArrayList<>();
        if (valid(row + 1, col) && !visited[row + 1][col] && map[row + 1][col] != '#') {
            neighbor.add(new Vector2f(row + 1, col));
        }
        if (valid(row - 1, col) && !visited[row - 1][col] && map[row - 1][col] != '#') {
            neighbor.add(new Vector2f(row - 1, col));
        }
        if (valid(row, col + 1) && !visited[row][col + 1] && map[row][col + 1] != '#') {
            neighbor.add(new Vector2f(row, col + 1));
        }
        if (valid(row, col - 1) && !visited[row][col - 1] && map[row][col - 1] != '#') {
            neighbor.add(new Vector2f(row, col - 1));
        }
        for (Vector2f pos : neighbor) {
            if (!visited[(int) pos.x][(int) pos.y]) {
                DFS((int) pos.x, (int) pos.y, visited);
            }
        }
    }

    public boolean valid(int row, int col) {
        return (row >= 0 && row <= 19 && col >= 0 && col <= 19);
    }
}
