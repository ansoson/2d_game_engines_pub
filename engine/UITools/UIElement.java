package engine.UITools;

import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class UIElement {

    public Vec2d location = null;
    public Vec2d dimensions = null;
    public UIElement parent = null;
    public ArrayList<UIElement> children = new ArrayList<UIElement>();
    public Color color = Color.BLACK;
    public Vec2d screenLockOffset = new Vec2d(0.0, 0.0);

    public UIElement(Double x, Double y, Double width, Double length, Color color) {
        this.location = new Vec2d(x, y);
        this.dimensions = new Vec2d(width, length);
        this.color = color;
    }

    public UIElement(Double x, Double y, Double width, Double length, Color color, Vec2d offset) {
        this.location = new Vec2d(x, y);
        this.dimensions = new Vec2d(width, length);
        this.color = color;
        this.screenLockOffset = offset;
    }
    public void onTick(long nanosSincePreviousTick, Vec2d mouseCoords) {
        for (UIElement element : children) {
            element.onTick(nanosSincePreviousTick, mouseCoords);
        }
    };
    public void onDraw(GraphicsContext g) {
        g.setFill(color);
        g.fillRoundRect(location.x + screenLockOffset.x, location.y + screenLockOffset.y, dimensions.x, dimensions.y, 15.0, 15.0);
        for (UIElement element : children) {
            element.onDraw(g);
        }
    };
    public void onResize(Vec2d resizeDims, Vec2d resizeOffset) {
        this.location = new Vec2d(this.location.x * resizeDims.x, this.location.y * resizeDims.y);
        this.screenLockOffset = new Vec2d(this.screenLockOffset.x + resizeOffset.x, this.screenLockOffset.y + resizeOffset.y);
        this.dimensions = new Vec2d(this.dimensions.x * resizeDims.x, this.dimensions.y * resizeDims.y);
        for (UIElement element : children) {
            element.onResize(resizeDims, resizeOffset);
        }
    };
    public void onHover() {};
    public int onClick(Vec2d coords) {
        return 0;
    };

    public void enactEvent(Integer i) {
    }


}
