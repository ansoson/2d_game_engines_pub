package engine.UITools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TextElement extends UIElement {
    public TextElement(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
    }

    @Override
    public void onDraw(GraphicsContext g) {
        g.setFill(color);
        g.fillText("Sample text", this.location.x, this.location.x, this.dimensions.x);
    }
}
