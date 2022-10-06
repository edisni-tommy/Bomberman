package UI.PlayerStatus;

import Cores.Main;
import UI.GUI.Image;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import java.util.ArrayList;
import java.util.List;

public class BombStatusBar extends PlayerStatus {
    private Image background = new Image(new Vector2f(60f, 15f), "Textures/Bar/Status/background.png");
    private Image border = new Image(new Vector2f(60f, 15f), "Textures/Bar/Status/border.png");

    private int currentBomb;
    private int maxBomb;
    private float currentCooldown;
    private List<Image> fill = new ArrayList<>();
    private List<Image> part = new ArrayList<>();
    public BombStatusBar(Spatial link, int currentBomb, int maxBomb) {
        super(link);
        this.currentBomb = currentBomb;
        this.maxBomb = maxBomb;
        this.currentCooldown = 0;
        Vector3f position = getPosition();
        float x = position.x - 30;
        float y = position.y;
        float z = position.z;
        background.setPosition(new Vector3f(x, y, z));
        border.setPosition(new Vector3f(x, y, z));
        for (int i = 0; i < currentBomb; ++ i) {
            Image filled = new Image(new Vector2f(60f/maxBomb, 15f), "Textures/Bar/Status/fill.png");
            filled.setPosition(new Vector3f(x + (60f/maxBomb) * i, y, z));
            fill.add(filled);
        }
        for (int i = 0; i < maxBomb; ++ i) {
            Image p = new Image(new Vector2f(60f/maxBomb, 15f), "Textures/Bar/Status/part.png");
            p.setPosition(new Vector3f(x + (60f/maxBomb) * i, y, z));
            fill.add(p);
        }
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
    }

    public void onUpdate(int currentBomb, int maxBomb, float currentCoolDown) {
        remove();
        this.currentBomb = currentBomb;
        this.maxBomb = maxBomb;
        this.currentCooldown = currentCoolDown;
        fill.removeAll(fill);
        part.removeAll(part);
        Vector3f position = getPosition();
        float x = position.x - 30;
        float y = position.y;
        float z = position.z;
        background.setPosition(new Vector3f(x, y, z));
        border.setPosition(new Vector3f(x, y, z));
        for (int i = 0; i < currentBomb; ++ i) {
            Image filled = new Image(new Vector2f(60f/maxBomb, 15f), "Textures/Bar/Status/fill.png");
            filled.setPosition(new Vector3f(x + (60f/maxBomb) * i, y, z));
            fill.add(filled);
        }
        if (currentBomb < maxBomb) {
            Image filled = new Image(new Vector2f((60f * (4.0f - currentCooldown))/(maxBomb * 4.0f), 15f), "Textures/Bar/Status/cool_down.png");
            filled.setPosition(new Vector3f(x + (60f/maxBomb) * currentBomb, y, z));
            fill.add(filled);
        }
        for (int i = 0; i < maxBomb; ++ i) {
            Image p = new Image(new Vector2f(60f/maxBomb, 15f), "Textures/Bar/Status/part.png");
            p.setPosition(new Vector3f(x + (60f/maxBomb) * i, y, z));
            part.add(p);
        }
        show();
    }

    private void show() {
        border.show();
        background.show();
        for (Image image: fill) {
            image.show();
        }
        for (Image image: part) {
            image.show();
        }
    }
}
