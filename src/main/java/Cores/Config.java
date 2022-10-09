package Cores;

import UI.InGameGUI.InGame;

import java.io.*;

public class Config {
    private static boolean fullScreen;
    private static int music = 0;
    private static int sound = 0;
    private static int level;

    public static void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            //Full screen
            if (isFullScreen()) {
                bw.write("On");
            } else {
                bw.write("Off");
            }
            bw.newLine();
            //Music
            bw.write(String.valueOf(getMusic()));
            bw.newLine();
            //Sound
            bw.write(String.valueOf(getSound()));
            bw.newLine();
            //Level
            setLevel(InGame.getLevel());
            bw.write(String.valueOf(getLevel()));
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();
            //Full screen
            setFullScreen(s.equals("On"));
            //Music volume
            s = br.readLine();
            setMusic(Integer.parseInt(s));
            //Sound
            s = br.readLine();
            setSound(Integer.parseInt(s));
            //Level
            s = br.readLine();
            setLevel(Integer.parseInt(s));
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getLevel() {
        return level;
    }

    public static boolean isFullScreen() {
        return fullScreen;
    }

    public static int getMusic() {
        return music;
    }

    public static int getSound() {
        return sound;
    }

    public static void setFullScreen(boolean fullScreen) {
        Config.fullScreen = fullScreen;
    }

    public static void setLevel(int level) {
        Config.level = level;
    }

    public static void setMusic(int music) {
        Config.music = music;
    }

    public static void setSound(int sound) {
        Config.sound = sound;
    }
}
