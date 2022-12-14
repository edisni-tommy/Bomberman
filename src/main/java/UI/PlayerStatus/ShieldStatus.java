package UI.PlayerStatus;

import Cores.Config;
import Cores.Main;
import UI.Menu.Image;
import com.jme3.math.Vector2f;
import com.jme3.scene.Spatial;
import com.jme3.ui.Picture;

public class ShieldStatus extends PlayerStatus {

    private final Image shield = new Image(new Vector2f(120f, 120f), "Textures/Buffs/shield_active.png");
    private boolean isActive;

    public ShieldStatus(Spatial link, boolean isActive) {
        super(link);
        position.x += 30;
        position.y -= 70;
        shield.setPos(new Vector2f(position.x, position.y ));
        this.isActive = isActive;
    }

    public void show() {
        shield.display();
    }

    public void remove() {
        shield.remove();
    }

    public void onUpdate(boolean isActive) {
        super.onUpdate();
        position.x -= 30;
        position.y -= 100;
        if (Config.isFullScreen()){
            shield.setPos(new Vector2f(position.x-80, position.y-40));
        } else {
            shield.setPos(new Vector2f(position.x, position.y));
        }
        if (!this.isActive && isActive) {
            show();
        } else if(this.isActive && !isActive) {
            remove();
        }
        this.isActive = isActive;
    }
}
