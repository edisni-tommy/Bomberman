package Cores;

import UI.InGameGUI.InGame;

import java.io.*;

public class Config {
    private static boolean fullScreen;
    private static int music = 20;
    private static int sound = 50;
    private static int level;

    public static void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            //ScreenMode
            bw.write("[Screen_Mode]: ");
            if (isFullScreen()) {
                bw.write("On");
            } else {
                bw.write("Off");
            }
            bw.newLine();
            //Music
            bw.write("[Music_Volume]: ");
            bw.write(String.valueOf(getMusic()));
            bw.newLine();
            //Sound
            bw.write("[Sound_Volume]: ");
            bw.write(String.valueOf(getSound()));
            bw.newLine();
            //Level
            bw.write("[Level]: ");
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
            String line = br.readLine();
            while (line != null) {
                String[] types = line.split(": ");
                switch (types[0]) {
                    case "[Screen_Mode]":
                        setFullScreen(types[1].equals("On"));
                        break;
                    case "[Music_Volume]":
                        setMusic(Integer.parseInt(types[1]));
                        break;
                    case "[Sound_Volume":
                        setSound(Integer.parseInt(types[1]));
                        break;
                    case "[Level]":
                        setLevel(Integer.parseInt(types[1]));
                        break;
                }
                line = br.readLine();
            }
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
