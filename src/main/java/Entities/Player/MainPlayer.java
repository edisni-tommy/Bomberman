package Entities.Player;

import Cores.Main;
import Cores.Map;
import Entities.BuffItem.BombExtend;
import Entities.BuffItem.FlameBuff;
import Entities.BuffItem.ShieldBuff;
import Entities.BuffItem.SpeedBuff;
import Entities.Entity;
import Entities.Terrain.Portal;
import com.jme3.input.ChaseCamera;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import UI.PlayerStatus.BombStatusBar;

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
        bombStatusBar.onUpdate(bombMax, bombLeft, 0f);
    }

    @Override
    public void moveForward(float tpf) {
        super.moveForward(tpf);
        checkPortal();
        checkBuffItem();
    }

    @Override
    public void moveBackward(float tpf) {
        super.moveBackward(tpf);
        checkPortal();
        checkBuffItem();
    }

    @Override
    public void moveLeft(float tpf) {
        super.moveLeft(tpf);
        checkPortal();
        checkBuffItem();
    }

    @Override
    public void moveRight(float tpf) {
        super.moveRight(tpf);
        checkPortal();
        checkBuffItem();
    }

    public void remove() {
        super.remove();
        bombStatusBar.remove();
    }

    public void checkBuffItem() {
        Vector2f position = getCord();
        Entity buffItem = Map.getObject((int)position.x, (int)position.y);
        if (buffItem == null) {
            return;
        }
        if (buffItem instanceof SpeedBuff) {
            setSpeedBuff();
            Map.remove((int)position.x, (int)position.y);
        } else if (buffItem instanceof FlameBuff) {
            setFlameBuff();
            Map.remove((int)position.x, (int)position.y);
        } else if (buffItem instanceof BombExtend) {
            setBombExtend();
            Map.remove((int)position.x, (int)position.y);
        } else if (buffItem instanceof ShieldBuff) {
            setShieldBuff();
            Map.remove((int)position.x, (int)position.y);
        }
    }

    public void checkPortal() {
        Vector2f position = getCord();
        Entity entity = Map.getObject((int)position.x, (int)position.y);
        if (entity == null) {
            return;
        }
        if (entity instanceof Portal) {
            if (Player.countEnemy == 0) {
                //win
            }
        }
    }

}
