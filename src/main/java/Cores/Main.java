package Cores;

import Entities.BombList;
import Entities.Player.Player;
import Entities.Player.PlayerList;
import Input.PlayerInput;
import UI.Menu.MainMenu;
import UI.ScenceGraph;
import UI.ScenceGraphController;
import Particles.BombExplosionList;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

import Input.SystemInput;
import com.jme3.ui.Picture;
import org.lwjgl.openal.AL10;

import java.io.FileNotFoundException;

public class Main extends SimpleApplication {

    public static final int HEIGHT = 720;
    public static final int WIDTH = 1080;
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
        //flyCam.setDragToRotate(true);
        assetManager.registerLocator("assets", FileLocator.class);
        SystemInput.initalize();
        PlayerInput.initalize();
        ScenceGraphController.setScence(new MainMenu());
    }

    @Override
    public void simpleUpdate(float tpf) {
<<<<<<< HEAD
        PlayerList.onUpdate(tpf);
        BombList.onUpdate(tpf);
        BombExplosionList.onUpdate(tpf);
=======
        ScenceGraphController.update(tpf);
>>>>>>> ffe04c60764a935c5fbd290aebcbee2ecb643380
    }
}