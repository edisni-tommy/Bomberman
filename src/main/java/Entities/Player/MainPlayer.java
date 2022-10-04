package Entities.Player;

import Cores.Main;
import com.jme3.input.ChaseCamera;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

public class MainPlayer extends Player {
    public MainPlayer(Vector3f position) {
        super(position, "Models/Player/player.gltf");
        ChaseCamera chaseCam = new ChaseCamera(Main.CAM, spatial, Main.INPUT_MANAGER);
        chaseCam.setDefaultHorizontalRotation(FastMath.PI);
        chaseCam.setDefaultVerticalRotation(FastMath.PI/3);
        chaseCam.setDefaultDistance(16);
        chaseCam.setMinDistance(10);
        chaseCam.setMaxDistance(20);
        chaseCam.setZoomSensitivity(0.25f);
        chaseCam.cleanupWithInput(Main.INPUT_MANAGER);
        PlayerList.add(this);
    }
}
