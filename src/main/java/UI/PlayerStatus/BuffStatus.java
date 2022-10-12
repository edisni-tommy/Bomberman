package UI.PlayerStatus;

import Cores.Main;
import Entities.Player.MainPlayer;
import Entities.Player.PlayerList;
import UI.Menu.Image;
import UI.Menu.Text;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import java.util.ArrayList;
import java.util.List;

public class BuffStatus{

    private static final Image speedBuff = new Image(new Vector2f(75, 75), "Textures/Buffs/speed_buff.png");
    private static final Image shieldBuff = new Image(new Vector2f(75, 75), "Textures/Buffs/shield_buff.png");
    private static final Image flameBuff = new Image(new Vector2f(75, 75), "Textures/Buffs/flame_buff.png");
    private static final Image bombExtendBuff = new Image(new Vector2f(75, 75), "Textures/Buffs/mega_bomb_buff.png");

    private static final List<Image> background = new ArrayList<>();
    private static final List<Text> buffDurations = new ArrayList<>();
    public static void remove() {
        speedBuff.remove();
        shieldBuff.remove();
        bombExtendBuff.remove();
        flameBuff.remove();
        for (Text text: buffDurations) {
            text.remove();
        }
        for (Image image: background) {
            image.remove();
        }
        buffDurations.clear();
        background.clear();
    }

    public static void onUpdate() {
        MainPlayer mainPlayer = (MainPlayer) PlayerList.getMainPlayer();
        if (mainPlayer == null) {
            return;
        }
        remove();
        float speedDuration = mainPlayer.getSpeedDuration();
        float shieldDuration = mainPlayer.getShieldDuration();
        float bombExtendDuration = mainPlayer.getBombExtendDuration();
        float flameDuration = mainPlayer.getFlameDuration();
        float count = (speedDuration > 0 ? 1 : 0) + (shieldDuration > 0 ? 1 : 0) + (bombExtendDuration > 0 ? 1 : 0) + (flameDuration > 0 ? 1 : 0);
        float cnt = count;
        count /= 2;
        for (int i = 1; i <= cnt; ++ i) {
            background.add(new Image(new Vector2f(Main.WIDTH/2 - (count --) * 80, 100), new Vector2f(75, 75), "Textures/Buffs/shadow.png"));
        }
        count = cnt/2;
        if (speedDuration > 0) {
            Text text = new Text(String.format("%.1f", speedDuration) + "s", new ColorRGBA(), new Vector2f(Main.WIDTH/2 - (count) * 80 + 10, 90), new Vector2f(50, 10));
            text.setSize(15);
            buffDurations.add(text);
            speedBuff.setPos(new Vector2f(Main.WIDTH/2 - (count --) * 80, 100));
            speedBuff.display();

        }
        if (shieldDuration > 0) {
            Text text = new Text(String.format("%.1f", shieldDuration) + "s", new ColorRGBA(), new Vector2f(Main.WIDTH/2 - (count) * 80 + 10, 90), new Vector2f(50, 10));
            text.setSize(15);
            buffDurations.add(text);
            shieldBuff.setPos(new Vector2f(Main.WIDTH/2 - (count --) * 80, 100));
            shieldBuff.display();
        }
        if (bombExtendDuration > 0) {
            Text text = new Text(String.format("%.1f", bombExtendDuration) + "s", new ColorRGBA(), new Vector2f(Main.WIDTH/2 - (count) * 80 + 10, 90), new Vector2f(50, 10));
            text.setSize(15);
            buffDurations.add(text);
            bombExtendBuff.setPos(new Vector2f(Main.WIDTH/2 - (count --) * 80, 100));
            bombExtendBuff.display();
        }
        if (flameDuration > 0) {
            Text text = new Text(String.format("%.1f", flameDuration) + "s", new ColorRGBA(), new Vector2f(Main.WIDTH/2 - (count) * 80 + 10, 90), new Vector2f(50, 10));
            text.setSize(15);
            buffDurations.add(text);
            flameBuff.setPos(new Vector2f(Main.WIDTH/2 - (count) * 80, 100));
            flameBuff.display();
        }
        show();
    }

    public static void show() {
        for (Image image: background) {
            image.display();
        }
        for (Text text: buffDurations) {
            text.display();
        }
    }
}