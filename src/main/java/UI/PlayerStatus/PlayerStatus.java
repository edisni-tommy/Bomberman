package UI.PlayerStatus;

import Cores.Main;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class PlayerStatus {
    private final Spatial link;
    private Vector3f position;

    public PlayerStatus(Spatial link) {
        this.link = link;
        position = Main.CAM.getScreenCoordinates(link.getWorldTranslation());
    }

    public void onUpdate() {
        position = Main.CAM.getScreenCoordinates(link.getLocalTranslation());
    }

    public Spatial getLink() {
        return this.link;
    }

    public Vector3f getPosition() {
        return this.position;
    }
}
