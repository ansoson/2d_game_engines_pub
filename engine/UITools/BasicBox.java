package engine.UITools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BasicBox extends UIElement {
    public BasicBox(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
    }

    @Override
    public void onDraw(GraphicsContext g) {
        g.setFill(this.color);
        g.fillRect(location.x + screenLockOffset.x, location.y + screenLockOffset.y, dimensions.x, dimensions.y);
        for (UIElement element : children) {
            element.onDraw(g);
        }
    }
}
