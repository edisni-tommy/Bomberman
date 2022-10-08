package UI.PlayerStatus;

import Cores.Main;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class PlayerStatus {
    private final Spatial link;
    protected Vector3f position;

    public PlayerStatus(Spatial link) {
        this.link = link;
        position = Main.CAM.getScreenCoordinates(this.link.getWorldTranslation());
        position.x -= 30;
        position.y += 70;
    }

    public void onUpdate() {
        position = Main.CAM.getScreenCoordinates(this.link.getWorldTranslation());
        position.x -= 30;
        position.y += 70;
    }

    public Spatial getLink() {
        return this.link;
    }

    public Vector3f getPosition() {
        return this.position;
    }
}
