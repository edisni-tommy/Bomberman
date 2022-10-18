package UI.PlayerStatus;

import Cores.Config;
import Cores.Main;
import UI.Menu.Image;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import java.util.ArrayList;
import java.util.List;

public class BombStatusBar extends PlayerStatus {
    private final Image background = new Image(new Vector2f(60f, 15f), "Textures/Bar/Status/background.png");
    private final Image border = new Image(new Vector2f(60f, 15f), "Textures/Bar/Status/border.png");
    private static final List<Image> fill = new ArrayList<>();
    private static final List<Image> part = new ArrayList<>();
    public BombStatusBar(Spatial link, int currentBomb, int maxBomb) {
        super(link);
        onUpdate(currentBomb, maxBomb, 0f);
        show();
    }

    public void remove() {
        background.remove();
        border.remove();
        for (Image image: fill) {
            image.remove();
        }
        for (Image image: part) {
            image.remove();
        }
        fill.clear();
        part.clear();
    }

    public void onUpdate(int currentBomb, int maxBomb, float currentCooldown) {
        super.onUpdate();
        remove();
        Vector3f position = getPosition();
        float distance2Filled = 60f/maxBomb;
        if (Config.isFullScreen()) {
            distance2Filled = (60f/maxBomb) * Main.SCALEWIDTH;
            position.x -= 40;
            position.z += 300;
            position.y += 40;
        }
        float x = position.x;
        float y = position.y;
        float z = position.z;
        background.remove();
        border.remove();
        background.setPos(position);
        border.setPos(position);
        for (int i = 0; i < currentBomb; ++ i) {
            Image filled = new Image(new Vector2f(60f/maxBomb, 15f), "Textures/Bar/Status/fill.png");
            filled.setPos(new Vector3f(x + i * distance2Filled, y, z));
            fill.add(filled);
        }
        if (currentBomb < maxBomb) {
            Image filled = new Image(new Vector2f((60f * (4.0f - currentCooldown))/(maxBomb * 4.0f), 15f), "Textures/Bar/Status/cool_down.png");
            filled.setPos(new Vector3f(x + currentBomb * distance2Filled, y, z));
            fill.add(filled);
        }
        for (int i = 0; i < maxBomb; ++ i) {
            Image p = new Image(new Vector2f(60f/maxBomb, 15f), "Textures/Bar/Status/part.png");
            p.setPos(new Vector3f(x + i * distance2Filled, y, z));
            part.add(p);
        }
        show();
    }

    private void show() {
        border.display();
        background.display();
        for (Image image: fill) {
            image.display();
        }
        for (Image image: part) {
            image.display();
        }
    }
}
