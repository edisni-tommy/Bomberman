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
    private float textSize;
    private Vector2f size;

    public Text(String text, ColorRGBA color, Vector2f pos, Vector2f size) {
        this.size = size;
        BitmapFont guiFont = Main.ASSET_MANAGER.loadFont("Fonts/debussy.ttf.fnt");
        hudText = new BitmapText(guiFont);
        hudText.setText(text);
        hudText.setColor(color);
        hudText.setSize(26);
        box = new Rectangle(pos.x, pos.y + size.y, size.x, size.y);
        if (Config.isFullScreen()) {
            scale();
        }
        textSize = hudText.getSize();
        hudText.setBox(box);
        hudText.setVerticalAlignment(BitmapFont.VAlign.Center);
        hudText.setAlignment(BitmapFont.Align.Center);
    }

    public void scale() {
        scaleTextSize();
        if (Config.isFullScreen()) {
            hudText.setSize(textSize);
            box = new Rectangle(box.x * Main.SCALEWIDTH, box.y * Main.SCALEHEIGHT, box.width * Main.SCALEWIDTH, box.height * Main.SCALEHEIGHT);
            hudText.setBox(box);
        } else {
            hudText.setSize(textSize);
            box = new Rectangle(box.x / Main.SCALEWIDTH, box.y / Main.SCALEHEIGHT, box.width / Main.SCALEWIDTH, box.height / Main.SCALEHEIGHT);
            hudText.setBox(box);
        }
    }

    public void setPosition(Vector2f position) {
        this.hudText.setBox(new Rectangle(position.x, position.y, position.x + size.x, position.y + size.y));
    }

    public void display() {
        Main.GUI_NODE.attachChild(hudText);
    }

    public void setSize(float size) {
        textSize = size;
        this.hudText.setSize(size);
    }

    public void setText(String text) {
        this.hudText.setText(text);
    }

    public void setAlignment(BitmapFont.Align type) {
        hudText.setAlignment(type);
    }

    public float getSize() {
        return this.hudText.getSize();
    }

    public void remove() {
        Main.GUI_NODE.detachChild(hudText);
    }

    public void setColor(ColorRGBA color) {
        hudText.setColor(color);
    }
    public void scaleTextSize() {
        textSize = hudText.getSize();
        if (Config.isFullScreen()) {
            switch ((int) textSize) {
                case 46:
                    textSize = 86;
                    break;
                case 36:
                    textSize = 76;
                    break;
                case 26:
                    textSize = 36;
                    break;
                default:
                    break;
            }
        } else {
            switch ((int) textSize) {
                case 86:
                    textSize = 46;
                    break;
                case 76:
                    textSize = 36;
                    break;
                case 36:
                    textSize = 26;
                    break;
                default:
                    break;
            }

        }
    }
}
