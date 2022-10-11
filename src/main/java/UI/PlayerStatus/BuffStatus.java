package UI.PlayerStatus;

import Cores.Main;
import UI.Menu.Image;
import UI.Menu.Text;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class BuffStatus extends PlayerStatus{

    private final Text duration = new Text("asd", new ColorRGBA(), new Vector2f(), new Vector2f());

    public BuffStatus(Spatial link) {
        super(link);
        duration.display();
    }

    public void onUpdate(float speedDuration, float shieldDuration, float bombDuration, float flameDuration) {
        int count = (speedDuration > 0 ? 1 : 0) + (speedDuration > 0 ? 1 : 0) + (bombDuration > 0 ? 1 : 0) + (flameDuration > 0 ? 1 : 0);
        Vector3f position = getPosition();
        position.z += 100;
        duration.setSize(100);
        duration.setHudText(Float.toString(speedDuration));
        duration.setPosition(new Vector2f(position.x, position.z));
    }
}
