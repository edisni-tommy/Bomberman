package Entities.Player;

import Cores.Config;
import Cores.Main;
import Cores.Map;
import Entities.BuffItem.*;
import Entities.Entity;
import Entities.Terrain.Portal;
import Particles.SpeedBuffEffect;
import UI.InGameGUI.InGame;
import UI.InGameGUI.Win;
import UI.Menu.MainMenu;
import UI.ScenceGraphController;
import com.jme3.anim.AnimComposer;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.input.ChaseCamera;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import utils.AnimUtils;

public class MainPlayer extends Player {
    private final AnimComposer composer;
    public final static AudioNode BuffItem = new AudioNode(Main.ASSET_MANAGER, "Sounds/Powerup.ogg", AudioData.DataType.Buffer);

    public MainPlayer(Vector3f position) {
        super(position, "Models/Player/player.gltf");
        Spatial child = AnimUtils.getAnimRoot(spatial);
        composer = child.getControl(AnimComposer.class);
        composer.setCurrentAction("stand");
        ChaseCamera chaseCam = new ChaseCamera(Main.CAM, spatial, Main.INPUT_MANAGER);
        chaseCam.setDefaultHorizontalRotation(FastMath.PI);
        chaseCam.setDefaultVerticalRotation(FastMath.PI/3);
        chaseCam.setDefaultDistance(16);
        chaseCam.setMinDistance(10);
        chaseCam.setMaxDistance(20);
        chaseCam.setZoomSensitivity(0.25f);
        chaseCam.cleanupWithInput(Main.INPUT_MANAGER);
        bombStatusBar.onUpdate(bombMax, bombLeft, 0f);
        BuffItem.setVolume(Config.getSound());
    }

    public void onUpdate(float tpf) {
        super.onUpdate(tpf);

    }

    public AnimComposer getComposer() {
        return composer;
    }

    @Override
    public void moveForward(float tpf) {
        super.moveForward(tpf);
        if (isSpeedBuff()) {
            SpeedBuffEffect a = new SpeedBuffEffect(getPosition());
        }
        checkPortal();
        checkBuffItem();
    }

    @Override
    public void moveBackward(float tpf) {
        super.moveBackward(tpf);
        if (isSpeedBuff()) {
            SpeedBuffEffect a = new SpeedBuffEffect(getPosition());
        }
        checkPortal();
        checkBuffItem();
    }

    @Override
    public void moveLeft(float tpf) {
        super.moveLeft(tpf);
        if (isSpeedBuff()) {
            SpeedBuffEffect a = new SpeedBuffEffect(getPosition());
        }
        checkPortal();
        checkBuffItem();
    }

    @Override
    public void moveRight(float tpf) {
        super.moveRight(tpf);
        if (isSpeedBuff()) {
            SpeedBuffEffect a = new SpeedBuffEffect(getPosition());
        }
        checkPortal();
        checkBuffItem();
    }

    public void remove() {
        super.remove();
        bombStatusBar.remove();
    }

    public void checkBuffItem() {
        Vector2f position = getCoord();
        Entity buffItem = Map.getObject((int)position.x, (int)position.y);
        if (buffItem == null) {
            return;
        }
        if (buffItem instanceof SpeedBuff) {
            setSound();
            setSpeedBuff();
            Map.remove((int)position.x, (int)position.y);
        } else if (buffItem instanceof FlameBuff) {
            setSound();
            setFlameBuff();
            Map.remove((int)position.x, (int)position.y);
        } else if (buffItem instanceof BombExtend) {
            setSound();
            setBombExtend();
            Map.remove((int)position.x, (int)position.y);
        } else if (buffItem instanceof ShieldBuff) {
            setSound();
            setShieldBuff();
            Map.remove((int)position.x, (int)position.y);
        }
    }

    public void setSound() {
        BuffItem.stop();
        BuffItem.play();
    }
    public void checkPortal() {
        Vector2f position = getCoord();
        Entity entity = Map.getObject((int)position.x, (int)position.y);
        if (entity == null) {
            return;
        }
        if (entity instanceof Portal) {
            if (Map.enemyCount == 0) {
                ScenceGraphController.setExtension(new Win());
            }
        }
    }
}
