package Cores;

import com.jme3.light.AmbientLight;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

public class GameEnvironment {

    private static AmbientLight light;
    private static Spatial sky;

    public static void initalize() {
        Texture b = Main.ASSET_MANAGER.loadTexture("Textures/Skybox/b.png");
        Texture d = Main.ASSET_MANAGER.loadTexture("Textures/Skybox/d.png");
        Texture f = Main.ASSET_MANAGER.loadTexture("Textures/Skybox/f.png");
        Texture l = Main.ASSET_MANAGER.loadTexture("Textures/Skybox/l.png");
        Texture r = Main.ASSET_MANAGER.loadTexture("Textures/Skybox/r.png");
        Texture t = Main.ASSET_MANAGER.loadTexture("Textures/Skybox/t.png");
        light = new AmbientLight();
        sky = SkyFactory.createSky(Main.ASSET_MANAGER, l, r, f, b, t, d);
        Main.ROOT_NODE.addLight(light);
        Main.ROOT_NODE.attachChild(sky);
    }

    public static void remove() {
        Main.ROOT_NODE.removeLight(light);
        Main.ROOT_NODE.detachChild(sky);
    }
}
