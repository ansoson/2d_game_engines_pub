package engine.UITools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BasicCircle extends UIElement {
    public BasicCircle(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
    }

    public void onDraw(GraphicsContext g) {
        g.setStroke(color);
        g.setLineWidth(20);
        g.strokeOval(location.x + screenLockOffset.x + .1 * dimensions.x,
                location.y + screenLockOffset.y + .1 * dimensions.y,
                .8 * dimensions.x,
                .8 * dimensions.y);
    }

}
