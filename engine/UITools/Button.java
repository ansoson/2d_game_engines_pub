package engine.UITools;

import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class Button extends UIElement {

    protected Color hoverColor = Color.color(.90, .75, .80, 1.0);
    protected Color disappearColor = Color.color(.5, .5, .5, 0.0);

    public Button(Double x, Double y, Double width, Double length, Color color) {
        super(x, y, width, length, color);
        UIElement hovering = new BasicCross(this.location.x, this.location.y, this.dimensions.x, this.dimensions.y, Color.color(.5, .5, .5, 0.0));
        hovering.screenLockOffset = this.screenLockOffset;
        this.children.add(hovering);
    }

    public Button(Double x, Double y, Double width, Double length, Color color, Vec2d offset) {
        super(x, y, width, length, color);
        UIElement hovering = new BasicCross(this.location.x, this.location.y, this.dimensions.x, this.dimensions.y, Color.color(.5, .5, .5, 0.0));
        hovering.screenLockOffset = this.screenLockOffset;
        this.children.add(hovering);
    }

    @Override
    public void onTick(long nanosSincePreviousTick, Vec2d mouseCoords) {
        super.onTick(nanosSincePreviousTick, mouseCoords);
        if (this.location.x + screenLockOffset.x <= mouseCoords.x && mouseCoords.x <= this.location.x + screenLockOffset.x + this.dimensions.x) {
            if (this.location.y + screenLockOffset.y <= mouseCoords.y && mouseCoords.y <= this.location.y + screenLockOffset.y + this.dimensions.y) {
                /**
                 * If the button is being hovered
                 */
                this.onHover();
            } else {
                this.children.get(0).color = Color.color(.5, .5, .5, 0.0);
            }
        } else {
            this.children.get(0).color = Color.color(.5, .5, .5, 0.0);
        }
    }

    @Override
    public void onHover() {
        this.children.get(0).color = hoverColor;
    }

    @Override
    public int onClick(Vec2d coords) {
        return 0;
    }
}
