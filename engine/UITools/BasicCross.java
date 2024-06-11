package engine.UITools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BasicCross extends UIElement {
    public BasicCross(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
    }

    public void onDraw(GraphicsContext g) {
        g.setStroke(color);
        g.setLineWidth(20);
        g.strokeLine(location.x + screenLockOffset.x + .2 * dimensions.x,
                location.y + screenLockOffset.y + .2 * dimensions.y,
                location.x + screenLockOffset.x + .8 * dimensions.x,
                location.y + screenLockOffset.y + .8 * dimensions.y);
        g.strokeLine(location.x + screenLockOffset.x + .2 * dimensions.x,
                location.y + screenLockOffset.y + .8 * dimensions.y,
                location.x + screenLockOffset.x + .8 * dimensions.x,
                location.y + screenLockOffset.y + .2 * dimensions.y);
    }
}
