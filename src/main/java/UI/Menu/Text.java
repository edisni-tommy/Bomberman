package UI.Menu;

import Cores.Config;
import Cores.Main;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.Rectangle;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;

public class Text {
    private final BitmapText hudText;
    private Rectangle box;
    public Text(String text, ColorRGBA color, Vector2f pos, Vector2f size) {
        BitmapFont guiFont = Main.ASSET_MANAGER.loadFont("Fonts/debussy.ttf.fnt");
        hudText = new BitmapText(guiFont);
        hudText.setText(text);
        hudText.setColor(color);
        hudText.setSize(26);
        box = new Rectangle(pos.x, pos.y + size.y, size.x, size.y);
        if (Config.isFullScreen()) {
            scale();
        }
        hudText.setBox(box);
        hudText.setVerticalAlignment(BitmapFont.VAlign.Center);
        hudText.setAlignment(BitmapFont.Align.Center);
    }

    public void scale() {
        if (Config.isFullScreen()) {
             hudText.setSize(36);
             box = new Rectangle(box.x * Main.SCALEWIDTH, box.y * Main.SCALEHEIGHT, box.width * Main.SCALEWIDTH, box.height * Main.SCALEHEIGHT );
             hudText.setBox(box);
             hudText.setVerticalAlignment(BitmapFont.VAlign.Center);
             hudText.setAlignment(BitmapFont.Align.Center);
        } else {
            hudText.setSize(26);
            box = new Rectangle(box.x / Main.SCALEWIDTH, box.y / Main.SCALEHEIGHT, box.width / Main.SCALEWIDTH, box.height / Main.SCALEHEIGHT );
            hudText.setBox(box);
            hudText.setVerticalAlignment(BitmapFont.VAlign.Center);
            hudText.setAlignment(BitmapFont.Align.Center);
        }
    }
    public void display() {
        Main.GUI_NODE.attachChild(hudText);
    }

    public void remove() {
        Main.GUI_NODE.detachChild(hudText);
    }
}
