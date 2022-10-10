package Cores;

import UI.Menu.MainMenu;
import UI.ScenceGraphController;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

import Input.SystemInput;
import org.lwjgl.openal.AL10;

import java.awt.*;

public class Main extends SimpleApplication {

    public static final int HEIGHT = 720;
    public static final int WIDTH = 1080;

    public static float SCALEWIDTH;
    public static float SCALEHEIGHT;
    public static AppSettings APP_SETTINGS;
    public static Node ROOT_NODE;
    public static Node GUI_NODE;
    public static AssetManager ASSET_MANAGER;
    public static InputManager INPUT_MANAGER;
    public static Camera CAM;
    public static Main APP;

    public static void main(String[] argv) {
        APP = new Main();
        APP_SETTINGS = new AppSettings(true);
        APP_SETTINGS.setFrameRate(60);
        APP_SETTINGS.setHeight(HEIGHT);
        APP_SETTINGS.setWidth(WIDTH);
        APP_SETTINGS.setTitle("Bomberman");
        APP_SETTINGS.setVSync(true);
        APP_SETTINGS.setFrameRate(60);
        Config.loadConfig();
        if (Config.isFullScreen()) {
            toggleToFullscreen();
        }
        APP.setSettings(APP_SETTINGS);
        APP.setDisplayStatView(false);
        APP.setShowSettings(false);
        APP.start();
    }

    @Override
    public void simpleInitApp() {
        AL10.alSourcef(1, AL10.AL_GAIN, 5f);
        ROOT_NODE = rootNode;
        INPUT_MANAGER = inputManager;
        ASSET_MANAGER = assetManager;
        GUI_NODE = guiNode;
        CAM = cam;
        flyCam.setEnabled(false);
        assetManager.registerLocator("assets", FileLocator.class);
        SystemInput.initialize();
        ScenceGraphController.setScence(new MainMenu());
    }

    @Override
    public void simpleUpdate(float tpf) {
        ScenceGraphController.update(tpf);
    }

    public static void toggleToFullscreen() {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode[] modes = device.getDisplayModes();
        int width = modes[modes.length-1].getWidth();
        int height = modes[modes.length-1].getHeight();
        SCALEWIDTH = (float) width / WIDTH;
        SCALEHEIGHT = (float)  height / HEIGHT;
        APP_SETTINGS.setResolution(width, height);
        APP_SETTINGS.setFullscreen(device.isFullScreenSupported());
        APP.setSettings(APP_SETTINGS);
    }
}